import atexit
import random
import string
import json
from time import sleep

import hash
import connection

blockchain = []
cr_list = []
new_cr_list = []
has_task = False
prev_hash = ''
need_sub = True


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


def get_modules_for_sub():
    get_modules = {
        'operation': 'get-modules'
    }
    conn.send(json.dumps(get_modules))
    print('Requested all modules')


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
        sleep(0.1)
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
        sleep(0.1)
    print('Stop command has been sent')


def save_block(message):
    global has_task
    global prev_hash
    send_all_stop()
    blockchain[len(blockchain) - 1]['nonce'] = json.loads(json.loads(message['payload'])['info-block'])['nonce']
    prev_hash = json.loads(json.loads(message['payload'])['info-block'])['hash']
    has_task = True
    print('Block has been saved')


def send_task_to_cr(new_cr: list):
    for cr in new_cr:
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
        print(f'Individual task to {cr} has been sent')
        sleep(0.1)


def sub_to_new_cr():
    subscribe = {
        'operation': 'subscribe',
        'payload': json.dumps({'ids': cr_list + new_cr_list})
    }
    conn.send(json.dumps(subscribe))
    print(f'Requested new sub to {str(cr_list + new_cr_list)}')


def manage_task():
    global has_task
    global cr_list
    global need_sub

    while True:
        if has_task:
            if len(cr_list) != 0:
                send_task()
            else:
                pass
        if conn.has_messages():
            message = json.loads(conn.wait_message())

            if message['operation'] == 'get-modules':
                print('Got all modules')
                if need_sub:
                    for module in json.loads(message['payload'])['modules']:
                        if module['type'] == 'CR':
                            cr_list.append(module['id'])
                    print(f'There is/are {len(cr_list)} CR-module(s)')
                    if len(cr_list) != 0:
                        subscribe = {
                            'operation': 'subscribe',
                            'payload': json.dumps({'ids': cr_list})
                        }
                        conn.send(json.dumps(subscribe))
                        print('Requested for sub')
                    else:
                        has_task = True
                        need_sub = False

            elif message['operation'] == 'subscribe':
                if json.loads(message['payload'])['code'] == 20:
                    print(f'Subbed successful ({str(cr_list + new_cr_list)})')
                    if need_sub:
                        has_task = True
                        need_sub = False
                    elif len(new_cr_list) != 0:
                        if not has_task:
                            send_task_to_cr(new_cr_list)
                        cr_list += new_cr_list
                        new_cr_list.clear()
                elif json.loads(message['payload'])['code'] in [43, 44]:
                    print(f'Some sub has failed - {json.loads(message["payload"])["ids"]}')
                    if need_sub:
                        cr_list.clear()
                        get_modules_for_sub()
                    elif len(new_cr_list) != 0:
                        for cr_id in json.loads(message['payload'])["ids"]:
                            new_cr_list.remove(cr_id)
                        cr_list += new_cr_list
                        new_cr_list.clear()

            elif message['operation'] == 'notify':
                if json.loads(message['payload'])['command'] == 'complete-task':
                    print('Someone attempted to complete task')
                    if json.loads(json.loads(message['payload'])['info-block'])['id-block'] != (len(blockchain)-1):
                        break
                    if not hash.check_hash_rule(json.loads(json.loads(message['payload'])['info-block'])['hash']):
                        break
                    if json.loads(json.loads(message['payload'])['info-block'])['hash'] == \
                            get_last_block_hash(json.loads(json.loads(message['payload'])['info-block'])['nonce']):
                        print(f'Block #{json.loads(json.loads(message["payload"])["info-block"])["id-block"]} has '
                              f'been finished\n\t'
                              f'by: {json.loads(message["payload"])["id-sender"]}\n\t'
                              f'nonce: {json.loads(json.loads(message["payload"])["info-block"])["nonce"]}\n\t'
                              f'hash: {json.loads(json.loads(message["payload"])["info-block"])["hash"]}')
                        save_block(message)

            elif message['operation'] == 'direct-message':
                print('Someone dm\'ed me - let\'s just ignore ¯\\_(ツ)_/¯')
                pass

            elif message['operation'] == 'welcome':
                if json.loads(message['payload'])['type'] == 'CR':
                    print('New cr in system')
                    new_cr_list.append(json.loads(message['payload'])['id'])
                    sub_to_new_cr()

            elif message['operation'] == 'good-bye':
                if json.loads(message['payload'])['type'] == 'CR':
                    print(f'One cr left - {json.loads(message["payload"])["id"]}')
                    cr_list.remove(json.loads(message['payload'])['id'])
                    if len(cr_list) == 0:
                        blockchain.pop()
                        has_task = True

            elif message['operation'] == 'close':
                print('Connection closed (by broker)')
                if not has_task:
                    send_all_stop()
                break

            elif message['operation'] == 'keep-alive':
                pass

            else:
                print(f'Untreated operation - {message["operation"]}')
                pass


if __name__ == '__main__':
    conn = connection.Connection('MNG')
    print('Connection established')

    atexit.register(close_connection)

    get_modules_for_sub()
    manage_task()
