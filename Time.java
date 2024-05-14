public class Time {
    public static double timeStarted = System.nanoTime();

    // Time in seconds while game has been started
    public static double getTime() {
        return (System.nanoTime() - timeStarted) * 1E-9;
    }
}
