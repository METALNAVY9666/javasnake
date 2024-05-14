import javax.swing.*;
import java.awt.*;


public class Window extends JFrame implements Runnable {
    // Variable to track window state
    public boolean isRunning;
    // Constructor for creating window
    public Window (int width, int height, String title) {
        // Setting window size, title, resizable option, and visibility
        setSize(width, height);
        setTitle(title);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        isRunning = true;
    }

    // Method for updating window content
    public void update(double deltaTime) {
        // Creating an image with window dimensions
        Image dbImage = createImage(getWidth(), getHeight());
        // Getting graphics context of the image
        Graphics g = dbImage.getGraphics();
        // Calling draw method to render content
        this.draw(g);
        // Displaying the image on the window
        getGraphics().drawImage(dbImage, 0, 0, this);
    }

    // Method for drawing window content
    public void draw (Graphics g) {
        Graphics2D g2D = (Graphics2D)g;
        g2D.setColor(Color.black);
        g2D.fillRect(0, 0, getWidth(), getHeight());
    }

    // Overridden run method from Runnable interface
    @Override
    public void run() {
        // Variable to store last frame time
        double lastFrameTime = 0.0;
        try {
            // Infinite loop for updating window content
            while (isRunning) {
                // Getting current time
                double time = Time.getTime();
                // Calculating time elapsed since last frame
                double deltaTime = time - lastFrameTime;
                // Updating last frame time variable
                lastFrameTime = time;

                // Calling update method to update window content
                update(deltaTime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
