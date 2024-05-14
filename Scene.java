import java.awt.*;

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

    // Abstract method to update the scene based on elapsed time
    public abstract void update(double deltaTime);
    // Abstract method to draw the scene using graphics context
    public abstract void draw(Graphics g);
}
