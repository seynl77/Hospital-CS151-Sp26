package src.main;
import java.util.Scanner;

import src.exceptions.MaxCapacityException;
import src.model.Hospital;
import src.model.Patient;
import src.model.Room;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static Hospital hospital;
    private static Patient currentPatient;
    private static Room currentRoom;

    public static void main(String[] args) {
        hospital = new Hospital("San Jose Hospital");
        currentPatient = null;
        currentRoom = null;

        while (true) {
            System.out.print("Hospital Main Menu\n" +
                            "==================\n" +
                            "1. Patient Management\n" +
                            "2. Doctor Management\n" +
                            "3. Appointments\n" +
                            "4. Rooms\n" +
                            "5. Billing\n" +
                            "6. Hospital Info\n" +
                            "7. Exit\n" +
                            "Choose an option: ");
            String input = getInput();

            switch (input) {
                case "1":
                    menuPatients();
                    break;

                case "2":
                    menuDoctors();
                    break;

                case "3":
                    menuAppointments();
                    break;

                case "4":
                    menuRooms();
                    break;

                case "5":
                    menuBillings();
                    break;

                case "6":
                    viewHospitalInfo();
                    break;

                case "7":
                    exitProgram();

                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private static void menuPatients() {
        System.out.print("Patients Management\n" +
                        "==================\n" +
                        "1. Add Patient\n" +
                        "2. View All Patients\n" +
                        "3. View Patient Details\n" +
                        "4. Admit Patient\n" +
                        "5. Discharge Patient\n" +
                        "6. Update Medical History\n" +
                        "7. View Patient Bills\n" +
                        "8. View Patient Appointments\n" +
                        "9. Return to Main Menu\n" +
                        "Choose an option: ");

        String input = getInput();

        switch (input) {
            case "1":
                System.out.print("Enter patient name: ");
                String patientName = getInput();

                System.out.print("Enter patient ID: ");
                int patientId = Integer.parseInt(getInput());

                System.out.print("Enter patient age: ");
                int patientAge = Integer.parseInt(getInput());

                System.out.print("Enter patient gender: ");
                String patientGender = getInput();

                System.out.print("Enter patient phone number: ");
                int patientPhone = Integer.parseInt(getInput());

                System.out.print("Enter medical history: ");
                String medicalHistory = getInput();

                currentPatient = new Patient(patientName, patientId, patientAge, patientGender, patientPhone, medicalHistory);

                try {
                    hospital.addPatient(currentPatient);
                }
                catch (MaxCapacityException e){
                    System.out.println("Error: " + e.getMessage()) ;
                }
                System.out.println("Patient added successfully.");
                break;
        }
    
    }

    private static void menuDoctors() {
        String input = getInput();
    }

    private static void menuAppointments() {
        String input = getInput();
    }

    private static void menuRooms() {
        System.out.print("Rooms Management\n" +
                        "==================\n" +
                        "1. Add Room\n" +
                        "2. Assign Room to Patient\n" +
                        "3. Vacate Room\n" +
                        "4. View Room Info\n" +
                        "5. Mark Room Under Maintenance\n" +
                        "6. Return to Main Menu\n" +
                        "Choose an option: ");

        String input = getInput();

        switch (input) {
                case "1":
                try {
                    System.out.print("Enter room number: ");
                    int roomNumber = Integer.parseInt(getInput());

                    System.out.print("Enter room type: ");
                    String roomType = getInput();

                    currentRoom = new Room(roomNumber, roomType);
                    System.out.println("Room added successfully.");
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;

                case "2":
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

                case "3":
                    if (currentRoom == null) {
                        System.out.println("No room exists yet.");
                    } else {
                        currentRoom.vacateRoom();
                    }
                    break;

                case "4":
                    if (currentRoom == null) {
                        System.out.println("No room exists yet.");
                    } else {
                        currentRoom.displayRoomInfo();
                    }
                    break;

                case "5":
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

                case "6":
                    System.out.println("Returning to main menu");
                    return;

                default:
                    System.out.println("Invalid input");
                    break;
        }
    }

    private static void menuBillings() {

    }

    private static void viewHospitalInfo() {
        System.out.println("\nDisplaying hospital info");
        hospital.hospitalInfo();;
        System.out.println();
    }

    private static String getInput() {
        String input = sc.nextLine();
        if (input.equalsIgnoreCase("exit")) {
            exitProgram();
        }
        return input;
    }

    private static void exitProgram() {
        System.out.println("Exiting");
        sc.close();
        System.exit(0);
    }
}

