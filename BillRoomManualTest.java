public class BillRoomManualTest {
    public static void main(String[] args) {
        System.out.println("Running Bill and Room tests...");

        try {
            Bill bill1 = new Bill(1, 500);
            if (!bill1.isPaid() && bill1.getAmount() == 500) {
                System.out.println("Test 1 Passed: Bill starts unpaid with correct amount.");
            } else {
                System.out.println("Test 1 Failed");
            }

            Bill bill2 = new Bill(2, 300);
            bill2.payBill();
            if (bill2.isPaid() && bill2.getAmount() == 0) {
                System.out.println("Test 2 Passed: payBill works.");
            } else {
                System.out.println("Test 2 Failed");
            }

            Bill bill3 = new Bill(3, 100);
            bill3.addCharge(50);
            if (bill3.getAmount() == 150) {
                System.out.println("Test 3 Passed: addCharge works.");
            } else {
                System.out.println("Test 3 Failed");
            }

        } catch (Exception e) {
            System.out.println("Bill test error: " + e.getMessage());
        }

        try {
            Room room1 = new Room(101, "ICU");
            if (!room1.isOccupied() && room1.isAvailable()) {
                System.out.println("Test 4 Passed: Room starts available.");
            } else {
                System.out.println("Test 4 Failed");
            }

            Patient patient = new Patient("Ali", 1, 25, "Male", 123456, "None");
            Room room2 = new Room(102, "General");
            room2.assignPatient(patient);
            if (room2.isOccupied() && room2.getAssignedPatient() == patient) {
                System.out.println("Test 5 Passed: assignPatient works.");
            } else {
                System.out.println("Test 5 Failed");
            }

            room2.vacateRoom();
            if (!room2.isOccupied() && room2.getAssignedPatient() == null) {
                System.out.println("Test 6 Passed: vacateRoom works.");
            } else {
                System.out.println("Test 6 Failed");
            }

        } catch (Exception e) {
            System.out.println("Room test error: " + e.getMessage());
        }

        System.out.println("Testing complete.");
    }
}