package array.assignment_problems;

public class TypingAccuracyChecker {

    void checkTypingAccuracy(String original, String typed) {

        int matched = 0;
        int firstMismatch = -1;

        for (int i = 0; i < original.length(); i++) {

            if (original.charAt(i) == typed.charAt(i)) {
                matched++;
            } else if (firstMismatch == -1) {
                firstMismatch = i;
            }
        }

        double accuracy = ((double) matched / original.length()) * 100;

        System.out.printf("Accuracy: %.2f%%%n", accuracy);

        if (firstMismatch == -1) {
            System.out.println("No Mismatches Found");
        } else {
            System.out.println("First Mismatch Position: " + (firstMismatch + 1));
            System.out.println("Original Character: " + original.charAt(firstMismatch));
            System.out.println("Typed Character: " + typed.charAt(firstMismatch));
        }
    }

    public static void main(String[] args) {

        TypingAccuracyChecker sc = new TypingAccuracyChecker();

        String original = "hello world";
        String typed = "hello worlt";

        sc.checkTypingAccuracy(original, typed);
    }
}