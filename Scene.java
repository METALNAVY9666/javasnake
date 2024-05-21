import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

// Abstract class representing a scene in a graphical application
public abstract class Scene {
    public ML mouseListener;
    public Rect background, foreground;
    public KL keyListener;

    public String getHomePath() {
        if (System.getProperty("os.name").contains("Linux")) {
            return "./assets/";
        }
        return "src/assets/";
    }

    public String homePath = getHomePath();

    public Scene() {
        // Initialize the background rectangle to cover the entire screen
        background = new Rect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        // Initialize the foreground rectangle with specific dimensions
        foreground = new Rect(24, 48, Constants.TILE_WIDTH * 31, Constants.TILE_WIDTH * 22);
    }

    public Scene(KL keyListener) {
        // Initialize the background rectangle to cover the entire screen
        background = new Rect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        // Initialize the foreground rectangle with specific dimensions
        foreground = new Rect(24, 48, Constants.TILE_WIDTH * 31, Constants.TILE_WIDTH * 22);
        // Initialize Key Listener
        this.keyListener = keyListener;
    }

    public Scene(KL keyListener, ML mouseListener) {
        this.keyListener = keyListener;
        this.mouseListener = mouseListener;
    }

    // returns the path of the texture of a png file using the right os
    public String getTexture(String filename) {
        return this.homePath + filename + ".png";
    }

    public Food[] generateFood(SnakeIA snakeRed, SnakeIA snakeBlue) {
        return getFoods(snakeRed, snakeBlue);
    }

    public Food[] generateFood(Snake snakeRed, Snake snakeBlue) {
        return getFoods(snakeRed, snakeBlue);
    }

    public Food[] getFoods(Snake snakeRed, Snake snakeBlue) {
        int randomNumber = (int) (Math.random() * 5) + 1;
        Food[] foods = new Food[randomNumber];

        for (int i = 0; i < foods.length; i++) {
            foods[i] = new Food(foreground, new Snake[] { snakeRed, snakeBlue }, 16, 16, Color.BLACK);
            foods[i].spawn();
        }
        return foods;
    }

    public void checkFoodSpawned(Food[] foods) {
        for (Food food : foods) {
            if (!food.spawned)
                food.spawn();
        }
    }

    public void drawGround(Graphics2D g2D) {
        // Set the color to black
        g2D.setColor(Color.BLACK);
        // Fill a rectangle to represent the background of the game scene
        g2D.fill(new Rectangle2D.Double(background.x, background.y, background.width,
                background.height));

        // Set the color to green for the foreground
        g2D.setColor(Color.GREEN);
        // Fill a rectangle to represent the foreground of the game scene
        g2D.fill(new Rectangle2D.Double(foreground.x, foreground.y, foreground.width,
                foreground.height));
    }

    // Abstract method to update the scene based on elapsed time
    public abstract void update(double deltaTime) throws IOException;

    // Abstract method to draw the scene using graphics context
    public abstract void draw(Graphics g);
}
