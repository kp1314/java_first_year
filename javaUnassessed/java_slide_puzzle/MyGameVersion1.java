public class MyGameVersion1 { 
  
  public static void main (String[] args) {
    
    System.out.println("\nHello and welcome to Slide Puzzle 3000!" + 
        "\nThe MOST ULTIMATE slide puzzle game known to man!!");
    System.out.println("\nPlease input your name:");
    
    String name = IOUtil.readString();
 
    System.out.println("\nThanks " + name + ". Now please enter the size SlidePuzzle\n"
    + "you would like to solve (i.e enter 3 for a 3 by 3 puzzle):");

    int size = IOUtil.readInt();
    SlidePuzzle puzzle = new SlidePuzzle(size);
    
    System.out.println("\nHere is the initial puzzle:");
    final String initial = puzzle.toString();
    System.out.println(puzzle);
    System.out.println("Press the letter (s) to scramble the puzzle");
       
    while (true) {
    String S = IOUtil.readString();
    if (!(S.equals("s"))) {
    System.out.println("\nPress the letter (s) to scramble the puzzle");
    } else {
    break;
    } 
    }
  
    puzzle.scramble(2000000);
    
    System.out.println("\nRight now let the fun begin: "+
                       "\nInput the words up, down, left and right" +
                       "\nto get back to the original puzzle. Have fun!");

    int moveCount = 0;
    
    do { 
    System.out.println("\nMovecount = " + moveCount);
    System.out.println("\n" + puzzle);
    String newMove = IOUtil.readString();
    switch (newMove) {
    case "up": puzzle.moveUp();     
         moveCount++;
         continue;
    case "right": puzzle.moveRight();
         moveCount++;
         continue;
    case "down": puzzle.moveDown();
         moveCount++;
         continue;
    case "left": puzzle.moveLeft();
         moveCount++;
         continue;
    }
    } while (!(puzzle.isGameOver(initial)));
    
   System.out.println("\n" + puzzle);
   System.out.println("You completed the Game in " + moveCount + " moves!");
   System.out.println("Well Done :)");
   }

//   public static boolean isGameOver() {
//   return initial.equals(puzzle.toString());
//   }
  
}
    
