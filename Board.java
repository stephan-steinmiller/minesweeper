public class Board {
    public int columns, rows, steps;
    public GUI gui;
    public long start, finish;
    public Board(int columns, int rows, GUI gui) {
        steps = 0;
        this.columns = columns;
        this.rows = rows;
        this.gui = gui;
    }
    public Cell getCell(int i, int j) {
        return null;
    }
    public void calculateValues(int i, int j) {}
    public void openCell(int i, int j, Cell cellToOpen) {
        System.out.println("openCell in Board:"+i+j);
        cellToOpen.openCell();
        gui.setButtonSelected(i,j,true);
        if(cellToOpen.getValue() == 0) {
            gui.setButtonText(i, j, "x");
            // findAllNeighbouredZeros(i, j);
        } else
            gui.setButtonText(i, j, cellToOpen.getValueAsString());
    }
    public void findAllNeighboredZeros(int i, int j) {}
}