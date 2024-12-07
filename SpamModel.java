import java.util.*;

public class SpamModel extends Model {
    private double[] coeff;
    private double intercept;
    private double changeRate;

    public SpamModel(double rate, DataSet training) {
        super(training);
        this.changeRate = rate;
        initCoefficients();
        for (int i = 0; i < 1000; i++) {
            updateCoeff();
            System.out.println("Iteration " + i + ": " + this);
        }
    }

    private void initCoefficients() {
        int numVars = trainingData.getNumIndependentVariables();
        coeff = new double[numVars];
        Random random = new Random();
        for (int i = 0; i < numVars; i++) {
            coeff[i] = -2.0 + 4.0 * random.nextDouble();
        }
        intercept = -2.0 + 4.0 * random.nextDouble();
    }

    private double sigmoid(double z) {
        return 1.0 / (1.0 + Math.exp(-z));
    }

    public double predict(double[] x) {
        double z = intercept;
        for (int i = 0; i < x.length; i++) {
            z += coeff[i] * x[i];
        }
        return sigmoid(z);
    }

    private void updateCoeff() {
        double[] pred = predict();
        ArrayList<DataRow> rows = trainingData.getRows();
        for (int i = 0; i < coeff.length; i++) {
            double sum = 0;
            for (int j = 0; j < pred.length; ++j) {
                DataRow row = rows.get(j);
                double x_i = row.getIndependentVariables()[i];
                sum += (pred[j] - row.getDependentVariable()) * x_i;
            }
            coeff[i] -= changeRate * sum / pred.length;
        }

        double sum = 0;
        for (int j = 0; j < pred.length; ++j) {
            DataRow row = rows.get(j);
            sum += pred[j] - row.getDependentVariable();
        }
        intercept -= changeRate * sum / pred.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < coeff.length; i++) {
            sb.append(coeff[i]).append("*X").append(i);
            if (i < coeff.length - 1) {
                sb.append(" + ");
            }
        }
        sb.append(" + ").append(intercept);
        return sb.toString();
    }
}
