import java.awt.Graphics2D;
import java.awt.Color;

public class Food {
    public Rect background;
    public Snake snake;
    public int width, height;
    public Color color;
    public Rect rect;

    public int xPadding;

    public boolean spawned = false;

    public Food(Rect background, Snake snake, int width, int height, Color color) {
        this.background = background; // background where the render is made
        this.snake = snake; // shnake
        this.width = width; // width of the food
        this.height = height; // height of the food
        this.color = color; // color of the food
        this.rect = new Rect(0, 0, width, height);

        this.xPadding = (int) ((Constants.TILE_WIDTH - this.width) / 2.0);
    }

    // appears the food on the rect
    public void spawn() {
        do {
            this.rect.x = (int) (Math.random() * (int) (this.background.width / Constants.TILE_WIDTH))
                    * Constants.TILE_WIDTH + this.background.x;
            this.rect.y = (int) (Math.random() * (int) (this.background.height / Constants.TILE_WIDTH))
                    * Constants.TILE_WIDTH + this.background.y;
        } while (snake.intersectingWithRect(this.rect));
        this.spawned = true;

    }

    // explicit
    public void update(double dt) {
        if (this.snake.intersectingWithRect(this.rect)) {
            this.snake.grow();
            // move the food away from the screen
            this.rect.x = -100;
            this.rect.y = -100;
            spawned = false;
        }
    }

    // draws the food on the screen
    public void draw(Graphics2D g2) {
        g2.setColor(color);
        g2.fillRect((int) this.rect.x + xPadding, (int) this.rect.y + xPadding, width, height);
    }
}
