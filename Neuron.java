import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Neuron {
    RandomExtension random = new RandomExtension();
    private Double oldBias = random.nextDouble(-1, 1), bias = random.nextDouble(-1, 1);
    private Double oldWeight1 = random.nextDouble(-1, 1), weight1 = random.nextDouble(-1, 1);
    private Double oldWeight2 = random.nextDouble(-1, 1), weight2 = random.nextDouble(-1, 1);

    public double compute(double input1, double input2) {
        double preActivation = (input1 * this.weight1) + (input2 * this.weight2) + bias;
        double output = Util.sigmoid(preActivation);
        return output;
    }

    public void mutate(Double learnFactor) {
        int propertyToChange = random.nextInt(0, 3);
        Double changeFactor = (learnFactor == null) ? random.nextDouble(-1, 1) : (learnFactor * random.nextDouble(-1, 1));
        if (propertyToChange == 0) {
            this.bias += changeFactor;
        } else if (propertyToChange == 1) {
            this.weight1 += changeFactor;
        } else {
            this.weight2 += changeFactor;
        }
    }

    public void forget() {
        bias = oldBias;
        weight1 = oldWeight1;
        weight2 = oldWeight2;
    }

    public void remember() {
        oldBias = bias;
        oldWeight1 = weight1;
        oldWeight2 = weight2;
    }

}

class Network {
    int epochs = 1000;
    Double learnFactor = null;
    List<Neuron> neurons = Arrays.asList(
            new Neuron(), new Neuron(), new Neuron(),   // input nodes
            new Neuron(), new Neuron(),                 // hidden nodes
            new Neuron());                              // output node

    public Double predict(Integer input1, Integer input2) {
        return neurons.get(5).compute(
                neurons.get(4).compute(
                        neurons.get(2).compute(input1, input2),
                        neurons.get(1).compute(input1, input2)),
                neurons.get(3).compute(
                        neurons.get(1).compute(input1, input2),
                        neurons.get(0).compute(input1, input2)
                )
        );
    }

    public void train(List<List<Integer>> data, List<Double> answers) {
        Double bestEpochLoss = null;
        for (int epoch = 0; epoch < epochs; epoch++) {
            // adapt neuron
            Neuron epochNeuron = neurons.get(epoch % 6);
            epochNeuron.mutate(this.learnFactor);

            List<Double> predictions = new ArrayList<>();
            for (int i = 0; i < data.size(); i++) {
                predictions.add(i, this.predict(data.get(i).get(0), data.get(i).get(1)));
            }
            Double thisEpochLoss = Util.meanSquareLoss(answers, predictions);

            if (bestEpochLoss == null) {
                bestEpochLoss = thisEpochLoss;
                epochNeuron.remember();
            } else if (thisEpochLoss < bestEpochLoss) {
                bestEpochLoss = thisEpochLoss;
                epochNeuron.remember();
            } else {
                epochNeuron.forget();
            }

            if (epoch % 10 == 0) {
                System.out.printf("Epoch: %s | bestEpochLoss: %.10f | thisEpochLoss: %.10f\n",
                        epoch, bestEpochLoss, thisEpochLoss);
            }
        }
    }
}
