import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<List<Integer>> data = new ArrayList<>();
        data.add(Arrays.asList(115, 66));
        data.add(Arrays.asList(175, 78));
        data.add(Arrays.asList(205, 72));
        data.add(Arrays.asList(120, 67));
        List<Double> answers = Arrays.asList(1.0,0.0,0.0,1.0); // 1 - female, 0 - male

        Network network = new Network();
        network.train(data, answers);

        System.out.println("");
        System.out.println(String.format("  male, 167, 73: %.10f", network.predict(167, 73)));
        System.out.println(String.format("female, 105, 67: %.10f", network.predict(105, 67)));
        System.out.println(String.format("female, 120, 62: %.10f", network.predict(120, 62)));
        System.out.println(String.format("  male, 143, 67: %.10f", network.predict(143, 67)));
        System.out.println(String.format("  male, 130, 66: %.10f", network.predict(130, 66)));

    }
}
