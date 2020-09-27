from concurrent import futures
import threading
import traceback
import atexit
import random
import string
import json
import grpc

from block import Block
import module_pb2_grpc
import module_pb2
import connection
import broker_pb2
import hash


# noinspection PyBroadException
def try_new_block(message_request: module_pb2.MessageRequest):
    try:
        message = json.loads(message_request.message)
        if message['info-block']['id-block'] != blockchain[-1].id_block:
            return
        if not hash.check_hash_rule(message['info-block']['hash']):
            return
        if message['info-block']['hash'] == get_last_block_hash(message['info-block']['nonce']):
            send_cr_stop()
            save_block(message)
            b = blockchain[-1]
            print(f'Block #{b.id_block} is completed\n\t'
                  f'by: cr (id = {message_request.idSender})\n\t'
                  f'hash: "{blockchain[-1].hash}"\n\t'
                  f'content: "{blockchain[-1].content}"\n\t'
                  f'nonce: "{blockchain[-1].nonce}"')

            create_new_block()
            send_cr_task()
    except Exception:
        print(f'\tMessage sent contains errors - "{message_request.message}"')


def send_cr_stop():
    message = {
        'command': 'stop'
    }
    message_string = json.dumps(message)
    message_request = broker_pb2.MessageRequest(idRequester=own_id, idReceiver=0, message=message_string)
    for cr in cr_list:
        message_request.idReceiver = cr
        broker.sendMessage(message_request)
    print('Stopped everyone')


def send_task(module_list: list):
    # info_block = {
    #     'id-block': len(blockchain) - 1,
    #     'prev-hash': blockchain[-1].prev_hash,
    #     'content': blockchain[-1].content
    # }
    message = {
        'command': 'new-task',
        'info-block': blockchain[-1].__dict__
    }
    message_string = json.dumps(message)
    message_request = broker_pb2.MessageRequest(idRequester=own_id, idReceiver=0, message=message_string)
    for module_id in module_list:
        message_request.idReceiver = module_id
        broker.sendMessage(message_request)
        print(f'Sent task to cr (id = {module_id})')


def send_cr_task():
    send_task(cr_list)


# noinspection PyBroadException
def sub_to_new_modules():
    sub_request = broker_pb2.SubscribeRequest(idSubscriber=own_id, id=cr_list + new_cr_list)
    sub_response = broker.subscribe(sub_request)

    print(f'Asked for sub to - {cr_list + new_cr_list}')

    if not sub_response.ok:
        for module in sub_response.wrongId:
            for possible_list in [new_cr_list, cr_list]:
                try:
                    possible_list.remove(module)
                except Exception:
                    pass

    print(f'Successful subbed to - {cr_list + new_cr_list}')

    send_task(new_cr_list)
    cr_list.extend(new_cr_list)
    new_cr_list.clear()


def sub_for_all_modules():
    get_modules_request = broker_pb2.EmptyIdRequest(idRequester=own_id)
    get_modules_response = broker.getModules(get_modules_request)
    for module in get_modules_response.module:
        if module.type == 'CR':
            new_cr_list.append(module.id)

    print(f'There is (are) {len(new_cr_list)} CR module(-s)')

    if len(new_cr_list) != 0:
        sub_to_new_modules()


# noinspection PyBroadException
class ModuleService(module_pb2_grpc.ModuleServiceServicer):
    def receiveMessage(self, request, context):
        global additional_thread
        try:
            if json.loads(request.message)['command'] == 'complete-task':
                print(f'CR (id = {request.idSender}) attempted to complete task')
                if additional_thread.is_alive():
                    additional_thread.join()
                additional_thread = threading.Thread(target=try_new_block, args=request.message)
            else:
                pass
        except Exception:
            print(f'Invalid JSON message has come - "{request.message}" from {request.idSender}')
        return module_pb2.EmptyMessage()

    def welcome(self, request, context):
        global additional_thread
        if request.module.type == 'CR':
            print(f'New CR has appear (id = {request.module.id})')
            new_cr_list.append(request.module.id)
            if additional_thread.is_alive():
                additional_thread.join()
            additional_thread = threading.Thread(target=sub_to_new_modules)
            additional_thread.start()
        return module_pb2.EmptyMessage()

    def goodBye(self, request, context):
        if request.module.type == 'CR':
            print(f'CR has left us (id = {request.module.id})')
            try:
                cr_list.remove(request.module.id)
            finally:
                return module_pb2.EmptyMessage()

    def close(self, request, context):
        print('Need to close (broker said so)')
        atexit.unregister(connection.close_connection)
        threading.Thread(target=server.stop, args=(None,)).start()
        return module_pb2.EmptyMessage()


def save_block(message):
    global prev_hash
    blockchain[-1].nonce = message['info-block']['nonce']
    prev_hash = message['info-block']['hash']


# noinspection PyUnusedLocal
def generate_content() -> str:
    length = random.randint(1, 15)
    letters = string.ascii_letters
    return ''.join(random.choice(letters) for i in range(length))


def create_new_block():
    blockchain.append(Block(id_block=len(blockchain), content=generate_content(), prev_hash=prev_hash))


def get_last_block_hash(nonce):
    return hash.get_block_hash(blockchain[-1].content, blockchain[-1].prev_hash, nonce)


blockchain = []
cr_list = []
new_cr_list = []
prev_hash = ''
additional_thread = threading.Thread()

if __name__ == '__main__':
    atexit.register(input, 'To exit press Enter...')
    create_new_block()

    # noinspection PyBroadException
    try:  # It will catch KeyboardInterrupt (Ctrl-C) and other Exceptions
        while True:
            broker = connection.init_broker_stub()  # Interface for messaging w/ broker

            server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))  # Init module service
            module_pb2_grpc.add_ModuleServiceServicer_to_server(ModuleService(), server)

            own_module_service_port = connection.get_listen_port()
            server.add_insecure_port(f'[::]:{own_module_service_port}')

            print(f'Port ({own_module_service_port}) has been selected')

            handshake_response = connection.handshake(broker, own_module_service_port, 'MNG')
            if not handshake_response.ok:
                print('Broker haven\'t permitted connection')
                break

            print(f'Handshake succeed')

            own_id = handshake_response.givenId

            atexit.register(connection.close_connection, broker, own_id)

            additional_thread = threading.Thread(target=sub_for_all_modules)
            additional_thread.start()

            server.start()
            server.wait_for_termination()
    except KeyboardInterrupt:
        pass
    except Exception as e:
        traceback.print_exc()
