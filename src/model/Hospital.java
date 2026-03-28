package src.model;
import java.util.ArrayList;

public class Hospital {
    private String name;
    private ArrayList<Patient> patients;
    private ArrayList<Doctor> doctors;
    private ArrayList<Appointment> appointments;
    private ArrayList<Bill> bills;
    private ArrayList<Room> rooms;

    public Hospital(String name) {
        this.name = name;
        patients = new ArrayList<>();
        doctors = new ArrayList<>();
        appointments = new ArrayList<>();
        bills = new ArrayList<>();
        rooms = new ArrayList<>();
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public void addBill(Bill bill) {
        bills.add(bill);
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public String getName() {
        return name;
    }

    public void hospitalInfo() {
    System.out.println("Hospital name: " + getName() + 
                        ", Total Patients: " + patients.size() +
                        ", Total Doctors: " + doctors.size() +
                        ", Total Rooms: " + rooms.size() +
                        ", Available Rooms: " + 1 +
                        ", Total Appointments: " + appointments.size());
    }
}
