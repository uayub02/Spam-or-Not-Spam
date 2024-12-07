public class DataRow {
    private double[] independentVariables; // Features of the email
    private double dependentVariable;      // Label of the email (spam or not)

    public DataRow(double[] independentVariables, double dependentVariable) {
        this.independentVariables = independentVariables;
        this.dependentVariable = dependentVariable;
    }


    public double[] getIndependentVariables() {
        return independentVariables;
    }


    public double getDependentVariable() {
        return dependentVariable;
    }
}
