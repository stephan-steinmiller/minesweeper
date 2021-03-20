public class Game {
    private GUI gui;
    // Das Spiel muss wissen wann es vorbei ist um nicht mehr auf die Eingaben des Spielers zu reagieren
    private boolean gameOver;
    // Das Spiel besitzt Anzahl von Reihen, Spalten, übrigen Minen und vergangenen Schritten
    private int columns, rows, mines, leftMinesCounter, movesCounter;
    
    // Zum speichern der Zellen habe ich mich gegen einen Array und für eine eigen erstellte Datenstruktur entschieden
    private DataStructure board;
    
    // Ich hatte mich zuerst für ein 2 Dimensionales Array entschieden
    // private Cell[][] board;
    
    public Game(int columns, int rows, int mines) {
        gameOver = false;
        
        this.columns = columns;
        this.rows = rows;
        this.mines = mines;
        leftMinesCounter = mines;
        movesCounter = 0;
        
        board = new DataStructure(columns, rows);
        
        // board = new Cell[8][8];
        // for(int i = 0; i < 8; i++) {
            // for(int j = 0; j < 8; j++) {
                // board[i][j] = new Cell();
            // }
        // }
        
        setMines();
        // Hier wird ein Punkt ausgewählt von dem aus die Werte der Felder berechnet werden
        board.calculateValues(board.getNode(4,4));        
    }
    
    private void setMines() {
        // Hier werden 10 Mienen gesetzt, wenn auf dem zufälligen Feld schon eine Mine gesetzt ist, so wird ein anderes Feld gewählt
        while(mines>0) {
            Cell cellToSetMineAt = board.getNode(getRandomColumn(),getRandomRow());
            if(!cellToSetMineAt.isMine()) {
                cellToSetMineAt.setMine();
                cellToSetMineAt.setValueIsCalculated(true);
                mines--;
            }
        }
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
        gui.showGameOverLabel();
    }

    
    public void openCell(int i, int j) {
        // Die Zelle die geöffnet werden soll wird mit Hilfe der Indizes bestimmt
        Cell cellToOpen;
        cellToOpen = board.getNode(i, j);
        if(cellToOpen.isOpen()) {
            // wenn das Feld bereits geöffnet ist soll der Knopf wieder gewählt werden
            gui.setButtonSelected(i,j,true);
        } else if(gameOver || cellToOpen.isFlagged()) {
            // wenn das Feld bereits eine Flagge hat soll der Knopf abgewählt werden
            gui.setButtonSelected(i,j,false);
        } else {
            // Wenn das Feld nicht offen, keine Flagge hatte und das Spiel noch nicht vorbei ist, so soll das Feld geöffnet werden
            System.out.println("open"+i+j);
            movesCounter++;
            gui.setMovesCounter(movesCounter);
            
            if(cellToOpen.isMine()) {
                // Wenn eine Mine getroffen, wird das Spiel beendet
                cellToOpen.openCell();
                gui.setButtonText(i, j, "*");
                endGame();
            } else {
                // Feld wird aufgedeckt, und die Zahl angegeben
                cellToOpen.openCell();
                if(cellToOpen.getValue() == 0)
                    gui.setButtonText(i, j, "");
                else
                    gui.setButtonText(i, j, cellToOpen.getValueAsString());
            }
        }
    }
    public void flagCell(int i, int j) {
        System.out.println("flag "+i+j);
        Cell cellToFlag;
        cellToFlag = board.getNode(i, j);
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