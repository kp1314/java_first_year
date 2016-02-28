public class SimpleTests {

  public static void main(String[] args) {

    checkInts("2 is next even after 0", UnassessedSimpleLibrary.nextEven(0), 2);
    checkInts("2 is next even after 1", UnassessedSimpleLibrary.nextEven(1), 2);
    checkInts("2 is next even after 4", UnassessedSimpleLibrary.nextEven(2), 4);

    checkBoolean("0 is even",      UnassessedSimpleLibrary.isEven(0));
    checkBoolean("1 is not even", !UnassessedSimpleLibrary.isEven(1));
    checkBoolean("2 is even",      UnassessedSimpleLibrary.isEven(2));

    checkDoubles("32.0F in C",
        UnassessedSimpleLibrary.fahrenheitToCelsius(32.0), 0.0);
    checkDoubles("100.0F in C",
        UnassessedSimpleLibrary.fahrenheitToCelsius(100.0), 37.77777777777778);

    checkDoubles("100 miles in m",
        UnassessedSimpleLibrary.milesToMeters(100.0), 160934.4);
    checkDoubles("32 miles in m",
        UnassessedSimpleLibrary.milesToMeters(32.0), 51499.008);

    checkBoolean("'A' is an uppercase character",
        UnassessedSimpleLibrary.isASCIIUpperCase('A'));
    checkBoolean("'a' is not an uppercase character",
        !UnassessedSimpleLibrary.isASCIIUpperCase('a'));

    checkBoolean("'a' is a lowercase character",
        UnassessedSimpleLibrary.isASCIILowerCase('a'));
    checkBoolean("'A' is not a lowercase character",
        !UnassessedSimpleLibrary.isASCIILowerCase('A'));

    checkBoolean("'0' is an ASCII number",
        UnassessedSimpleLibrary.isASCIINumber('0'));
    checkBoolean("'8' is an ASCII number",
        UnassessedSimpleLibrary.isASCIINumber('8'));
    checkBoolean("'a' is not an ASCII number",
        !UnassessedSimpleLibrary.isASCIINumber('a'));

    checkInts("'a''s index", UnassessedSimpleLibrary.letterIndex('a'), 0);
    checkInts("'z''s index", UnassessedSimpleLibrary.letterIndex('z'), 25);

    checkInts("'B''s index", UnassessedSimpleLibrary.letterIndex('B'), 1);
    checkInts("'Y''s index", UnassessedSimpleLibrary.letterIndex('Y'), 24);

    checkInts("'0's representation",
        UnassessedSimpleLibrary.digitCharToInt('0'), 0);
    checkInts("'8's representation",
        UnassessedSimpleLibrary.digitCharToInt('8'), 8);

  }


  private static void checkInts(String message, int actual, int expected) {
    if (expected != actual) {
      System.out.println(message + ", expected: " + expected + ", got: " + actual);
    }
  }

  private static void checkBoolean(String message, boolean actual) {
    if (!actual) {
      System.out.println(message + ", got false.");
    }
  }

  public static void checkDoubles(String message, double actual, double expected) {
    if (expected != actual) {
      System.out.println(message + ", expected: " + expected + ", got: " + actual);
    }
  }

}
