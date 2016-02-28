public class Bowl {
 
  private int stones;
  private int deposits = 0;

  public Bowl(int stones) {
    this.stones = stones;
  }
  
  public int getStones() {
    return stones;
  }
  
  public int takeAllStones() {
    int prevStones = stones;
    stones = 0;
    return prevStones;
  }
 
  public void depositStone() {
    deposits = deposits + 1;
  }

  public int updateAndGetScore() {
    int bowlscore = 0;
    for (int i = deposits; i > 0; i--) {
      if (stones == 1) {
        stones++;
        bowlscore += takeAllStones();
      } else if (stones == 0) {
          stones++; 
      } else if (stones > 2) {
          stones += deposits;
          break;  
      }  
    }
    deposits = 0;
    return bowlscore;
  }
}
