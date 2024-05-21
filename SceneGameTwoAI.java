import java.awt.*;
import java.io.IOException;

public class SceneGameTwoAI extends Scene {
    public SnakeIA snakeRed;
    public SnakeIA snakeBlue;
    public Food[] foods;

    public SceneGameTwoAI() throws IOException {
        snakeRed = new SnakeIA(2, 48, 48 + 24, 24, 24, super.foreground);
        snakeBlue = new SnakeIA(2, 48, 216 + 24, 24, 24, super.foreground);

        this.foods = super.generateFood(snakeRed, snakeBlue);

        snakeRed.setFoods(this.foods);
        snakeBlue.setFoods(this.foods);
    }

    public boolean checkCollision() {
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

    @Override
    public void update(double deltaTime) throws IOException {

        if (this.checkCollision()) {
            Window.getWindow().changeState(0);
        }

        // update the food position and state
        super.checkFoodSpawned(this.foods);
        for (Food food : this.foods) {
            food.update(deltaTime);
        }

        // Update the snake's position and state
        snakeRed.update(deltaTime);
        snakeBlue.update(deltaTime);
    }

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
