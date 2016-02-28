package dictionary;

import java.util.*;

/*
 * Binary search tree based implementation of the Dictionary
 * interface. The nodes of the tree are ordered by an associated key-attribute
 * key of type K, such that every node's left subtree contains only nodes 
 * whose key-attributes are less than key, and every node's right subtree
 * contains only nodes whose key-attributes are greater than key. A
 * linear order is defined on keys through the Comparable interface.
 * Duplicate keys are not permitted.
 */
public class BinarySearchTree<K extends Comparable<? super K>, V> implements
        Dictionary<K, V> {

  private BinarySearchTreeEntry<K, V> root;
  private int numOfElements = 0;
  private int initialModCounter = 0;

  public BinarySearchTree() {
    this.root = null;
  }

  @Override
  public int size() {
    return numOfElements;
  }

  @Override
  public boolean isEmpty() {
    return (root == null);
  }

  @Override
  public V get(K key) throws NoSuchElementException {
    BinarySearchTreeEntry<K,V> valueFound = getElemInTree(root, key);
    if (valueFound != null) {
      return valueFound.getValue();
    }
      throw new NoSuchElementException("There is no element in the tree with " +
          "key " + key + ".");
  }

  private BinarySearchTreeEntry<K,V> getElemInTree(BinarySearchTreeEntry<K,V>
      currentNode, K key) {
    if (currentNode != null) {
      K newKey = currentNode.getKey();
      if (newKey == key) {
        return currentNode;
      } else if (key.compareTo(newKey) < 0) {
        return getElemInTree(currentNode.getLeftNode(), key);
      } else {
        return getElemInTree(currentNode.getRightNode(), key);
      }
    } else {
      return null;
    }
  }

  @Override
  public void put(K key, V value) {
    root = putElemInTree(root, key, value);
    initialModCounter++;
  }

  private BinarySearchTreeEntry<K, V> putElemInTree(BinarySearchTreeEntry<K, V>
                                                        currentNode, K key, V value) {
    if (currentNode == null) {
      currentNode = new BinarySearchTreeEntry<>(key, value, null, null);
      numOfElements++;
    } else if (key.equals(currentNode.getKey())) {
      currentNode.setValue(value);
    } else if (key.compareTo(currentNode.getKey()) < 0) {
      currentNode.setLeftNode(putElemInTree(currentNode.getLeftNode(),
          key, value));
    } else {
      currentNode.setRightNode(putElemInTree(currentNode.getRightNode(),
          key, value));
    }
    return currentNode;
  }

  @Override
  public void remove(K key) throws NoSuchElementException {
    root = removeElemInTree(root, key);
    initialModCounter++;
  }

  private BinarySearchTreeEntry<K, V> removeElemInTree(BinarySearchTreeEntry<K, V>
                                                           currentNode, K key) {
    if (currentNode == null) {
      throw new NoSuchElementException("Key not found it the tree");
    } else if (key.equals(currentNode.getKey())) {
      numOfElements--;
      deleteNode(currentNode);
    } else if (key.compareTo(currentNode.getKey()) < 0) {
      currentNode.setLeftNode(removeElemInTree(currentNode.getLeftNode(),
          key));
    } else {
      currentNode.setRightNode(removeElemInTree(currentNode.getRightNode(),
          key));
    }
    return currentNode;
  }

  private BinarySearchTreeEntry<K, V> deleteNode(BinarySearchTreeEntry<K, V>
                                                     nodeToDelete) {

    if (nodeToDelete.getLeftNode() == null && nodeToDelete.getRightNode() == null) {
      return null;
    }
    if (nodeToDelete.getLeftNode() == null) {
      return nodeToDelete.getLeftNode();
    }
    if (nodeToDelete.getRightNode() == null) {
      return nodeToDelete.getRightNode();
    }

    BinarySearchTreeEntry<K, V> replacementNode = findMostLeft(
        nodeToDelete.getRightNode());
    BinarySearchTreeEntry<K, V> newRight = deleteLeftMost(
        nodeToDelete.getRightNode());

    replacementNode.setRightNode(newRight);
    replacementNode.setLeftNode(nodeToDelete.getLeftNode());
    return replacementNode;
  }

  private BinarySearchTreeEntry<K, V> findMostLeft(BinarySearchTreeEntry<K, V> nodeToDelete) {
    if (nodeToDelete.getLeftNode() == null) {
      return nodeToDelete;
    }
    return findMostLeft(nodeToDelete.getLeftNode());
  }

  private BinarySearchTreeEntry<K, V> deleteLeftMost(BinarySearchTreeEntry<K, V>
                                                         nodeToDelete) {
    if (nodeToDelete.getLeftNode() == null) {
      return nodeToDelete.getRightNode();
    }
    BinarySearchTreeEntry<K, V> newChild = deleteLeftMost(
        nodeToDelete.getLeftNode());
    nodeToDelete.setLeftNode(newChild);
    return nodeToDelete;
  }

  @Override
  public void clear() {
    numOfElements = 0;
    initialModCounter++;
    root = null;
  }

  @Override
  public Iterator<DictionaryEntry<K, V>> iterator() {
    return new Iterator<DictionaryEntry<K,V>>() {

      private Stack<BinarySearchTreeEntry<K,V>> workList = new Stack<>();
      private BinarySearchTreeEntry<K, V> current = root;
      private int currentModCounter = numOfElements;

      @Override
      public boolean hasNext () {
        return (current != null || !workList.isEmpty());
      }

      @Override
      public DictionaryEntry<K,V> next () {
        BinarySearchTreeEntry<K, V> RESULT = null;
        if (currentModCounter != initialModCounter) {
          throw new ConcurrentModificationException("Can't modify whilst" +
              " iterating");
        }
        while (current != null) {
          workList.push(current);
          current = current.getLeftNode();
        }
        if (!workList.isEmpty()) {
          RESULT = workList.pop();
          current = RESULT.getRightNode();
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
