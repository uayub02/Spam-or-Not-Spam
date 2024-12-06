import java.util.HashMap;
import java.util.Map;

public class Email {
    private String content;
    private boolean isSpam;
    private Map<String, Double> features;

    public Email(String content, boolean isSpam) {
        this.content = content;
        this.isSpam = isSpam;
        this.features = new HashMap<>();
    }

    public void extractFeatures() {
        String[] words = content.split("\\s+");
        features.put("word_count", (double) words.length);

        for (int i = 0; i < words.length - 1; i++) {
            String bigram = words[i] + " " + words[i + 1];
            features.put("bigram_" + bigram, features.getOrDefault("bigram_" + bigram, 0.0) + 1);
        }
    }

    public Map<String, Double> getFeatures() {
        return features;
    }

    public boolean isSpam() {
        return isSpam;
    }
} 