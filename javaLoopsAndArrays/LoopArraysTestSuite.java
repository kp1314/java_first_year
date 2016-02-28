import java.util.Arrays;

public class LoopArraysTestSuite {

    public static void main(String[] args) {
        System.out.println("Running tests...");

        fibTests();
        maximumTests();
        frequencyTableTests();
        matrixMultiplyTests();

        System.out.println("...tests complete.");
    }

    public static void fibTests() {
        testFib(5, 5);
        testFib(0, 0);
        testFib(1, 1);
        testFib(6, 8);
        testFib(42, 267914296);
    }

    public static void maximumTests() {
        testMaximum(new int[] { 1, 3, 2 }, 3);
        testMaximum(new int[] { 1 }, 1);
        testMaximum(new int[] { 0, 0, 0, 1 }, 1);
        testMaximum(new int[] { 300, 1000, 2, 1000, 300 }, 1000);
        testMaximum(new int[] { 5, 5, 5, 5, 5 }, 5); 
        testMaximum(new int[] { -1, -5, -1, -3, -4 }, -1);
    }

    public static void frequencyTableTests() {
        testFrequencyTable(-1.5, 1.5, 6
                          ,new double[] { -1.1, 1.1, 1.2, -0.8, 1.6 }
                          ,new int[] { 1, 1, 0, 0, 0, 2 });
        testFrequencyTable(-2.0,2.0,4
                          ,new double[] { -1.3, 5.0, 2.0, 1.1, 1.0, -0.5, 1.5, 0.4}                          ,new int[] { 1, 1, 1, 3 });
    }

    public static void matrixMultiplyTests() {
    // checks if matricies also multiply correctly the other way round
        testMatrixMultiply(new double[][] { { 1, 2, 3, 4 }
                                          , { 5, 6, 7, 8 }
                                          , { 9, 1, 2, 3 }
                                          }
                          ,new double[][] { { 1, 2, 3 }
                                          , { 4, 5, 6 }
                                          , { 7, 8, 9 }
                                          , { 1, 2, 3 }
                                          }
                          , new double[][] { { 34, 44, 54 }
                                           , { 86, 112, 138 }
                                           , { 30, 45, 60 } });

        testMatrixMultiply(new double[][] { { 1, 2, 3 }
                                          , { 4, 5, 6 }
                                          }
                          ,new double[][] { { 7, 8 }
                                          , { 9, 10 }
                                          , { 11, 12 }
                                          }
                          , new double[][] { { 58, 64 }
                                          , { 139, 154 } });
        
        testMatrixMultiply(new double[][] { { 1, 2 }
                                          , { 3, 4 }
                                          }
                          ,new double[][] { { 2, 0 }
                                          , { 1, 2 }
                                          }
                          , new double[][] { { 4, 4 }
                                          , { 10, 8 } }); 
        
        testMatrixMultiply(new double[][] { { 2, 0 }
                                          , { 1, 2 }
                                          }
                          ,new double[][] { { 1, 2 }
                                          , { 3, 4 }
                                          }
                          , new double[][] { { 2, 4 }
                                          , { 7, 10 } });
     }

    private static final double EPSILON = 0.001;

    private static boolean approxEqualDouble(double a, double b) {
      return a == b
          || Math.abs(a - b) / Math.max(Math.abs(a), Math.abs(b)) < EPSILON;
    }

    private static boolean approxEqualDoubleArrays(double[][] x, double[][] y) {
   
      for (int i = 0; i < x.length; i++) {
        for (int j = 0; j < y[0].length; j++) { 
           if (!approxEqualDouble(x[i][j], y[i][j])) {
             return false;
           } 
        }
      }
      return true;
    }
 
    private static void testFib(int n, int expected) {
        int actual = LoopArraysLibrary.fib(n);
        if (expected != actual) {
            System.out.println("fib(" + n + "); expected: " + expected
                    + " got: " + actual);
        }
    }

    private static void testMaximum(int[] vs, int expected) {
        int actual = LoopArraysLibrary.maximum(vs);
        if (expected != actual) {
            System.out.println("maximum(" + Arrays.toString(vs)
                    + "); expected: " + expected + " got: " + actual);
        }
    }

    private static void testFrequencyTable(double minimum, double maximum,
            int numBuckets, double[] data, int[] expected) {
        int[] actual = LoopArraysLibrary.frequencyTable(minimum, maximum,
                numBuckets, data);

        if (!Arrays.equals(expected, actual)) {
            System.out.println("frequencyTable(" + minimum + ", " + maximum
                    + ", " + numBuckets + ", " + Arrays.toString(data)
                    + "); expected: " + Arrays.toString(expected) + " got: "
                    + Arrays.toString(actual));
        }
    }

    private static void testMatrixMultiply(double[][] m, double[][] n,
            double[][] expected) {
        double[][] actual = LoopArraysLibrary.matrixMultiply(m, n);

        if (!approxEqualDoubleArrays(expected, actual)) {
            System.out.println("matrixMultiply(" + Arrays.deepToString(m)
                    + ", " + Arrays.deepToString(n) + "); expected: "
                    + Arrays.deepToString(expected) + ", got: "
                    + Arrays.deepToString(actual));
        }
    }

}
