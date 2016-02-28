public class DoNotMeltTheSnowman {

  public static void main(String[] args) {
  Level[] levels = Levels.getLevels();
    System.out.println("Welcome to Do Not Melt the Snowman");
    System.out.println("----------------------------------");
    System.out.println("\nPlease choose which level you would like play (0 (easy) - 10 (evil))");

    int m = IOUtil.readInt();
    while (m > 11 && m < 0) {
    System.out.println("\nPlease choose a valid level (0 (easy) - 10 (evil))");
    m = IOUtil.readInt();
    }
    Level level = levels[m];
    int boardHeight = level.getHeight();
    int boardWidth = level.getWidth();

    Board board = new Board(PieceUtils.charsToPieces(level.getCharArray(), boardWidth, boardHeight));
    
    do {
    Result currentResult = board.fireLaser(); 
    board.renderBoard();
    if (currentResult == Result.HIT_TARGET) {
      System.out.println("YOU WON OMG IN DA HIZZY"); break;
    } else if (currentResult == Result.MELT_SNOWMAN) { System.out.println("YOU FAIL THE SNOWMAN IS DEAD"); break;
    }
    System.out.println("Please enter the co-ordinates of the peice you would like to move...");
    int x = IOUtil.readInt();
    int y = IOUtil.readInt();
    Coordinate piece = new Coordinate(x, y);
    board.rotatePiece(piece);
    board.clearLasers();
    } while (true);
  }
}
