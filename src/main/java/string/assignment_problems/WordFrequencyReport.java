package string.assignment_problems;

import java.util.HashMap;
import java.util.Map;

public class WordFrequencyReport {

    void printFilteredWordFrequency(String feedback) {

        String cleaned = feedback
                .toLowerCase()
                .replace(".", "")
                .replace(",", "");

        String[] words = cleaned.split("\\s+");

        String[] stopWords = {
                "the", "was", "and", "a", "is", "of", "in"
        };

        HashMap<String, Integer> frequency = new HashMap<>();

        for (String word : words) {

            boolean isStopWord = false;

            for (String stopWord : stopWords) {
                if (word.equals(stopWord)) {
                    isStopWord = true;
                    break;
                }
            }

            if (!isStopWord && !word.isEmpty()) {
                frequency.put(
                        word,
                        frequency.getOrDefault(word, 0) + 1
                );
            }
        }

        String[] uniqueWords =
                frequency.keySet().toArray(new String[0]);

        for (int i = 0; i < uniqueWords.length - 1; i++) {

            for (int j = 0; j < uniqueWords.length - 1 - i; j++) {

                if (frequency.get(uniqueWords[j]) <
                        frequency.get(uniqueWords[j + 1])) {

                    String temp = uniqueWords[j];
                    uniqueWords[j] = uniqueWords[j + 1];
                    uniqueWords[j + 1] = temp;
                }
            }
        }

        for (String word : uniqueWords) {
            System.out.println(
                    word + ": " + frequency.get(word)
            );
        }
    }

    public static void main(String[] args) {

        WordFrequencyReport report =
                new WordFrequencyReport();

        report.printFilteredWordFrequency(
                "The mentor was great, the session was great and clear."
        );
    }
}