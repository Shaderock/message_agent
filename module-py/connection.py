import select
import socket
import json
import threading

server_ip = 'localhost'
# server_ip = '89.28.116.199'
server_port = 17001


class Connection:
    def __init__(self, module_type):
        self.module_type = module_type
        self.server_port = server_port
        self.socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.work = threading.Lock()
        self.messages = []

        self.connect()
        self.listener = threading.Thread(target=self.listen)

    def connect(self):
        ready_to_go = False
        while not ready_to_go:
            self.socket.connect((server_ip, self.server_port))

            payload = \
                {
                    "type": self.module_type
                }
            handshake = \
                {
                    "operation": "handshake",
                    "payload": json.dumps(payload)
                }

            self.socket.sendall((json.dumps(handshake) + '\n').encode('utf8'))

            response = json.loads(self.socket.recv(4096).decode('utf8'))

            if response["payload"]["code"] >= 40:
                print("Caught an error")
                raise Exception()
            elif response["payload"]["code"] == 30:
                self.server_port = response["payload"]["port"]
            elif response["payload"]["code"] == 20:
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
                descriptors = select.select([self.socket], [], [])
                if self.socket in descriptors[0]:
                    recv += self.socket.recv(4096).decode('utf8')
                else:
                    break
            if recv != '':
                self.messages.append(json.loads(recv))
            self.work.release()

    def has_messages(self) -> bool:
        self.work.acquire()
        has = len(self.messages) != 0
        self.work.release()
        return has

    def send(self, send_string: str):
        self.work.acquire()
        if self.socket is None:
            raise Exception()
        self.socket.sendall((send_string + '\n').encode('utf8'))
        self.work.release()
