public class UnassesedSimpleLibrary {
  
  public static int nextEven(int i) {
   // gives next even number after inputted number
   if (i % 2 == 0) {
      return (i + 2);
   } else {
      return (i + 1);
   }
  } 

  public static boolean isEven(int j) {
   // returns true if j is even  	  
   return (j%2 == 0);
  }

  public static double fahrenheitToCelcius(int n) {
   // changes temp from Farenheit to Celcius
   return (n-32) * (5/9);
  }
  
  public static double milesToMeters(double m) { 
   // changes a distance in miles to meters
   assert m>=0 : "distance is posotive";
   return m*1609.344;
  }
  
  private static final boolean isInASCIIRange(char c, char d, char e) {
   // finds if a given character is in a certain range of characters
   return (c >= d && c <= e);
  }  

  public static boolean isASCIIUpperCase(char c) {
   // returns true if input is upper case
   return isInASCIIRange (c, 'A', 'Z');
  }
  
  public static boolean isASCIILowerCase(char c) {
   // returns true if input is lowercase
   return isInASCIIRange (c, 'a', 'z');
  }
  
  public static boolean isASCIINumber(char c) { 
   // returns true if the input is a Number
   return isInASCIIRange (c, '0', '9');
  }
  public static int letterIndex(char c) {
   // returns the index of the character
   assert (isASCIIUpperCase (c)||isASCIILowerCase (c)): "must be either" +
   " uppercase or lowercase character in ASCII table";
   if (isASCIIUpperCase (c)) {
      return c - 'A';
   } else {
      return c - 'a';
   }
  }
  
  public static int digitCharToInt(char a) {
   // returs the interger representation of the inputted char
   return int a;
  }
} 
