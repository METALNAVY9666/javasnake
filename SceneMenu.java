import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import java.util.Scanner;

public class SceneMenu extends Scene {
    // BufferedImages for menu items
    public BufferedImage title, onePlayer, twoPlayers, AI, exit, difficulty;
    // Rectangles to define the position and size of menu items
    public Rect titleRect, onePlayerRect, twoPlayersRect, AIRect, exitRect, difficultyRect;

    public boolean difficultyPromptLock = false;

    // Constructor to initialize SceneMenu with a key listener
    public SceneMenu(KL keyListener, ML mouseListener) {
        // Initializing keyListener and mouseListener
        super(keyListener, mouseListener);

        try {

            // Loading images for menu items from file
            title = ImageIO.read(new File(getTexture("snake_title")));
            onePlayer = ImageIO.read(new File(getTexture("players"))).getSubimage(0, 0, 100, 200);
            twoPlayers = ImageIO.read(new File(getTexture("players")));
            AI = ImageIO.read(new File(getTexture("ai")));
            exit = ImageIO.read(new File(getTexture("exit")));
            difficulty = ImageIO.read(new File(getTexture("difficulty")));
        } catch (Exception e) {
            System.out.println(this.homePath);
            System.out.println(getTexture("snake_title"));
            e.printStackTrace();
        }

        // Defining rectangles for menu items
        titleRect = new Rect(250, 70, 300, 100);
        onePlayerRect = new Rect(360, 200, 60, 100);
        twoPlayersRect = new Rect(330, 320, 120, 100);
        AIRect = new Rect(320, 420, 150, 150);
        exitRect = new Rect(50, 520, 70, 70);
        difficultyRect = new Rect(720, 520, 64, 64);
    }

    // checks if a string is numeric
    public static boolean isNumeric(String strNum) {
        try {
            int n = Integer.parseInt(strNum);
            System.out.println("is numeric");
            return true;
        } catch (NumberFormatException nfe) {
            System.out.println("isn't numeric");
            return false;
        }
    }

    // Difficulty prompt
    public void setDifficulty() throws IOException {
        super.mouseListener.x = 0;
        super.mouseListener.y = 0;
        super.mouseListener.isReleased = false;
        super.mouseListener.isPressed = false;

        String difficultyText;
        int number;

        String sampleMessage = "How many turns for the snake to automatically grow ?";
        String[] messages = { sampleMessage, sampleMessage + "\nIntegers only.",
                sampleMessage + "\nIntegers greater than 3 " };
        int messageChoice = 0;
        this.difficultyPromptLock = true;
        while (true) {
            difficultyText = JOptionPane.showInputDialog(messages[messageChoice], getGrowFactor().toString());

            try {
                number = Integer.parseInt(difficultyText);
                if (number > 3) {
                    setGrowFactor(number);
                    return;
                } else {
                    messageChoice = 2;
                }
            } catch (NumberFormatException nfe) {
                messageChoice = 1;
            }
        }
    }

    public void setGrowFactor(int value) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("settings.txt"));
        writer.write(String.valueOf(value));
        writer.close();
    }

    public Integer getGrowFactor() throws IOException {
        File file = new File("settings.txt");
        Scanner sc = new Scanner(file);
        String text = "";
        while (sc.hasNextLine()) {
            text = text + sc.nextLine();
        }
        sc.close();
        return Integer.parseInt(text);
    }

    private void mouseOverOnePlayer() throws IOException {
        if (super.mouseListener.getX() >= onePlayerRect.x
                && super.mouseListener.getX() <= onePlayerRect.x + onePlayerRect.width &&
                super.mouseListener.getY() >= onePlayerRect.y
                && super.mouseListener.getY() <= onePlayerRect.y + onePlayerRect.height) {
            if (super.mouseListener.isPressed()) {
                Window.getWindow().changeState(1);
            }
        }
    }

    private void mouseOverTwoPlayers() throws IOException {
        if (super.mouseListener.getX() >= twoPlayersRect.x
                && super.mouseListener.getX() <= twoPlayersRect.x + twoPlayersRect.width &&
                super.mouseListener.getY() >= twoPlayersRect.y
                && super.mouseListener.getY() <= twoPlayersRect.y + twoPlayersRect.height) {
            if (super.mouseListener.isPressed()) {
                Window.getWindow().changeState(2);
            }
        }
    }

    private void mouseOverAI() throws IOException {
        if (super.mouseListener.getX() >= AIRect.x
                && super.mouseListener.getX() <= AIRect.x + AIRect.width &&
                super.mouseListener.getY() >= AIRect.y
                && super.mouseListener.getY() <= AIRect.y + AIRect.height) {
            if (super.mouseListener.isPressed()) {
                Window.getWindow().changeState(3);
            }
        }
    }

    private void mouseOverExit() throws IOException {
        if (super.mouseListener.getX() >= exitRect.x && super.mouseListener.getX() <= exitRect.x + exitRect.width &&
                super.mouseListener.getY() >= exitRect.y
                && super.mouseListener.getY() <= exitRect.y + exitRect.height) {
            if (super.mouseListener.isPressed()) {
                Window.getWindow().close();
            }
        }
    }

    private void mouseOverDifficulty() throws IOException {
        if (super.mouseListener.getX() >= difficultyRect.x
                && super.mouseListener.getX() <= difficultyRect.x + difficultyRect.width &&
                super.mouseListener.getY() >= difficultyRect.y
                && super.mouseListener.getY() <= difficultyRect.y + difficultyRect.height) {
            if (super.mouseListener.isReleased()) {
                this.setDifficulty();
            }
        }
    }

    @Override
    public void update(double deltaTime) throws IOException {
        // Check if mouse is over the "One player" button and if it's pressed, change
        // the game state
        this.mouseOverOnePlayer();

        // Check if mouse is over the "Two players" button and if it's pressed, change
        // the game state
        this.mouseOverTwoPlayers();

        // Check if mouse is over the "AI" button and if it's pressed, change
        // the game state
        this.mouseOverAI();

        // Check if mouse is over the "Exit" button and if it's pressed, close the game
        this.mouseOverExit();

        // Check if mouse is over the "Difficulty" button and if it's pressed, show the
        // difficulty prompt
        this.mouseOverDifficulty();
    }

    // Implementation of the draw method from the Scene class
    @Override
    public void draw(Graphics g) {
        // Set the color to blue
        g.setColor(Color.cyan);
        // Fill a rectangle to represent the menu scene
        g.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        // Draw menu items at their respective positions
        g.drawImage(title, (int) titleRect.x, (int) titleRect.y, (int) titleRect.width, (int) titleRect.height, null);
        g.drawImage(onePlayer, (int) onePlayerRect.x, (int) onePlayerRect.y, (int) onePlayerRect.width,
                (int) onePlayerRect.height, null);
        g.drawImage(twoPlayers, (int) twoPlayersRect.x, (int) twoPlayersRect.y, (int) twoPlayersRect.width,
                (int) twoPlayersRect.height, null);
        g.drawImage(AI, (int) AIRect.x, (int) AIRect.y, (int) AIRect.width, (int) AIRect.height, null);
        g.drawImage(exit, (int) exitRect.x, (int) exitRect.y, (int) exitRect.width, (int) exitRect.height, null);
        g.drawImage(difficulty, (int) difficultyRect.x, (int) difficultyRect.y, (int) difficultyRect.width,
                (int) difficultyRect.height, null);
    }
}
