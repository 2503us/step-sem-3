package food_delivery.assignment_problems;

public final class SurgeFeeCalculator {

    private final double minimumSurgePercent;

    public SurgeFeeCalculator(double minimumSurgePercent) {
        this.minimumSurgePercent = minimumSurgePercent;
    }

    public final double calculateSurgeFee(double orderValue, int delayMinutes) {

        if (orderValue < 0 || delayMinutes < 0) {
            throw new IllegalArgumentException(
                    "Order value and delay minutes cannot be negative"
            );
        }

        if (delayMinutes == 0) {
            return 0.0;
        }

        double surgeFee = 0.0;

        int firstTierMinutes = Math.min(delayMinutes, 5);
        surgeFee += orderValue * 0.005 * firstTierMinutes;

        if (delayMinutes > 5) {
            int secondTierMinutes =
                    Math.min(delayMinutes - 5, 10);

            surgeFee += orderValue * 0.01 * secondTierMinutes;
        }

        if (delayMinutes > 15) {
            int thirdTierMinutes = delayMinutes - 15;

            surgeFee += orderValue * 0.02 * thirdTierMinutes;
        }

        double minimumFee =
                orderValue * (minimumSurgePercent / 100.0);

        return Math.max(surgeFee, minimumFee);
    }

    public static void main(String[] args) {

        SurgeFeeCalculator calculator =
                new SurgeFeeCalculator(1.0);

        System.out.println(
                "Rs " + calculator.calculateSurgeFee(500, 0)
        );

        System.out.println(
                "Rs " + calculator.calculateSurgeFee(500, 1)
        );

        System.out.println(
                "Rs " + calculator.calculateSurgeFee(500, 16)
        );
    }
}