package access.assignment_problems;

public class LoanReceipt {

    private final String memberId;
    private final String[] bookIds;

    static {
        System.out.println("Loan receipt system initialized");
    }

    public LoanReceipt(String memberId, String[] bookIds) {

        if (bookIds == null || bookIds.length > 20) {
            throw new IllegalArgumentException("Invalid book list");
        }

        for (int i = 0; i < bookIds.length; i++) {

            if (!isValidBookId(bookIds[i])) {
                throw new IllegalArgumentException("Invalid book ID");
            }
        }

        this.memberId = memberId;
        this.bookIds = bookIds.clone();
    }

    private boolean isValidBookId(String id) {

        if (id == null || id.length() != 6) {
            return false;
        }

        if (!id.startsWith("BK-")) {
            return false;
        }

        for (int i = 3; i < 6; i++) {

            if (!Character.isDigit(id.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public String getMemberId() {
        return memberId;
    }

    public String[] getBookIds() {
        return bookIds.clone();
    }

    public LoanReceipt withCorrectedBookId(int index, String newId) {

        if (index < 0 || index >= bookIds.length) {
            throw new IllegalArgumentException("Invalid index");
        }

        if (!isValidBookId(newId)) {
            throw new IllegalArgumentException("Invalid book ID");
        }

        String[] correctedBooks = bookIds.clone();
        correctedBooks[index] = newId;

        return new LoanReceipt(memberId, correctedBooks);
    }

    public static String processNightlyCirculation(LoanReceipt[] receipts) {

        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        for (int i = 0; i < receipts.length; i++) {

            if (receipts[i] == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (receipts[i] instanceof ReferenceOnlyLoanReceipt) {
                referenceOnly++;
            } else {
                regular++;
            }
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + referenceOnly + " reference-only | "
                + regular + " regular";
    }

    public static void main(String[] args) {

        try {

            String[] invalidBooks = {
                    "BK-101",
                    "bad"
            };

            LoanReceipt receipt =
                    new LoanReceipt("LIB-100", invalidBooks);

            System.out.println("Construction succeeded");

        } catch (IllegalArgumentException e) {

            System.out.println("construction rejected");
        }

        String[] books = {
                "BK-101",
                "BK-202"
        };

        LoanReceipt receipt =
                new LoanReceipt("LIB-100", books);

        String[] returnedBooks = receipt.getBookIds();

        returnedBooks[0] = "BK-999";

        System.out.println(receipt.getBookIds()[0]);

        LoanReceipt corrected =
                receipt.withCorrectedBookId(0, "BK-303");

        System.out.println(corrected.getBookIds()[0]);

        ReferenceOnlyLoanReceipt referenceReceipt =
                new ReferenceOnlyLoanReceipt(
                        "LIB-200",
                        new String[]{"BK-404"},
                        "R-12"
                );

        LoanReceipt[] receipts = {
                receipt,
                referenceReceipt,
                null
        };

        System.out.println(
                processNightlyCirculation(receipts)
        );
    }
}