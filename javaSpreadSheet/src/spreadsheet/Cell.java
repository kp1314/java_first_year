package spreadsheet;

import spreadsheet.api.CellLocation;
import spreadsheet.api.ExpressionUtils;
import spreadsheet.api.observer.Observer;
import spreadsheet.api.value.InvalidValue;
import spreadsheet.api.value.Value;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by kp1314 on 18/02/15.
 */
public class Cell implements Observer<Cell> {

  private final CellLocation location;
  private Value value;
  private String expression = "";
  private Spreadsheet spreadSheet;
  protected Set<Observer<Cell>> observers = new HashSet<>();
  protected Set<Cell> cellsToObserve = new HashSet<>();

  protected Cell(CellLocation location, Spreadsheet spreadsheet) {
    this.location = location;
    this.spreadSheet = spreadsheet;
    this.value = new InvalidValue(expression);
  }

  /**
   *
   * @return
   */
  public String getExpression() {
    return expression;
  }

  /**
   *
   * @param expression
   */
  protected void setExpression(String expression) {
    removeAsObserver(this);
    this.expression = expression;
    value = new InvalidValue(expression);
    addToCellsToRecompute();
    for (CellLocation cl : ExpressionUtils.getReferencedLocations(expression)) {
      if (!spreadSheet.cellMap.containsKey(cl)) {
        spreadSheet.cellMap.put(cl, new Cell(cl, spreadSheet));
      }
      cellsToObserve.add(spreadSheet.cellMap.get(cl));
    }
    for (Observer<Cell> o : observers) {
      o.update(this);
    }
  }

  /**
   *
   * @return
   */
  protected CellLocation getLocation() {
    return location;
  }

  /**
   *
   * @return
   */
  protected Value getValue() {
    return value;
  }

  /**
   *
   * @param value
   */
  protected void setValue(Value value) {
    this.value = value;
  }

  /**
   * Pre: Adds this cell to list cellsToRecompute.
   */
  private void addToCellsToRecompute() {
    if (!spreadSheet.cellsToBeRecomputed.contains(this)) {
      spreadSheet.cellsToBeRecomputed.add(this);
    }
  }

  /**
   * Pre: This method adds this cell to the list cellsToBeRecomputed
   *      if it's not in there then informs all observers of this cell
   *      that it has changed.
   * @param changed is the cell that has changed.
   */
  @Override
  public void update(Cell changed) {
      if (!spreadSheet.cellsToBeRecomputed.contains(this)) {
        addToCellsToRecompute();
        setValue(new InvalidValue(expression));
        for (Observer<Cell> o : observers) {
          o.update(this);
        }
      }
    }

  /**
   * Pre: This method removes this cell as an observer from
   *      all of it's dependants then removes all of it's dependants
   *      when it is given a new expression.
   * @param observer
   */
  private void removeAsObserver(Observer<Cell> observer) {
    for (Cell c : cellsToObserve) {
      c.observers.remove(observer);
    }
    cellsToObserve.clear();
  }

  /**
   * Pre: Overrides the Object equals function, comparing
   *      cells by their location in the cellmap.
   * @param o is the object you want to compare this object with.
   * @return true if this object and o are the same.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Cell cell = (Cell) o;

    if (location != null ? !location.equals(cell.location) : cell.location != null) return false;

    return true;
  }

  /**
   * Pre: Overrides the HashCode to be based upon a cell's location.
   * @return The HashCode for the cell is returned.
   */
  @Override
  public int hashCode() {
    return location != null ? location.hashCode() : 0;
  }
}
