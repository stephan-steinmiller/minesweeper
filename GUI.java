import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//  Diese Klasse werde ich nicht dokumentieren, da sie in keinem Fall Teil der Aufgabe war und ich dies nur aus eigenem Vergnügen hinzugefügt habe

public class GUI {
    private Game game;
    private JPanel panel;
    private JLabel gameOverLabel, leftMinesCounter, movesCounter;
    private JToggleButton[][] buttonGrid;

    public GUI(Game game) {
        this.game = game;
        
        JFrame frame = new JFrame("Minesweeper");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
             
        JMenuBar menuBar = new JMenuBar();
        frame.add(menuBar, BorderLayout.NORTH);
        
        JMenu gameMenu = new JMenu("Game");
        menuBar.add(gameMenu);
        gameMenu.add(new JMenuItem("New      F2"));
        gameMenu.add(new JMenuItem("Exit"));
        
        menuBar.add(new JMenu("Help"));
        
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        frame.add(panel);
        
        gameOverLabel = new JLabel("Game Over!");
        gameOverLabel.setForeground(Color.RED);
        gameOverLabel.setFont(new Font("LCD", Font.PLAIN, 32));
        
        JPanel gameInformation = new JPanel();
        gameInformation.setLayout(new BoxLayout(gameInformation, BoxLayout.LINE_AXIS));
        panel.add(gameInformation);
        
        leftMinesCounter = new JLabel("010");
        leftMinesCounter.setFont(new Font("LCD", Font.PLAIN, 32));
        leftMinesCounter.setForeground(Color.RED);
        leftMinesCounter.setBackground(Color.BLACK);
        leftMinesCounter.setOpaque(true);  
        gameInformation.add(leftMinesCounter);
        
        gameInformation.add(Box.createRigidArea(new Dimension(160,0)));
        
        movesCounter = new JLabel("000");
        movesCounter.setFont(new Font("LCD", Font.PLAIN, 32));
        movesCounter.setForeground(Color.RED);
        movesCounter.setBackground(Color.BLACK);
        movesCounter.setOpaque(true);    
        gameInformation.add(movesCounter);
        
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(game.getColumns(),game.getRows()));
        panel.add(boardPanel);
        
        buttonGrid = new JToggleButton[game.getColumns()][game.getRows()];
        for(int i = 0; i < buttonGrid.length; i++) {
            for(int j = 0; j < buttonGrid[0].length; j++) {
                buttonGrid[i][j] = new JToggleButton();
                buttonGrid[i][j].addMouseListener(new ownMouseAdapter(i, j, game));
                boardPanel.add(buttonGrid[i][j]);
            }
        }
        frame.setSize(360, 520);
    }


    public void setLeftMinesCounter(int newLeftMinesCounter) {
        if(newLeftMinesCounter>=100)
            leftMinesCounter.setText(""+newLeftMinesCounter);
        else if(newLeftMinesCounter>=10)
            leftMinesCounter.setText("0"+newLeftMinesCounter);
        else
            leftMinesCounter.setText("00"+newLeftMinesCounter);
    }
    public void setMovesCounter(JLabel newMovesCounter){
        movesCounter = newMovesCounter;
    }
    public void setButtonText(int i, int j, String text){
        JToggleButton button = buttonGrid[i][j];
        button.setText(text);
        switch(text) {
            case "*":
                button.setBackground(Color.RED);
                break;
            case "1":
                button.setForeground(new Color(0, 0, 202));
                break;
            case "2":
                button.setForeground(new Color(2, 102, 1));
                break;
            case "3":
                button.setForeground(new Color(203, 0, 1));
                break;
            case "4":
                button.setForeground(new Color(3, 0, 102));
                break;
            case "5":
                button.setForeground(new Color(105, 0, 2));
                break;
            case "6":
                button.setForeground(new Color(0, 102, 102));
                break;
            case "8":
                button.setForeground(new Color(96, 104, 108));
                break;
            default:
        }
    }
    public void setButtonSelected(int i, int j, boolean isSelected) {
        buttonGrid[i][j].setSelected(isSelected);
    }
    public void setMovesCounter(int newMovesCounter){
        if(newMovesCounter>=100)
            movesCounter.setText(""+newMovesCounter);
        else if(newMovesCounter>=10)
            movesCounter.setText("0"+newMovesCounter);
        else
            movesCounter.setText("00"+newMovesCounter);
    }
    public void showGameOverLabel() {
        panel.add(gameOverLabel);
    }
}

