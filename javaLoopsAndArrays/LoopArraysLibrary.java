public class LoopArraysLibrary {

  public static int fib(int n) {
  // pre: Gives the nth fibonacci number:
  assert n>=0: "Please give a number >= 0";
    if (n == 1) {
      return 1;
    } 
    int a = 0; 
    int b = 1; 
    int sum = 0;
      for (int i = 1; i < n; i++) {
        sum = a + b;
        a = b;
        b = sum; 
      }
        return sum;
  }

  public static int maximum(int[] ms) {
  //post: Gives the largest element in an array:
  assert ms.length > 0: "Array must have at least one elem";
    if (ms.length == 1) {
      return ms[0];
    }
    int biggestNumSoFar = ms[0];
      for (int i = 1; i < ms.length; i++) {
        if (ms[i] > biggestNumSoFar) {
          biggestNumSoFar = ms[i];
        }
      }   
        return biggestNumSoFar;
  }

  public static int[] frequencyTable(double minimum, double maximum,
      int numBuckets, double[] data) {
  /** post: Gives the frequency table for the given interval
   ** with a certain amount of ranges inbetween: ***/ 
    double interval = ((maximum - minimum)/ numBuckets);
    int [] ft = new int [numBuckets];
      for (int i = 0; i < (data.length); i++){
        double index = Math.floor((data [i] - minimum)/(interval)); 
          if (index < numBuckets) { 
            ft[(int)(index)] = ft[(int)(index)] + 1;
          }
      }
      return ft;
  }
 
  public static double[][] matrixMultiply(double[][] m, double[][] n) {
  // Multiplies 2x2 matricies together:
  assert m != null || n != null: "not multiplying empty arrays";
  assert m[0].length == n.length: "number of collums in m == rows in n";
    double [][] newMatrix = new double [m.length][n[0].length]; 
      for (int k = 0; k < m.length; k++) {
        for (int i = 0; i < m.length; i++) {
          for (int j = 0; j < n.length; j++) {
            newMatrix[i][k] = newMatrix[i][k] + (m[i][j] * n[j][k]);
          }
        }
      }
        return newMatrix;
  }
}
