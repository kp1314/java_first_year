public class AlwaysBotPlayer {
   
   public static void main (String[] args) {
     
   int numberOfPlayers = IOUtil.readInt();
   int numberOfRounds = IOUtil.readInt();

   Move move = Move.readMove(args[0]);

   AlwaysBot ab = new AlwaysBot(move);
    
     for(int i = 0; i < numberOfRounds; i++) {
       Move roundMove = ab.getNextMove();
       System.out.println(ab.getNextMove());
   
       Round round = new Round(numberOfPlayers, roundMove); 
       ab.finishRound(round);
     }
   }
}
