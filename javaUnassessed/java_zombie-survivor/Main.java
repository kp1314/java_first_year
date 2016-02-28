import java.util.Arrays;

public class Main {
  
  private static void playTurn(Turn turn) {
    outer: do {
    Outcome[] outcomes = turn.drawAndGetOutcomes();
    if (outcomes.length == 0) {
      System.out.println("There were no outcomes this round");
      return;
      }
    System.out.println("You got: " + java.util.Arrays.toString(outcomes));
    System.out.println("Current Piles:"); 
    System.out.println(turn.toString());
    if (turn.hasBeenBittenTooManyTimes()) {
      System.out.println("You got bitten too many times, your insides" +
                       "\nare being spilt on the floor, die while you stil can...");
      return;
    }
    System.out.println("If you would like to score press (s)" +
                       " if you want to carry on the rampage press any other key...");
    String s = IOUtil.readString();
    if (!(s.equals("s"))) {
      continue outer;
    } else break; 
    } while (true);
    System.out.println("You scored " + turn.getCurrentScore());
  }

  public static void main(String[] args) {
    System.out.println(" 0  Welcome to Zombie Survior!!  0");
    System.out.println(" |= --------------------------- =|");
    System.out.println("/ \\                             / \\");
    System.out.println("\nHow many surviors?");
    int players = IOUtil.readInt();
    ZombieSurvivor game = new ZombieSurvivor(players);
      while (!game.isGameOver()) {
        System.out.println("\nIt's Player " + 
            game.getCurrentPlayer() + "'s Turn");
        Turn turn = game.startPlayerTurn();
        playTurn(turn);
        game.scorePlayerTurn(turn);
        System.out.println("Current Score: " + game.toString());
        game.nextPlayer();
      } 
    System.out.println("Well done player " + game.getWinningPlayer() + 
                       ". You Survived and all your friends are dead!!!");
  } 

}
