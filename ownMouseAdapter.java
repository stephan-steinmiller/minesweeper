import javax.swing.*;
import java.awt.event.*;

// Keine Dokumentation
public class ownMouseAdapter extends MouseAdapter {
    private Game game;
    private int i, j;
    public ownMouseAdapter(int i, int j, Game game) {
        this.game = game;
        this.i = i;
        this.j = j;
    }
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if (SwingUtilities.isLeftMouseButton(mouseEvent)) {
            System.out.println("leftMouse");
            game.openCell(i, j);
        }    //Left button released!
        if (SwingUtilities.isRightMouseButton(mouseEvent)) {
            System.out.println("rightMouse");
            game.flagCell(i, j);
        }    //Right button released
    }
}