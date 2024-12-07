import java.util.*;
import java.io.*;

public class EmailDataset {
    private List<Email> emails;
    private List<double[]> trainData;
    private List<double[]> testData;

    public EmailDataset(String filename) {
        emails = new ArrayList<>();
        try {
            Scanner fileScanner = new Scanner(new File(filename));

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",", 2);
                if (parts.length == 2) {
                    boolean isSpam = parts[0].equalsIgnoreCase("spam");
                    emails.add(new Email(parts[1], isSpam));
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        }
    }

    public List<Email> getEmails() {
        return emails;
    }


    public void extractFeaturesForAll() {
        for (Email email : emails) {
            email.extractFeatures();
        }
    }


    public void exportFeaturesToCSV(String filename) {
        try (PrintWriter writer = new PrintWriter(new File(filename))) {
            StringBuilder sb = new StringBuilder();
            sb.append("EmailID,WordCount,...\n");

            for (int i = 0; i < emails.size(); i++) {
                Email email = emails.get(i);
                Map<String, Double> features = email.getFeatures();
                sb.append(i).append(",");
                for (Double value : features.values()) {
                    sb.append(value).append(",");
                }
                sb.append("\n");
            }

            writer.write(sb.toString());
        } catch (FileNotFoundException e) {
            System.out.println("Error: Unable to write to file.");
        }
    }


    public double calculateDistance(Map<String, Double> features1, Map<String, Double> features2) {
        double sum = 0.0;
        Set<String> allKeys = new HashSet<>(features1.keySet());
        allKeys.addAll(features2.keySet());

        for (String key : allKeys) {
            double value1 = features1.getOrDefault(key, 0.0);
            double value2 = features2.getOrDefault(key, 0.0);
            sum += Math.pow(value1 - value2, 2);
        }

        return Math.sqrt(sum);
    }


    public List<double[]> getFeatureVectorsAndLabels() {
        List<double[]> featureVectors = new ArrayList<>();
        for (Email email : emails) {
            Map<String, Double> features = email.getFeatures();
            double[] featureVector = new double[features.size() + 1];

            int index = 0;
            for (Double value : features.values()) {
                featureVector[index++] = value;
            }

            featureVector[index] = email.isSpam() ? 1.0 : 0.0;
            featureVectors.add(featureVector);
        }
        return featureVectors;
    }

    public void splitData(double trainingFraction) {
        List<double[]> featureVectorsAndLabels = getFeatureVectorsAndLabels();
        int trainSize = (int) (featureVectorsAndLabels.size() * trainingFraction);
        trainData = featureVectorsAndLabels.subList(0, trainSize);
        testData = featureVectorsAndLabels.subList(trainSize, featureVectorsAndLabels.size());
    }

    public List<double[]> getTrainData() {
        return trainData;
    }


    public List<double[]> getTestData() {
        return testData;
    }


    public Email getEmailAt(int index) {
        if (index >= 0 && index < emails.size()) {
            return emails.get(index);
        } else {
            System.out.println("Index out of range");
        }
        return null;
    }
}