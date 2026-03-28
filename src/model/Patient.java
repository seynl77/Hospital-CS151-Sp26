package src.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import src.exceptions.InvalidOperationException;

public class Patient extends Person {
    private String medicalHistory;
    private boolean admitted;
    private List<Appointment> appointments;

    public Patient(String name, int id, int age, String gender, int phoneNumber, String medicalHistory) {
        super(name, id, age, gender, phoneNumber);
        this.medicalHistory = medicalHistory;
        this.admitted = false;
        this.appointments = new ArrayList<>();
    }

    @Override
    public void displayRole() {
        System.out.println("Patient: " + getName());
    }

    public void admitPatient() throws InvalidOperationException {
        if (admitted) {
            throw new InvalidOperationException("Patient already admitted");
        }
        admitted = true;
        System.out.println(getName() + " has been admitted.");
    }

    public void dischargePatient() throws InvalidOperationException {
        if (!admitted) {
            throw new InvalidOperationException("Patient is not admitted");
        }

        for (Appointment a : appointments) {
            if (!a.getStatus().equals("Completed") && !a.getStatus().equals("Cancelled")) {
                throw new InvalidOperationException("Cannot discharge with pending appointments");
            }
        }

        admitted = false;
        System.out.println(getName() + " has been discharged.");
    }

    public void scheduleAppointment(Appointment appointment) throws InvalidOperationException {
        if (appointment == null) {
            throw new IllegalArgumentException("Appointment cannot be null");
        }

        // check patient conflicts
        for (Appointment a : appointments) {
            if (a.getDate().equals(appointment.getDate()) &&
                a.getTime().equals(appointment.getTime())) {
                throw new InvalidOperationException("Time conflict for patient");
            }
        }

        appointments.add(appointment);
    }

    public void cancelAppointment(int appointmentId) throws InvalidOperationException {
        Iterator<Appointment> iter = appointments.iterator();
        while (iter.hasNext()) {
            Appointment a = iter.next();
            if (a.getAppointmentId() == appointmentId) {
                a.cancel();
                iter.remove();
                return;
            }
        }
        throw new InvalidOperationException("Appointment not found");
    }

    public void updateMedicalHistory(String history) {
        if (history == null || history.isEmpty()) {
            throw new IllegalArgumentException("Invalid medical history");
        }
        medicalHistory = history;
    }

    public void viewPatientStatus() {
        System.out.println("Patient: " + getName());
        System.out.println("Admitted: " + admitted);
        System.out.println("Medical History: " + medicalHistory);
        System.out.println("Number of appointments: " + appointments.size());
    }
}