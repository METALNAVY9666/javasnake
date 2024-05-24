import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

public class SceneGameCoop extends Scene {
    public Snake snakeRed;
    public Snake snakeBlue;
    public Food[] foods;
    public Control control;

    public SceneGameCoop(KL keyListener) throws IOException {
        super(keyListener);
        // Initialize the snake with starting parameters
        snakeRed = new Snake(0, 2, 48, 48 + 24, 24, 24, super.foreground);
        snakeBlue = new Snake(1, 2, 48, 216 + 24, 24, 24, super.foreground);

        this.control = new Control(keyListener);

        this.foods = super.generateFood(snakeRed, snakeBlue);

        snakeRed.setFoods(this.foods);
        snakeBlue.setFoods(this.foods);
    }

    public boolean checkCollision() {
        // Iterate through all segments of each snake
        for (Rect segmentRed : snakeRed.body) {
            for (Rect segmentBlue : snakeBlue.body) {
                // Check collision of each segment of one snake with each segment of
                // the other snake
                if (Snake.intersecting(segmentRed, segmentBlue)) {
                    return true; // If collision occurs, return true
                }
            }
        }
        return false; // If no collisions found, return false
    }

    // Method to update the game state
    @Override
    public void update(double deltaTime) throws IOException {
        // Update the snake's position and state

        if (this.checkCollision()) {
            Window.getWindow().changeState(0);
        }

        // Check for user input to change snake direction
        control.arrowsControl(snakeRed);
        control.wasdControl(snakeBlue);

        super.checkFoodSpawned(this.foods);

        // update the food position and state
        for (Food food : this.foods) {
            food.update(deltaTime);
        }

        snakeRed.update(deltaTime);
        snakeBlue.update(deltaTime);
    }

    // Implementation of the draw method from the Scene class
    @Override
    public void draw(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;

        // Drawing the playground
        super.drawGround(g2D);

        // Drawing the snake
        snakeRed.draw(g2D, Color.RED);
        snakeBlue.draw(g2D, Color.BLUE);

        for (Food food : foods) {
            food.draw(g2D);
        }
    }
}
