import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

public class SceneGameAI extends Scene {
    public SnakeRed snakeRed;

    public SceneGameAI(KL keyListener) {
        super(keyListener);
        // Initialize the snake with starting parameters
        snakeRed = new SnakeRed(10, 48, 48 + 40, 24,24);
        // Initialize Key Listener
    }

    // Method to update the game state
    @Override
    public void update(double deltaTime) {
        // Check for user input to change snake direction
        if (super.keyListener.isKeyPressed(KeyEvent.VK_UP)) {
            snakeRed.changeDirection(Direction.UP);
        } else if (super.keyListener.isKeyPressed(KeyEvent.VK_DOWN)) {
            snakeRed.changeDirection(Direction.DOWN);
        } else if (super.keyListener.isKeyPressed(KeyEvent.VK_RIGHT)) {
            snakeRed.changeDirection(Direction.RIGHT);
        } else if (super.keyListener.isKeyPressed(KeyEvent.VK_LEFT)) {
            snakeRed.changeDirection(Direction.LEFT);
        }

        // Update the snake's position and state
        snakeRed.update(deltaTime);
    }

    // Implementation of the draw method from the Scene class
    @Override
    public void draw(Graphics g) {
        Graphics2D g2D = (Graphics2D)g;
        // Set the color to black
        g2D.setColor(Color.BLACK);
        // Fill a rectangle to represent the background of the game scene
        g2D.fill(new Rectangle2D.Double(super.background.x, super.background.y, super.background.width, super.background.height));

        // Set the color to green for the foreground
        g2D.setColor(Color.GREEN);
        // Fill a rectangle to represent the foreground of the game scene
        g2D.fill(new Rectangle2D.Double(super.foreground.x, super.foreground.y, super.foreground.width, super.foreground.height));

        // Drawing the snake
        snakeRed.draw(g2D);
    }
}
