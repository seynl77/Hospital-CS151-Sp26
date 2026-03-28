package src.model;

import java.time.LocalDate;
import java.time.LocalTime;
import src.exceptions.InvalidAmountException;
import src.exceptions.InvalidOperationException;
import src.exceptions.MaxCapacityException;

public class Appointment {
    private int appointmentId;
    private Patient patient;
    private Doctor doctor;
    private LocalDate date;
    private LocalTime time;
    private String status; // Scheduled, Completed, Cancelled
    private Bill bill;     

    public Appointment(int appointmentId, Patient patient, Doctor doctor, LocalDate date, LocalTime time) {
        if (patient == null || doctor == null) {
            throw new IllegalArgumentException("Patient and Doctor cannot be null");
        }
        if (date == null || time == null) {
            throw new IllegalArgumentException("Date and time cannot be null");
        }

        this.appointmentId = appointmentId;
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.time = time;
        this.status = "Scheduled";
        this.bill = null;
    }

    // getters
    public int getAppointmentId() { return appointmentId; }
    public LocalDate getDate() { return date; }
    public LocalTime getTime() { return time; }
    public Doctor getDoctor() { return doctor; }
    public Patient getPatient() { return patient; }
    public String getStatus() { return status; }
    public Bill getBill() { return bill; }

    public void cancel() throws InvalidOperationException {
        if (status.equals("Completed")) {
            throw new InvalidOperationException("Cannot cancel completed appointment");
        }
        if (status.equals("Cancelled")) {
            throw new InvalidOperationException("Already cancelled");
        }
        status = "Cancelled";
    }

    public void completeAppointment(double baseCharge) throws InvalidOperationException, MaxCapacityException, InvalidAmountException {
        if (!status.equals("Scheduled")) {
            throw new InvalidOperationException("Only scheduled appointments can be completed");
        }
        status = "Completed";
        generateBill(baseCharge);
    }

    public void reschedule(LocalDate newDate, LocalTime newTime) throws InvalidOperationException {
        if (status.equals("Cancelled")) {
            throw new InvalidOperationException("Cannot reschedule cancelled appointment");
        }
        if (newDate == null || newTime == null) {
            throw new IllegalArgumentException("Invalid date/time");
        }
        this.date = newDate;
        this.time = newTime;
    }

    public void assignDoctor(Doctor newDoctor) {
        if (newDoctor == null) {
            throw new IllegalArgumentException("Doctor cannot be null");
        }
        this.doctor = newDoctor;
    }

    public void generateBill(double amount) throws MaxCapacityException, InvalidAmountException {
        if (bill != null) {
            throw new IllegalStateException("Bill already exists");
        }
        bill = new Bill(appointmentId, amount);
    }

    public void payBill() throws InvalidOperationException {
        if (bill == null) {
            throw new InvalidOperationException("No bill generated");
        }
        bill.payBill();
    }

    public void addCharge(double amount) throws InvalidAmountException, InvalidOperationException {
        if (bill == null) {
            throw new InvalidOperationException("No bill available");
        }
        bill.addCharge(amount);
    }

    public boolean conflictsWith(Appointment other) {
        if (other == null) return false;
        return this.date.equals(other.date) &&
               this.time.equals(other.time) &&
               this.doctor.getId() == other.doctor.getId();
    }

    public void displayInfo() {
        System.out.println("Appointment ID: " + appointmentId);
        System.out.println("Patient: " + patient.getName());
        System.out.println("Doctor: " + doctor.getName());
        System.out.println("Date: " + date);
        System.out.println("Time: " + time);
        System.out.println("Status: " + status);
        System.out.println("Bill status: " + (bill == null ? "Not generated" : (bill.isPaid() ? "Paid" : "Unpaid")));
    }
}