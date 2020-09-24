import select
import socket
import json
import threading

# server_ip = 'localhost'
server_ip = '89.28.116.199'
server_port = 17001


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
            brackets -= 1
            if brackets == 0 and string_start is not None:
                list_of_strings.append(string_list[string_start:index+1])
                string_start = None

    return list_of_strings


class Connection:
    def __init__(self, module_type):
        self.module_type = module_type
        self.server_port = server_port
        self.socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.work = threading.Lock()
        self.messages = []

        self.connect()
        self.listener = threading.Thread(target=self.listen)
        self.listener.start()

    def connect(self):
        ready_to_go = False
        while not ready_to_go:
            self.socket.connect((server_ip, self.server_port))

            payload = \
                {
                    'type': self.module_type
                }
            handshake = \
                {
                    'operation': 'handshake',
                    'payload': json.dumps(payload)
                }

            self.socket.sendall((json.dumps(handshake) + '\n').encode('utf8'))

            response = json.loads(self.socket.recv(4096).decode('utf8'))

            if json.loads(response['payload'])['code'] >= 40:
                print('Caught an error')
                raise Exception()
            elif json.loads(response['payload'])['code'] == 30:
                self.server_port = json.loads(response['payload'])['port']
                self.socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
            elif json.loads(response['payload'])['code'] == 20:
                ready_to_go = True

    def reset_socket(self):
        self.work.acquire()
        self.socket = None
        self.work.release()

    def listen(self):
        while True:
            self.work.acquire()
            if self.socket is None:
                break
            recv = ''
            while True:
                descriptors = select.select([self.socket], [], [], 0.01)
                if self.socket in descriptors[0]:
                    try:
                        buff = self.socket.recv(4096).decode('utf8')
                    except Exception:
                        self.socket.close()
                        self.socket = None
                        return
                    recv += buff
                else:
                    break
            if recv != '':
                self.messages.extend(parse_json(recv))
            self.work.release()

    def has_messages(self) -> bool:
        self.work.acquire()
        has = len(self.messages) != 0
        self.work.release()
        return has

    def is_socket_resetted(self) -> bool:
        return self.socket is None

    def send(self, send_string: str):
        self.work.acquire()
        if self.socket is None:
            raise Exception()
        self.socket.sendall((send_string + '\n').encode('utf8'))
        self.work.release()

    def wait_message(self) -> str:
        while True:
            if self.has_messages():
                return self.messages.pop(0)
