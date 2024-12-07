import java.util.*;

public class Main {
    public static void main(String[] args) {
        EmailDataset dataset = new EmailDataset("spam_or_not_spam.csv");
        
        dataset.extractFeaturesForAll();
        
        dataset.exportFeaturesToCSV("email_features.csv");
        
        System.out.println("Features have been exported to 'email_features.csv'.");

        dataset.splitData(0.8);

        List<double[]> trainData = dataset.getTrainData();
        double[][] trainFeatures = new double[trainData.size()][];
        double[] trainLabels = new double[trainData.size()];
        for (int i = 0; i < trainData.size(); i++) {
            trainFeatures[i] = Arrays.copyOfRange(trainData.get(i), 0, trainData.get(i).length - 1);
            trainLabels[i] = trainData.get(i)[trainData.get(i).length - 1];
        }
        
        DataSet trainDataSet = new DataSet(trainFeatures, trainLabels);
        SpamModel model = new SpamModel(0.01, trainDataSet);
        
        List<double[]> testData = dataset.getTestData(); 
        int correctPredictions = 0;
        for (double[] test : testData) {
            double[] testFeatures = Arrays.copyOfRange(test, 0, test.length - 1);
            double actualLabel = test[test.length - 1];
            double prediction = model.predict(testFeatures); 
            
            if ((prediction > 0.5 && actualLabel == 1.0) || (prediction <= 0.5 && actualLabel == 0.0)) {
                correctPredictions++;
            }
        }

        System.out.println("Accuracy: " + (correctPredictions / (double) testData.size()) * 100 + "%");

        if (dataset.getEmails().size() > 1) {
            Email email1 = dataset.getEmails().get(0);
            Email email2 = dataset.getEmails().get(1);
            double distance = dataset.calculateDistance(email1.getFeatures(), email2.getFeatures());
            System.out.println("Distance between email 1 and email 2: " + distance);
        }
    }
}
