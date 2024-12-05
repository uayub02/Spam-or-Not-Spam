import java.util.*;
import java.io.*;

public class EmailDataset {

    private List<Email> emails;

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
        for (int i = 0; i < emails.size(); i++) {
            Email email = emails.get(i);
            email.extractFeatures();
        }
    }

    public Email getEmailAt(int index) {
        if (index >= 0 && index < emails.size()) {
            return emails.get(index);
        } else {
          System.out.println("Index out of range");
        }
    }
}

