public class Game {
    private GUI gui;
    // Das Spiel muss wissen wann es vorbei ist um nicht mehr auf die Eingaben des Spielers zu reagieren
    private boolean gameOver, lostGame;
    // Das Spiel besitzt Anzahl von Reihen, Spalten, übrigen Minen und vergangenen Schritten
    private int columns, rows, mines, leftMinesCounter, movesCounter;
    private Board board;
    
    public Game(int columns, int rows, int mines, boolean withArrayBoard, CellPosition[] minePattern) {
        gameOver = false;
        
        this.columns = columns;
        this.rows = rows;
        this.mines = mines;
        leftMinesCounter = mines;
        movesCounter = 0;
        
        if(withArrayBoard) {
            board = new ArrayBoard(columns, rows, gui);
        } else {
            board = new DataStructureBoard(columns, rows, gui);
        }
        
        if(minePattern == null) {
            setMines();
        } else {
            this.mines = minePattern.length;
            setMinePattern(minePattern);
        }
        board.calculateValues(6,4);
    }
    
    private void setMines() {
        // Hier werden 10 Mienen gesetzt, wenn auf dem zufälligen Feld schon eine Mine gesetzt ist, so wird ein anderes Feld gewählt
        int minesLeftToSet = mines;
        while(minesLeftToSet>0) {
            Cell cellToSetMineAt = board.getCell(getRandomColumn(),getRandomRow());
            if(!cellToSetMineAt.isMine()) {
                cellToSetMineAt.setMine();
                cellToSetMineAt.setValueIsCalculated(true);
                minesLeftToSet--;
            }
        }
    }
    private void setMinePattern(CellPosition[] minePattern) {
        for(int i = 0; i<=(minePattern.length-1); i++) {
            Cell cellToSetMineAt = board.getCell(minePattern[i].getColumn(),minePattern[i].getRow());
            if(!cellToSetMineAt.isMine()) {
                cellToSetMineAt.setMine();
                cellToSetMineAt.setValueIsCalculated(true);
            }
        }
    }
    void calculateValues() {
        int randomColumn = getRandomColumn();
        int randomRow = getRandomRow();;
        while(board.getCell(randomColumn,randomRow).isMine()) {
             randomColumn = getRandomColumn();
             randomRow = getRandomRow();
        }
        board.calculateValues(randomColumn,randomRow);
    }
    
    private int getRandomColumn() {
        // Der Index einer zufälligen Reihe wird zurückgegeben
        // mithilfe von Modolu erhält man eine Zahl zwischen 1 und der Reihenanzahl
        return (int)(Math.random()*100)%columns;
    }
    private int getRandomRow() {
        // Der Index einer zufälligen Spalte wird zurückgegeben
        // mithilfe von Modolu erhält man eine Zahl zwischen 1 und der Spaltenanzahl
        return (int)(Math.random()*100)%rows;
    }
    
    private void endGame() {
        // das Spiel ist vorbei und Game Over wird ausgegeben
        gameOver = true;
        if(lostGame) {
            gui.showGameOverLabel();
        } else {
            gui.showYouWonLabel();
        }
    }

    
    public void handleClick(int i, int j) {
        // Die Zelle die geöffnet werden soll wird mit Hilfe der Indizes bestimmt
        Cell cellToOpen;
        cellToOpen = board.getCell(i, j);
        if(cellToOpen.isOpen()) {
            // wenn das Feld bereits geöffnet ist soll der Knopf wieder gewählt werden
            gui.setButtonSelected(i,j,true);
        } else if(gameOver || cellToOpen.isFlagged()) {
            // wenn das Feld bereits eine Flagge hat soll der Knopf abgewählt werden
            gui.setButtonSelected(i,j,false);
        } else {
            // Wenn das Feld nicht offen, keine Flagge hatte und das Spiel noch nicht vorbei ist, so soll das Feld geöffnet werden
            // System.out.println("open"+i+j);
            movesCounter++;
            gui.setMovesCounter(movesCounter);
            
            // Feld wird aufgedeckt, und die Zahl angegeben
            cellToOpen.openCell();
            if(cellToOpen.isMine()) {
                // Wenn eine Mine getroffen, wird das Spiel beendet
                gui.setButtonText(i, j, "*");
                lostGame = true;
                endGame();
            } else {
                if(cellToOpen.getValue() == 0) {
                    gui.setButtonText(i, j, "");
                    findAllNeighboredZeros(i, j);
                } else
                    gui.setButtonText(i, j, cellToOpen.getValueAsString());
                if(movesCounter+mines == columns*rows) {
                    endGame();
                }
            }
        }
    }
    private void findAllNeighboredZeros(int i, int j) {
        board.findAllNeighboredZeros(i, j);
    }
    public void flagCell(int i, int j) {
        System.out.println("flag "+i+j);
        Cell cellToFlag;
        cellToFlag = board.getCell(i, j);
        if(!gameOver && !cellToFlag.isOpen()) {
            // Flagge wird gesetzt oder entfernt wenn das Feld nicht offen und das Spiel nicht vorbei ist
            if(cellToFlag.isFlagged()) {
                // Wenn eine Flagge im Feld schon vorhanden ist, wird die Flagge entfernt
                leftMinesCounter++;
                cellToFlag.setIsFlagged(false);
                gui.setLeftMinesCounter(leftMinesCounter);
                gui.setButtonText(i, j, "");
            } else {
                // Ansonsten wird sie gesetzt
                leftMinesCounter--;
                cellToFlag.setIsFlagged(true);
                gui.setLeftMinesCounter(leftMinesCounter);
                gui.setButtonText(i, j, "P");
            }
        }
    }


    public void setGui(GUI gui) {
        this.gui = gui;
    }
    public int getColumns() {
        return columns;
    }
    public int getRows() {
        return rows;
    }
}