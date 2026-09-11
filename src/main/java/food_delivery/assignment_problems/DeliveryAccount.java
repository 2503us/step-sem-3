package food_delivery.assignment_problems;

public class DeliveryAccount {

    private static double minimumSurgePercent;

    static {
        minimumSurgePercent = 1.0;
    }

    protected String studentId;
    protected double orderValue;

    public DeliveryAccount(String studentId, double orderValue) {
        this.studentId = studentId;
        this.orderValue = orderValue;
    }

    public DeliveryAccount(String studentId) {
        this(studentId, 0.0);
    }

    public final double calculateSurgeFee(int delayMinutes) {
        SurgeFeeCalculator calculator =
                new SurgeFeeCalculator(minimumSurgePercent);

        return calculator.calculateSurgeFee(orderValue, delayMinutes);
    }

    void processAccount(DeliveryAccount account,
                        double amount,
                        int delayMinutes) {

        if (account == null) {
            return;
        }

        account.orderValue = amount;

        double surgeFee = account.calculateSurgeFee(delayMinutes);

        System.out.println(
                account.studentId +
                        " | Surge fee: Rs " +
                        surgeFee
        );
    }

    static void processBatch(DeliveryAccount[] accounts,
                             double[] amounts,
                             int[] delayMinutesArray) {

        int processed = 0;
        int nullSkipped = 0;
        int premiumCount = 0;
        int regularCount = 0;
        double grandTotal = 0.0;

        int length = Math.min(
                accounts.length,
                Math.min(amounts.length, delayMinutesArray.length)
        );

        DeliveryAccount processor = new DeliveryAccount("PROCESSOR");

        for (int i = 0; i < length; i++) {

            DeliveryAccount account = accounts[i];

            if (account == null) {
                nullSkipped++;
                continue;
            }

            if (account instanceof PremiumDeliveryAccount) {
                premiumCount++;
            } else {
                regularCount++;
            }

            account.orderValue = amounts[i];

            processor.processAccount(
                    account,
                    amounts[i],
                    delayMinutesArray[i]
            );

            grandTotal +=
                    account.calculateSurgeFee(delayMinutesArray[i]);

            processed++;
        }

        System.out.println(
                processed + " processed | " +
                        nullSkipped + " null skipped | " +
                        premiumCount + " premium | " +
                        regularCount + " regular | " +
                        "grand total surge fees = Rs " +
                        grandTotal
        );
    }

    public static void main(String[] args) {

        DeliveryAccount premium =
                new PremiumDeliveryAccount("STU001", 500);

        DeliveryAccount regular =
                new DeliveryAccount("STU002", 300);

        DeliveryAccount[] accounts = {
                premium,
                null,
                regular
        };

        double[] amounts = {
                500,
                400,
                300
        };

        int[] delayMinutesArray = {
                10,
                5,
                0
        };

        processBatch(
                accounts,
                amounts,
                delayMinutesArray
        );
    }
}

class PremiumDeliveryAccount extends DeliveryAccount {

    PremiumDeliveryAccount(String studentId, double orderValue) {
        super(studentId, orderValue);
    }

    PremiumDeliveryAccount(String studentId) {
        super(studentId);
    }
}