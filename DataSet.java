import java.util.ArrayList;

public class DataSet {
    private double[][] features; // 2D array to store features (independent variables)
    private double[] labels;    // 1D array to store labels (dependent variables)

    public DataSet(double[][] features, double[] labels) {
        this.features = features;
        this.labels = labels;
    }


    public double[][] getFeatures() {
        return features;
    }


    public double[] getLabels() {
        return labels;
    }


    public int getNumIndependentVariables() {
        return features[0].length; 
    }


    public int getNumRows() {
        return features.length;
    }


    public ArrayList<DataRow> getRows() {
        ArrayList<DataRow> rows = new ArrayList<>();
        for (int i = 0; i < features.length; i++) {
            rows.add(new DataRow(features[i], labels[i]));
        }
        return rows;
    }
}
