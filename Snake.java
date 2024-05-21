import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Snake {
    public Rect[] body = new Rect[1000];
    // Width and height of each body segment
    public double bodyWidth, bodyHeight;
    // Size of the snake
    public int size;
    // Index of the tail and head of the snake
    public int tail = 0;
    public int head;
    // auto-grow counter
    public int autoGrow = 0;

    // Direction of the snake
    public Direction direction = Direction.RIGHT;

    // Time variables for controlling the snake movement speed
    public double ogWaitBetweenUpdates = 0.1f;
    public double waitTimeLeft = ogWaitBetweenUpdates;
    public Rect background;
    public int growFactor;

    public Food[] foods;

    public Snake(int size, double startX, double startY, double bodyWidth, double bodyHeight, Rect background)
            throws IOException {
        this.size = size;
        this.bodyWidth = bodyWidth;
        this.bodyHeight = bodyHeight;
        this.background = background;
        this.growFactor = getGrowFactor();

        // Creating body segments and populating the body array
        for (int i = 0; i <= size; i++) {
            Rect bodyPiece = new Rect(startX + i * bodyWidth, startY, bodyWidth, bodyHeight);
            body[i] = bodyPiece;
            head++; // Incrementing head to point to the last element
        }
        head--; // Adjusting head to point to the correct index
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

    public void setFoods(Food[] foods) {
        this.foods = foods;
    }

    // Method to change the direction of the snake
    public void changeDirection(Direction d) {
        if (d == Direction.RIGHT && direction != Direction.LEFT) {
            direction = d;
        } else if (d == Direction.LEFT && direction != Direction.RIGHT) {
            direction = d;
        } else if (d == Direction.UP && direction != Direction.DOWN) {
            direction = d;
        } else if (d == Direction.DOWN && direction != Direction.UP) {
            direction = d;
        }
    }

    private void updateSnakePosition() {
        waitTimeLeft = ogWaitBetweenUpdates;
        double newX = 0;
        double newY = 0;

        if (direction == Direction.RIGHT) {
            newX = body[head].x + bodyWidth;
            newY = body[head].y;
        } else if (direction == Direction.LEFT) {
            newX = body[head].x - bodyWidth;
            newY = body[head].y;
        } else if (direction == Direction.UP) {
            newX = body[head].x;
            newY = body[head].y - bodyHeight;
        } else if (direction == Direction.DOWN) {
            newX = body[head].x;
            newY = body[head].y + bodyHeight;
        }

        // Move the snake's head to the new position
        body[(head + 1) % body.length] = body[tail];
        body[tail] = null;
        head = (head + 1) % body.length;
        tail = (tail + 1) % body.length;

        body[head].x = newX;
        body[head].y = newY;
    }

    public void grow() {
        double newX = 0;
        double newY = 0;

        if (direction == Direction.RIGHT) {
            newX = body[tail].x - bodyWidth;
            newY = body[tail].y;
        } else if (direction == Direction.LEFT) {
            newX = body[tail].x + bodyWidth;
            newY = body[tail].y;
        } else if (direction == Direction.UP) {
            newX = body[tail].x;
            newY = body[tail].y + bodyHeight;
        } else if (direction == Direction.DOWN) {
            newX = body[tail].x;
            newY = body[tail].y - bodyHeight;
        }
        Rect newBodyPiece = new Rect(newX, newY, bodyWidth, bodyHeight);

        int newTail = (tail - 1 + body.length) % body.length;
        body[newTail] = newBodyPiece;
        tail = newTail;
    }

    public void shrink() {
        if ((this.head - this.tail) < 2) {
            return;
        }
        this.body[this.tail] = null;
        this.tail++;
    }

    // Method to check if the snake intersects with itself
    public boolean intersectingWithSelf() {
        return intersectingWithRect(body[head]) || intersectingWithScreenBoundaries(body[head]);
    }

    // checks if the snake is intersecting with a rect
    public boolean intersectingWithRect(Rect rect) {
        for (int i = tail; i != head; i = (i + 1) % body.length) {
            if (intersecting(rect, body[i]))
                return true;
        }
        return false;
    }

    // Method to check if two rectangles intersect
    public static boolean intersecting(Rect r1, Rect r2) {
        if (r1 == null || r2 == null) {
            return false;
        }

        return (r1.x < r2.x + r2.width &&
                r1.x + r1.width > r2.x &&
                r1.y < r2.y + r2.height &&
                r1.y + r1.height > r2.y);
    }

    public boolean intersectingWithScreenBoundaries(Rect head) {
        return (head.x < background.x || (head.x + head.width) > background.x + background.width ||
                head.y < background.y || (head.y + head.height) > background.y + background.height);
    }

    // Method to update the position and state of the snake
    public void update(double deltaTime) throws IOException {
        // Check if the snake should move based on the time elapsed
        if (waitTimeLeft > 0) {
            waitTimeLeft -= deltaTime;
            return;
        }

        autoGrow++;
        if (this.autoGrow == this.growFactor) {
            this.autoGrow = 0;
            this.grow();
        }

        if (intersectingWithSelf()) {
            Window.getWindow().changeState(4);
        }

        // Reset the timer and calculate the new position for the head of the snake
        this.updateSnakePosition();
    }

    public void draw(Graphics2D g2D, Color color) {
        for (int i = tail; i != head; i = (i + 1) % body.length) {
            Rect piece = body[i];
            // Calculating dimensions for drawing the segment
            double subWidth = (piece.width - 6.0) / 2.0;
            double subHeight = (piece.height - 6.0) / 2.0;

            // Setting color for a snake
            g2D.setColor(color);

            // Drawing rectangles to represent the segment
            g2D.fill(new Rectangle2D.Double(piece.x + 2.0, piece.y + 2.0, subWidth, subHeight));
            g2D.fill(new Rectangle2D.Double(piece.x + 4.0 + subWidth, piece.y + 2.0, subWidth, subHeight));
            g2D.fill(new Rectangle2D.Double(piece.x + 2.0, piece.y + 4.0 + subHeight, subWidth, subHeight));
            g2D.fill(new Rectangle2D.Double(piece.x + 4.0 + subWidth, piece.y + 4.0 + subHeight, subWidth, subHeight));
        }
    }
}
