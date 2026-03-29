package src.main;

import java.time.LocalDate;
import java.time.LocalTime;
import src.exceptions.InvalidOperationException;
import src.model.Appointment;
import src.model.Bill;
import src.model.Doctor;
import src.model.Hospital;
import src.model.Patient;
import src.model.Room;

public class Main {
    private static java.util.Scanner sc = new java.util.Scanner(System.in);
    private static Hospital hospital = new Hospital("San Jose Hospital");

    public static void main(String[] args) {
        while (true) {
            System.out.print(
                "Hospital Main Menu\n" +
                "==================\n" +
                "1. Patient Management\n" +
                "2. Doctor Management\n" +
                "3. Appointment Management\n" +
                "4. Room Management\n" +
                "5. Billing Management\n" +
                "6. View Hospital Info\n" +
                "7. Exit\n" +
                "Choose an option: "
            );

            String input = getInput();
            switch (input) {
                case "1": menuPatients(); break;
                case "2": menuDoctors(); break;
                case "3": menuAppointments(); break;
                case "4": menuRooms(); break;
                case "5": menuBillings(); break;
                case "6": viewHospitalInfo(); break;
                case "7": exitProgram(); break;
                default: System.out.println("Invalid Option");
            }
        }
    }

    // -------------------- Patient Menu --------------------
    private static void menuPatients() {
        System.out.print(
            "Patient Management\n" +
            "==================\n" +
            "1. Add Patient\n" +
            "2. View All Patients\n" +
            "3. Admit Patient\n" +
            "4. Discharge Patient\n" +
            "5. Update Medical History\n" +
            "6. View Patient Appointments\n" +
            "7. View Patient Bills\n" +
            "8. Return to Main Menu\n" +
            "Choose an option: "
        );

        String input = getInput();
        switch (input) {
            case "1":
                try {
                    System.out.print("Enter patient name: ");
                    String name = getInput();
                    System.out.print("Enter patient ID: ");
                    int id = Integer.parseInt(getInput());
                    System.out.print("Enter age: ");
                    int age = Integer.parseInt(getInput());
                    System.out.print("Enter gender: ");
                    String gender = getInput();
                    System.out.print("Enter phone number: ");
                    String phone = getInput();
                    System.out.print("Enter medical history: ");
                    String history = getInput();

                    Patient p = new Patient(name, id, age, gender, phone, history);
                    hospital.addPatient(p);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;

            case "2":
                hospital.displayAllPatients();
                break;

            case "3":
                if (hospital.getPatients().isEmpty()) {
                    System.out.println("No patients available.");
                    break;
                }
                try {
                    Patient p = selectPatient();
                    p.admitPatient();
                } catch (InvalidOperationException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;

            case "4":
                if (hospital.getPatients().isEmpty()) {
                    System.out.println("No patients available.");
                    break;
                }
                try {
                    Patient p = selectPatient();
                    p.dischargePatient();
                } catch (InvalidOperationException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;

            case "5":
                if (hospital.getPatients().isEmpty()) {
                    System.out.println("No patients available.");
                    break;
                }
                try {
                    Patient p = selectPatient();
                    System.out.print("Enter new medical history: ");
                    String history = getInput();
                    p.updateMedicalHistory(history);
                    System.out.println("Medical history updated.");
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;

            case "6":
                // View patient appointments
                if (hospital.getPatients().isEmpty()) {
                    System.out.println("No patients available.");
                    break;
                }
                Patient pAppt = selectPatient();
                System.out.println("Appointments for " + pAppt.getName() + ":");
                for (Appointment a : hospital.getAppointments()) {
                    if (a.getPatient() == pAppt) {
                        a.displayInfo();
                        System.out.println("------------------");
                    }
                }
                break;

            case "7":
                // View patient bills
                if (hospital.getPatients().isEmpty()) {
                    System.out.println("No patients available.");
                    break;
                }
                Patient pBill = selectPatient();
                System.out.println("Bills for " + pBill.getName() + ":");
                for (Appointment a : hospital.getAppointments()) {
                    if (a.getPatient() == pBill) {
                        Bill b = a.getBill();
                        if (b != null) {
                            System.out.println("Appointment ID: " + a.getAppointmentId() + " | Bill status: " + b.isPaid());
                        } else {
                            System.out.println("Appointment ID: " + a.getAppointmentId() + " | No bill generated");
                        }
                    }
                }
                break;

            case "8": return;
            default: System.out.println("Invalid option");
        }
    }

    // -------------------- Doctor Menu --------------------
    private static void menuDoctors() {
        System.out.print(
            "Doctor Management\n" +
            "==================\n" +
            "1. Add Doctor\n" +
            "2. View All Doctors\n" +
            "3. Apply Salary BOnus of 10%\n" + 
            "4. Display a Doctors Summary\n" +
            "5. Return to Main Menu\n" +
            "Choose an option: "
        );
        String input = getInput();
        switch (input) {
            case "1":
                try {
                    System.out.print("Enter doctor name: ");
                    String name = getInput();
                    System.out.print("Enter doctor ID: ");
                    int id = Integer.parseInt(getInput());
                    System.out.print("Enter age: ");
                    int age = Integer.parseInt(getInput());
                    System.out.print("Enter gender: ");
                    String gender = getInput();
                    System.out.print("Enter phone number: ");
                    String phone = getInput();
                    System.out.print("Enter salary: ");
                    double salary = Double.parseDouble(getInput());
                    System.out.print("Enter specialty: ");
                    String specialty = getInput();

                    Doctor d = new Doctor(name, id, age, gender, phone, salary, specialty, false);
                    hospital.addDoctor(d);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;

            case "2":
                hospital.displayAllDoctors();
                break;

            case "3":
                for (int i = 0; i < hospital.getDoctors().size(); i++) {
                    System.out.println(i + ": " + hospital.getDoctors().get(i).getName());
                }
                System.out.print("Select doctor index: ");
                int idx = Integer.parseInt(getInput());
                hospital.getDoctors().get(idx).salaryBonus();
                break;

            case "4":
                for (int i = 0; i < hospital.getDoctors().size(); i++) {
                    System.out.println(i + ": " + hospital.getDoctors().get(i).getName());
                }
                System.out.print("Select doctor index: ");
                int idxx = Integer.parseInt(getInput());
                hospital.getDoctors().get(idxx).displayDoctor();
                break;

            case "5": return;
            default: System.out.println("Invalid option");
        }
    }

    // -------------------- Appointment Menu --------------------
    private static void menuAppointments() {
        System.out.print(
            "Appointment Management\n" +
            "==================\n" +
            "1. Create Appointment\n" +
            "2. View All Appointments\n" +
            "3. Cancel Appointment\n" +
            "4. Complete Appointment\n" +
            "5. Return to Main Menu\n" +
            "Choose an option: "
        );
        String input = getInput();
        switch (input) {
            case "1":
                if (hospital.getPatients().isEmpty() || hospital.getDoctors().isEmpty()) {
                    System.out.println("Need at least one patient and one doctor.");
                    break;
                }
                try {
                    Patient p = selectPatient();
                    Doctor d = selectDoctor();
                    System.out.print("Enter appointment ID: ");
                    int id = Integer.parseInt(getInput());
                    System.out.print("Enter date (YYYY-MM-DD): ");
                    LocalDate date = LocalDate.parse(getInput());
                    System.out.print("Enter time (HH:MM): ");
                    LocalTime time = LocalTime.parse(getInput());

                    Appointment a = new Appointment(id, p, d, date, time);
                    hospital.addAppointment(a);
                    p.scheduleAppointment(a);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;

            case "2":
                hospital.displayAllAppointments();
                break;

            case "3":
                if (hospital.getAppointments().isEmpty()) {
                    System.out.println("No appointments scheduled.");
                    break;
                }
                try {
                    Appointment a = selectAppointment();
                    a.cancel();
                    System.out.println("Appointment cancelled.");
                } catch (InvalidOperationException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;

            case "4":
                if (hospital.getAppointments().isEmpty()) {
                    System.out.println("No appointments scheduled.");
                    break;
                }
                try {
                    Appointment a = selectAppointment();
                    System.out.print("Enter base charge: ");
                    double charge = Double.parseDouble(getInput());
                    a.completeAppointment(charge);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;

            case "5": return;
            default: System.out.println("Invalid option");
        }
    }

    // -------------------- Room Menu --------------------
    private static void menuRooms() {
        System.out.print(
            "Room Management\n" +
            "==================\n" +
            "1. Add Room\n" +
            "2. Assign Room to Patient\n" +
            "3. Vacate Room\n" +
            "4. View Room Info\n" +
            "5. Mark Room Under Maintenance\n" +
            "6. Display All Rooms\n" +
            "7. Return to Main Menu\n" +
            "Choose an option: "
        );
        String input = getInput();
        switch (input) {
            case "1":
                try {
                    System.out.print("Enter room number: ");
                    int roomNumber = Integer.parseInt(getInput());
                    System.out.print("Enter room type: ");
                    String type = getInput();
                    Room r = new Room(roomNumber, type);
                    hospital.addRoom(r);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;

            case "2":
                if (hospital.getRooms().isEmpty() || hospital.getPatients().isEmpty()) {
                    System.out.println("No rooms or patients available.");
                    break;
                }
                try {
                    Room r = selectRoom();
                    Patient p = selectPatient();
                    r.assignPatient(p);
                    System.out.println("Patient assigned to room.");
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;

            case "3":
                if (hospital.getRooms().isEmpty()) {
                    System.out.println("No rooms available.");
                    break;
                }
                Room rVac = selectRoom();
                rVac.vacateRoom();
                System.out.println("Room vacated.");
                break;

            case "4":
                if (hospital.getRooms().isEmpty()) {
                    System.out.println("No rooms available.");
                    break;
                }
                for (Room room : hospital.getRooms()) {
                    room.displayRoomInfo();
                    System.out.println("------------------");
                }
                break;

            case "5":
                if (hospital.getRooms().isEmpty()) {
                    System.out.println("No rooms available.");
                    break;
                }
                try {
                    Room rMaint = selectRoom();
                    rMaint.markUnderMaintenance();
                    System.out.println("Room marked under maintenance.");
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;

            case "6":
                hospital.displayAllRooms();
                break;

            case "7":
                System.out.println("Returning to main menu");
                return;

            default:
                System.out.println("Invalid input");
                break;
        }
    }

    // -------------------- Billing Menu --------------------
    private static void menuBillings() {
        System.out.print(
            "Billing Management\n" +
            "==================\n" +
            "1. View Bill for Appointment\n" +
            "2. Pay Bill\n" +
            "3. Add Charge\n" +
            "4. Apply Discount\n" +
            "5. Display All Bills\n" +
            "6. Return to Main Menu\n" +
            "Choose an option: "
        );

        String input = getInput();

        switch (input) {
            case "1":
                if (hospital.getAppointments().isEmpty()) {
                    System.out.println("No appointments available.");
                    break;
                }
                try {
                    Appointment a = selectAppointment();
                    Bill b = a.getBill();
                    if (b == null) {
                        System.out.println("No bill has been generated for this appointment.");
                    } else {
                        b.displayBill();
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;

            case "2":
                if (hospital.getAppointments().isEmpty()) {
                    System.out.println("No appointments available.");
                    break;
                }
                try {
                    Appointment a = selectAppointment();
                    Bill b = a.getBill();
                    if (b == null) {
                        System.out.println("No bill has been generated for this appointment.");
                    } else {
                        b.payBill();
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;

            case "3":
                if (hospital.getAppointments().isEmpty()) {
                    System.out.println("No appointments available.");
                    break;
                }
                try {
                    Appointment a = selectAppointment();
                    Bill b = a.getBill();
                    if (b == null) {
                        System.out.println("No bill has been generated for this appointment.");
                    } else {
                        System.out.print("Enter additional charge: ");
                        double extra = Double.parseDouble(getInput());
                        b.addCharge(extra);
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;

            case "4":
                if (hospital.getAppointments().isEmpty()) {
                    System.out.println("No appointments available.");
                    break;
                }
                try {
                    Appointment a = selectAppointment();
                    Bill b = a.getBill();
                    if (b == null) {
                        System.out.println("No bill has been generated for this appointment.");
                    } else {
                        System.out.print("Enter discount percent: ");
                        double percent = Double.parseDouble(getInput());
                        b.applyDiscount(percent);
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;

            case "5":
                hospital.displayAllBills();
                break;

            case "6":
                return;

            default:
                System.out.println("Invalid option");
        }
    }

    // -------------------- Utility Methods --------------------
    private static Patient selectPatient() {
        for (int i = 0; i < hospital.getPatients().size(); i++) {
            System.out.println(i + ": " + hospital.getPatients().get(i).getName());
        }
        System.out.print("Select patient index: ");
        int idx = Integer.parseInt(getInput());
        return hospital.getPatients().get(idx);
    }

    private static Doctor selectDoctor() {
        for (int i = 0; i < hospital.getDoctors().size(); i++) {
            if(!hospital.getDoctors().get(i).isAvailable()) {
                System.out.println(i + ": " + hospital.getDoctors().get(i).getName() + " is unavailable.");
            } else {
                System.out.println(i + ": " + hospital.getDoctors().get(i).getName());
            }
        }
        System.out.print("Select doctor index: ");
        int idx = Integer.parseInt(getInput());
        return hospital.getDoctors().get(idx);
    }

    private static Appointment selectAppointment() {
        for (int i = 0; i < hospital.getAppointments().size(); i++) {
            System.out.println(i + ": Appointment ID " + hospital.getAppointments().get(i).getAppointmentId());
        }
        System.out.print("Select appointment index: ");
        int idx = Integer.parseInt(getInput());
        return hospital.getAppointments().get(idx);
    }

    private static Room selectRoom() {
        for (int i = 0; i < hospital.getRooms().size(); i++) {
            System.out.println(i + ": Room " + hospital.getRooms().get(i).getRoomNumber());
        }
        System.out.print("Select room index: ");
        int idx = Integer.parseInt(getInput());
        return hospital.getRooms().get(idx);
    }

    private static void viewHospitalInfo() {
        hospital.hospitalInfo();
    }

    private static void exitProgram() {
        System.out.println("Exiting system. Goodbye.");
        sc.close();
        System.exit(0);
    }

    private static String getInput() {
        String input = sc.nextLine();
        if (input.equalsIgnoreCase("exit")) {
            exitProgram();
        }
        return input;
    }
}