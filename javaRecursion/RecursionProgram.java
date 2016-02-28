public class RecursionProgram {

  public static void main(String[] args) {

    System.out.println("Please enter two positive numbers separated by a space: ");
    int x = IOUtil.readInt();
    int y = IOUtil.readInt();

    int gcd = RecursionLibrary.greatestCommonDivisor(x, y);

    System.out.println("The gcd of " + x + " and " + y + " is: " + gcd
        + ".");
    
    printDetails(x);
    printDetails(y);
   

    System.out.println("Would you like to see a lot of happy primes?" +
    " (enter 1 for yes or 0 for no)");
    int z = IOUtil.readInt();
    if(z == 0) { 
      System.out.println("Ok goodbye your loss :)");
    } else if (z == 1) {
      printHappyPrimes(7);
      // start with 7 as it's the first happy prime number
     }
    }


  private static void printDetails(int n) {
    if (RecursionLibrary.isPrime(n)) {
      System.out.println(n + " is prime.");
    }

    int ssds = RecursionLibrary.sumSquareDigits(n);
    System.out.println("The sum of the digits squared in " + n + " is: "
        + ssds);

    if (RecursionLibrary.isHappy(n)) {
      System.out.println(n + " is happy.");
    }

    if (RecursionLibrary.isCarmichael(n)) {
      System.out.println(n + " is a Carmichael number!");
    }
       return;
  }

  
  private static void printHappyPrimes(int n) {
    //calls printHappyPrimes recursively   
    if (RecursionLibrary.isPrime(n) && RecursionLibrary.isHappy(n)) { 
      System.out.println(n + " is happy to be prime");
    } 
    if (n > 10771) {
    /* tried doing a recursive call of the function infinitely
     however a stack overflow occured at any number greater than
     10771 */
      return; 
    } 
      printHappyPrimes (n+1);
  }
}
