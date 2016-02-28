public class Game {
  
  Player player1 = new Player();
  Player player2 = new Player();
  Bowl[] board = new Bowl[12];
  public int turn;

  public Game () {
  this.turn = turn;
      for (int i = 0; i < 12 ; i++) {
        board[i] = new Bowl(4);
      }
  }

  public int getCurrentPlayer() {
    return turn;
  } 

  public void swapPlayers() {
    if (getCurrentPlayer() == 1) {
       turn = 2;
    } else { 
        turn = 1;
    }
  }

  public boolean isValidMove(int bowl) {
    if (bowl < 12 && bowl >= 0 && 
        board[bowl].getStones() > 0) {
       return true;
    } else {
      return false;
    }
  }  
  
  public boolean canCurrentPlayerMove() {
    if (getCurrentPlayer() == 1) {
      for (int i = 0; i < 6; i++) {
        if (isValidMove(i)) {
          return true;
        }  
         
        }
        return false;
      } else {
        for (int i = 6; i < 12; i++) {
          if (isValidMove(i)) {
            return true;
          } 
              
        }
        return false;
    }
  }

  public void move(int bowl) {
    while (!isValidMove(bowl)) {
      System.out.println("Please enter a VALID move");
      bowl = (IOUtil.readInt()) - 1;
    }
    if (getCurrentPlayer() == 1) {
      while (!(bowl < 6 && bowl >= 0)) {
        System.out.println("Please enter a VALID move");
        bowl = (IOUtil.readInt()) - 1;
      }
        int st = (board[bowl].takeAllStones());
        for (int i = 1; i <= st; i++) {
          board[(bowl+i)%12].depositStone();
        } 
        for (Bowl bowls : board) {
        player1.addToScore(bowls.updateAndGetScore());
        }
    } else {
      while (!(bowl < 12 && bowl >=6)) {
        System.out.println("Please enter a VALID move");
        bowl = (IOUtil.readInt()) -1;
      }  
        int st = (board[bowl].takeAllStones());
        for (int i = 1; i <= st; i++) {
          board[(bowl+i)%12].depositStone();
        }
        for (Bowl bowls : board) {
          player2.addToScore(bowls.updateAndGetScore());  
      } 
    }
   }

  public int getLeadingPlayer() {
    if (player1.getScore() > player2.getScore()) {
      return 1;
    } else if (player1.getScore() == player2.getScore()) {
      return 0;
    } else { 
      return 2;
    }
  }

  public boolean isOver() {
    if (player1.getScore()>=24 || player2.getScore()>=24) {
      return true;
    } else {
        return false;
    } 
  } 
  
  public void display() {
  System.out.println("Player 2's Score = " + player2.getScore());
  System.out.print("\n");
  System.out.println("  12    11    10    9     8     7  ");
    for (int i = (board.length-1); i > 5; i--) {
      System.out.print("( " + board[i].getStones() + " ) ");
    }
  System.out.print("\n");
    for (int i = 0; i < 6; i++) { 
      System.out.print("( " + board[i].getStones() + " ) ");
    }
  System.out.print("\n");
  System.out.println("  1     2     3     4     5     6  ");
  System.out.print("\n");
  System.out.println("Player 1's Score = " + player1.getScore());
  }
}       
