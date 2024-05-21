import java.awt.event.KeyEvent;

public class Control {
    public KL keyListener;

    public Control (KL keyListener) {
        this.keyListener = keyListener;
    }

    public void arrowsControl (Snake snake) {
        if (this.keyListener.isKeyPressed(KeyEvent.VK_UP)) {
            snake.changeDirection(Direction.UP);
        } else if (this.keyListener.isKeyPressed(KeyEvent.VK_DOWN)) {
            snake.changeDirection(Direction.DOWN);
        } else if (this.keyListener.isKeyPressed(KeyEvent.VK_RIGHT)) {
            snake.changeDirection(Direction.RIGHT);
        } else if (this.keyListener.isKeyPressed(KeyEvent.VK_LEFT)) {
            snake.changeDirection(Direction.LEFT);
        }
    }

    public void wasdControl (Snake snake) {
        if (this.keyListener.isKeyPressed(KeyEvent.VK_W)) {
            snake.changeDirection(Direction.UP);
        } else if (this.keyListener.isKeyPressed(KeyEvent.VK_S)) {
            snake.changeDirection(Direction.DOWN);
        } else if (this.keyListener.isKeyPressed(KeyEvent.VK_D)) {
            snake.changeDirection(Direction.RIGHT);
        } else if (this.keyListener.isKeyPressed(KeyEvent.VK_A)) {
            snake.changeDirection(Direction.LEFT);
        }
    }

    public void zqsdControl (Snake snake) {
        if (this.keyListener.isKeyPressed(KeyEvent.VK_Z)) {
            snake.changeDirection(Direction.UP);
        } else if (this.keyListener.isKeyPressed(KeyEvent.VK_S)) {
            snake.changeDirection(Direction.DOWN);
        } else if (this.keyListener.isKeyPressed(KeyEvent.VK_D)) {
            snake.changeDirection(Direction.RIGHT);
        } else if (this.keyListener.isKeyPressed(KeyEvent.VK_Q)) {
            snake.changeDirection(Direction.LEFT);
        }
    }
}
