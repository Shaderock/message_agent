package proto.module;

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
    comments = "Source: module.proto")
public final class ModuleServiceGrpc {

  private ModuleServiceGrpc() {}

  public static final String SERVICE_NAME = "ModuleService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getReceiveMessageMethod()} instead. 
  public static final io.grpc.MethodDescriptor<proto.module.MessageRequest,
      proto.module.EmptyMessage> METHOD_RECEIVE_MESSAGE = getReceiveMessageMethod();

  private static volatile io.grpc.MethodDescriptor<proto.module.MessageRequest,
      proto.module.EmptyMessage> getReceiveMessageMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<proto.module.MessageRequest,
      proto.module.EmptyMessage> getReceiveMessageMethod() {
    io.grpc.MethodDescriptor<proto.module.MessageRequest, proto.module.EmptyMessage> getReceiveMessageMethod;
    if ((getReceiveMessageMethod = ModuleServiceGrpc.getReceiveMessageMethod) == null) {
      synchronized (ModuleServiceGrpc.class) {
        if ((getReceiveMessageMethod = ModuleServiceGrpc.getReceiveMessageMethod) == null) {
          ModuleServiceGrpc.getReceiveMessageMethod = getReceiveMessageMethod = 
              io.grpc.MethodDescriptor.<proto.module.MessageRequest, proto.module.EmptyMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "ModuleService", "receiveMessage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  proto.module.MessageRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  proto.module.EmptyMessage.getDefaultInstance()))
                  .setSchemaDescriptor(new ModuleServiceMethodDescriptorSupplier("receiveMessage"))
                  .build();
          }
        }
     }
     return getReceiveMessageMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getCloseMethod()} instead. 
  public static final io.grpc.MethodDescriptor<proto.module.EmptyMessage,
      proto.module.EmptyMessage> METHOD_CLOSE = getCloseMethod();

  private static volatile io.grpc.MethodDescriptor<proto.module.EmptyMessage,
      proto.module.EmptyMessage> getCloseMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<proto.module.EmptyMessage,
      proto.module.EmptyMessage> getCloseMethod() {
    io.grpc.MethodDescriptor<proto.module.EmptyMessage, proto.module.EmptyMessage> getCloseMethod;
    if ((getCloseMethod = ModuleServiceGrpc.getCloseMethod) == null) {
      synchronized (ModuleServiceGrpc.class) {
        if ((getCloseMethod = ModuleServiceGrpc.getCloseMethod) == null) {
          ModuleServiceGrpc.getCloseMethod = getCloseMethod = 
              io.grpc.MethodDescriptor.<proto.module.EmptyMessage, proto.module.EmptyMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "ModuleService", "close"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  proto.module.EmptyMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  proto.module.EmptyMessage.getDefaultInstance()))
                  .setSchemaDescriptor(new ModuleServiceMethodDescriptorSupplier("close"))
                  .build();
          }
        }
     }
     return getCloseMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ModuleServiceStub newStub(io.grpc.Channel channel) {
    return new ModuleServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ModuleServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ModuleServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ModuleServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ModuleServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class ModuleServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void receiveMessage(proto.module.MessageRequest request,
        io.grpc.stub.StreamObserver<proto.module.EmptyMessage> responseObserver) {
      asyncUnimplementedUnaryCall(getReceiveMessageMethod(), responseObserver);
    }

    /**
     */
    public void close(proto.module.EmptyMessage request,
        io.grpc.stub.StreamObserver<proto.module.EmptyMessage> responseObserver) {
      asyncUnimplementedUnaryCall(getCloseMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getReceiveMessageMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                proto.module.MessageRequest,
                proto.module.EmptyMessage>(
                  this, METHODID_RECEIVE_MESSAGE)))
          .addMethod(
            getCloseMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                proto.module.EmptyMessage,
                proto.module.EmptyMessage>(
                  this, METHODID_CLOSE)))
          .build();
    }
  }

  /**
   */
  public static final class ModuleServiceStub extends io.grpc.stub.AbstractStub<ModuleServiceStub> {
    private ModuleServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ModuleServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ModuleServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ModuleServiceStub(channel, callOptions);
    }

    /**
     */
    public void receiveMessage(proto.module.MessageRequest request,
        io.grpc.stub.StreamObserver<proto.module.EmptyMessage> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getReceiveMessageMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void close(proto.module.EmptyMessage request,
        io.grpc.stub.StreamObserver<proto.module.EmptyMessage> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCloseMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ModuleServiceBlockingStub extends io.grpc.stub.AbstractStub<ModuleServiceBlockingStub> {
    private ModuleServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ModuleServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ModuleServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ModuleServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public proto.module.EmptyMessage receiveMessage(proto.module.MessageRequest request) {
      return blockingUnaryCall(
          getChannel(), getReceiveMessageMethod(), getCallOptions(), request);
    }

    /**
     */
    public proto.module.EmptyMessage close(proto.module.EmptyMessage request) {
      return blockingUnaryCall(
          getChannel(), getCloseMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ModuleServiceFutureStub extends io.grpc.stub.AbstractStub<ModuleServiceFutureStub> {
    private ModuleServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ModuleServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ModuleServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ModuleServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<proto.module.EmptyMessage> receiveMessage(
        proto.module.MessageRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getReceiveMessageMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<proto.module.EmptyMessage> close(
        proto.module.EmptyMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getCloseMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_RECEIVE_MESSAGE = 0;
  private static final int METHODID_CLOSE = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ModuleServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ModuleServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_RECEIVE_MESSAGE:
          serviceImpl.receiveMessage((proto.module.MessageRequest) request,
              (io.grpc.stub.StreamObserver<proto.module.EmptyMessage>) responseObserver);
          break;
        case METHODID_CLOSE:
          serviceImpl.close((proto.module.EmptyMessage) request,
              (io.grpc.stub.StreamObserver<proto.module.EmptyMessage>) responseObserver);
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

  private static abstract class ModuleServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ModuleServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return proto.module.Module.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ModuleService");
    }
  }

  private static final class ModuleServiceFileDescriptorSupplier
      extends ModuleServiceBaseDescriptorSupplier {
    ModuleServiceFileDescriptorSupplier() {}
  }

  private static final class ModuleServiceMethodDescriptorSupplier
      extends ModuleServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ModuleServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (ModuleServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ModuleServiceFileDescriptorSupplier())
              .addMethod(getReceiveMessageMethod())
              .addMethod(getCloseMethod())
              .build();
        }
      }
    }
    return result;
  }
}
