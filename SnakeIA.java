import java.io.IOException;

public class SnakeIA extends Snake {

    public SnakeIA(int size, double startX, double startY, double bodyWidth, double bodyHeight, Rect background)
            throws IOException {
        super(size, startX, startY, bodyWidth, bodyHeight, background);
    }

    private Rect findNearestFood() {
        Rect nearestFood = null;
        double minDistance = Double.MAX_VALUE;

        for (Food food : foods) {
            double distance = Math.sqrt(Math.pow(body[head].x - food.rect.x, 2) + Math.pow(body[head].y - food.rect.y, 2));
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

        // Рассчитываем разницу между координатами головы и еды
        double diffX = food.x - headX;
        double diffY = food.y - headY;

        // Если еда находится выше головы змеи, двигаемся вверх
        if (diffY < 0 && direction != Direction.DOWN && headY - bodyHeight >= background.y) {
            super.changeDirection(Direction.UP);
        }
        // Если еда находится ниже головы змеи, двигаемся вниз
        else if (diffY > 0 && direction != Direction.UP && headY + bodyHeight <= background.y + background.height) {
            super.changeDirection(Direction.DOWN);
        }
        // Если еда находится левее головы змеи, двигаемся влево
        else if (diffX < 0 && direction != Direction.RIGHT && headX - bodyWidth >= background.x) {
            super.changeDirection(Direction.LEFT);
        }
        // Если еда находится правее головы змеи, двигаемся вправо
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
