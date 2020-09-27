import select
import socket
import json
import netifaces

broadcast_udp_ip = ''
broker_tcp_port = 17001
broker_udp_port = 16002


def get_own_ip() -> str:
    return netifaces.ifaddresses(netifaces.gateways()['default'][2][1])[2][0]['addr']


def broadcast_init():
    global broadcast_udp_ip
    broadcast_udp_ip = netifaces.ifaddresses(netifaces.gateways()['default'][2][1])[2][0]['broadcast']


# noinspection PyBroadException
def listen_to_broker_udp() -> str:
    if broadcast_udp_ip == '':
        broadcast_init()
    udp_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    module_is_alive = {'operation': 'module-is-alive'}
    module_is_alive = json.dumps(module_is_alive).encode('utf8')
    print('Waiting broker')

    while True:
        udp_socket.sendto(module_is_alive, (broadcast_udp_ip, broker_udp_port))

        descriptors = select.select([udp_socket], [], [], 5)
        if udp_socket in descriptors[0]:
            data, address = udp_socket.recvfrom(1024)
            try:
                data_dict = json.loads(data.decode('utf8'))
                if data_dict['operation'] == 'broker-is-alive':
                    break
            except Exception:
                print(f'Invalid json from udp listener {str(address)}:\n\t{data.decode("utf8")}')

    return address[0]  # Only IP (without port)
