import atexit
import random
import string
import json

import hash
import connection

blockchain = []
cr_list = []
has_task = True
prev_hash = ''


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


def sub_to_all():
    get_modules = {
        'operation': 'get-modules'
    }
    print('Getting all modules list')
    conn.send(json.dumps(get_modules))
    response = json.loads(conn.wait_message())
    print('Response got')
    if response['operation'] == 'get-modules' and response['payload']['code'] == 20:
        ids = []
        for module in response['payload']['modules']:
            if module['type'] == 'CR':
                ids.append(module['id'])
        print(f'There is/are {len(ids)} CR-module(s)')
        if len(ids) != 0:
            global cr_list
            cr_list = ids
            subscribe = {
                'operation': 'subscribe',
                'payload': json.dumps({'ids': ids})
            }
            conn.send(json.dumps(subscribe))
            response = json.loads(conn.wait_message())
            if response['operation'] == 'subscribe' and response['operation']['code'] == 20:
                print(f'Subbed successful ({str(cr_list)})')
                return
            else:
                raise Exception


def generate_content():
    length = random.randint(1, 15)
    letters = string.ascii_letters
    return ''.join(random.choice(letters) for i in range(length))


def send_task():
    blockchain.append({'content': generate_content()})
    info_block = {
        'id-block': len(blockchain)-1,
        'prev-hash': prev_hash,
        'content': blockchain[len(blockchain)-1]['content']
    }
    info_block = json.dumps(info_block)
    for cr in cr_list:
        payload = {
            'id-receiver': cr,
            'command': 'new-task',
            'info-block': info_block
        }
        direct_message = {
            'operation': 'direct-message',
            'payload': json.dumps(payload)
        }
        conn.send(json.dumps(direct_message))
        print('New task has been sent')
    global has_task
    has_task = False


def get_last_block_hash(nonce):
    return hash.get_block_hash(blockchain[len(blockchain)-1]['content'], prev_hash, nonce)


def send_all_stop():
    for cr in cr_list:
        payload = {
            "id-receiver": cr,
            'command': 'stop',
            'info-block': ''
        }
        direct_message = {
            'operation': 'direct-message',
            'payload': json.dumps(payload)
        }
        conn.send(json.dumps(direct_message))
    print('Stop command has been sent')


def save_block(message):
    global has_task
    global prev_hash
    send_all_stop()
    blockchain[len(blockchain) - 1]['nonce'] = message['payload']['info-block']['nonce']
    prev_hash = message['payload']['info-block']['hash']
    has_task = True
    print('Block has been saved')


def send_task_to_cr(cr):
    info_block = {
        'id-block': len(blockchain) - 1,
        'prev-hash': prev_hash,
        'content': blockchain[len(blockchain) - 1]['content']
    }
    payload = {
        'id-receiver': cr,
        'command': 'new-task',
        'info-block': json.dumps(info_block)
    }
    direct_message = {
        'operation': 'direct-message',
        'payload': json.dumps(payload)
    }
    conn.send(json.dumps(direct_message))
    print('Individual task has been sent')


def manage_task():
    global has_task
    while True:
        if has_task:
            if len(cr_list) != 0:
                send_task()
            else:
                pass
        if conn.has_messages():
            message = json.loads(conn.wait_message())

            if message['operation'] == 'notify':
                if message['payload']['command'] == 'complete-task':
                    print('Someone attempted to complete task')
                    if message['payload']['info-block']['id-block'] != (len(blockchain)-1):
                        break
                    if not hash.check_hash_rule(message['payload']['info-block']['hash']):
                        break
                    if message['payload']['info-block']['hash'] == get_last_block_hash(message['payload']['info-block']
                                                                                       ['nonce']):
                        print(f'Block #{message["payload"]["info-block"]["id-block"]} has been finished\n\t'
                              f'by: {message["payload"]["id-sender"]}\n\t'
                              f'nonce: {message["payload"]["info-block"]["nonce"]}\n\t'
                              f'hash: {message["payload"]["info-block"]["hash"]}')
                        save_block(message)

            elif message['operation'] == 'direct-message':
                print('Someone dm\'ed me - let\'s just ignore ¯\\_(ツ)_/¯')
                pass

            elif message['operation'] == 'welcome':
                if message['payload']['type'] == 'CR':
                    print('New cr in system')
                    cr_list.append(message['payload']['id'])
                    if not has_task:
                        send_task_to_cr(message['payload']['id'])

            elif message['operation'] == 'good-bye':
                if message['payload']['type'] == 'CR':
                    print('One cr left')
                    cr_list.remove(message['payload']['id'])
                    if len(cr_list) == 0:
                        blockchain.pop()
                        has_task = True

            elif message['operation'] == 'close':
                print('Connection closed (by broker)')
                if not has_task:
                    send_all_stop()
                break

            else:
                print(f'Got untreated operation - {message["operation"]}')
                pass


if __name__ == '__main__':
    conn = connection.Connection('MNG')
    print('Connection established')

    atexit.register(close_connection)

    sub_to_all()
    manage_task()
