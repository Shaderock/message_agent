import select
import socket
import json

import grpc
import netifaces

import broker_pb2
import broker_pb2_grpc

broker_tcp_port = 17001
broker_udp_port = 16002


def init_broker_stub() -> broker_pb2_grpc.BrokerServiceStub:
    broker_ip = listen_to_broker_udp()  # Obtain broker's ip
    return broker_pb2_grpc.BrokerServiceStub(grpc.insecure_channel(f'{broker_ip}:{broker_tcp_port}'))


def get_listen_port(server: grpc.Server) -> int:
    port = 16003
    while True:  # Port detection
        # noinspection PyBroadException
        try:
            server.add_insecure_port(f'[::]:{port}')  # Trying to add listen port
        except Exception:  # If port is occupied
            port += 1
            if port > 65535:
                raise Exception('No port available')
        else:
            return port


def handshake(broker: broker_pb2_grpc.BrokerServiceStub, own_port: int,
              module_type: str) -> broker_pb2.HandshakeResponse:
    handshake_request = broker_pb2.HandshakeRequest(type=module_type, ip=get_own_ip(), port=own_port)
    return broker.handshake(handshake_request)


def close_connection(broker: broker_pb2_grpc.BrokerServiceStub, own_id):
    close_request = broker_pb2.EmptyIdRequest(idRequester=own_id)
    broker.close(close_request)


def get_own_ip() -> str:
    return netifaces.ifaddresses(netifaces.gateways()['default'][2][1])[2][0]['addr']


def get_broadcast() -> str:
    return netifaces.ifaddresses(netifaces.gateways()['default'][2][1])[2][0]['broadcast']


# noinspection PyBroadException
def listen_to_broker_udp() -> str:  # Broadcast to local network for broker
    broadcast_udp_ip = get_broadcast()
    udp_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

    module_is_alive = {'operation': 'module-is-alive'}
    module_is_alive = json.dumps(module_is_alive).encode('utf8')

    print('Waiting broker')

    while True:
        udp_socket.sendto(module_is_alive, (broadcast_udp_ip, broker_udp_port))

        descriptors = select.select([udp_socket], [], [], 5)  # Wait 5 sec for responce
        if udp_socket in descriptors[0]:
            data, address = udp_socket.recvfrom(1024)
            try:
                data_dict = json.loads(data.decode('utf8'))
                if data_dict['operation'] == 'broker-is-alive':
                    break
            except Exception:
                print(f'Invalid json from udp listener {str(address)}:\n\t{data.decode("utf8")}')

    return address[0]  # Only IP (without port)
