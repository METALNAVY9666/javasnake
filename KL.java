import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KL extends KeyAdapter implements KeyListener {
    // Array to track which keys are currently pressed
    private boolean[] keyPressed = new boolean[128];

    // Method called when a key is pressed
    @Override
    public void keyPressed(KeyEvent event) {
        // Set the corresponding key index to true
        keyPressed[event.getKeyCode()] = true;
    }

    // Method called when a key is released
    @Override
    public void keyReleased(KeyEvent event) {
        // Set the corresponding key index to false
        keyPressed[event.getKeyCode()] = false;
    }

    // Method to check if a specific key is pressed
    public boolean isKeyPressed(int keyCode) {
        return keyPressed[keyCode];
    }
}
