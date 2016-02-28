package spreadsheet;


import spreadsheet.gui.SpreadsheetGUI;

public class Main {

    private static final int DEFAULT_NUM_ROWS = 5000;
    private static final int DEFAULT_NUM_COLUMNS = 5000;

    public static void main(String[] args) {
      Spreadsheet spreadsheet = new Spreadsheet();
      SpreadsheetGUI spreadsheetGUI = new SpreadsheetGUI(spreadsheet,
          DEFAULT_NUM_ROWS, DEFAULT_NUM_COLUMNS);
      try {
        if (args.length == 2) {
          int rows = Integer.parseInt(args[0]);
          int columns = Integer.parseInt(args[1]);
          spreadsheetGUI = new SpreadsheetGUI(spreadsheet,
              rows, columns);
        }
        spreadsheetGUI.start();
      } catch (NumberFormatException e) {
        System.err.println("Please enter two integer inputs for rows " +
            "\nand columns respectively.");
      }
    }

}
