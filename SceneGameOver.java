import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SceneGameOver extends Scene {

    public BufferedImage gameOverImage;

    public Rect gameOverRect;

    // time elapsed since the end of the game in ms
    public double deltaSum = 0;

    public SceneGameOver() {
        try {
            this.gameOverImage = ImageIO.read(new File(getTexture("gameover")));
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.gameOverRect = new Rect(0, 0, 360, 240);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        g.drawImage(gameOverImage, (int) gameOverRect.x, (int) gameOverRect.y, (int) gameOverRect.width,
                (int) gameOverRect.height, null);

    };

    @Override
    public void update(double deltaTime) throws IOException {
        this.deltaSum += deltaTime;
        if (deltaSum > 5) {
            Window.getWindow().changeState(0);
        }
    };

}
