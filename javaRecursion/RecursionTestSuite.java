public class RecursionTestSuite {

  public static void main(String[] args) {
    System.out.println("Running tests...");

    gcdTests();
    isPrimeTests();
    sumSquareDigitsTests();
    isHappyTests();
    isCarmichaelTests();

    System.out.println("...tests complete.");
  }


  public static void gcdTests() {
    checkGCD(12, 16, 4);
    checkGCD(3456, 66, 6);
    checkGCD(1, -1, 1);
    checkGCD(56, 8, 8);
    checkGCD(-4,-12, 4);
  }


  public static void isPrimeTests() {
    checkIsPrime(9, false);
    checkIsPrime(7919, true);
    checkIsPrime(1000003, true);
    checkIsPrime(1, false);
    checkIsPrime(2, true);
  }


  public static void sumSquareDigitsTests() {
    checkSumSquareDigits(10, 1);
    checkSumSquareDigits(103, 10);
    checkSumSquareDigits(529, 110); 
    checkSumSquareDigits(003, 9);
  }


  public static void isHappyTests() {
    checkIsHappy(397, true);
    checkIsHappy(123, false);
    checkIsHappy(10771, true);
    checkIsHappy(1, true);
    checkIsHappy(7, true);
  }


  public static void isCarmichaelTests() {
    checkIsCarmichael(1, false);
    checkIsCarmichael(60, false);
    checkIsCarmichael(3, false);
    checkIsCarmichael(561, true);
    checkIsCarmichael(1105, true);
    checkIsCarmichael(1729, true);
    checkIsCarmichael(8911, true);
  }


  private static void checkGCD(int x, int y, int expected) {
    int actual = RecursionLibrary.greatestCommonDivisor(x, y);
    if (actual != expected) {
      System.out.println("greatestCommonDivisor(" + x + ", " + y + "); expected: "
          + expected + ", got: " + actual);
    }
  }


  private static void checkIsPrime(int value, boolean expected) {
    boolean actual = RecursionLibrary.isPrime(value);
    if (actual != expected) {
      System.out.println("isPrime(" + value + "); expected: " + expected
          + ", got: " + actual);
    }
  }


  private static void checkSumSquareDigits(int n, int expected) {
    int actual = RecursionLibrary.sumSquareDigits(n);
    if (actual != expected) {
      System.out.println("sumSquareDigits(" + n + "); expected: "
          + expected + ", got: " + actual);
    }
  }


  private static void checkIsHappy(int n, boolean expected) {
    boolean actual = RecursionLibrary.isHappy(n);
    if (actual != expected) {
      System.out.println("isHappy(" + n + "); expected: " + expected
          + ", got: " + actual);
    }
  }

  
  private static void checkIsCarmichael(int n, boolean expected) {
    boolean actual = RecursionLibrary.isCarmichael(n);
    if (actual != expected) {
       System.out.println("isCarmichael(" + n + "); expexted: " + expected 
          + ", got: " + actual);
     }
   }
}
