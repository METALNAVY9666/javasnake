import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Snake {
    // Array of body segments of the snake
    public Rect[] body = new Rect[100];
    // Width and height of each body segment
    public double bodyWidth, bodyHeight;
    // Size of the snake
    public int size;
    // Index of the tail and head of the snake
    public int tail = 0;
    public int head;

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

    // Method to draw the snake
    public void draw(Graphics2D g2D) {
        // Iterating through the body segments of the snake
        for (int i = tail; i != head; i = (i + 1) % body.length) {
            Rect piece = body[i];
            // Calculating dimensions for drawing the segment
            double subWidth = (piece.width - 6.0) / 2.0;
            double subHeight = (piece.height - 6.0) / 2.0;

            // Setting color based on whether the segment is the head or not
            if (i == 0) { g2D.setColor(Color.DARK_GRAY); } else { g2D.setColor(Color.WHITE); }

            // Drawing rectangles to represent the segment
            g2D.fill(new Rectangle2D.Double(piece.x + 2.0, piece.y + 2.0, subWidth, subHeight));
            g2D.fill(new Rectangle2D.Double(piece.x + 4.0 + subWidth, piece.y + 2.0, subWidth, subHeight));
            g2D.fill(new Rectangle2D.Double(piece.x + 2.0, piece.y + 4.0 + subHeight, subWidth, subHeight));
            g2D.fill(new Rectangle2D.Double(piece.x + 4.0 + subWidth, piece.y + 4.0 + subHeight , subWidth, subHeight));
        }
    }
}
