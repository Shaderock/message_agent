// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: broker.proto

package proto.broker;

public interface SubscribeRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:broker.SubscribeRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>required int64 idSubscriber = 1;</code>
   * @return Whether the idSubscriber field is set.
   */
  boolean hasIdSubscriber();
  /**
   * <code>required int64 idSubscriber = 1;</code>
   * @return The idSubscriber.
   */
  long getIdSubscriber();

  /**
   * <code>repeated int64 id = 2;</code>
   * @return A list containing the id.
   */
  java.util.List<java.lang.Long> getIdList();
  /**
   * <code>repeated int64 id = 2;</code>
   * @return The count of id.
   */
  int getIdCount();
  /**
   * <code>repeated int64 id = 2;</code>
   * @param index The index of the element to return.
   * @return The id at the given index.
   */
  long getId(int index);
}
