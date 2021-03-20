class Cell {
    // ein Feld muss speichern ob es geöffnet ist, ob es eine Flagge besitzt und ob es eine Mine ist
    private boolean isOpen, isFlagged, isMine, valueIsCalculated;
    // Ebenso wird der Wert gespeichert, der aussagt wie viele Minen das Feld benachbarn
    private int value;
    
    Cell() {
        isOpen = false;
        isFlagged = false;
        isMine = false;
        valueIsCalculated = false;
        value = 0;
    }

    // set- und get-Methoden sind selbsterklärend
    void setMine() {
        isMine = true;
    }
    void setIsFlagged(boolean pIsFlagged) {
        isFlagged = pIsFlagged;
    }
    void openCell() {
        isOpen = true;
    }
    void setValue(int value) {
        this.value = value;
    }
    void addToValue(int valueToAdd) {
        value += valueToAdd;
    }
    void setValueIsCalculated(boolean valueIsCalculated) {
        this.valueIsCalculated = valueIsCalculated;
    }
    
    String getValueAsString() {
        return ""+value;
    }
    int getValue() {
        return +value;
    }
    boolean isOpen() {
        return isOpen;
    }
    boolean isFlagged() {
        return isFlagged;
    }
    boolean isMine() {
        return isMine;
    }
    boolean getValueIsCalculated() {
        return valueIsCalculated;
    }
}