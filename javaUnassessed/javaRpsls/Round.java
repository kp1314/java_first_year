import java.util.Arrays;

public class Round {

  private Move[] moves;
  private int[] results;
 
  public Round(int numberOfPlayers, Move ourPlayerMove) {
    this.moves = new Move[numberOfPlayers];
      for (int i = 0; i < (numberOfPlayers - 1); i++) {
        String playerMove = IOUtil.readString();
        moves[i] = Move.readMove(playerMove);
      }
        moves[numberOfPlayers-1] = ourPlayerMove;
  }
  
  public Move[] getMoves() {
    return moves;
  }  
  
  public int getNumberOfPlayers() {
    return moves.length;
  }
   
  public Move getPlayerMove(int playerIndex) {
    return moves[playerIndex];
  }
  
  public int[] getResult() {
   for (int j = 0; j < getNumberOfPlayers(); j++) {
     for (int k = 0; k < getNumberOfPlayers(); k++) {
       if (moves[j].beats(moves[k])) {
         results[j]++;
       }
     }
   } return results;
  }

  public boolean isSimilar(Round otherRound) {
    if (getNumberOfPlayers() != otherRound.getNumberOfPlayers()) {
      return false;
    } 
    Move[] moveHolder = moves;
    Move[] otherMoveHolder = otherRound.getMoves();
    Arrays.sort(moveHolder);
    Arrays.sort(otherMoveHolder);
    return moveHolder == otherMoveHolder;
  }
}
