package spreadsheet;

import spreadsheet.api.CellLocation;
import spreadsheet.api.ExpressionUtils;
import spreadsheet.api.SpreadsheetInterface;
import spreadsheet.api.value.*;
import java.util.*;

public class Spreadsheet implements SpreadsheetInterface {

  Map<CellLocation, Cell> cellMap = new HashMap<>();
  Set<Cell> cellsToBeRecomputed = new HashSet<>();
  Map<CellLocation,Double> assignments = new HashMap<>();


  /**
   * Pre: Cell doesn't have to be in the map.
   * Post: If the cell at location doesn't exist
   *       this method makes a new cell at that location with
   *       the expression "expression", else it just
   *       sets the expression at "location" to "expression".
   * @param location
   *            The location of the cell to update.
   * @param expression
   *            The expression to update the cell at
   *            location with.
   */
  @Override
  public void setExpression(CellLocation location, String expression) {
    if (cellMap.containsKey(location)) {
      cellMap.get(location).setExpression(expression);
    } else {
      Cell cell = new Cell(location, this);
      cell.setExpression(expression);
      cellMap.put(location, cell);
    }
  }

  /**
   * @param location
   *           The location to get expression from.
   * @return   Returns the expression at "location" if
   *           there is a cell there, if not it returns
   *           an empty string.
   */
  @Override
  public String getExpression(CellLocation location) {
    if (cellMap.containsKey(location)) {
      return cellMap.get(location).getExpression();
    } else {
      return "";
    }
  }

  /**
   * @param location
   *          The location of the cell to get the Value from.
   * @return  Returns the Value of the given cell at "location"
   *          if it exists else it returns null.
   */
  @Override
  public Value getValue(CellLocation location) {
    if (cellMap.containsKey(location)) {
      return cellMap.get(location).getValue();
    } else {
      return null;
    }
  }

  /**
   * Pre:  Initiates the process of recomputing all cells in the
   *       list of cells to Recompute.
   * Post: Passes one cell at a time to the recomputeCell function
   *       below, for it to Recompute.
   */
  @Override
  public void recompute() {
    while (cellsToBeRecomputed.iterator().hasNext()) {
      Cell cellToCompute = cellsToBeRecomputed.iterator().next();
      recomputeCell(cellToCompute);
      cellsToBeRecomputed.remove(cellToCompute);
    }
  }

  /**
   * Post: In this order the method passes the "cellToCompute" to
   *       the checkLoops method. It then follows through to compute
   *       all of the currentCell's immediate dependent cells first
   *       then the current cell if any of the dependants are in the
   *       "cellsToRecompute" set, It does this using a breadth first queue
   *       style method.
   * @param cellToCompute
   *          This is the current cell we are looking at recomputing.
   */
  private void recomputeCell(Cell cellToCompute) {
    checkLoops(cellToCompute, new LinkedHashSet<Cell>());

    if (!dependsOnOrIsLoop(cellToCompute)) {
      Deque<Cell> cellsToConsiderRecomputing = new LinkedList<>();
      cellsToConsiderRecomputing.add(cellToCompute);

      while (!cellsToConsiderRecomputing.isEmpty()) {
        Cell currentCell = cellsToConsiderRecomputing.poll();

          if (dependantNeedsRecomputing(currentCell)) {
            cellsToConsiderRecomputing.addAll(currentCell.cellsToObserve);
            cellsToConsiderRecomputing.addLast(currentCell);
          } else {
          calculateCellValue(currentCell);
          cellsToBeRecomputed.remove(currentCell);
        }
      }
    }
  }

  /**
   * Pre: This helper function supports recomputeCell as it checks
   *      if any of a cell's dependants are in the set of invalid
   *      cells to be recomputed already.
   * @param currentCell is the current cell to check.
   * @return true if the exists a cell in the dependants of 'c
   */
  private boolean dependantNeedsRecomputing(Cell currentCell) {
    for (Cell c : currentCell.cellsToObserve) {
      if (cellsToBeRecomputed.contains(c)) {
        return true;
      }
    }
    return false;
  }

  /**
   * @param cell is the current cell to check.
   * @return true if a cell depends on or is a loop itself.
   */
  private boolean dependsOnOrIsLoop(Cell cell) {
    if (cell.getValue() == LoopValue.INSTANCE) {
      return true;
    } else {
      for (Cell c : cell.cellsToObserve) {
        if (c.getValue() == LoopValue.INSTANCE) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Post: Calculates the cell.
   * @param currentCell The cell to calculate.
   */
  private void calculateCellValue(final Cell currentCell) {

    for (Cell dependant : currentCell.cellsToObserve) {
      final CellLocation l = dependant.getLocation();
      dependant.getValue().visit(new ValueVisitor() {

        /**
         * Pre: Changes a cells' expression to the same string as it's
         * dependant, if its' dependants' expression is a string itself.
         *
         * @param expression dependants' expression.
         */
        @Override
        public void visitString(String expression) {
          currentCell.setExpression(expression);
        }

        /**
         * Will never reach here for a loop as the
         * loops are caught before we recompute. We do
         * not want to compute a loop.
         */
        @Override
        public void visitLoop() {
        }

        @Override
        public void visitDouble(double value) {
          assignments.put(l, value);

        }

        @Override
        public void visitInvalid(String expression) {
          currentCell.setValue(new InvalidValue(currentCell.getExpression()));
        }

      });

    }
//    if (!assignments.isEmpty()) {
      currentCell.setValue(ExpressionUtils.computeValue(
          currentCell.getExpression(), assignments));
//    }

  }

  /**
   * Pre: Checks whether a cells' dependants are loops and passes
   *      control over to markAsValidatedLoop if this condition is true.
   * @param cellToCheck the current cell to check.
   * @param cellsSeen stores a set of cells seen.
   */
  private void checkLoops(Cell cellToCheck, LinkedHashSet<Cell> cellsSeen) {
    if (cellsSeen.contains(cellToCheck)) {
      markAsValidatedLoop(cellToCheck, cellsSeen);
    } else {
      cellsSeen.add(cellToCheck);
      for (Cell c : cellToCheck.cellsToObserve) {
        checkLoops(c, cellsSeen);
      }
      cellsSeen.remove(cellToCheck);
    }
  }

  /**
   * Pre:  Marks the cell at the start of a loop and
   * all that follow it int he "cells" set with a loop value.
   * @param startCell is the cell that the Loop begins.
   * @param cells is the set of all cells seen.
   */
  private void markAsValidatedLoop(Cell startCell, LinkedHashSet<Cell> cells) {
    boolean fromStartCellInitiator = false;
    for (Cell currentCell : cells) {
      cellsToBeRecomputed.remove(currentCell);
      if (currentCell.equals(startCell)) {
        fromStartCellInitiator = true;
      }
      if (fromStartCellInitiator) {
        currentCell.setValue(LoopValue.INSTANCE);
      }
    }
  }
}