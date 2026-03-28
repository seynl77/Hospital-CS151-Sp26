package src.tests;

import src.model.Bill;
import src.model.Patient;
import src.model.Room;

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

            Bill bill4 = new Bill(4, 200);
            bill4.applyDiscount(25);
            if (bill4.getAmount() == 150) {
                System.out.println("Test 4 Passed: applyDiscount works.");
            } else {
                System.out.println("Test 4 Failed");
            }

            try {
                Bill invalidBill = new Bill(5, -100);
                System.out.println("Test 5 Failed: negative amount allowed.");
            } catch (Exception e) {
                System.out.println("Test 5 Passed: negative amount rejected.");
            }

            try {
                Bill bill6 = new Bill(6, 50);
                bill6.payBill();
                bill6.payBill();
                System.out.println("Test 6 Failed: double pay allowed.");
            } catch (Exception e) {
                System.out.println("Test 6 Passed: double pay rejected.");
            }

            Patient p = new Patient("Ali", 1, 20, "Male", "12345", "None");
            Room room1 = new Room(101, "Single");
            room1.assignPatient(p);

            if (room1.isOccupied()) {
                System.out.println("Test 7 Passed: patient assigned to room.");
            } else {
                System.out.println("Test 7 Failed");
            }

            try {
                room1.assignPatient(p);
                System.out.println("Test 8 Failed: assigned to occupied room.");
            } catch (Exception e) {
                System.out.println("Test 8 Passed: occupied room rejected.");
            }

        } catch (Exception e) {
            System.out.println("Unexpected test setup error: " + e.getMessage());
        }
    }
}