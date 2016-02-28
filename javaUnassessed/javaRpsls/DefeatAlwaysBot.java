public class DefeatAlwaysBot {
   
   private Move[] AlwaysBotMoves;
   private int numberOfPlayers;
 
   public DefeatAlwaysBot(int numPlayers) {
     AlwaysBotMoves = new Move[numPlayers];
     numberOfPlayers = numPlayers;
   }

   public Move getNextMove() {
    if (Round.getMoves() == null) {
      RandomBot rb = new RandomBot;
      return rb.getNextMove();
    }
 
    int[] score = new int[numberOfPlayers];
    for (Move move : AlwaysBotMoves) {
      for (i = 0; i < numberOfPlayers; i++) {
        if (move.beats(AlwaysBotMoves[i+1])) {
          
    

