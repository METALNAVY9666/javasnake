import java.awt.*;

public abstract class Snake {
    public Rect[] body = new Rect[100];
    // Width and height of each body segment
    public double bodyWidth, bodyHeight;
    // Size of the snake
    public int size;
    // Index of the tail and head of the snake
    public int tail = 0;
    public int head;

    // Direction of the snake
    public Direction direction = Direction.RIGHT;

    // Time variables for controlling the snake movement speed
    public double ogWaitBetweenUpdates = 0.2f;
    public double waitTimeLeft = ogWaitBetweenUpdates;

    public Snake(int size, double startX, double startY, double bodyWidth, double bodyHeight) {
        this.size = size;
        this.bodyWidth = bodyWidth;
        this.bodyHeight = bodyHeight;

        // Creating body segments and populating the body array
        for (int i = 0; i <= size; i++) {
            Rect bodyPiece = new Rect(startX + i * bodyWidth, startY, bodyWidth, bodyHeight);
            body[i] = bodyPiece;
            head++; // Incrementing head to point to the last element
        }
        head--; // Adjusting head to point to the correct index
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


    // Method to update the position and state of the snake
    public void update(double deltaTime) {
        // Check if the snake should move based on the time elapsed
        if (waitTimeLeft > 0) {
            waitTimeLeft -= deltaTime;
            return;
        }

        if (intersectingWithSelf()) {
            Window.getWindow().changeState(0);
        }



        // Reset the timer and calculate the new position for the head of the snake
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

    // Method to check if the snake intersects with itself
    public boolean intersectingWithSelf() {
        Rect head = body[this.head];
        // Iterate through all body segments except the head itself
        for (int i = tail; i != this.head - 1; i = (i + 1) % body.length) {
            // Check if the head intersects with any body segment
            if (intersecting(head, body[i])) { return true; }
        }
        return false;
    }

    // Method to check if two rectangles intersect
    public static boolean intersecting(Rect r1, Rect r2) {
        return (r1.x < r2.x + r2.width &&
                r1.x + r1.width > r2.x &&
                r1.y < r2.y + r2.height &&
                r1.y + r1.height > r2.y);
    }
    public abstract void draw(Graphics2D g2D);
}
