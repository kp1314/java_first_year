public class PieceUtils {

  public static Piece charToPiece(char c) {
  Piece piece = null;
    switch (c) {
     case '^': return Piece.EMITTER_NORTH;
     case '>': return Piece.EMITTER_EAST;
     case 'v': return Piece.EMITTER_SOUTH;
     case '<': return Piece.EMITTER_WEST;
     case '|': return Piece.LASER_VERTICAL;
     case '-': return Piece.LASER_HORIZONTAL;
     case '+': return Piece.LASER_CROSSED;
     case '/': return Piece.MIRROR_SW_NE;
     case '\\': return Piece.MIRROR_NW_SE; 
     case '#': return Piece.WALL;
     case 'o': return Piece.TARGET;
     case ' ': return Piece.EMPTY;
     case '@': return Piece.SNOWMAN;
   }return null;
 }

  public static Piece[][] charsToPieces(char[] description,
                                        int width, int height) {
  Piece[][] level = new Piece[width][height];
  int count = 0;
  // the first element of the first row is the first element 
  // of the lst row.
  for (int i = height-1; i >= 0; i--) {
    for (int j = 0; j < width; j++) {
      level[j][i] = charToPiece(description[count]);
      count++;
    }
 
  } return level;
  }

  public static boolean isEmitter(Piece p) {
    switch (p) {
      case EMITTER_NORTH:
      case EMITTER_EAST:
      case EMITTER_SOUTH:
      case EMITTER_WEST:
        return true;
    }
    return false;
  }

  public static Coordinate findEmitter(Piece[][] pieces) {
  for (int i = 0; i < pieces.length; i++) {
   for (int j = 0; j < pieces[i].length; j++) {
   if (isEmitter(pieces[i][j])) {
     return new Coordinate(i, j);
   }
   }
  }
   return null;
  }
  

  public static Piece hideLaser(Piece p) {
    switch (p) {
      case LASER_VERTICAL:
      case LASER_HORIZONTAL:
      case LASER_CROSSED:
        return Piece.EMPTY;
    }
    return p;
  }

  public static Piece addLaser(Piece p, boolean isHorizontal) {
    if (isHorizontal) {
      switch (p) {
        case EMPTY:
          return Piece.LASER_HORIZONTAL;
        case LASER_VERTICAL:
          return Piece.LASER_CROSSED;
        default: return p;
      }
    }
    switch (p) {
      case EMPTY: 
        return Piece.LASER_VERTICAL;
      case LASER_HORIZONTAL: 
        return Piece.LASER_CROSSED;
      default: return p;
    } 
  } 

  public static Coordinate move(Piece p, Coordinate c, int xo, int yo) {
    switch (p) {
      case EMITTER_NORTH:
        return new Coordinate(c.getX(), c.getY() + 1);
      case EMITTER_EAST:
        return new Coordinate(c.getX() + 1, c.getY());
      case EMITTER_SOUTH:
        return new Coordinate(c.getX(), c.getY() - 1);
      case EMITTER_WEST:
        return new Coordinate(c.getX() - 1, c.getY());
      case LASER_VERTICAL:
      case LASER_HORIZONTAL:
      case LASER_CROSSED:
      case EMPTY:
        return new Coordinate(c.getX() + xo, c.getY() + yo);
      case MIRROR_SW_NE:
        if (xo == -1 && yo == 0) {
          return new Coordinate(c.getX(), c.getY() -1);
        } else if (xo == 1 && yo == 0) {
          return new Coordinate(c.getX(), c.getY() +1);
        } else if (xo == 0 && yo == -1) {
          return new Coordinate(c.getX() -1, c.getY());
        } else {
          return new Coordinate(c.getX() +1, c.getY());
        }
      case MIRROR_NW_SE:
        if (xo == -1 && yo == 0) {
          return new Coordinate(c.getX(), c.getY() +1);
        } else if (xo == 1 && yo == 0) {
          return new Coordinate(c.getX(), c.getY() -1);
        } else if (xo == 0 && yo == -1) {
          return new Coordinate(c.getX() +1, c.getY());
        } else {
          return new Coordinate(c.getX() -1, c.getY());
        }
      default: return c;
      }  
  }
  public static Piece rotate(Piece p) {
    switch (p) {
      case EMITTER_NORTH:
        return Piece.EMITTER_EAST;
      case EMITTER_EAST:
        return Piece.EMITTER_SOUTH;
      case EMITTER_SOUTH:
        return Piece.EMITTER_WEST;
      case EMITTER_WEST:
        return Piece.EMITTER_NORTH;
      case MIRROR_SW_NE:
        return Piece.MIRROR_NW_SE;
      case MIRROR_NW_SE:
        return Piece.MIRROR_SW_NE;
    }
    return p;
  }

}
