package food_delivery.assignment_problems;

public class FoodOrder {

    private String studentName;
    private String dishName;
    private boolean delivered;

    public FoodOrder(String studentName, String dishName) {

        if (studentName == null || studentName.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be blank");
        }

        if (dishName == null || dishName.trim().isEmpty()) {
            throw new IllegalArgumentException("Dish name cannot be blank");
        }

        this.studentName = studentName;
        this.dishName = dishName;
        this.delivered = false;
    }

    public void markDelivered() {

        if (!delivered) {
            delivered = true;
            System.out.println(
                    "Order for " + studentName +
                            " (" + dishName + ") marked delivered"
            );
        } else {
            System.out.println(
                    "Order for " + studentName +
                            " (" + dishName + ") was already delivered"
            );
        }
    }

    static void processBatch(String[][] rawOrders) {

        int accepted = 0;
        int rejected = 0;

        for (String[] rawOrder : rawOrders) {

            try {
                new FoodOrder(rawOrder[0], rawOrder[1]);
                accepted++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        System.out.println(
                "Valid: " + accepted +
                        " | Rejected: " + rejected
        );
    }

    public static void main(String[] args) {

        String[][] rawOrders = {
                {"Ravi", "Paneer Butter Masala"},
                {"", "Chole Bhature"},
                {"Meera", " "},
                {"Divya", "Veg Biryani"}
        };

        processBatch(rawOrders);

        FoodOrder order =
                new FoodOrder("Ravi", "Paneer Butter Masala");

        order.markDelivered();
        order.markDelivered();
    }
}