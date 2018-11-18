package ElectricityV020;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by user on 11.09.2018.
 */
public class PantKeyListener implements KeyListener {
    PantPanel pantPanel;
    public PantKeyListener(PantPanel pantPanel) {
        this.pantPanel = pantPanel;
        pantPanel.setFocusable(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("keyTyped");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("keyPressed");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("keyReleased");
    }
}
