package inheritance.assignment_problems;

public class RaceEntry {

    private String bibNumber;
    private double entryFee;
    private double balanceDue;

    private final String entryCode;

    private static int bibCounter = 0;

    private double[] lateFeeHistory = new double[10];
    private int lateFeeHistoryCount = 0;

    public RaceEntry(String bibNumber, double entryFee) {

        if (bibNumber == null || bibNumber.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid bib number");
        }

        this.bibNumber = bibNumber;
        this.entryFee = entryFee;
        this.balanceDue = entryFee;

        bibCounter++;
        entryCode = "ENTRY" + bibCounter;
    }

    public void pay(double amount) {
        balanceDue = balanceDue - amount;
    }

    public void pay(double amount, String mode) {
        pay(amount);

        System.out.println(
                "Payment of " + amount
                        + " received by " + mode
        );
    }

    public double getBalanceDue() {
        return balanceDue;
    }

    protected void applyLateFee(double amount) {

        balanceDue = balanceDue + amount;

        lateFeeHistory[lateFeeHistoryCount] = amount;
        lateFeeHistoryCount++;
    }

    public double[] getLateFeeHistory() {

        double[] copy = new double[lateFeeHistoryCount];

        for (int i = 0; i < lateFeeHistoryCount; i++) {
            copy[i] = lateFeeHistory[i];
        }

        return copy;
    }

    public String getBibNumber() {
        return bibNumber;
    }

    public double getEntryFee() {
        return entryFee;
    }

    public String getEntryCode() {
        return entryCode;
    }

    public String announce() {

        return "Race Entry | Bib: " + bibNumber
                + " | Entry Code: " + entryCode
                + " | Balance: " + balanceDue;
    }

    public static String registerBatch(
            String[] bibNumbers,
            double entryFee) {

        int registered = 0;
        int rejected = 0;

        for (int i = 0; i < bibNumbers.length; i++) {

            try {
                new RaceEntry(bibNumbers[i], entryFee);
                registered++;

            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Registered: " + registered
                + " | Rejected: " + rejected;
    }

    public static String classifyGeneration(RaceEntry entry) {

        if (entry instanceof EliteRunnerEntry) {
            return "Multilevel descendant (3 generations deep)";
        }

        if (entry instanceof RelayTeamEntry) {
            return "Hierarchical sibling (independent branch)";
        }

        return "Base entry";
    }

    public static double getTotalBalanceDue(RaceEntry[] entries) {

        double total = 0;

        for (int i = 0; i < entries.length; i++) {
            total = total + entries[i].getBalanceDue();
        }

        return total;
    }

    public static String announceAll(RaceEntry[] entries) {

        StringBuilder report = new StringBuilder();

        for (int i = 0; i < entries.length; i++) {

            report.append(entries[i].announce());

            if (entries[i] instanceof RelayTeamEntry) {

                RelayTeamEntry relay =
                        (RelayTeamEntry) entries[i];

                report.append(" [Team size via downcast: ")
                        .append(relay.getTeamSize())
                        .append("]");
            }

            report.append(" | ");
        }

        return report.toString();
    }

    public static boolean isValidDiscountCode(String code) {

        if (code == null || code.length() != 5) {
            return false;
        }

        if (code.charAt(0) != 'M') {
            return false;
        }

        if (!Character.isDigit(code.charAt(1))) {
            return false;
        }

        if (!Character.isDigit(code.charAt(2))) {
            return false;
        }

        if (!Character.isDigit(code.charAt(3))) {
            return false;
        }

        if (!Character.isUpperCase(code.charAt(4))) {
            return false;
        }

        return true;
    }

    public static String settleNight(RaceEntry[] entries) {

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < entries.length; i++) {

            if (entries[i] == null) {
                continue;
            }

            if (entries[i] instanceof RelayTeamEntry) {

                RelayTeamEntry relay =
                        (RelayTeamEntry) entries[i];

                result.append(
                        "Relay Team | Bib: "
                                + relay.getBibNumber()
                                + " | Team Size: "
                                + relay.getTeamSize()
                                + " | Balance: "
                                + relay.getBalanceDue()
                );

            } else {

                result.append(
                        "Individual | Bib: "
                                + entries[i].getBibNumber()
                                + " | Balance: "
                                + entries[i].getBalanceDue()
                );
            }

            result.append("\n");
        }

        return result.toString();
    }

    public static int getBibCounter() {
        return bibCounter;
    }

    public static void main(String[] args) {

        RaceEntry entry1 =
                new RaceEntry(
                        "BIB1001",
                        100
                );

        RaceEntry entry2 =
                new RaceEntry(
                        "BIB1002",
                        200
                );

        System.out.println(
                entry1.getEntryCode()
        );

        System.out.println(
                entry2.getEntryCode()
        );

        System.out.println(
                getBibCounter()
        );

        System.out.println(
                isValidDiscountCode("M123A")
        );

        System.out.println(
                isValidDiscountCode("M12AA")
        );

        entry1.pay(30);

        entry1.pay(
                20,
                "UPI"
        );

        RelayTeamEntry relay =
                new RelayTeamEntry(
                        "BIB4001",
                        300,
                        4
                );

        RaceEntry[] entries = {
                entry1,
                null,
                relay,
                entry2
        };

        System.out.println(
                settleNight(entries)
        );
    }
}