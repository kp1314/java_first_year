public class Player {

  private int score;

  public Player() {
    score = 0;
  }  

  public void addToScore(int points) {
    score += points;
  }

  public int getScore() {
    return score;
  }
} 
