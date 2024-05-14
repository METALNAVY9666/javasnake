import java.awt.*;
import java.awt.event.KeyEvent;

public class MenuScene extends Scene {
    public KL keyListener;
    // Constructor to initialize MenuScene with a key listener
    public MenuScene(KL keyListener) {
        this.keyListener = keyListener;
    }
    @Override
    public void update(double deltaTime) {}

    // Implementation of the draw method from the Scene class
    @Override
    public void draw(Graphics g) {
        // Set the color to blue
        g.setColor(Color.blue);
        // Fill a rectangle to represent the menu scene
        g.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
    }
}
