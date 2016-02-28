package dictionary;

import dictionary.DictionaryEntry;

// Implementation class representing nodes of the binary search tree.
public class BinarySearchTreeEntry<K, V> implements DictionaryEntry<K, V> {

  private K key;
  private V value;
  private BinarySearchTreeEntry<K,V> leftNode;
  private BinarySearchTreeEntry<K,V> rightNode;

  public BinarySearchTreeEntry(K key, V value, BinarySearchTreeEntry<K,V>
      leftNode, BinarySearchTreeEntry<K,V> rightNode) {
    this.key = key;
    this.value = value;
    this.leftNode = leftNode;
    this.rightNode = rightNode;
  }

  @Override
  public K getKey() {
    return key;
  }

  @Override
  public V getValue() {
    return value;
  }

  void setValue(V value) {
    this.value = value;
  }

  public BinarySearchTreeEntry<K,V> getLeftNode() {
    return leftNode;
  }

  public BinarySearchTreeEntry<K,V> getRightNode() {
    return rightNode;
  }

  void setLeftNode(BinarySearchTreeEntry<K,V> newNode) {
    leftNode = newNode;
  }

  void setRightNode(BinarySearchTreeEntry<K,V> newNode) {
    rightNode = newNode;
  }

}
