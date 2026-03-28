package src.main;
import java.util.Scanner;

import src.model.Hospital;
import src.model.Patient;
import src.model.Room;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Hospital hospital = new Hospital("San Jose Hospital");
        Patient currentPatient = null;
        Room currentRoom = null;

        while (true) {
            System.out.print("Hospital Main Menu\n" +
                            "==================\n" +
                            "1. Add Patient\n" +
                            "2. Add Doctor\n" +
                            "3. Create Appointment\n" +
                            "4. View Patients\n" +
                            "5. View Doctors\n" +
                            "6. View Appointments\n" +
                            "7. Add Room\n" +
                            "8. Assign Room to Patient\n" +
                            "9. Vacate Room\n" +
                            "10. View Room Info\n" +
                            "11. Mark Room Under Maintenance\n" +
                            "12. Exit\n" +
                            "Choose an option: ");
            String input = sc.nextLine();
            switch (input) {
                case "1":
                    System.out.print("Enter patient name: ");
                    String patientName = sc.nextLine();

                    System.out.print("Enter patient ID: ");
                    int patientId = Integer.parseInt(sc.nextLine());

                    System.out.print("Enter patient age: ");
                    int patientAge = Integer.parseInt(sc.nextLine());

                    System.out.print("Enter patient gender: ");
                    String patientGender = sc.nextLine();

                    System.out.print("Enter patient phone number: ");
                    int patientPhone = Integer.parseInt(sc.nextLine());

                    System.out.print("Enter medical history: ");
                    String medicalHistory = sc.nextLine();

                    currentPatient = new Patient(patientName, patientId, patientAge, patientGender, patientPhone, medicalHistory);
                    System.out.println("Patient added successfully.");
                    break;

                case "7":
                try {
                    System.out.print("Enter room number: ");
                    int roomNumber = Integer.parseInt(sc.nextLine());

                    System.out.print("Enter room type: ");
                    String roomType = sc.nextLine();

                    currentRoom = new Room(roomNumber, roomType);
                    System.out.println("Room added successfully.");
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;

                case "8":
                if (currentRoom == null) {
                    System.out.println("No room exists yet. Please add a room first.");
                } else if (currentPatient == null) {
                    System.out.println("No patient exists yet. Please add a patient first.");
                } else {
                    try {
                        currentRoom.assignPatient(currentPatient);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                break;

                case "9":
                    if (currentRoom == null) {
                        System.out.println("No room exists yet.");
                    } else {
                        currentRoom.vacateRoom();
                    }
                    break;

                case "10":
                    if (currentRoom == null) {
                        System.out.println("No room exists yet.");
                    } else {
                        currentRoom.displayRoomInfo();
                    }
                    break;

                case "11":
                if (currentRoom == null) {
                    System.out.println("No room exists yet.");
                } else {
                    try {
                        currentRoom.markUnderMaintenance();
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                break;

                case "12":
                    System.out.println("Exiting system. Goodbye.");
                    sc.close();
                    return;

                default:
                    System.out.println("Option not connected yet.");
            }
        }
    }
}
