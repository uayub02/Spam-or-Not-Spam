import java.util.*;

public class Main {
    public static void main(String[] args) {
        EmailDataset dataset = new EmailDataset("emails.csv");
        dataset.extractFeaturesForAll();
        dataset.exportFeaturesToCSV("email_features.csv");

        // Example of calculating distance between two emails
        if (dataset.getEmails().size() > 1) {
            Email email1 = dataset.getEmails().get(0);
            Email email2 = dataset.getEmails().get(1);
            double distance = dataset.calculateDistance(email1.getFeatures(), email2.getFeatures());
            System.out.println("Distance between email 1 and email 2: " + distance);
        }

        // Placeholder for model training and prediction
        // You can implement your own logic here
    }
} 