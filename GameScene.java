import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

public class GameScene extends Scene {
    // Variables to represent the background and foreground rectangles
    public Rect background, foreground;
    public Snake snake;

    public KL keyListener;

    public GameScene(KL keyListener) {
        // Initialize the background rectangle to cover the entire screen
        background = new Rect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        // Initialize the foreground rectangle with specific dimensions
        foreground = new Rect(24, 48, 24 * 40, 24 * 30);
        // Initialize the snake with starting parameters
        snake = new Snake(5, 48, 48 + 40, 24,24);
        // Initialize Key Listener
        this.keyListener = keyListener;
    }

    // Method to update the game state
    @Override
    public void update(double deltaTime) {
        // Check for user input to change snake direction
        if (keyListener.isKeyPressed(KeyEvent.VK_UP)) {
            snake.changeDirection(Direction.UP);
        } else if (keyListener.isKeyPressed(KeyEvent.VK_DOWN)) {
            snake.changeDirection(Direction.DOWN);
        } else if (keyListener.isKeyPressed(KeyEvent.VK_RIGHT)) {
            snake.changeDirection(Direction.RIGHT);
        } else if (keyListener.isKeyPressed(KeyEvent.VK_LEFT)) {
            snake.changeDirection(Direction.LEFT);
        }
        // Update the snake's position and state
        snake.update(deltaTime);
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
