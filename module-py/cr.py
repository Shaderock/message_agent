import traceback
from concurrent import futures
import json
import random
import string
from threading import Thread

import grpc
import atexit

import hash
import connection
import module_pb2
import module_pb2_grpc
import broker_pb2
import broker_pb2_grpc
from block import Block


# noinspection PyUnusedLocal
def get_new_nonce() -> str:
    length = random.randint(1, nonce_max_len)
    letters = (string.ascii_letters + string.digits + string.punctuation).replace('{', '').replace('}', '')
    return ''.join(random.choice(letters) for i in range(length))


def get_block_hash() -> str:
    return hash.get_block_hash(block['content'], block['prev-hash'], block['nonce'])


def check_nonce() -> bool:
    return hash.check_hash_rule(get_block_hash())


def send_task():
    info_block = {
        'id-block': block['id-block'],
        'nonce': block['nonce'],
        'hash': get_block_hash()
    }
    payload = {
        'command': 'complete-task',
        'info-block': json.dumps(info_block)
    }
    notify = {
        'operation': 'notify',
        'payload': json.dumps(payload)
    }
    conn.send(json.dumps(notify))


def wait_task():
    global has_task
    global block

    while True:

        if random.choice([True, False]):
            print('Broker force closed connection')
            break

        if has_task:
            if block['nonce'] == '':
                block['nonce'] = get_new_nonce()
                # print(f'Random new nonce - {block["nonce"]}')
                if check_nonce():
                    print(f'Block nonce found - {block["nonce"]}\n\tHash: {get_block_hash()}')
                else:
                    block['nonce'] = ''
            else:
                send_task()
                print('Hash has been sent')
                has_task = False
        else:
            pass

        if True:
            message = json.loads('')

            if message['operation'] == 'notify':
                print('Notified (but i\'ve never subscribed)')
                pass

            elif message['operation'] == 'direct-message':
                if json.loads(message['payload'])['command'] == 'stop':
                    print('DM\'ed to stop')
                    block = {}
                    has_task = False

                elif json.loads(message['payload'])['command'] == 'new-task':
                    print('DM\'ed to start new task')
                    new_block = {
                        'id-block': json.loads(json.loads(message['payload'])['info-block'])['id-block'],
                        'prev-hash': json.loads(json.loads(message['payload'])['info-block'])['prev-hash'],
                        'content': json.loads(json.loads(message['payload'])['info-block'])['content']
                    }

                    if 'nonce' in block.keys():
                        nonce = block['nonce']
                        block = new_block
                        block['nonce'] = nonce
                        if block['nonce'] != '':
                            if not check_nonce():
                                block['nonce'] = ''
                    else:
                        block = new_block
                        block['nonce'] = ''

                    has_task = True


def search_nonce():
    if not has_task:
        return
    pass  # Search new nonce


class ModuleService(module_pb2_grpc.ModuleServiceServicer):
    def receiveMessage(self, request, context):
        print('Received smth')
        message_json = request.message
        try:
            message = json.loads(message_json)
            if message['command'] == 'new-task':
                print('New task')

            elif message['command'] == 'stop':
                print('Stopped')

            else:
                print(f'Untreated command - {message["command"]}')

        except Exception:
            print(f'Или лыжи не едут, или мессэдж неправильный - "{message_json}"')
        return module_pb2.EmptyMessage()

    def welcome(self, request, context):
        print('Smn new - idk')
        return module_pb2.EmptyMessage()

    def goodBye(self, request, context):
        print('Smn left - idk')
        return module_pb2.EmptyMessage()

    def close(self, request, context):
        print('Need to close (broker said so)')
        atexit.unregister(connection.close_connection)
        raise KeyboardInterrupt
        # return module_pb2.EmptyMessage()


has_task = False
block = None
nonce_max_len = 20
nonce_thread = Thread(target=search_nonce)


if __name__ == '__main__':
    atexit.register(input, 'To exit press Enter...')

    # noinspection PyBroadException
    try:  # It will catch KeyboardInterrupt (Ctrl-C) and other Exceptions
        while True:
            broker = connection.init_broker_stub()  # Interface for messaging w/ broker

            server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))  # Init module service
            module_pb2_grpc.add_ModuleServiceServicer_to_server(ModuleService(), server)

            own_module_service_port = connection.get_listen_port(server)

            handshake_response = connection.handshake(broker, own_module_service_port, 'CR')
            if not handshake_response.ok:
                print('Broker haven\'t permitted connection')
                break

            print('Connection established')

            own_id = handshake_response.givenId

            atexit.register(connection.close_connection, broker, own_id)

            server.start()
            server.wait_for_termination()
    except KeyboardInterrupt:
        pass
    except Exception as e:
        traceback.print_exc()
