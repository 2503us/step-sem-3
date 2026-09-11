package array.assignment_problems;

public class MovieReviewWordLengthProfiler {

    void classifyWordLengths(String review) {

        String[] words = review.split(" ");

        int shortWords = 0;
        int mediumWords = 0;
        int longWords = 0;

        for (int i = 0; i < words.length; i++) {

            int length = words[i].length();

            if (length >= 1 && length <= 4) {
                shortWords++;
            } else if (length >= 5 && length <= 8) {
                mediumWords++;
            } else {
                longWords++;
            }
        }

        System.out.println("Short Words: " + shortWords);
        System.out.println("Medium Words: " + mediumWords);
        System.out.println("Long Words: " + longWords);
    }

    public static void main(String[] args) {

        MovieReviewWordLengthProfiler sc = new MovieReviewWordLengthProfiler();

        String review = "This movie was absolutely fantastic and thrilling";

        sc.classifyWordLengths(review);
    }
}