package inheritance.assignment_problems;

public class RaceEntry {

    private String bibNumber;
    private double entryFee;
    private double balanceDue;

    private double[] lateFeeHistory = new double[10];
    private int lateFeeHistoryCount = 0;

    public RaceEntry(String bibNumber, double entryFee) {

        if (bibNumber == null || bibNumber.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid bib number");
        }

        this.bibNumber = bibNumber;
        this.entryFee = entryFee;
        this.balanceDue = entryFee;
    }

    public void pay(double amount) {
        balanceDue = balanceDue - amount;
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

    public String announce() {
        return "Race Entry | Bib: " + bibNumber
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

    public static void main(String[] args) {

        RunnerEntry r =
                new RunnerEntry(
                        "BIB2001",
                        80,
                        "Open 10K"
                );

        r.pay(30);

        r.applyLateFee(20);

        System.out.println(r.getBalanceDue());

        double[] history = r.getLateFeeHistory();

        System.out.println(history[0]);

        history[0] = 999;

        System.out.println(r.getLateFeeHistory()[0]);
    }
}