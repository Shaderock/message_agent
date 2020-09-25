import json
import random
import string
import atexit

import hash
import connection

has_task = False
block = {}
nonce_max_len = 20


# noinspection PyBroadException
def close_connection():
    try:
        close = {
            'operation': 'close'
        }
        conn.send(json.dumps(close))
        conn.reset_socket()
        print('Connection closed (by self)')

    except Exception:
        pass
    finally:
        input('Press enter to exit...')


# noinspection PyUnusedLocal
def get_new_nonce() -> str:
    length = random.randint(1, nonce_max_len)
    letters = string.ascii_letters + string.digits + string.punctuation
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

        if conn.is_socket_resetted():
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

        if conn.has_messages():
            message = json.loads(conn.wait_message())

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

            elif message['operation'] == 'close':
                print('Close connection (by broker)')
                conn.reset_socket()
                break

            elif message['operation'] == 'keep-alive':
                pass

            else:
                print(f'Untreated operation - {message["operation"]}')
        # raise KeyboardInterrupt


if __name__ == '__main__':
    conn = None
    try:
        while True:
            conn = connection.Connection("CR")

            wait_task()
    finally:
        close_connection()
