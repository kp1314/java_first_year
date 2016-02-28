public class LoopArraysProgram {
  
  /**This constant is the length of the String "'s Histogram"
   **which is used later when looking at spacing in both the 
   **main and some private functions**/
  public static final int histLeng = 12;

  public static void main(String[] args) {
    
    System.out.println("Please enter your name followed by "
        + "the size of your data set:");

    String name = IOUtil.readString();
    int n = IOUtil.readInt();

    System.out.println("Please enter the " + n + 
        " numbers you want to visualize in a histogram:");

    double array [] = new double [n];
      for (int i = 0; i < n; i++) { 
        array[i] = IOUtil.readDouble();
      }

    System.out.println("Please enter a minimum, maximum and number of "
        + "buckets to print a histogram with:");

    double min = IOUtil.readDouble();
    double max = IOUtil.readDouble();
    int buckets = IOUtil.readInt();
    
    int [] freqtab = LoopArraysLibrary.frequencyTable(min, max, buckets, array);
    int biggest = LoopArraysLibrary.maximum(freqtab);

    System.out.print("\n");
    
    /** This whole if loop centers the title to the histogram if the 
     ** number of buckets is > than the length of the title and centers
     ** the histogram to the title otherwise, just makes it look nice
     ** in my opinion.**/
    if (buckets > ((name.length() + histLeng))) {
    //Moves title in line with histogram.
      for (int k = 0; k <= ((buckets-(name.length() + histLeng))/2); k++) {
        System.out.print(" ");
      }
      System.out.println(name + "'s Histogram\n");
        for (int i = biggest+1; i > 0; i--) {
          System.out.println("|" + rest(freqtab, i));
        }    
          for (int j = 0; j <= buckets; j++) {
            if (j == 0) {
              System.out.print("+");
            } else {
              System.out.print("-");
            }
          }
    } else {
    //Moves histogram in line with title. 
      System.out.println(name + "'s Histogram\n");
        for (int i = biggest+1; i > 0; i--) {
         System.out.println(spaces(name.length(), buckets)
            + "|" + rest(freqtab, i));
        }
      System.out.print(spaces(name.length(), buckets));
        for (int j = 0; j <= buckets; j++) {
          if (j == 0) {
            System.out.print("+");
          } else {
            System.out.print("-");
          }
        }
    }
      
    System.out.println("\n");    
    System.out.println("Thanks, enjoy your histogram :)\n");
  }   
  
  private static String spaces(int n, int b) {
  // finds number of spaces to put behind the bars of the histogram:
    String spaceHolder = "";
    
    for (int k = 0; k <= (((n + histLeng)-b)/2); k++) {
      spaceHolder += (" ");
    } return spaceHolder;
  }
  
  private static String rest(int[] ys, int i) {
  // Determins whether to print a space or hash in each line:
    String stringHolder = "";

    for (int j = 0; j < ys.length; j++) {
      if (ys[j] < i) {
        stringHolder += " ";
      } else {
        stringHolder += "#";
      }
    } return stringHolder;
  }
}
      

