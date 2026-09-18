package inheritance.assignment_problems;

public class RaceEntry {

    private String bibNumber;
    private double entryFee;
    private double balanceDue;

    public RaceEntry(String bibNumber, double entryFee) {

        if (bibNumber == null ||
                bibNumber.trim().length() < 4) {

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

        try {
            RaceEntry entry =
                    new RaceEntry("B1", 50);

            System.out.println("Construction succeeded");

        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        RunnerEntry r =
                new RunnerEntry(
                        "BIB2001",
                        80,
                        "Open 10K"
                );

        r.pay(30);

        System.out.println(r.getBalanceDue());

        String[] bibNumbers = {
                "BIB1",
                "B1",
                "BIB2"
        };

        System.out.println(
                registerBatch(bibNumbers, 80)
        );

        RunnerEntry runnerEntry =
                new RunnerEntry(
                        "BIB2001",
                        80,
                        "Open 10K"
                );

        EliteRunnerEntry eliteEntry =
                new EliteRunnerEntry(
                        "BIB3001",
                        150,
                        "Elite Full Marathon",
                        500
                );

        RelayTeamEntry relayEntry =
                new RelayTeamEntry(
                        "BIB4001",
                        300,
                        4
                );

        System.out.println(runnerEntry.announce());
        System.out.println(eliteEntry.announce());
        System.out.println(relayEntry.announce());

        System.out.println(
                classifyGeneration(eliteEntry)
        );

        System.out.println(
                classifyGeneration(relayEntry)
        );

        RaceEntry[] entries = {
                runnerEntry,
                eliteEntry,
                relayEntry
        };

        System.out.println(
                getTotalBalanceDue(entries)
        );
    }
}