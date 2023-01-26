import java.util.List;

public class Util {
    public static double sigmoid(double in) {
        return 1 / (1 + Math.exp(-in));
    }

    public static double meanSquareLoss(List<Double> correctAnswers, List<Double> predictedAnswers) {
        double squareError = 0;
        for (int i = 0; i < correctAnswers.size(); i++) {
            double error = correctAnswers.get(i) - predictedAnswers.get(i);
            squareError += (error * error);
        }
        return squareError / correctAnswers.size();
    }
}
