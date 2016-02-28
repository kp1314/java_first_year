package dictionary;

import java.io.*;
import java.util.Random;
import dictionary.InsertComplexities.InstrumentedKey;

public class Main {

    private static final int MAX_SIZE = 500;
    private static final int REPITITIONS = 500;


    public static void main(String[] args) throws FileNotFoundException {

      Random random = new Random();
      InsertComplexities complexities = new InsertComplexities(random);

      Dictionary<InstrumentedKey, Integer> list1 = new OrderedLinkedList<>();

      int[] orderedLinkedListComplexities = complexities.getInsertComplexities(
          list1,MAX_SIZE,REPITITIONS);

      Dictionary<InstrumentedKey, Integer> list2 = new BinarySearchTree<>();

      int[] binarySearchTreeComplexities = complexities.getInsertComplexities(
          list2,MAX_SIZE,REPITITIONS);

      try {
        FileWriter binaryTreeData = new FileWriter("BinarySearchTree.dat");

        FileWriter orderedLinkedListData = new FileWriter("OrderedLinkedList.dat");

        for (int i = 0; i < MAX_SIZE; i++) {
          binaryTreeData.write(i + "\t" +
              binarySearchTreeComplexities[i] + "\n");
          System.out.println(i + "\t" +
              binarySearchTreeComplexities[i] + "\n");
          orderedLinkedListData.write(i + "\t" +
              orderedLinkedListComplexities[i] + "\n");

        }

        binaryTreeData.close();
        orderedLinkedListData.close();

      } catch (IOException e) {
        System.err.println("Can't write to this directory");
      }


    }

}
