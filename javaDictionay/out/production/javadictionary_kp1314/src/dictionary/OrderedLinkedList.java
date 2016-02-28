package dictionary;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/*
 * Ordered linked list based implementation of the Dictionary
 * interface. The nodes of the list are ordered in ascending order by
 * the key-attribute of type K. Duplicate keys are not permitted.
 */
public class OrderedLinkedList<K extends Comparable<? super K>, V> implements
        Dictionary<K, V> {

  private int numberOfElements = 0;
  private OrderedLinkedListEntry<K, V> head;
  private int initialModCount = 0;

  public OrderedLinkedList() {
    this.head = null;
  }

  @Override
  public int size() {
    return numberOfElements;
  }

  @Override
  public boolean isEmpty() {
    return (head == null);
  }

  @Override
  public V get(K key) throws NoSuchElementException {
    OrderedLinkedListEntry<K, V> prev = head;

    if (prev == null) {
      throw new NoSuchElementException("HI");
    } else if (prev.getKey().equals(key)) {
      return prev.getValue();
    } else {
      OrderedLinkedListEntry<K, V> curr = prev.getNextNode();
      while (curr != null && (curr.getKey().compareTo(key) < 0)) {
        prev = curr;
        curr = curr.getNextNode();
      }
      if (curr != null && curr.getKey().equals(key)) {
        return curr.getValue();
      } else {
        throw new NoSuchElementException("No element with key " + key + ".");
      }
    }

  }

  @Override
  public void put(K key, V value) {

    OrderedLinkedListEntry<K,V> prev = findPrevNode(key);
    OrderedLinkedListEntry<K,V> entry = new OrderedLinkedListEntry<>(key,value,null);

    initialModCount++;

    if(head == null) {
      head = entry;
      numberOfElements++;
    } else if (key.equals(prev.getKey())) {
      prev.setValue(value);
    } else if (prev.getKey().compareTo(key) < 0) {
      entry.setNextNode(prev.getNextNode());
      prev.setNextNode(entry);
      numberOfElements++;
    } else {
      entry.setNextNode(head);
      head = entry;
      numberOfElements++;
    }

  }

  private OrderedLinkedListEntry<K,V> findPrevNode(K key) {
    OrderedLinkedListEntry<K,V> prev = head;
    if(prev != null && prev.getKey().compareTo(key) < 0) {
      OrderedLinkedListEntry<K,V> curr = prev.getNextNode();
      while (curr != null && curr.getKey().compareTo(key) <= 0) {
        prev = curr;
        curr = curr.getNextNode();
      }
    }
    return prev;
  }

  @Override
  public void remove(K key) throws NoSuchElementException {
    OrderedLinkedListEntry<K, V> prev = head;
    initialModCount++;

    if (prev == null) {
      throw new NoSuchElementException("There is no element with key " + key
          + "." );
    } else if (prev.getKey().equals(key)) {
      head = head.getNextNode();
      numberOfElements--;
    } else {
      OrderedLinkedListEntry<K, V> curr = prev.getNextNode();
      while (curr != null && (key.compareTo(curr.getKey()) < 0)) {
        prev = curr;
        curr = curr.getNextNode();
      }
      if (curr != null && key.equals(curr.getKey())) {
        numberOfElements--;
        prev.setNextNode(curr.getNextNode());
      }
    }

  }

  @Override
  public void clear() {
    head = null;
    numberOfElements = 0;
    initialModCount++;
  }

  @Override
  public Iterator<DictionaryEntry<K, V>> iterator() {
    return new Iterator<DictionaryEntry<K,V>>() {

      private OrderedLinkedListEntry<K, V> current = head;
      private int currrentModCount = initialModCount;

      @Override
      public boolean hasNext () {
        return (current != null);
      }

      @Override
      public DictionaryEntry<K,V> next () {
        OrderedLinkedListEntry<K, V> RESULT = null;
        if (currrentModCount != initialModCount) {
          throw new ConcurrentModificationException("Can't modify whilst" +
              " iterating");
        }
        if (current != null) {
          RESULT = current;
          current = current.getNextNode();
        }
        return RESULT;
      }

      @Override
      public void remove () {
        throw new UnsupportedOperationException();
      }
    };
  }

}
