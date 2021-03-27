public class ArrayBoard extends Board {
    private Cell[][] board;
    public ArrayBoard(int columns, int rows, GUI gui) {
        super(columns,rows,gui);
        board = new Cell[columns][rows];
        for(int i = 0; i < columns; i++) {
            for(int j = 0; j < rows; j++) {
                board[i][j] = new Cell(i,j);
            }
        }
    }
    @Override
    public void calculateValues(int i, int j) {
        long start = System.nanoTime();
        steps = 0;
        calculateValuesRecursion(i,j);
        long finish = System.nanoTime();
        long timeElapsed = finish - start;
        System.out.println("Steps Array:"+steps);
        System.out.println("Time Array:"+timeElapsed);
    }
    public void calculateValuesRecursion(int i, int j) {
        if(!board[i][j].getValueIsCalculated())
            calculateValue(i,j);
        steps += i+j+2;
        int l;
        int m;
        for(int k = 0; k < 8; k++) {
            int[] neighborIndexArray = getNeighboredCellByIndex(i,j,k);
            l = neighborIndexArray[0];
            m = neighborIndexArray[1];
            if(l>=0 && m>=0 && l<board.length && m<board[0].length && !board[l][m].getValueIsCalculated())
                calculateValuesRecursion(l,m);
            steps += l+m+2;
        }
    }
    public void calculateValue(int i, int j) {
        int l;
        int m;
        for(int k = 0; k < 8; k++) {
            int[] neighborIndexArray = getNeighboredCellByIndex(i,j,k);
            l = neighborIndexArray[0];
            m = neighborIndexArray[1];
            if(l>=0 && m>=0 && l<board.length && m<board[0].length && board[l][m].isMine())
                board[i][j].addToValue(1);
            steps += l+m+2;
            steps += i+j+2;
        }
        board[i][j].setValueIsCalculated(true);
        steps += i+j+2;
    }
    
    private int[] getNeighboredCellByIndex(int i, int j, int index) {
        switch(index) {
            case 0: return new int[] {i-1,j};
            case 1: return new int[] {i-1,j+1};
            case 2: return new int[] {i,j+1};
            case 3: return new int[] {i+1,j+1};
            case 4: return new int[] {i+1,j};
            case 5: return new int[] {i+1,j-1};
            case 6: return new int[] {i,j-1};
            case 7: return new int[] {i-1,j-1};
            default:return null;
        }
    }
    
    @Override
    public Cell getCell(int i, int j) {
        // steps = i+j+2;
        // System.out.println("Array, steps to get to Cell:" + steps);
        return board[i][j];
    }
}