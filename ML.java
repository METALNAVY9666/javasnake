import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class ML extends MouseAdapter implements MouseMotionListener {
    // Boolean flag to indicate whether the mouse button is pressed
    public boolean isPressed = false;
    public boolean isReleased = false;
    // Variables to store the current mouse coordinates
    public double x = 0.0, y = 0.0;

    // Called when a mouse button is pressed
    @Override
    public void mousePressed(MouseEvent event) {
        isPressed = true;
        isReleased = false;
    }

    // Called when a mouse button is released
    @Override
    public void mouseReleased(MouseEvent event) {
        isReleased = false;
        if (isPressed) {
            isPressed = false;
            isReleased = true;
        }
        ;
    }

    // Called when the mouse is moved
    @Override
    public void mouseMoved(MouseEvent event) {
        this.x = event.getX();
        this.y = event.getY();
    }

    // Getters
    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public boolean isPressed() {
        return this.isPressed;
    }

    public boolean isReleased() {
        return this.isReleased;
    }
}
