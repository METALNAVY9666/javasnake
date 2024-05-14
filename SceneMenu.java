import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;


public class SceneMenu extends Scene {
    // BufferedImages for menu items
    public BufferedImage title, onePlayer, twoPlayers, exit;
    // Rectangles to define the position and size of menu items
    public Rect titleRect, onePlayerRect, twoPlayersRect, exitRect;

    // Constructor to initialize SceneMenu with a key listener
    public SceneMenu(KL keyListener, ML mouseListener) {
        // Initializing keyListener and mouseListener
        super(keyListener, mouseListener);

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
        titleRect = new Rect(250, 70, 300, 100);
        onePlayerRect = new Rect(360, 200, 60, 100);
        twoPlayersRect = new Rect(330, 320, 120, 100);
        exitRect = new Rect(350, 450, 100, 100);
    }
    @Override
    public void update(double deltaTime) {
        // Check if mouse is over the "One player" button and if it's pressed, change the game state
        if (super.mouseListener.getX() >= onePlayerRect.x && super.mouseListener.getX() <= onePlayerRect.x + onePlayerRect.width &&
            super.mouseListener.getY() >= onePlayerRect.y && super.mouseListener.getY() <= onePlayerRect.y + onePlayerRect.height) {
            if (super.mouseListener.isPressed()) {
                Window.getWindow().changeState(1);
            }
        }
        // Check if mouse is over the "Two players" button and if it's pressed, change the game state
        if (super.mouseListener.getX() >= twoPlayersRect.x && super.mouseListener.getX() <= twoPlayersRect.x + twoPlayersRect.width &&
            super.mouseListener.getY() >= twoPlayersRect.y && super.mouseListener.getY() <= twoPlayersRect.y + twoPlayersRect.height) {
            if (super.mouseListener.isPressed()) {
                Window.getWindow().changeState(2);
            }
        }
        // Check if mouse is over the "Exit" button and if it's pressed, close the game
        if (super.mouseListener.getX() >= exitRect.x && super.mouseListener.getX() <= exitRect.x + exitRect.width &&
            super.mouseListener.getY() >= exitRect.y && super.mouseListener.getY() <= exitRect.y + exitRect.height) {
            if (super.mouseListener.isPressed()) {
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
