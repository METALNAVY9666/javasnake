import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

public class SceneGameAI extends Scene {
    public SnakeRed snakeRed;
    public Food food;

    public SceneGameAI(KL keyListener) {
        super(keyListener);
        // Initialize the snake with starting parameters
        snakeRed = new SnakeRed(2, 48, 48 + 24, 24,24, super.foreground);
        food = new Food(foreground, snakeRed, 12, 12, Color.BLACK);
        food.spawn();
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

        if (!food.spawned)
            food.spawn();

        // update the food position and state
        food.update(deltaTime);

        // Update the snake's position and state
        snakeRed.update(deltaTime);
    }

    // Implementation of the draw method from the Scene class
    @Override
    public void draw(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
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
        food.draw(g2D);
    }
}
