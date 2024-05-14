import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;


public class MenuScene extends Scene {
    // BufferedImages for menu items
    public BufferedImage title, onePlayer, twoPlayers, exit;
    // Rectangles to define the position and size of menu items
    public Rect titleRect, onePlayerRect, twoPlayersRect, exitRect;
    // KeyListener and MouseListener objects
    public KL keyListener;
    public ML mouseListener;

    // Constructor to initialize MenuScene with a key listener
    public MenuScene(KL keyListener, ML mouseListener) {
        // Initializing keyListener and mouseListener
        this.keyListener = keyListener;
        this.mouseListener = mouseListener;

        try {
            // Loading images for menu items from file
            title = ImageIO.read(new File("src/assets/snake_title.png"));
            onePlayer = ImageIO.read(new File("src/assets/players.png")).getSubimage(0, 0, 100, 200);
            twoPlayers = ImageIO.read(new File("src/assets/players.png"));
            exit = ImageIO.read(new File("src/assets/exit.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Defining rectangles for menu items
        titleRect = new Rect(355, 70, 300, 100);
        onePlayerRect = new Rect(470, 250, 70, 120);
        twoPlayersRect = new Rect(445, 400, 130, 120);
        exitRect = new Rect(460, 600, 130, 120);
    }
    @Override
    public void update(double deltaTime) {
        // Check if mouse is over the "One player" button and if it's pressed, change the game state
        if (mouseListener.getX() >= onePlayerRect.x && mouseListener.getX() <= onePlayerRect.x + onePlayerRect.width &&
            mouseListener.getY() >= onePlayerRect.y && mouseListener.getY() <= onePlayerRect.y + onePlayerRect.height) {
            if (mouseListener.isPressed()) {
                Window.getWindow().changeState(1);
            }
        }
        // Check if mouse is over the "Two players" button and if it's pressed, change the game state
        if (mouseListener.getX() >= twoPlayersRect.x && mouseListener.getX() <= twoPlayersRect.x + twoPlayersRect.width &&
                mouseListener.getY() >= twoPlayersRect.y && mouseListener.getY() <= twoPlayersRect.y + twoPlayersRect.height) {
            if (mouseListener.isPressed()) {
                Window.getWindow().changeState(1);
            }
        }
        // Check if mouse is over the "Exit" button and if it's pressed, close the game
        if (mouseListener.getX() >= exitRect.x && mouseListener.getX() <= exitRect.x + exitRect.width &&
                mouseListener.getY() >= exitRect.y && mouseListener.getY() <= exitRect.y + exitRect.height) {
            if (mouseListener.isPressed()) {
                Window.getWindow().close();
            }
        }
    }

    // Implementation of the draw method from the Scene class
    @Override
    public void draw(Graphics g) {
        // Set the color to blue
        g.setColor(Color.cyan);
        // Fill a rectangle to represent the menu scene
        g.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        // Draw menu items at their respective positions
        g.drawImage(title, (int)titleRect.x, (int)titleRect.y, (int)titleRect.width, (int)titleRect.height, null);
        g.drawImage(onePlayer, (int)onePlayerRect.x, (int)onePlayerRect.y, (int)onePlayerRect.width, (int)onePlayerRect.height, null);
        g.drawImage(twoPlayers, (int)twoPlayersRect.x, (int)twoPlayersRect.y, (int)twoPlayersRect.width, (int)twoPlayersRect.height, null);
        g.drawImage(exit, (int)exitRect.x, (int)exitRect.y, (int)exitRect.width, (int)exitRect.height, null);
    }
}
