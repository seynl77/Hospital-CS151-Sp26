package src.model;

import java.util.ArrayList;
import java.util.List;
import src.exceptions.MaxCapacityException;

public class Hospital {
    public static final int MAX_INSTANCES = 100;

    private String name;
    private List<Patient> patients;
    private List<Doctor> doctors;
    private List<Appointment> appointments;
    private List<Bill> bills;
    private List<Room> rooms;

    public Hospital(String name) {
    this.name = name;
    patients = new ArrayList<>();
    doctors = new ArrayList<>();
    appointments = new ArrayList<>();
    bills = new ArrayList<>();
    rooms = new ArrayList<>();
}

    public void addPatient(Patient patient) throws MaxCapacityException {
        if (patients.size() >= MAX_INSTANCES) {
            throw new MaxCapacityException("patient");
        }
        patients.add(patient);
        System.out.println("Patient " + patient.getName() + " added.");
    }

    public void addDoctor(Doctor doctor) throws MaxCapacityException {
        if (doctors.size() >= MAX_INSTANCES) {
            throw new MaxCapacityException("doctor");
        }
        doctors.add(doctor);
        System.out.println("Doctor " + doctor.getName() + " added.");
    }

    public void addAppointment(Appointment appointment) throws MaxCapacityException {
        if (appointments.size() >= MAX_INSTANCES) {
            throw new MaxCapacityException("appointment");
        }
        appointments.add(appointment);
        System.out.println("Appointment added.");
    }

    public void addBill(Bill bill) throws MaxCapacityException {
        if (bills.size() >= MAX_INSTANCES) {
            throw new MaxCapacityException("bill");
        }
        bills.add(bill);
        System.out.println("Bill added.");
    }

    public void addRoom(Room room) throws MaxCapacityException {
        if (rooms.size() >= MAX_INSTANCES) {
            throw new MaxCapacityException("room");
        }
        rooms.add(room);
        System.out.println("Room added.");
    }

    public String getName() {
        return name;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void displayAllPatients() {
        if (patients.isEmpty()) {
            System.out.println("No patients registered.");
            return;
        }
        System.out.println("\nDisplaying all patients");
        for (Patient p : patients) {
            p.viewPatientStatus();
            System.out.println();
        }
    }
 
    public void displayAllDoctors() {
        if (doctors.isEmpty()) {
            System.out.println("No doctors registered.");
            return;
        }
        System.out.println("\nDisplaying all doctors");
        for (Doctor d : doctors) {
            System.out.println(d.getName());
        }
    }
 
    public void displayAllAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No appointments on record.");
            return;
        }
        System.out.println("\nDisplaying all appointments");
        for (Appointment a : appointments) {
            a.displayInfo();
            System.out.println();
        }
    }
 
    public void displayAllRooms() {
        if (rooms.isEmpty()) {
            System.out.println("No rooms added.");
            return;
        }
        System.out.println("\nDisplaying all rooms");
        for (Room r : rooms) {
            System.out.println(r);
        }
    }
 
    public void displayAllBills() {
        if (bills.isEmpty()) {
            System.out.println("No bills on record.");
            return;
        }
        System.out.println("\nDisplaying all bills");
        for (Bill b : bills) {
            System.out.println(b);
        }
    }

    public void hospitalInfo() {
        System.out.println("Hospital name: " + name);
        System.out.println("Total Patients: " + patients.size());
        System.out.println("Total Doctors: " + doctors.size());
        System.out.println("Total Appointments: " + appointments.size());
        System.out.println("Total Bills: " + bills.size());
        System.out.println("Total Rooms: " + rooms.size());
    }

    @Override
    public String toString() {
        return "Hospital Name: " + name + "\n" +
            "Total Patients: " + patients.size() + "\n" +
            "Total Doctors: " + doctors.size() + "\n" +
            "Total Appointments: " + appointments.size() + "\n" +
            "Total Bills: " + bills.size() + "\n" + 
            "Total Rooms: " + rooms.size() + "\n";
    }
}
