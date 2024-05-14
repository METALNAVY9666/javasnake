import java.awt.*;

public class GameScene extends Scene {
    @Override
    public void update(double deltaTime) {}

    // Implementation of the draw method from the Scene class
    @Override
    public void draw(Graphics g) {
        // Set the color to green
        g.setColor(Color.green);
        // Fill a rectangle to represent the game scene
        g.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
    }
}
