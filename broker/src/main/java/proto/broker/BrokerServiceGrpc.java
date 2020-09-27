package proto.broker;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.8.0)",
    comments = "Source: broker.proto")
public final class BrokerServiceGrpc {

  private BrokerServiceGrpc() {}

  public static final String SERVICE_NAME = "broker.BrokerService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getHandshakeMethod()} instead. 
  public static final io.grpc.MethodDescriptor<proto.broker.HandshakeRequest,
      proto.broker.HandshakeResponse> METHOD_HANDSHAKE = getHandshakeMethod();

  private static volatile io.grpc.MethodDescriptor<proto.broker.HandshakeRequest,
      proto.broker.HandshakeResponse> getHandshakeMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<proto.broker.HandshakeRequest,
      proto.broker.HandshakeResponse> getHandshakeMethod() {
    io.grpc.MethodDescriptor<proto.broker.HandshakeRequest, proto.broker.HandshakeResponse> getHandshakeMethod;
    if ((getHandshakeMethod = BrokerServiceGrpc.getHandshakeMethod) == null) {
      synchronized (BrokerServiceGrpc.class) {
        if ((getHandshakeMethod = BrokerServiceGrpc.getHandshakeMethod) == null) {
          BrokerServiceGrpc.getHandshakeMethod = getHandshakeMethod = 
              io.grpc.MethodDescriptor.<proto.broker.HandshakeRequest, proto.broker.HandshakeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "broker.BrokerService", "handshake"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  proto.broker.HandshakeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  proto.broker.HandshakeResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new BrokerServiceMethodDescriptorSupplier("handshake"))
                  .build();
          }
        }
     }
     return getHandshakeMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getGetModulesMethod()} instead. 
  public static final io.grpc.MethodDescriptor<proto.broker.EmptyIdRequest,
      proto.broker.GetModulesResponse> METHOD_GET_MODULES = getGetModulesMethod();

  private static volatile io.grpc.MethodDescriptor<proto.broker.EmptyIdRequest,
      proto.broker.GetModulesResponse> getGetModulesMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<proto.broker.EmptyIdRequest,
      proto.broker.GetModulesResponse> getGetModulesMethod() {
    io.grpc.MethodDescriptor<proto.broker.EmptyIdRequest, proto.broker.GetModulesResponse> getGetModulesMethod;
    if ((getGetModulesMethod = BrokerServiceGrpc.getGetModulesMethod) == null) {
      synchronized (BrokerServiceGrpc.class) {
        if ((getGetModulesMethod = BrokerServiceGrpc.getGetModulesMethod) == null) {
          BrokerServiceGrpc.getGetModulesMethod = getGetModulesMethod = 
              io.grpc.MethodDescriptor.<proto.broker.EmptyIdRequest, proto.broker.GetModulesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "broker.BrokerService", "getModules"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  proto.broker.EmptyIdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  proto.broker.GetModulesResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new BrokerServiceMethodDescriptorSupplier("getModules"))
                  .build();
          }
        }
     }
     return getGetModulesMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getSubscribeMethod()} instead. 
  public static final io.grpc.MethodDescriptor<proto.broker.SubscribeRequest,
      proto.broker.SubscribeResponse> METHOD_SUBSCRIBE = getSubscribeMethod();

  private static volatile io.grpc.MethodDescriptor<proto.broker.SubscribeRequest,
      proto.broker.SubscribeResponse> getSubscribeMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<proto.broker.SubscribeRequest,
      proto.broker.SubscribeResponse> getSubscribeMethod() {
    io.grpc.MethodDescriptor<proto.broker.SubscribeRequest, proto.broker.SubscribeResponse> getSubscribeMethod;
    if ((getSubscribeMethod = BrokerServiceGrpc.getSubscribeMethod) == null) {
      synchronized (BrokerServiceGrpc.class) {
        if ((getSubscribeMethod = BrokerServiceGrpc.getSubscribeMethod) == null) {
          BrokerServiceGrpc.getSubscribeMethod = getSubscribeMethod = 
              io.grpc.MethodDescriptor.<proto.broker.SubscribeRequest, proto.broker.SubscribeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "broker.BrokerService", "subscribe"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  proto.broker.SubscribeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  proto.broker.SubscribeResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new BrokerServiceMethodDescriptorSupplier("subscribe"))
                  .build();
          }
        }
     }
     return getSubscribeMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getSendMessageMethod()} instead. 
  public static final io.grpc.MethodDescriptor<proto.broker.MessageRequest,
      proto.broker.EmptyMessage> METHOD_SEND_MESSAGE = getSendMessageMethod();

  private static volatile io.grpc.MethodDescriptor<proto.broker.MessageRequest,
      proto.broker.EmptyMessage> getSendMessageMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<proto.broker.MessageRequest,
      proto.broker.EmptyMessage> getSendMessageMethod() {
    io.grpc.MethodDescriptor<proto.broker.MessageRequest, proto.broker.EmptyMessage> getSendMessageMethod;
    if ((getSendMessageMethod = BrokerServiceGrpc.getSendMessageMethod) == null) {
      synchronized (BrokerServiceGrpc.class) {
        if ((getSendMessageMethod = BrokerServiceGrpc.getSendMessageMethod) == null) {
          BrokerServiceGrpc.getSendMessageMethod = getSendMessageMethod = 
              io.grpc.MethodDescriptor.<proto.broker.MessageRequest, proto.broker.EmptyMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "broker.BrokerService", "sendMessage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  proto.broker.MessageRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  proto.broker.EmptyMessage.getDefaultInstance()))
                  .setSchemaDescriptor(new BrokerServiceMethodDescriptorSupplier("sendMessage"))
                  .build();
          }
        }
     }
     return getSendMessageMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getCloseMethod()} instead. 
  public static final io.grpc.MethodDescriptor<proto.broker.EmptyIdRequest,
      proto.broker.EmptyMessage> METHOD_CLOSE = getCloseMethod();

  private static volatile io.grpc.MethodDescriptor<proto.broker.EmptyIdRequest,
      proto.broker.EmptyMessage> getCloseMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<proto.broker.EmptyIdRequest,
      proto.broker.EmptyMessage> getCloseMethod() {
    io.grpc.MethodDescriptor<proto.broker.EmptyIdRequest, proto.broker.EmptyMessage> getCloseMethod;
    if ((getCloseMethod = BrokerServiceGrpc.getCloseMethod) == null) {
      synchronized (BrokerServiceGrpc.class) {
        if ((getCloseMethod = BrokerServiceGrpc.getCloseMethod) == null) {
          BrokerServiceGrpc.getCloseMethod = getCloseMethod = 
              io.grpc.MethodDescriptor.<proto.broker.EmptyIdRequest, proto.broker.EmptyMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "broker.BrokerService", "close"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  proto.broker.EmptyIdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  proto.broker.EmptyMessage.getDefaultInstance()))
                  .setSchemaDescriptor(new BrokerServiceMethodDescriptorSupplier("close"))
                  .build();
          }
        }
     }
     return getCloseMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static BrokerServiceStub newStub(io.grpc.Channel channel) {
    return new BrokerServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static BrokerServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new BrokerServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static BrokerServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new BrokerServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class BrokerServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void handshake(proto.broker.HandshakeRequest request,
        io.grpc.stub.StreamObserver<proto.broker.HandshakeResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getHandshakeMethod(), responseObserver);
    }

    /**
     */
    public void getModules(proto.broker.EmptyIdRequest request,
        io.grpc.stub.StreamObserver<proto.broker.GetModulesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetModulesMethod(), responseObserver);
    }

    /**
     */
    public void subscribe(proto.broker.SubscribeRequest request,
        io.grpc.stub.StreamObserver<proto.broker.SubscribeResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSubscribeMethod(), responseObserver);
    }

    /**
     */
    public void sendMessage(proto.broker.MessageRequest request,
        io.grpc.stub.StreamObserver<proto.broker.EmptyMessage> responseObserver) {
      asyncUnimplementedUnaryCall(getSendMessageMethod(), responseObserver);
    }

    /**
     */
    public void close(proto.broker.EmptyIdRequest request,
        io.grpc.stub.StreamObserver<proto.broker.EmptyMessage> responseObserver) {
      asyncUnimplementedUnaryCall(getCloseMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getHandshakeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                proto.broker.HandshakeRequest,
                proto.broker.HandshakeResponse>(
                  this, METHODID_HANDSHAKE)))
          .addMethod(
            getGetModulesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                proto.broker.EmptyIdRequest,
                proto.broker.GetModulesResponse>(
                  this, METHODID_GET_MODULES)))
          .addMethod(
            getSubscribeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                proto.broker.SubscribeRequest,
                proto.broker.SubscribeResponse>(
                  this, METHODID_SUBSCRIBE)))
          .addMethod(
            getSendMessageMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                proto.broker.MessageRequest,
                proto.broker.EmptyMessage>(
                  this, METHODID_SEND_MESSAGE)))
          .addMethod(
            getCloseMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                proto.broker.EmptyIdRequest,
                proto.broker.EmptyMessage>(
                  this, METHODID_CLOSE)))
          .build();
    }
  }

  /**
   */
  public static final class BrokerServiceStub extends io.grpc.stub.AbstractStub<BrokerServiceStub> {
    private BrokerServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private BrokerServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BrokerServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new BrokerServiceStub(channel, callOptions);
    }

    /**
     */
    public void handshake(proto.broker.HandshakeRequest request,
        io.grpc.stub.StreamObserver<proto.broker.HandshakeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getHandshakeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getModules(proto.broker.EmptyIdRequest request,
        io.grpc.stub.StreamObserver<proto.broker.GetModulesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetModulesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void subscribe(proto.broker.SubscribeRequest request,
        io.grpc.stub.StreamObserver<proto.broker.SubscribeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSubscribeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void sendMessage(proto.broker.MessageRequest request,
        io.grpc.stub.StreamObserver<proto.broker.EmptyMessage> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSendMessageMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void close(proto.broker.EmptyIdRequest request,
        io.grpc.stub.StreamObserver<proto.broker.EmptyMessage> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCloseMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class BrokerServiceBlockingStub extends io.grpc.stub.AbstractStub<BrokerServiceBlockingStub> {
    private BrokerServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private BrokerServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BrokerServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new BrokerServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public proto.broker.HandshakeResponse handshake(proto.broker.HandshakeRequest request) {
      return blockingUnaryCall(
          getChannel(), getHandshakeMethod(), getCallOptions(), request);
    }

    /**
     */
    public proto.broker.GetModulesResponse getModules(proto.broker.EmptyIdRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetModulesMethod(), getCallOptions(), request);
    }

    /**
     */
    public proto.broker.SubscribeResponse subscribe(proto.broker.SubscribeRequest request) {
      return blockingUnaryCall(
          getChannel(), getSubscribeMethod(), getCallOptions(), request);
    }

    /**
     */
    public proto.broker.EmptyMessage sendMessage(proto.broker.MessageRequest request) {
      return blockingUnaryCall(
          getChannel(), getSendMessageMethod(), getCallOptions(), request);
    }

    /**
     */
    public proto.broker.EmptyMessage close(proto.broker.EmptyIdRequest request) {
      return blockingUnaryCall(
          getChannel(), getCloseMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class BrokerServiceFutureStub extends io.grpc.stub.AbstractStub<BrokerServiceFutureStub> {
    private BrokerServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private BrokerServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BrokerServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new BrokerServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<proto.broker.HandshakeResponse> handshake(
        proto.broker.HandshakeRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getHandshakeMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<proto.broker.GetModulesResponse> getModules(
        proto.broker.EmptyIdRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetModulesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<proto.broker.SubscribeResponse> subscribe(
        proto.broker.SubscribeRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSubscribeMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<proto.broker.EmptyMessage> sendMessage(
        proto.broker.MessageRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSendMessageMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<proto.broker.EmptyMessage> close(
        proto.broker.EmptyIdRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCloseMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_HANDSHAKE = 0;
  private static final int METHODID_GET_MODULES = 1;
  private static final int METHODID_SUBSCRIBE = 2;
  private static final int METHODID_SEND_MESSAGE = 3;
  private static final int METHODID_CLOSE = 4;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final BrokerServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(BrokerServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_HANDSHAKE:
          serviceImpl.handshake((proto.broker.HandshakeRequest) request,
              (io.grpc.stub.StreamObserver<proto.broker.HandshakeResponse>) responseObserver);
          break;
        case METHODID_GET_MODULES:
          serviceImpl.getModules((proto.broker.EmptyIdRequest) request,
              (io.grpc.stub.StreamObserver<proto.broker.GetModulesResponse>) responseObserver);
          break;
        case METHODID_SUBSCRIBE:
          serviceImpl.subscribe((proto.broker.SubscribeRequest) request,
              (io.grpc.stub.StreamObserver<proto.broker.SubscribeResponse>) responseObserver);
          break;
        case METHODID_SEND_MESSAGE:
          serviceImpl.sendMessage((proto.broker.MessageRequest) request,
              (io.grpc.stub.StreamObserver<proto.broker.EmptyMessage>) responseObserver);
          break;
        case METHODID_CLOSE:
          serviceImpl.close((proto.broker.EmptyIdRequest) request,
              (io.grpc.stub.StreamObserver<proto.broker.EmptyMessage>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class BrokerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    BrokerServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return proto.broker.BrokerProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("BrokerService");
    }
  }

  private static final class BrokerServiceFileDescriptorSupplier
      extends BrokerServiceBaseDescriptorSupplier {
    BrokerServiceFileDescriptorSupplier() {}
  }

  private static final class BrokerServiceMethodDescriptorSupplier
      extends BrokerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    BrokerServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (BrokerServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new BrokerServiceFileDescriptorSupplier())
              .addMethod(getHandshakeMethod())
              .addMethod(getGetModulesMethod())
              .addMethod(getSubscribeMethod())
              .addMethod(getSendMessageMethod())
              .addMethod(getCloseMethod())
              .build();
        }
      }
    }
    return result;
  }
}
