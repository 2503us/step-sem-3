package array.assignment_problems;

public class WarehouseInventoryBalancer {

    void analyzeInventory(int[] sectionA, int[] sectionB) {

        int totalA = 0;
        int totalB = 0;

        for (int i = 0; i < sectionA.length; i++) {
            totalA += sectionA[i];
        }

        for (int i = 0; i < sectionB.length; i++) {
            totalB += sectionB[i];
        }

        System.out.println("Section A Total: " + totalA);
        System.out.println("Section B Total: " + totalB);

        if (totalA == totalB) {
            System.out.println("Inventory Status: Balanced");
        } else {
            System.out.println("Inventory Status: Not Balanced");
        }

        int highest = sectionA[0];
        String highestSection = "A";
        int highestIndex = 0;

        for (int i = 1; i < sectionA.length; i++) {
            if (sectionA[i] > highest) {
                highest = sectionA[i];
                highestSection = "A";
                highestIndex = i;
            }
        }

        for (int i = 0; i < sectionB.length; i++) {
            if (sectionB[i] > highest) {
                highest = sectionB[i];
                highestSection = "B";
                highestIndex = i;
            }
        }

        System.out.println("Highest Quantity: " + highest);
        System.out.println("Section " + highestSection + ", Item " + (highestIndex + 1));
    }

    public static void main(String[] args) {

        WarehouseInventoryBalancer sc = new WarehouseInventoryBalancer();

        int[] sectionA = {20, 15, 30};
        int[] sectionB = {25, 10, 30};

        sc.analyzeInventory(sectionA, sectionB);
    }
}