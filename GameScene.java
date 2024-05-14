import java.awt.*;
import java.awt.geom.Rectangle2D;

public class GameScene extends Scene {
    // Variables to represent the background and foreground rectangles
    public Rect background, foreground;
    public Snake snake;

    public GameScene() {
        // Initialize the background rectangle to cover the entire screen
        background = new Rect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        // Initialize the foreground rectangle with specific dimensions
        foreground = new Rect(24, 48, 24 * 40, 24 * 30);
        snake = new Snake(5, 48, 48 + 40, 24,24);
    }
    @Override
    public void update(double deltaTime) {

    }

    // Implementation of the draw method from the Scene class
    @Override
    public void draw(Graphics g) {
        Graphics2D g2D = (Graphics2D)g;
        // Set the color to black
        g2D.setColor(Color.BLACK);
        // Fill a rectangle to represent the background of the game scene
        g2D.fill(new Rectangle2D.Double(background.x, background.y, background.width, background.height));

        // Set the color to green for the foreground
        g2D.setColor(Color.GREEN);
        // Fill a rectangle to represent the foreground of the game scene
        g2D.fill(new Rectangle2D.Double(foreground.x, foreground.y, foreground.width, foreground.height));

        // Drawing the snake
        snake.draw(g2D);
    }
}
