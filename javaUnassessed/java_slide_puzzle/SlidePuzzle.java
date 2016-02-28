import java.util.Random;

public class SlidePuzzle {

  public int[][] board; 
  private int currentGapColl = 0;
  private int currentGapRow = 0;
  private int size;
  final Random random;

  public SlidePuzzle(int size) {
  this.size = size;
  this.random = new Random();
  this.board = new int[size][size];
  reset();
  }
  
  public void reset() {
  int a = 0;
  for (int i = 0; i < size; i++) {
    for (int j = 0; j < size; j++) {
    board[i][j] = a;
    a += 1;
    }
  }
  currentGapColl = 0;
  currentGapRow  = 0;
  } 

  private void swapWith(int targetRow, int targetCollumn) {
  if (targetRow >= 0 && targetRow < size && targetCollumn >= 0 
      && targetCollumn < size) {
    int targetHolder = board[targetRow][targetCollumn];
    board[targetRow][targetCollumn] = board[currentGapRow][currentGapColl];
    board[currentGapRow][currentGapColl] = targetHolder;
    currentGapRow = targetRow;
    currentGapColl = targetCollumn;
  } else {
      return;
  }
  }

  public void moveUp() {
  swapWith(currentGapRow + 1, currentGapColl);
  }
  
  public void moveDown() {
  swapWith(currentGapRow - 1, currentGapColl);
  }
  
  public void moveLeft() {
  swapWith(currentGapRow, currentGapColl + 1);
  }

  public void moveRight() {
  swapWith(currentGapRow, currentGapColl - 1);
  }

  public void scramble(int steps) {
  st : while (steps > 0) {
    steps--;
    int move = random.nextInt(4);
    switch (move) {
    case 0: moveUp();
            continue st;
    case 1: moveDown();
            continue st;
    case 2: moveLeft();
            continue st;
    case 3: moveRight();
            continue st;
    }
  }
  }

 public boolean isGameOver(String initial) {
 return toString().equals(initial);
 }
    
  public String toString() {
  StringBuilder result = new StringBuilder();

    for (int i = 0; i < size; i++) {
      result.append("(");
      for (int j = 0; j < size; j++) { 
          if (board[i][j] == 0) {
          result.append(" ");
          } else { 
          result.append(board[i][j]);
          }
          if (!(j == size - 1)) {
          result.append("\t");
          }
      } result.append(")\n");
    } return result.toString();
  }
}

  
