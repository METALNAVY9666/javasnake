import java.io.IOException;

public class SnakeIA extends Snake {

    public SnakeIA(int id, int size, double startX, double startY, double bodyWidth, double bodyHeight, Rect background)
            throws IOException {
        super(id, size, startX, startY, bodyWidth, bodyHeight, background);
    }

    private Rect findNearestFood() {
        Rect nearestFood = null;
        double minDistance = Double.MAX_VALUE;

        // Loop through all the food items to find the nearest one
        for (Food food : foods) {
            double distance = Math
                    .sqrt(Math.pow(body[head].x - food.rect.x, 2) + Math.pow(body[head].y - food.rect.y, 2));
            if (distance < minDistance) {
                minDistance = distance;
                nearestFood = food.rect;
            }
        }

        return nearestFood;
    }

    private void findFoodDirection(Rect food) {
        double headX = body[head].x;
        double headY = body[head].y;

        // Calculate the difference between the coordinates of the head and the food
        double diffX = food.x - headX;
        double diffY = food.y - headY;

        // If the food is above the snake's head, move up
        if (diffY < 0 && direction != Direction.DOWN && headY - bodyHeight >= background.y) {
            super.changeDirection(Direction.UP);
        }
        // If the food is below the snake's head, move down
        else if (diffY > 0 && direction != Direction.UP && headY + bodyHeight <= background.y + background.height) {
            super.changeDirection(Direction.DOWN);
        }
        // If the food is to the left of the snake's head, move left
        else if (diffX < 0 && direction != Direction.RIGHT && headX - bodyWidth >= background.x) {
            super.changeDirection(Direction.LEFT);
        }
        // If the food is to the right of the snake's head, move right
        else if (diffX > 0 && direction != Direction.LEFT && headX + bodyWidth <= background.x + background.width) {
            super.changeDirection(Direction.RIGHT);
        }
    }

    @Override
    public void update(double deltaTime) throws IOException {
        Rect nearestFood = findNearestFood();
        if (nearestFood != null) {
            this.findFoodDirection(nearestFood);
        }
        super.update(deltaTime);
    }
}