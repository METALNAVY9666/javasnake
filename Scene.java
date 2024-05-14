import java.awt.*;

// Abstract class representing a scene in a graphical application
public abstract class Scene {
    // Abstract method to update the scene based on elapsed time
    public abstract void update(double deltaTime);
    // Abstract method to draw the scene using graphics context
    public abstract void draw(Graphics g);
}
