// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: broker.proto

package proto.broker;

public interface HandshakeResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:broker.HandshakeResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>required bool ok = 1;</code>
   * @return Whether the ok field is set.
   */
  boolean hasOk();
  /**
   * <code>required bool ok = 1;</code>
   * @return The ok.
   */
  boolean getOk();

  /**
   * <code>optional int64 givenId = 2;</code>
   * @return Whether the givenId field is set.
   */
  boolean hasGivenId();
  /**
   * <code>optional int64 givenId = 2;</code>
   * @return The givenId.
   */
  long getGivenId();
}
