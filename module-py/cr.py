import json
import random
import string
import atexit

import hash
import connection

has_task = False
block = {}
nonce_max_len = 20


def close_connection():
    try:
        close = {
            'operation': 'close'
        }
        conn.send(json.dumps(close))
        print('Connection closed (by self)')
        input('Press enter to exit...')
    except:
        pass


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
        if has_task:
            block['nonce'] = get_new_nonce()
            print(f'Random new nonce - {block["nonce"]}')
            if check_nonce():
                print(f'Block nonce found - {block["nonce"]}\n\tHash: {get_block_hash()}')
                send_task()
                print('Hash has been sent')
                block = {}
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
                    block = {
                        'id-block': json.loads(message['payload'])['id-block'],
                        'prev-hash': json.loads(message['payload'])['prev-hash'],
                        'content': json.loads(message['payload'])['content'],
                        'nonce': ''
                    }
                    has_task = True

            elif message['operation'] == 'close':
                print('Close connection (by broker)')
                break

            else:
                print(f'Untreated operation - {message["operation"]}')
                break


if __name__ == '__main__':
    conn = connection.Connection("CR")
    print('Connection established')

    atexit.register(close_connection)

    wait_task()
