import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Window extends JFrame implements Runnable {
    public static Window window = null;
    // Variable to track window state
    public boolean isRunning;
    // Static variables for current state and scene
    public int currentState;
    public Scene currentScene;
    // Key listener and mouse listener
    public KL keyListener = new KL();
    public ML mouseListener = new ML();

    // Constructor for creating window
    public Window(int width, int height, String title) throws IOException {
        // Setting window size, title, resizable option, and visibility
        setSize(width, height);
        setTitle(title);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Adding key listener to the window
        addKeyListener(keyListener);

        // Adding mouse listener to the window
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);

        // Setting initial state and scene
        isRunning = true;
        changeState(0);
    }

    // Method to get the window instance
    public static Window getWindow() throws IOException {
        if (Window.window == null) {
            Window.window = new Window(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, Constants.SCREEN_TITLE);
        }
        return Window.window;
    }

    // Method to close the game
    public void close() {
        isRunning = false;
    }

    // Method to change the current state and scene
    public void changeState(int newState) throws IOException {
        currentState = newState;

        // Creating corresponding scene based on the state
        switch (currentState) {
            case 0:
                currentScene = new SceneMenu(keyListener, mouseListener);
                break;
            case 1:
                currentScene = new SceneGameAI(keyListener);
                break;
            case 2:
                currentScene = new SceneGameCoop(keyListener);
                break;
            case 3:
                currentScene = new SceneGameTwoAI();
                break;
            case 4:
                currentScene = new SceneGameOver();
                break;
            default:
                System.out.println("Unknown scene");
                currentScene = null;
                break;
        }
    }

    // Method for updating window content
    public void update(double deltaTime) throws IOException {
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
    public void draw(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
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
        System.exit(0);
    }
}
