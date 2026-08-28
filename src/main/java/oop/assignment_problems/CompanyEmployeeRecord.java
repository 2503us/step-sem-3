package oop.assignment_problems;

public class CompanyEmployeeRecord {

    String name;
    String empId;
    Employee employee;
    ParkingSlot slot;

    static int totalRecords = 0;

    CompanyEmployeeRecord(String name, String empId,
                          Employee employee, ParkingSlot slot) {
        this.name = name;
        this.empId = empId;
        this.employee = employee;
        this.slot = slot;

        totalRecords++;
    }

    String fullProfile() {

        double pay;

        if (employee instanceof ManagerEmployee) {
            ManagerEmployee manager = (ManagerEmployee) employee;
            pay = manager.effectiveSalary();
        } else if (employee instanceof InternEmployee) {
            InternEmployee intern = (InternEmployee) employee;
            pay = intern.effectiveSalary();
        } else {
            pay = employee.getSalary();
        }

        String slotInfo;

        if (slot == null) {
            slotInfo = "no parking assigned";
        } else {
            slotInfo = slot.slotNo;
        }

        return name + " | Pay: Rs " + pay + " | Slot: " + slotInfo;
    }

    public static void main(String[] args) {

        ParkingSlot slot1 = new ParkingSlot("A1", 4, 0);
        ParkingSlot slot2 = new ParkingSlot("A2", 5, 0);

        Employee employee1 =
                new ManagerEmployee(101, "Divya", 70000, 8000);

        Employee employee2 =
                new Employee(102, "Karan", 40000);

        Employee employee3 =
                new InternEmployee(103, "Meera", 12000, 10000);

        slot1.allot("DIVYA101");
        slot2.allot("KARAN102");

        CompanyEmployeeRecord record1 =
                new CompanyEmployeeRecord("Divya", "101", employee1, slot1);

        CompanyEmployeeRecord record2 =
                new CompanyEmployeeRecord("Karan", "102", employee2, slot2);

        CompanyEmployeeRecord record3 =
                new CompanyEmployeeRecord("Meera", "103", employee3, null);

        System.out.println(record1.fullProfile());
        System.out.println(record2.fullProfile());
        System.out.println(record3.fullProfile());

        System.out.println(
                "Total records: " + CompanyEmployeeRecord.totalRecords
        );
    }
}