// Variablen- und Methodennamen gehören in meinem Programm zur Dokumentation dazu, da sie aussagen was sie speichern und was sie tun

public class Main {
    Game game;
    public Main() {
        // Ein Spiel  mit 8x8 Feld und 10 Minen wird generiert
        game = new Game(8,8,10);
        // Die grafische Oberfläche wird erschaffen und Game als Parameter übergeben, so dass die Klassen sich gegenseitig kennen
        GUI gui = new GUI(game);
        // gui wird als Parameter übergeben, so dass die Klassen sich gegenseitig kennen
        game.setGui(gui);
        // createGame(8,8);
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
                createGame(30,16,99);
                break;
        }
    }
    
    public void createGame(int columns, int rows, int mines) {
        Game game = new Game(columns,rows,mines);
        GUI gui = new GUI(game);
        game.setGui(gui);
    }
}