// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: broker.proto

package proto.broker;

/**
 * Protobuf type {@code GetModulesResponse}
 */
public  final class GetModulesResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:GetModulesResponse)
    GetModulesResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use GetModulesResponse.newBuilder() to construct.
  private GetModulesResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private GetModulesResponse() {
    modules_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new GetModulesResponse();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private GetModulesResponse(
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
          case 10: {
            if (!((mutable_bitField0_ & 0x00000001) != 0)) {
              modules_ = new java.util.ArrayList<proto.broker.Module>();
              mutable_bitField0_ |= 0x00000001;
            }
            modules_.add(
                input.readMessage(proto.broker.Module.PARSER, extensionRegistry));
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
      if (((mutable_bitField0_ & 0x00000001) != 0)) {
        modules_ = java.util.Collections.unmodifiableList(modules_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return proto.broker.Broker.internal_static_GetModulesResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return proto.broker.Broker.internal_static_GetModulesResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            proto.broker.GetModulesResponse.class, proto.broker.GetModulesResponse.Builder.class);
  }

  public static final int MODULES_FIELD_NUMBER = 1;
  private java.util.List<proto.broker.Module> modules_;
  /**
   * <code>repeated .Module modules = 1;</code>
   */
  public java.util.List<proto.broker.Module> getModulesList() {
    return modules_;
  }
  /**
   * <code>repeated .Module modules = 1;</code>
   */
  public java.util.List<? extends proto.broker.ModuleOrBuilder> 
      getModulesOrBuilderList() {
    return modules_;
  }
  /**
   * <code>repeated .Module modules = 1;</code>
   */
  public int getModulesCount() {
    return modules_.size();
  }
  /**
   * <code>repeated .Module modules = 1;</code>
   */
  public proto.broker.Module getModules(int index) {
    return modules_.get(index);
  }
  /**
   * <code>repeated .Module modules = 1;</code>
   */
  public proto.broker.ModuleOrBuilder getModulesOrBuilder(
      int index) {
    return modules_.get(index);
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    for (int i = 0; i < getModulesCount(); i++) {
      if (!getModules(i).isInitialized()) {
        memoizedIsInitialized = 0;
        return false;
      }
    }
    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    for (int i = 0; i < modules_.size(); i++) {
      output.writeMessage(1, modules_.get(i));
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < modules_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, modules_.get(i));
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
    if (!(obj instanceof proto.broker.GetModulesResponse)) {
      return super.equals(obj);
    }
    proto.broker.GetModulesResponse other = (proto.broker.GetModulesResponse) obj;

    if (!getModulesList()
        .equals(other.getModulesList())) return false;
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
    if (getModulesCount() > 0) {
      hash = (37 * hash) + MODULES_FIELD_NUMBER;
      hash = (53 * hash) + getModulesList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static proto.broker.GetModulesResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static proto.broker.GetModulesResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static proto.broker.GetModulesResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static proto.broker.GetModulesResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static proto.broker.GetModulesResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static proto.broker.GetModulesResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static proto.broker.GetModulesResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static proto.broker.GetModulesResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static proto.broker.GetModulesResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static proto.broker.GetModulesResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static proto.broker.GetModulesResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static proto.broker.GetModulesResponse parseFrom(
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
  public static Builder newBuilder(proto.broker.GetModulesResponse prototype) {
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
   * Protobuf type {@code GetModulesResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:GetModulesResponse)
      proto.broker.GetModulesResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return proto.broker.Broker.internal_static_GetModulesResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return proto.broker.Broker.internal_static_GetModulesResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              proto.broker.GetModulesResponse.class, proto.broker.GetModulesResponse.Builder.class);
    }

    // Construct using proto.broker.GetModulesResponse.newBuilder()
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
        getModulesFieldBuilder();
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      if (modulesBuilder_ == null) {
        modules_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        modulesBuilder_.clear();
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return proto.broker.Broker.internal_static_GetModulesResponse_descriptor;
    }

    @java.lang.Override
    public proto.broker.GetModulesResponse getDefaultInstanceForType() {
      return proto.broker.GetModulesResponse.getDefaultInstance();
    }

    @java.lang.Override
    public proto.broker.GetModulesResponse build() {
      proto.broker.GetModulesResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public proto.broker.GetModulesResponse buildPartial() {
      proto.broker.GetModulesResponse result = new proto.broker.GetModulesResponse(this);
      int from_bitField0_ = bitField0_;
      if (modulesBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0)) {
          modules_ = java.util.Collections.unmodifiableList(modules_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.modules_ = modules_;
      } else {
        result.modules_ = modulesBuilder_.build();
      }
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
      if (other instanceof proto.broker.GetModulesResponse) {
        return mergeFrom((proto.broker.GetModulesResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(proto.broker.GetModulesResponse other) {
      if (other == proto.broker.GetModulesResponse.getDefaultInstance()) return this;
      if (modulesBuilder_ == null) {
        if (!other.modules_.isEmpty()) {
          if (modules_.isEmpty()) {
            modules_ = other.modules_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureModulesIsMutable();
            modules_.addAll(other.modules_);
          }
          onChanged();
        }
      } else {
        if (!other.modules_.isEmpty()) {
          if (modulesBuilder_.isEmpty()) {
            modulesBuilder_.dispose();
            modulesBuilder_ = null;
            modules_ = other.modules_;
            bitField0_ = (bitField0_ & ~0x00000001);
            modulesBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getModulesFieldBuilder() : null;
          } else {
            modulesBuilder_.addAllMessages(other.modules_);
          }
        }
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      for (int i = 0; i < getModulesCount(); i++) {
        if (!getModules(i).isInitialized()) {
          return false;
        }
      }
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      proto.broker.GetModulesResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (proto.broker.GetModulesResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<proto.broker.Module> modules_ =
      java.util.Collections.emptyList();
    private void ensureModulesIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        modules_ = new java.util.ArrayList<proto.broker.Module>(modules_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        proto.broker.Module, proto.broker.Module.Builder, proto.broker.ModuleOrBuilder> modulesBuilder_;

    /**
     * <code>repeated .Module modules = 1;</code>
     */
    public java.util.List<proto.broker.Module> getModulesList() {
      if (modulesBuilder_ == null) {
        return java.util.Collections.unmodifiableList(modules_);
      } else {
        return modulesBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .Module modules = 1;</code>
     */
    public int getModulesCount() {
      if (modulesBuilder_ == null) {
        return modules_.size();
      } else {
        return modulesBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .Module modules = 1;</code>
     */
    public proto.broker.Module getModules(int index) {
      if (modulesBuilder_ == null) {
        return modules_.get(index);
      } else {
        return modulesBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .Module modules = 1;</code>
     */
    public Builder setModules(
        int index, proto.broker.Module value) {
      if (modulesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureModulesIsMutable();
        modules_.set(index, value);
        onChanged();
      } else {
        modulesBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .Module modules = 1;</code>
     */
    public Builder setModules(
        int index, proto.broker.Module.Builder builderForValue) {
      if (modulesBuilder_ == null) {
        ensureModulesIsMutable();
        modules_.set(index, builderForValue.build());
        onChanged();
      } else {
        modulesBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .Module modules = 1;</code>
     */
    public Builder addModules(proto.broker.Module value) {
      if (modulesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureModulesIsMutable();
        modules_.add(value);
        onChanged();
      } else {
        modulesBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .Module modules = 1;</code>
     */
    public Builder addModules(
        int index, proto.broker.Module value) {
      if (modulesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureModulesIsMutable();
        modules_.add(index, value);
        onChanged();
      } else {
        modulesBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .Module modules = 1;</code>
     */
    public Builder addModules(
        proto.broker.Module.Builder builderForValue) {
      if (modulesBuilder_ == null) {
        ensureModulesIsMutable();
        modules_.add(builderForValue.build());
        onChanged();
      } else {
        modulesBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .Module modules = 1;</code>
     */
    public Builder addModules(
        int index, proto.broker.Module.Builder builderForValue) {
      if (modulesBuilder_ == null) {
        ensureModulesIsMutable();
        modules_.add(index, builderForValue.build());
        onChanged();
      } else {
        modulesBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .Module modules = 1;</code>
     */
    public Builder addAllModules(
        java.lang.Iterable<? extends proto.broker.Module> values) {
      if (modulesBuilder_ == null) {
        ensureModulesIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, modules_);
        onChanged();
      } else {
        modulesBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .Module modules = 1;</code>
     */
    public Builder clearModules() {
      if (modulesBuilder_ == null) {
        modules_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        modulesBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .Module modules = 1;</code>
     */
    public Builder removeModules(int index) {
      if (modulesBuilder_ == null) {
        ensureModulesIsMutable();
        modules_.remove(index);
        onChanged();
      } else {
        modulesBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .Module modules = 1;</code>
     */
    public proto.broker.Module.Builder getModulesBuilder(
        int index) {
      return getModulesFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .Module modules = 1;</code>
     */
    public proto.broker.ModuleOrBuilder getModulesOrBuilder(
        int index) {
      if (modulesBuilder_ == null) {
        return modules_.get(index);  } else {
        return modulesBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .Module modules = 1;</code>
     */
    public java.util.List<? extends proto.broker.ModuleOrBuilder> 
         getModulesOrBuilderList() {
      if (modulesBuilder_ != null) {
        return modulesBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(modules_);
      }
    }
    /**
     * <code>repeated .Module modules = 1;</code>
     */
    public proto.broker.Module.Builder addModulesBuilder() {
      return getModulesFieldBuilder().addBuilder(
          proto.broker.Module.getDefaultInstance());
    }
    /**
     * <code>repeated .Module modules = 1;</code>
     */
    public proto.broker.Module.Builder addModulesBuilder(
        int index) {
      return getModulesFieldBuilder().addBuilder(
          index, proto.broker.Module.getDefaultInstance());
    }
    /**
     * <code>repeated .Module modules = 1;</code>
     */
    public java.util.List<proto.broker.Module.Builder> 
         getModulesBuilderList() {
      return getModulesFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        proto.broker.Module, proto.broker.Module.Builder, proto.broker.ModuleOrBuilder> 
        getModulesFieldBuilder() {
      if (modulesBuilder_ == null) {
        modulesBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            proto.broker.Module, proto.broker.Module.Builder, proto.broker.ModuleOrBuilder>(
                modules_,
                ((bitField0_ & 0x00000001) != 0),
                getParentForChildren(),
                isClean());
        modules_ = null;
      }
      return modulesBuilder_;
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


    // @@protoc_insertion_point(builder_scope:GetModulesResponse)
  }

  // @@protoc_insertion_point(class_scope:GetModulesResponse)
  private static final proto.broker.GetModulesResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new proto.broker.GetModulesResponse();
  }

  public static proto.broker.GetModulesResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<GetModulesResponse>
      PARSER = new com.google.protobuf.AbstractParser<GetModulesResponse>() {
    @java.lang.Override
    public GetModulesResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new GetModulesResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<GetModulesResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GetModulesResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public proto.broker.GetModulesResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

