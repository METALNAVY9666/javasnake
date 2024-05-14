import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

public class SceneGameCoop extends Scene {
    private boolean collisionDetected = false;
    public SnakeRed snakeRed;
    public SnakeBlue snakeBlue;
    public Food foodRed;
    public Food foodBlue;

    public SceneGameCoop(KL keyListener) {
        super(keyListener);
        // Initialize the snake with starting parameters
        snakeRed = new SnakeRed(2, 48, 48 + 24, 24,24, super.foreground);
        snakeBlue = new SnakeBlue(2, 48, 216 + 24, 24 , 24, super.foreground);

        foodRed = new Food(foreground, snakeRed, 12, 12, Color.RED);
        foodBlue = new Food(foreground, snakeBlue, 12, 12, Color.BLUE);
        foodRed.spawn();
        foodBlue.spawn();
    }

    public boolean checkCollision() {
        // Перебираем все сегменты каждой змейки
        for (Rect segmentRed : snakeRed.body) {
            for (Rect segmentBlue : snakeBlue.body) {
                // Проверяем столкновение каждого сегмента одной змейки с каждым сегментом другой змейки
                if (Snake.intersecting(segmentRed, segmentBlue)) {
                    return true; // Если есть столкновение, возвращаем true
                }
            }
        }
        return false; // Если столкновений не найдено, возвращаем false
    }

    // Method to update the game state
    @Override
    public void update(double deltaTime) {
        collisionDetected = false;
        // Update the snake's position and state

        if (!collisionDetected && this.checkCollision()) {
            Window.getWindow().changeState(0);
            collisionDetected = true;
        }

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

        if (super.keyListener.isKeyPressed(KeyEvent.VK_W)) {
            snakeBlue.changeDirection(Direction.UP);
        } else if (super.keyListener.isKeyPressed(KeyEvent.VK_S)) {
            snakeBlue.changeDirection(Direction.DOWN);
        } else if (super.keyListener.isKeyPressed(KeyEvent.VK_D)) {
            snakeBlue.changeDirection(Direction.RIGHT);
        } else if (super.keyListener.isKeyPressed(KeyEvent.VK_A)) {
            snakeBlue.changeDirection(Direction.LEFT);
        }

        if (!foodRed.spawned)
            foodRed.spawn();
        if (!foodBlue.spawned)
            foodBlue.spawn();

        // update the food position and state
        foodRed.update(deltaTime);
        foodBlue.update(deltaTime);

        snakeRed.update(deltaTime);
        snakeBlue.update(deltaTime);
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
        snakeBlue.draw(g2D);

        foodRed.draw(g2D);
        foodBlue.draw(g2D);
    }
}
