import java.util.ArrayList;

public abstract class Model {
    protected DataSet trainingData;

    public Model(DataSet training) {
        trainingData = training;
    }

    public abstract double predict(double[] x);

    protected double[] predict() {
        ArrayList<DataRow> rows = trainingData.getRows();
        double[] pred = new double[rows.size()];
        for (int i = 0; i < pred.length; ++i) {
            DataRow currRow = rows.get(i);
            double[] x = currRow.getIndependentVariables();
            pred[i] = predict(x);
        }
        return pred;
    }

    public double sumSquaredError() {
        double[] pred = predict();
        ArrayList<DataRow> rows = trainingData.getRows();
        double sum = 0;
        for (int j = 0; j < pred.length; ++j) {
            DataRow row = rows.get(j);
            double err = pred[j] - row.getDependentVariable();
            sum += err * err;
        }
        return sum;
    }
}
