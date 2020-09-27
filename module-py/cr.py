from concurrent import futures
from threading import Thread
import threading
import traceback
import random
import string
import atexit
import json
import grpc

from block import Block
import module_pb2_grpc
import module_pb2
import broker_pb2
import connection
import hash


# noinspection PyUnusedLocal
def get_new_nonce() -> str:
    length = random.randint(1, nonce_max_len)
    letters = (string.ascii_letters + string.digits + string.punctuation).replace('{', '').replace('}', '')
    return ''.join(random.choice(letters) for i in range(length))


def get_block_hash() -> str:
    return hash.get_block_hash(block.content, block.prev_hash, block.nonce)


def check_nonce() -> bool:
    return hash.check_hash_rule(get_block_hash())


def send_block():
    info_block = {
        'id-block': block.id_block,
        'nonce': block.nonce,
        'hash': get_block_hash()
    }
    message = {
        'command': 'complete-task',
        'info-block': info_block
    }
    message_string = json.dumps(message)

    broker.sendMessage(broker_pb2.MessageRequest(idRequester=own_id, message=message_string))


def search_nonce():
    global has_task
    if not has_task:
        return
    if block.nonce == '':  # Check for saved nonce
        block.nonce = get_new_nonce()
    if check_nonce():
        print(f'Block nonce found - {block.nonce}\n\tHash: {get_block_hash()}')
        send_block()
        has_task = False
    else:
        block.nonce = ''


def re_thread():
    global nonce_thread
    nonce_thread = Thread(target=search_nonce)


# noinspection PyBroadException
class ModuleService(module_pb2_grpc.ModuleServiceServicer):
    def receiveMessage(self, request, context):
        global has_task
        global block
        message_json = request.message
        try:
            message = json.loads(message_json)
            if message['command'] == 'new-task':
                print('New task')
                has_task = False
                if nonce_thread.is_alive():
                    nonce_thread.join()
                new_block = Block(message['info-block']['id_block'], message['info-block']['content'],
                                  message['info-block']['prev_hash'])
                if new_block == block:
                    new_block.nonce = block.nonce
                block = new_block
                has_task = True
                re_thread()
                nonce_thread.start()

            elif message['command'] == 'stop':
                print('Stopped')
                has_task = False
                if nonce_thread.is_alive():
                    nonce_thread.join()

            else:
                print(f'Untreated command - {message["command"]}')

        except Exception:
            print(f'Invalid message - "{message_json}"')
        return module_pb2.EmptyMessage()

    def welcome(self, request, context):
        print(f'Welcome #{request.module.id}')
        return module_pb2.EmptyMessage()

    def goodBye(self, request, context):
        print(f'Good bye #{request.module.id}')
        return module_pb2.EmptyMessage()

    def close(self, request, context):
        print('Need to close (broker said so)')
        atexit.unregister(connection.close_connection)
        threading.Thread(target=server.stop, args=(None,)).start()
        return module_pb2.EmptyMessage()


has_task = False
block = Block(None, None, None)
nonce_max_len = 20
nonce_thread = Thread(target=search_nonce)

if __name__ == '__main__':
    atexit.register(input, 'To exit press Enter...')
    re_thread()

    # noinspection PyBroadException
    try:  # It will catch KeyboardInterrupt (Ctrl-C) and other Exceptions
        while True:
            broker = connection.init_broker_stub()  # Interface for messaging w/ broker

            server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))  # Init module service
            module_pb2_grpc.add_ModuleServiceServicer_to_server(ModuleService(), server)

            own_module_service_port = connection.get_listen_port(server)

            print(f'Port ({own_module_service_port}) has been selected')

            handshake_response = connection.handshake(broker, own_module_service_port, 'CR')
            if not handshake_response.ok:
                print('Broker haven\'t permitted connection')
                break

            print(f'Handshake succeed')

            own_id = handshake_response.givenId

            atexit.register(connection.close_connection, broker, own_id)

            server.start()
            server.wait_for_termination()
    except KeyboardInterrupt:
        pass
    except Exception as e:
        traceback.print_exc()
