// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: module.proto

package proto.module;

public interface MessageRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:module.MessageRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>required int64 idSender = 1;</code>
   * @return Whether the idSender field is set.
   */
  boolean hasIdSender();
  /**
   * <code>required int64 idSender = 1;</code>
   * @return The idSender.
   */
  long getIdSender();

  /**
   * <code>required string message = 2;</code>
   * @return Whether the message field is set.
   */
  boolean hasMessage();
  /**
   * <code>required string message = 2;</code>
   * @return The message.
   */
  java.lang.String getMessage();
  /**
   * <code>required string message = 2;</code>
   * @return The bytes for message.
   */
  com.google.protobuf.ByteString
      getMessageBytes();
}
