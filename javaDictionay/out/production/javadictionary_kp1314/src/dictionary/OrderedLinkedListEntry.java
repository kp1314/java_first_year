package dictionary;

public class OrderedLinkedListEntry<K, V> implements DictionaryEntry<K, V> {

  private K key;
  private V value;
  private OrderedLinkedListEntry<K,V> currentNextNode;

  public OrderedLinkedListEntry(K key, V value, OrderedLinkedListEntry<K,V>
      currentNextNode) {
   this.key = key;
   this.value = value;
   this.currentNextNode = currentNextNode;
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

  OrderedLinkedListEntry<K,V> getNextNode() {
    return currentNextNode;
  }

  void setNextNode(OrderedLinkedListEntry<K,V> newNextNode) {
    currentNextNode = newNextNode;
  }

}
