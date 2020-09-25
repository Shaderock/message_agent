import select
import socket
import json
import threading

# server_ip = 'localhost'
# server_ip = '89.28.116.199'
import time

broadcast_udp_ip = '192.168.0.255'
broker_tcp_ip = ''
broker_tcp_port = 17001
module_udp_port = 16001
broker_udp_port = 16002


# noinspection PyBroadException
def parse_json(string_list: str) -> list:
    list_of_strings = []
    brackets = 0
    string_start = None
    for index in range(len(string_list)):
        if string_list[index] == '{':
            if string_start is None:
                string_start = index
            brackets += 1
        elif string_list[index] == '}':
            if brackets < 0:  # Message started from '}'
                print(f'Invalid message - "{string_list}"')
                return []
            brackets -= 1
            if brackets == 0 and string_start is not None:
                try:
                    json.loads(string_list[string_start:index + 1])
                except Exception:
                    print(f'Invalid json component "{string_list[string_start:index + 1]}" in message "{string_list}"')
                else:
                    list_of_strings.append(string_list[string_start:index + 1])
                finally:
                    string_start = None

    return list_of_strings


# noinspection PyBroadException
def listen_to_broker_udp() -> str:
    udp_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    module_is_alive = {'operation': 'module-is-alive'}
    udp_socket.sendto(json.dumps(module_is_alive).encode('utf8'), (broadcast_udp_ip, broker_udp_port))
    print('Send UDP broadcast')

    udp_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    udp_socket.bind(('0.0.0.0', module_udp_port))

    print('Waiting broker')
    while True:
        data, address = udp_socket.recvfrom(1024)
        try:
            data_dict = json.loads(data.decode('utf8'))
            if data_dict['operation'] == 'broker-is-alive':
                break
        except Exception:
            print(f'Invalid json from udp listener {str(address)}:\n\t{data.decode("utf8")}')

    return address[0]


# noinspection PyBroadException,SpellCheckingInspection
class Connection:
    def __init__(self, module_type):
        self.module_type = module_type
        self.broker_port = broker_tcp_port
        self.socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.work = threading.Lock()
        self.messages = []

        global broker_tcp_ip
        broker_tcp_ip = listen_to_broker_udp()

        self.connect()
        self.listener = threading.Thread(target=self.listen)
        self.listener.start()

    def connect(self):
        ready_to_go = False
        while not ready_to_go:
            self.socket.connect((broker_tcp_ip, self.broker_port))

            payload = {
                    'type': self.module_type
                }
            handshake = {
                    'operation': 'handshake',
                    'payload': json.dumps(payload)
                }

            self.socket.sendall((json.dumps(handshake) + '\n').encode('utf8'))

            try:
                while True:
                    response_bytes = self.socket.recv(4096)
                    try:
                        response = json.loads(response_bytes.decode('utf8'))
                        payload = json.loads(response['payload'])
                        if response['operation'] == 'handshake':
                            break
                    except Exception:
                        print(f'Invalid response or payload - {response_bytes.decode("utf8")}')
            except ConnectionResetError:
                print('Refused to handshake')
                self.socket.close()
                self.socket = None
                return

            if payload['code'] >= 40:
                print(f'Caught an error {payload["code"]}')
                self.socket.close()
                self.socket = None
                return
            elif payload['code'] == 30:
                self.broker_port = payload['port']
                self.socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
            elif payload['code'] == 20:
                print('Connection established')
                ready_to_go = True

    def reset_socket(self):
        # print('will acq')
        self.work.acquire()
        # print('acq')
        self.socket = None
        # print('none')
        self.work.release()
        # print('relea')

    def listen(self):
        while True:
            # time.sleep(1)
            self.work.acquire()
            # print('acq_lstn')
            if self.socket is None:
                # print('found none')
                self.work.release()
                # print('acq_rel')
                break
            recv = ''
            while True:
                descriptors = select.select([self.socket], [], [], 0.01)
                if self.socket in descriptors[0]:
                    try:
                        buff = self.socket.recv(4096).decode('utf8')
                    except ConnectionResetError:
                        print('Broker unexpectedly turned down')
                        self.socket.close()
                        self.socket = None
                        self.work.release()
                        # print('acq_rel')
                        return
                    recv += buff
                else:
                    break
            if recv != '':
                self.messages.extend(parse_json(recv))
            self.work.release()
            # print('acq_rel-1')

    def has_messages(self) -> bool:
        try:
            self.work.acquire()
            # print('acq_has')
            has = len(self.messages) != 0
        except KeyboardInterrupt:
            # print('GOTCHA!')
            self.work.release()
            # print('acq_rel')
            raise KeyboardInterrupt()
        else:
            self.work.release()
            # print('acq_rel')
        return has

    def is_socket_resetted(self) -> bool:
        try:
            self.work.acquire()
            # print('acq_is')
            _is = self.socket is None
        except KeyboardInterrupt:
            _is = True
            self.work.release()
            # print('acq_rel')
            raise KeyboardInterrupt()
        else:
            self.work.release()
            # print('acq_rel')
        return _is

    def send(self, send_string: str):
        self.work.acquire()
        # print('acq_send')
        if self.socket is None:
            print('Send can\'t be done - no socket')
            self.work.release()
            # print('acq_rel')
            return
        self.socket.sendall((send_string + '\n').encode('utf8'))
        self.work.release()
        # print('acq_rel')

    def wait_message(self) -> str:
        while True:
            if self.has_messages():
                return self.messages.pop(0)
