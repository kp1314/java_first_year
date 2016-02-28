

public class SociableNumbersTest {

  public static void main(String[] args) {
    check(6, "perfect");
    check(220, "amicable");
    check(1264460, "sociable");
    check(95, "aspiring");
  }
  
  private static void check(int val, String expected) {
    String actual = UnassessedRecursionLibrary.aliquotClassify(val); 
    if(!expected.equals(actual)) {
      System.out.println("Failed " + val + " - expected: " + expected + ", but got: " + actual);
    }
  }
}
