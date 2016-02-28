public class RecursionLibrary {

  public static int greatestCommonDivisor(int x, int y) {
    
    int abX = Math.abs(x);
    int abY = Math.abs(y);

   // post: returns the greatest common divisor of x and y
   if (abX>0 && abY>0) {
      return (greatestCommonDivisor(abY, abX%abY));
   }
      return x;
  }
  

  private static boolean isPrimeHelper(int a, int b) {
    /* helps the isPrime function by holding numbers 
     upto the square root of the input */
    if (b > Math.sqrt(a)) {
       return true;
    } else if (a%b == 0) {
       return false;
    } else {
       return isPrimeHelper (a, b+1);
    }
  }
     
 
  public static boolean isPrime(int n) {
   // post: tells you if a number is Prime 
   assert (n>0): "number must be posotive";
    if (n == 2) {
      return true;
   } else if ((n<2)|(n%2==0)) {
      return false; 
   } else {
      return isPrimeHelper (n, 2);
    }
  }


  public static int sumSquareDigits(int n) {
    /* post: returns the sum of the square of 
    digits of a number.*/
    assert (n>=0): "this function is not applicable to" +
    " negative numbers";
    if (n < 10) {
       return n*n;
    } else { 
       return (n%10)*(n%10) + sumSquareDigits(n/10);
    } 
  }


  public static boolean isHappy(int n) {
    // post: returns true if a number is happy
    return isHappyHelper (sumSquareDigits(n), n, 0, 1);
  }

  
  private static boolean isHappyHelper(int n, int r, int t, int nt) {
    /* remembers the last number in the sequence for a 
       certain amount of steps then discards it */
    if (n == 1) {
       return true;
    } else if (n == r) {
       return false;
    } else if (t > 0) {
       return isHappyHelper(sumSquareDigits (n), r, (t-1), nt);
    } else if (t==0) {
       return isHappyHelper(sumSquareDigits (n), n, nt, (nt+1));
    } else {
       return false;
    }
  }


  public static boolean isCarmichael (int n) {
    // Tells you if a number is Carmichael using 
    // fermatTest
    if ((isPrime(n)) || (n == 1)) {
       return false;
    } else {
       return fermatTest (n-1, n);
    }
  }


  private static boolean fermatTest (int m, int n) {
    // Checks if a number passes the fermat test
    // using modPow.
    if (m<2) { 
       return true;
    } else if (modPow (m, n, n) == m) {
       return fermatTest (m-1, n);
    } else {
       return false;
    }
  }

  
  private static int modPow (int a, int b, int m) {
    /* Uses simplify function to repeatedly break down 
    a given number so that the modPow function can
    compute it*/
    return (simplify (a, b, m)) % m;
  }


  private static int simplify(int s, int t, int m) {
    // breaks down a number by recursive division and 
    // taking a modulus
    if (t<3) {
       return (int)Math.pow(s,t);
    } else if (t%2 !=0) {
       return (s%m)*((simplify(s, (t-1), m)) %m);
    } else {
       return (int)Math.pow(((simplify (s, (t/2), m))%m), 2);
    }
  }
}
