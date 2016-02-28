public class Owari {
  public static void main(String[] args) {
  Game game = new Game();
  System.out.println("Which player would like to move first? (1 or 2)");
  game.turn = IOUtil.readInt();

    while (!(game.isOver())) {
      game.display();
          System.out.println("Player " + game.getCurrentPlayer() + ", please make your move.");
          int m = IOUtil.readInt();
          game.move(m-1);
          game.swapPlayers(); 
          if (!game.canCurrentPlayerMove()) {
          System.out.println("Sorry Player " + game.getCurrentPlayer() + ", you can't make a move!!");
          game.swapPlayers();
          } continue;
    }

    game.display();
    System.out.println("Well done Player " + game.getLeadingPlayer() + " YOU WON!!");
  }
}
  
  
  
