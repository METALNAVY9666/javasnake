import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;


public class Window extends JFrame implements Runnable {
    // Variable to track window state
    public boolean isRunning;
    // Static variables for current state and scene
    public static int currentState;
    public static Scene currentScene;
    // Static key listener
    public static KL keyListener = new KL();
    // Constructor for creating window
    public Window (int width, int height, String title) {
        // Setting window size, title, resizable option, and visibility
        setSize(width, height);
        setTitle(title);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Adding key listener to the window
        addKeyListener(Window.keyListener);

        // Setting initial state and scene
        isRunning = true;
        Window.changeState(0);
    }

    // Method to change the current state and scene
    public static void changeState(int newState) {
        Window.currentState = newState;

        // Creating corresponding scene based on the state
        switch (Window.currentState) {
            case 0:
                Window.currentScene = new MenuScene(Window.keyListener);
                break;
            case 1:
                Window.currentScene = new GameScene();
                break;
            default:
                System.out.println("Unknown scene");
                Window.currentScene = null;
                break;
        }
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

        currentScene.update(deltaTime);
    }

    // Method for drawing window content
    public void draw (Graphics g) {
        Graphics2D g2D = (Graphics2D)g;

        // Drawing current scene
        currentScene.draw(g);
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
