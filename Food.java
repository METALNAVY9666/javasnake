import java.awt.Graphics2D;
import java.awt.Color;

public class Food {
    public Rect background;
    public Snake[] snakes;
    public int width, height;
    public Color color;
    public Rect rect;

    public int xPadding;

    public boolean spawned = false;

    public Food(Rect background, Snake[] snakes, int width, int height, Color color) {
        this.background = background; // background where the render is made
        this.snakes = snakes; // snake
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
        } while (this.intersectingWithSnake());
        this.spawned = true;
    }

    private boolean intersectingWithSnake() {
        for (Snake snake : this.snakes) {
            if (snake.intersectingWithRect(this.rect)) {
                return true; // если есть пересечение, вернем true
            }
        }
        return false;
    }

    // explicit
    public void update(double dt) {
        for (Snake snake : this.snakes) {
            if (snake.intersectingWithRect(this.rect)) {
                snake.shrink();
                // move the food away from the screen
                this.rect.x = -10000;
                this.rect.y = -10000;
                spawned = false;
                System.out.println("Nyam");
                break;
            }
        }
    }

    // draws the food on the screen
    public void draw(Graphics2D g2) {
        g2.setColor(color);
        g2.fillRect((int) this.rect.x + xPadding, (int) this.rect.y + xPadding, width, height);
    }
}