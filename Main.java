import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Window window = Window.getWindow();
        Thread thread = new Thread(window);
        thread.start();
    }
}
