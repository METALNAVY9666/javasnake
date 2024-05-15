import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

public class SnakeRed extends Snake {
    public SnakeRed(int size, double startX, double startY, double bodyWidth, double bodyHeight, Rect background)
            throws IOException {
        super(size, startX, startY, bodyWidth, bodyHeight, background);
    }

    @Override
    public void draw(Graphics2D g2D) {
        for (int i = tail; i != head; i = (i + 1) % body.length) {
            Rect piece = body[i];
            // Calculating dimensions for drawing the segment
            double subWidth = (piece.width - 6.0) / 2.0;
            double subHeight = (piece.height - 6.0) / 2.0;

            // Setting color for a snake
            g2D.setColor(Color.RED);

            // Drawing rectangles to represent the segment
            g2D.fill(new Rectangle2D.Double(piece.x + 2.0, piece.y + 2.0, subWidth, subHeight));
            g2D.fill(new Rectangle2D.Double(piece.x + 4.0 + subWidth, piece.y + 2.0, subWidth, subHeight));
            g2D.fill(new Rectangle2D.Double(piece.x + 2.0, piece.y + 4.0 + subHeight, subWidth, subHeight));
            g2D.fill(new Rectangle2D.Double(piece.x + 4.0 + subWidth, piece.y + 4.0 + subHeight, subWidth, subHeight));
        }
    }
}
