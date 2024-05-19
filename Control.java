import java.awt.event.KeyEvent;

public class Control {
    public static void arrowsControl (KL keyListener, Snake snake) {
        if (keyListener.isKeyPressed(KeyEvent.VK_UP)) {
            snake.changeDirection(Direction.UP);
        } else if (keyListener.isKeyPressed(KeyEvent.VK_DOWN)) {
            snake.changeDirection(Direction.DOWN);
        } else if (keyListener.isKeyPressed(KeyEvent.VK_RIGHT)) {
            snake.changeDirection(Direction.RIGHT);
        } else if (keyListener.isKeyPressed(KeyEvent.VK_LEFT)) {
            snake.changeDirection(Direction.LEFT);
        }
    }

    public static void wasdControl (KL keyListener, Snake snake) {
        if (keyListener.isKeyPressed(KeyEvent.VK_W)) {
            snake.changeDirection(Direction.UP);
        } else if (keyListener.isKeyPressed(KeyEvent.VK_S)) {
            snake.changeDirection(Direction.DOWN);
        } else if (keyListener.isKeyPressed(KeyEvent.VK_D)) {
            snake.changeDirection(Direction.RIGHT);
        } else if (keyListener.isKeyPressed(KeyEvent.VK_A)) {
            snake.changeDirection(Direction.LEFT);
        }
    }

    public static void zqsdControl (KL keyListener, Snake snake) {
        if (keyListener.isKeyPressed(KeyEvent.VK_Z)) {
            snake.changeDirection(Direction.UP);
        } else if (keyListener.isKeyPressed(KeyEvent.VK_S)) {
            snake.changeDirection(Direction.DOWN);
        } else if (keyListener.isKeyPressed(KeyEvent.VK_D)) {
            snake.changeDirection(Direction.RIGHT);
        } else if (keyListener.isKeyPressed(KeyEvent.VK_Q)) {
            snake.changeDirection(Direction.LEFT);
        }
    }
}
