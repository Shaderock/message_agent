# Generated by the gRPC Python protocol compiler plugin. DO NOT EDIT!
"""Client and server classes corresponding to protobuf-defined services."""
import grpc

import module_pb2 as module__pb2


class ModuleServiceStub(object):
    """Missing associated documentation comment in .proto file."""

    def __init__(self, channel):
        """Constructor.

        Args:
            channel: A grpc.Channel.
        """
        self.receiveMessage = channel.unary_unary(
                '/ModuleService/receiveMessage',
                request_serializer=module__pb2.MessageRequest.SerializeToString,
                response_deserializer=module__pb2.EmptyMessage.FromString,
                )
        self.close = channel.unary_unary(
                '/ModuleService/close',
                request_serializer=module__pb2.EmptyMessage.SerializeToString,
                response_deserializer=module__pb2.EmptyMessage.FromString,
                )


class ModuleServiceServicer(object):
    """Missing associated documentation comment in .proto file."""

    def receiveMessage(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')

    def close(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')


def add_ModuleServiceServicer_to_server(servicer, server):
    rpc_method_handlers = {
            'receiveMessage': grpc.unary_unary_rpc_method_handler(
                    servicer.receiveMessage,
                    request_deserializer=module__pb2.MessageRequest.FromString,
                    response_serializer=module__pb2.EmptyMessage.SerializeToString,
            ),
            'close': grpc.unary_unary_rpc_method_handler(
                    servicer.close,
                    request_deserializer=module__pb2.EmptyMessage.FromString,
                    response_serializer=module__pb2.EmptyMessage.SerializeToString,
            ),
    }
    generic_handler = grpc.method_handlers_generic_handler(
            'ModuleService', rpc_method_handlers)
    server.add_generic_rpc_handlers((generic_handler,))


 # This class is part of an EXPERIMENTAL API.
class ModuleService(object):
    """Missing associated documentation comment in .proto file."""

    @staticmethod
    def receiveMessage(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_unary(request, target, '/ModuleService/receiveMessage',
            module__pb2.MessageRequest.SerializeToString,
            module__pb2.EmptyMessage.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)

    @staticmethod
    def close(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_unary(request, target, '/ModuleService/close',
            module__pb2.EmptyMessage.SerializeToString,
            module__pb2.EmptyMessage.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)