import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

// Abstract class representing a scene in a graphical application
public abstract class Scene {
    public ML mouseListener;
    public Rect background, foreground;
    public KL keyListener;

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
