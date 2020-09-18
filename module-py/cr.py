import json
import random
import string

import hash
import connection

has_task = False
block = {}
nonce_max_len = 20


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
            if check_nonce():
                send_task()
        else:
            pass
        if conn.has_messages():
            message = json.loads(conn.wait_message())

            if message['operation'] == 'notify':
                pass

            if message['operation'] == 'direct-message':
                if message['payload']['command'] == 'stop':
                    block = {}
                    has_task = False

                if message['payload']['command'] == 'new-task':
                    block = {
                        'id-block': message['payload']['id-block'],
                        'prev-hash': message['payload']['prev-hash'],
                        'content': message['payload']['content'],
                        'nonce': ''
                    }
                    has_task = True


if __name__ == '__main__':
    conn = connection.Connection("CR")

    wait_task()
