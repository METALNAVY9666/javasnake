import java.awt.*;
import java.io.IOException;

public class SceneGameAI extends Scene {
    public Snake snakeRed;
    public Food food;

    public SceneGameAI(KL keyListener) throws IOException {
        super(keyListener);
        // Initialize the snake with starting parameters
        snakeRed = new Snake(2, 48, 48 + 24, 24, 24, super.foreground);
        food = new Food(foreground, new Snake[]{snakeRed}, 12, 12, Color.BLACK);
        food.spawn();
    }

    // Method to update the game state
    @Override
    public void update(double deltaTime) throws IOException {
        // Check for user input to change snake direction
        Control.arrowsControl(keyListener, snakeRed);
        Control.wasdControl(keyListener, snakeRed);
        Control.zqsdControl(keyListener, snakeRed);

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

        // Drawing the playground
        super.drawGround(g2D);

        // Drawing the snake
        snakeRed.draw(g2D, Color.RED);
        food.draw(g2D);
    }
}
