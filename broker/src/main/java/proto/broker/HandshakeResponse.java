// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: broker.proto

package proto.broker;

/**
 * Protobuf type {@code HandshakeResponse}
 */
public  final class HandshakeResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:HandshakeResponse)
    HandshakeResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use HandshakeResponse.newBuilder() to construct.
  private HandshakeResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private HandshakeResponse() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new HandshakeResponse();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private HandshakeResponse(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 8: {
            bitField0_ |= 0x00000001;
            ok_ = input.readBool();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            givenId_ = input.readInt64();
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return proto.broker.BrokerProto.internal_static_HandshakeResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return proto.broker.BrokerProto.internal_static_HandshakeResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            proto.broker.HandshakeResponse.class, proto.broker.HandshakeResponse.Builder.class);
  }

  private int bitField0_;
  public static final int OK_FIELD_NUMBER = 1;
  private boolean ok_;
  /**
   * <code>required bool ok = 1;</code>
   * @return Whether the ok field is set.
   */
  public boolean hasOk() {
    return ((bitField0_ & 0x00000001) != 0);
  }
  /**
   * <code>required bool ok = 1;</code>
   * @return The ok.
   */
  public boolean getOk() {
    return ok_;
  }

  public static final int GIVENID_FIELD_NUMBER = 2;
  private long givenId_;
  /**
   * <code>optional int64 givenId = 2;</code>
   * @return Whether the givenId field is set.
   */
  public boolean hasGivenId() {
    return ((bitField0_ & 0x00000002) != 0);
  }
  /**
   * <code>optional int64 givenId = 2;</code>
   * @return The givenId.
   */
  public long getGivenId() {
    return givenId_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasOk()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) != 0)) {
      output.writeBool(1, ok_);
    }
    if (((bitField0_ & 0x00000002) != 0)) {
      output.writeInt64(2, givenId_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) != 0)) {
      size += com.google.protobuf.CodedOutputStream
        .computeBoolSize(1, ok_);
    }
    if (((bitField0_ & 0x00000002) != 0)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, givenId_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof proto.broker.HandshakeResponse)) {
      return super.equals(obj);
    }
    proto.broker.HandshakeResponse other = (proto.broker.HandshakeResponse) obj;

    if (hasOk() != other.hasOk()) return false;
    if (hasOk()) {
      if (getOk()
          != other.getOk()) return false;
    }
    if (hasGivenId() != other.hasGivenId()) return false;
    if (hasGivenId()) {
      if (getGivenId()
          != other.getGivenId()) return false;
    }
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (hasOk()) {
      hash = (37 * hash) + OK_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
          getOk());
    }
    if (hasGivenId()) {
      hash = (37 * hash) + GIVENID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getGivenId());
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static proto.broker.HandshakeResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static proto.broker.HandshakeResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static proto.broker.HandshakeResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static proto.broker.HandshakeResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static proto.broker.HandshakeResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static proto.broker.HandshakeResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static proto.broker.HandshakeResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static proto.broker.HandshakeResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static proto.broker.HandshakeResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static proto.broker.HandshakeResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static proto.broker.HandshakeResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static proto.broker.HandshakeResponse parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(proto.broker.HandshakeResponse prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code HandshakeResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:HandshakeResponse)
      proto.broker.HandshakeResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return proto.broker.BrokerProto.internal_static_HandshakeResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return proto.broker.BrokerProto.internal_static_HandshakeResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              proto.broker.HandshakeResponse.class, proto.broker.HandshakeResponse.Builder.class);
    }

    // Construct using proto.broker.HandshakeResponse.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      ok_ = false;
      bitField0_ = (bitField0_ & ~0x00000001);
      givenId_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return proto.broker.BrokerProto.internal_static_HandshakeResponse_descriptor;
    }

    @java.lang.Override
    public proto.broker.HandshakeResponse getDefaultInstanceForType() {
      return proto.broker.HandshakeResponse.getDefaultInstance();
    }

    @java.lang.Override
    public proto.broker.HandshakeResponse build() {
      proto.broker.HandshakeResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public proto.broker.HandshakeResponse buildPartial() {
      proto.broker.HandshakeResponse result = new proto.broker.HandshakeResponse(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.ok_ = ok_;
        to_bitField0_ |= 0x00000001;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.givenId_ = givenId_;
        to_bitField0_ |= 0x00000002;
      }
      result.bitField0_ = to_bitField0_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof proto.broker.HandshakeResponse) {
        return mergeFrom((proto.broker.HandshakeResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(proto.broker.HandshakeResponse other) {
      if (other == proto.broker.HandshakeResponse.getDefaultInstance()) return this;
      if (other.hasOk()) {
        setOk(other.getOk());
      }
      if (other.hasGivenId()) {
        setGivenId(other.getGivenId());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      if (!hasOk()) {
        return false;
      }
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      proto.broker.HandshakeResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (proto.broker.HandshakeResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private boolean ok_ ;
    /**
     * <code>required bool ok = 1;</code>
     * @return Whether the ok field is set.
     */
    public boolean hasOk() {
      return ((bitField0_ & 0x00000001) != 0);
    }
    /**
     * <code>required bool ok = 1;</code>
     * @return The ok.
     */
    public boolean getOk() {
      return ok_;
    }
    /**
     * <code>required bool ok = 1;</code>
     * @param value The ok to set.
     * @return This builder for chaining.
     */
    public Builder setOk(boolean value) {
      bitField0_ |= 0x00000001;
      ok_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required bool ok = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearOk() {
      bitField0_ = (bitField0_ & ~0x00000001);
      ok_ = false;
      onChanged();
      return this;
    }

    private long givenId_ ;
    /**
     * <code>optional int64 givenId = 2;</code>
     * @return Whether the givenId field is set.
     */
    public boolean hasGivenId() {
      return ((bitField0_ & 0x00000002) != 0);
    }
    /**
     * <code>optional int64 givenId = 2;</code>
     * @return The givenId.
     */
    public long getGivenId() {
      return givenId_;
    }
    /**
     * <code>optional int64 givenId = 2;</code>
     * @param value The givenId to set.
     * @return This builder for chaining.
     */
    public Builder setGivenId(long value) {
      bitField0_ |= 0x00000002;
      givenId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional int64 givenId = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearGivenId() {
      bitField0_ = (bitField0_ & ~0x00000002);
      givenId_ = 0L;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:HandshakeResponse)
  }

  // @@protoc_insertion_point(class_scope:HandshakeResponse)
  private static final proto.broker.HandshakeResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new proto.broker.HandshakeResponse();
  }

  public static proto.broker.HandshakeResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<HandshakeResponse>
      PARSER = new com.google.protobuf.AbstractParser<HandshakeResponse>() {
    @java.lang.Override
    public HandshakeResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new HandshakeResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<HandshakeResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<HandshakeResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public proto.broker.HandshakeResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

