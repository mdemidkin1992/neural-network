import java.util.Random;

public class RandomExtension {
    private Random random = new Random();

    public double nextDouble(double minValue, double maxValue) {
        return minValue + (random.nextDouble() * (maxValue - minValue));
    }

    public int nextInt(int minValue, int maxValue) {
        return minValue + random.nextInt(maxValue);
    }
}
