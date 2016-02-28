public class SociableNumbers {

  public static void main(String[] args) {

    System.out.println("Please enter a number to classify:");
    int i = IOUtil.readInt();
    System.out.println(UnassessedRecursionLibrary.aliquotClassify(i));
  }
}
