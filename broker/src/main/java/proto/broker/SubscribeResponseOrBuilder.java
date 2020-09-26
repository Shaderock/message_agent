// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: broker.proto

package proto.broker;

public interface SubscribeResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:SubscribeResponse)
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
   * <code>repeated int64 wrongIds = 2;</code>
   * @return A list containing the wrongIds.
   */
  java.util.List<java.lang.Long> getWrongIdsList();
  /**
   * <code>repeated int64 wrongIds = 2;</code>
   * @return The count of wrongIds.
   */
  int getWrongIdsCount();
  /**
   * <code>repeated int64 wrongIds = 2;</code>
   * @param index The index of the element to return.
   * @return The wrongIds at the given index.
   */
  long getWrongIds(int index);
}
