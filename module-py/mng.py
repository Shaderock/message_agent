import json
import connection

blockchain = []
has_cr = False


def wait_message():
    while True:
        if conn.has_messages():
            return conn.messages.pop(0)


def sub_to_all():
    get_modules = {
        'operation': 'get-modules'
    }
    conn.send(json.dumps(get_modules))
    response = json.loads(wait_message())
    if response['payload']['code'] == 20:
        ids = []
        for module in response['payload']['modules']:
            if module['type'] == 'CR':
                ids.append(module['id'])
        if len(ids) != 0:
            global has_cr
            has_cr = True
            subscribe = {
                'operation': 'subscribe',
                'payload': json.dumps({ids})
            }
            conn.send(json.dumps(subscribe))
            response = json.loads(wait_message())
            if response['operation'] == 'subscribe' and response['operation']['code'] == 20:
                return
            else:
                raise Exception


if __name__ == '__main__':
    conn = connection.Connection('MNG')

    sub_to_all()
