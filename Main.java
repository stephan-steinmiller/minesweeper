// Variablen- und Methodennamen gehören in meinem Programm zur Dokumentation dazu, da sie aussagen was sie speichern und was sie tun

public class Main {
    Game game, game2;
    // CellPosition[] minePattern;
    public Main() {
        // Ein Spiel  mit 8x8 Feld und 10 Minen wird generiert
        // minePattern = new CellPosition[8];
        // for(int i = 0; i<=(minePattern.length-1); i++) {
            // minePattern[i] = new CellPosition(i,i);
        // }
        // game = new Game(8,8,8,false,minePattern);
        // // Die grafische Oberfläche wird erschaffen und Game als Parameter übergeben, so dass die Klassen sich gegenseitig kennen
        // GUI gui = new GUI(game);
        // // gui wird als Parameter übergeben, so dass die Klassen sich gegenseitig kennen
        // game.setGui(gui);
        // // createGame(8,8);
        
        // game2 = new Game(8,8,8,true,minePattern);
        // GUI gui2 = new GUI(game2);
        // game2.setGui(gui2);
        createGame(64,64,160);
    }
    
    public void selectDificulty(String dificulty) {
        switch(dificulty) {
            case "Beginner":
            case "beginner":
            case "Anfänger":
            case "anfänger":
                createGame(8,8,10); 
                break;               
            case "Advanced":
            case "advanced":
            case "Fortgeschrittene":
            case "fortgeschrittene":
                createGame(16,16,40);
                break;
            case "Profis":
            case "profis":
            case "Profi":
            case "profi":
                createGame(30,16,99);
                break;
        }
    }
    
    public void createGame(int columns, int rows, int mines) {
        Game game = new Game(columns,rows,mines,false,null);
        GUI gui = new GUI(game);
        game.setGui(gui);
        game2 = new Game(columns,rows,mines,true,null);
        GUI gui2 = new GUI(game2);
        game2.setGui(gui2);
    }
}