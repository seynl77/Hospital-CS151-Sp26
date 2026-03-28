package src.main;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import src.exceptions.InvalidOperationException;
import src.model.Appointment;
import src.model.Doctor;
import src.model.Hospital;
import src.model.Patient;
import src.model.Room;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    // Hospital instance
    private static Hospital hospital = new Hospital("San Jose Hospital");

    // Lists to store patients, doctors, appointments, and rooms
    private static List<Patient> patients = new ArrayList<>();
    private static List<Doctor> doctors = new ArrayList<>();
    private static List<Appointment> appointments = new ArrayList<>();
    private static List<Room> rooms = new ArrayList<>();

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

            String input = sc.nextLine();

            switch (input) {
                case "1": menuPatients(); break;
                case "2": menuDoctors(); break;
                case "3": menuAppointments(); break;
                case "4": menuRooms(); break;
                case "5": menuBillings(); break;
                case "6": viewHospitalInfo(); break;
                case "7": exitProgram(); break;
                default: System.out.println("Invalid option");
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
            "6. Return to Main Menu\n" +
            "Choose an option: "
        );

        String input = sc.nextLine();

        switch (input) {
            case "1": // Add patient
                try {
                    System.out.print("Enter patient name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter patient ID: ");
                    int id = Integer.parseInt(sc.nextLine());
                    System.out.print("Enter age: ");
                    int age = Integer.parseInt(sc.nextLine());
                    System.out.print("Enter gender: ");
                    String gender = sc.nextLine();
                    System.out.print("Enter phone number: ");
                    int phone = Integer.parseInt(sc.nextLine());
                    System.out.print("Enter medical history: ");
                    String history = sc.nextLine();

                    Patient p = new Patient(name, id, age, gender, phone, history);
                    patients.add(p);
                    System.out.println("Patient added successfully.");
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;

            case "2": // View all patients
                if (patients.isEmpty()) {
                    System.out.println("No patients added.");
                } else {
                    for (int i = 0; i < patients.size(); i++) {
                        System.out.println("Index " + i + ":");
                        patients.get(i).viewPatientStatus();
                        System.out.println("------------------");
                    }
                }
                break;

            case "3": // Admit patient
                if (patients.isEmpty()) {
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

            case "4": // Discharge patient
                if (patients.isEmpty()) {
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

            case "5": // Update medical history
                if (patients.isEmpty()) {
                    System.out.println("No patients available.");
                    break;
                }
                try {
                    Patient p = selectPatient();
                    System.out.print("Enter new medical history: ");
                    String history = sc.nextLine();
                    p.updateMedicalHistory(history);
                    System.out.println("Medical history updated.");
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;

            case "6": return;
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
            "3. Return to Main Menu\n" +
            "Choose an option: "
        );

        String input = sc.nextLine();
        switch (input) {
            case "1": // Add doctor
                try {
                    System.out.print("Enter doctor name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter doctor ID: ");
                    int id = Integer.parseInt(sc.nextLine());
                    System.out.print("Enter age: ");
                    int age = Integer.parseInt(sc.nextLine());
                    System.out.print("Enter gender: ");
                    String gender = sc.nextLine();
                    System.out.print("Enter phone number: ");
                    int phone = Integer.parseInt(sc.nextLine());
                    System.out.print("Enter salary: ");
                    double salary = Double.parseDouble(sc.nextLine());
                    System.out.print("Enter specialty: ");
                    String specialty = sc.nextLine();

                    Doctor d = new Doctor(name, id, age, gender, phone, salary, specialty, false);
                    doctors.add(d);
                    System.out.println("Doctor added successfully.");
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;

            case "2": // View all doctors
                if (doctors.isEmpty()) {
                    System.out.println("No doctors added.");
                } else {
                    for (Doctor d : doctors) {
                        d.displayRole();
                    }
                }
                break;

            case "3": return;
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

        String input = sc.nextLine();
        switch (input) {
            case "1": // Create appointment
                if (patients.isEmpty() || doctors.isEmpty()) {
                    System.out.println("Need at least one patient and one doctor.");
                    break;
                }
                try {
                    Patient p = selectPatient();
                    Doctor d = selectDoctor();

                    System.out.print("Enter appointment ID: ");
                    int id = Integer.parseInt(sc.nextLine());
                    System.out.print("Enter date (YYYY-MM-DD): ");
                    LocalDate date = LocalDate.parse(sc.nextLine());
                    System.out.print("Enter time (HH:MM): ");
                    LocalTime time = LocalTime.parse(sc.nextLine());

                    Appointment a = new Appointment(id, p, d, date, time);
                    appointments.add(a);
                    p.scheduleAppointment(a);
                    System.out.println("Appointment created successfully.");
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;

            case "2": // View appointments
                if (appointments.isEmpty()) {
                    System.out.println("No appointments scheduled.");
                } else {
                    for (Appointment a : appointments) {
                        a.displayInfo();
                        System.out.println("------------------");
                    }
                }
                break;

            case "3": // Cancel appointment
                if (appointments.isEmpty()) {
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

            case "4": // Complete appointment
                if (appointments.isEmpty()) {
                    System.out.println("No appointments scheduled.");
                    break;
                }
                try {
                    Appointment a = selectAppointment();
                    System.out.print("Enter base charge: ");
                    double charge = Double.parseDouble(sc.nextLine());
                    a.completeAppointment(charge);
                    System.out.println("Appointment completed and bill generated.");
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
            "6. Return to Main Menu\n" +
            "Choose an option: "
        );

        String input = sc.nextLine();
        switch (input) {
            case "1": // Add room
                try {
                    System.out.print("Enter room number: ");
                    int roomNumber = Integer.parseInt(sc.nextLine());
                    System.out.print("Enter room type: ");
                    String type = sc.nextLine();
                    Room r = new Room(roomNumber, type);
                    rooms.add(r);
                    System.out.println("Room added successfully.");
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;

            case "2": // Assign room
                if (rooms.isEmpty() || patients.isEmpty()) {
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

            case "3": // Vacate room
                if (rooms.isEmpty()) {
                    System.out.println("No rooms available.");
                    break;
                }
                Room r = selectRoom();
                r.vacateRoom();
                System.out.println("Room vacated.");
                break;

            case "4": // View room info
                if (rooms.isEmpty()) {
                    System.out.println("No rooms available.");
                    break;
                }
                for (Room room : rooms) {
                    room.displayRoomInfo();
                    System.out.println("------------------");
                }
                break;

            case "5": // Mark maintenance
                if (rooms.isEmpty()) {
                    System.out.println("No rooms available.");
                    break;
                }
                try {
                    Room room = selectRoom();
                    room.markUnderMaintenance();
                    System.out.println("Room marked under maintenance.");
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;

            case "6": return;
            default: System.out.println("Invalid option");
        }
    }

    // -------------------- Billing Menu --------------------
    private static void menuBillings() {
        System.out.println("Billing feature not implemented yet.");
    }

    // -------------------- Utility Methods --------------------
    private static Patient selectPatient() {
        for (int i = 0; i < patients.size(); i++) {
            System.out.println(i + ": " + patients.get(i).getName());
        }
        System.out.print("Select patient index: ");
        int idx = Integer.parseInt(sc.nextLine());
        return patients.get(idx);
    }

    private static Doctor selectDoctor() {
        for (int i = 0; i < doctors.size(); i++) {
            System.out.println(i + ": " + doctors.get(i).getName());
        }
        System.out.print("Select doctor index: ");
        int idx = Integer.parseInt(sc.nextLine());
        return doctors.get(idx);
    }

    private static Appointment selectAppointment() {
        for (int i = 0; i < appointments.size(); i++) {
            System.out.println(i + ": Appointment ID " + appointments.get(i).getAppointmentId());
        }
        System.out.print("Select appointment index: ");
        int idx = Integer.parseInt(sc.nextLine());
        return appointments.get(idx);
    }

    private static Room selectRoom() {
        for (int i = 0; i < rooms.size(); i++) {
            System.out.println(i + ": Room " + rooms.get(i).getRoomNumber());
        }
        System.out.print("Select room index: ");
        int idx = Integer.parseInt(sc.nextLine());
        return rooms.get(idx);
    }

    // -------------------- Hospital Info --------------------
    private static void viewHospitalInfo() {
        hospital.hospitalInfo();
    }

    private static void exitProgram() {
        System.out.println("Exiting system. Goodbye.");
        sc.close();
        System.exit(0);
    }
}