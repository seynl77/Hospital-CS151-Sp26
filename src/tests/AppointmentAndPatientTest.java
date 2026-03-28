package src.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import src.exceptions.InvalidAmountException;
import src.exceptions.InvalidOperationException;
import src.exceptions.MaxCapacityException;
import src.model.Appointment;
import src.model.Doctor;
import src.model.Patient;

public class AppointmentAndPatientTest {

    private Patient patient;
    private Doctor doctor;
    private Appointment appointment;

    @Before
    public void setUp() {
        patient = new Patient("Alice", 1, 30, "Female", 1234567890, "No allergies");
        doctor = new Doctor("Dr. Bob", 100, 45, "Male", 987654321, 120000, "Cardiology", false);
        appointment = new Appointment(1, patient, doctor, LocalDate.of(2026, 3, 28), LocalTime.of(10, 0));
    }

    // ---------------- Appointment Tests ----------------

    @Test
    public void testAppointmentCreation() {
        assertEquals(1, appointment.getAppointmentId());
        assertEquals(patient, appointment.getPatient());
        assertEquals(doctor, appointment.getDoctor());
        assertEquals("Scheduled", appointment.getStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAppointmentCreationNullPatient() {
        new Appointment(2, null, doctor, LocalDate.now(), LocalTime.now());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAppointmentCreationNullDoctor() {
        new Appointment(2, patient, null, LocalDate.now(), LocalTime.now());
    }

    @Test
    public void testCancelAppointment() throws InvalidOperationException {
        appointment.cancel();
        assertEquals("Cancelled", appointment.getStatus());
    }

    @Test(expected = InvalidOperationException.class)
    public void testCancelCompletedAppointment() throws InvalidOperationException, MaxCapacityException, InvalidAmountException {
        appointment.completeAppointment(100.0);
        appointment.cancel();
    }

    @Test
    public void testRescheduleAppointment() throws InvalidOperationException {
        LocalDate newDate = LocalDate.of(2026, 4, 1);
        LocalTime newTime = LocalTime.of(11, 0);
        appointment.reschedule(newDate, newTime);
        assertEquals(newDate, appointment.getDate());
        assertEquals(newTime, appointment.getTime());
    }

    @Test(expected = InvalidOperationException.class)
    public void testRescheduleCancelledAppointment() throws InvalidOperationException {
        appointment.cancel();
        appointment.reschedule(LocalDate.of(2026, 4, 1), LocalTime.of(11, 0));
    }

    @Test
    public void testConflictsWith() {
        Appointment other = new Appointment(2, patient, doctor, appointment.getDate(), appointment.getTime());
        assertTrue(appointment.conflictsWith(other));
    }

    // ---------------- Patient Tests ----------------

    @Test
    public void testScheduleAppointment() throws InvalidOperationException {
        patient.scheduleAppointment(appointment);
    }

    @Test(expected = InvalidOperationException.class)
    public void testScheduleConflictingAppointment() throws InvalidOperationException {
        patient.scheduleAppointment(appointment);
        Appointment conflict = new Appointment(2, patient, doctor, appointment.getDate(), appointment.getTime());
        patient.scheduleAppointment(conflict);
    }

    @Test
    public void testCancelAppointmentFromPatient() throws InvalidOperationException {
        patient.scheduleAppointment(appointment);
        patient.cancelAppointment(1);
        assertEquals("Cancelled", appointment.getStatus());
    }

    @Test(expected = InvalidOperationException.class)
    public void testCancelNonExistentAppointment() throws InvalidOperationException {
        patient.cancelAppointment(999);
    }

    @Test
    public void testAdmitAndDischargePatient() throws InvalidOperationException, MaxCapacityException, InvalidAmountException {
        patient.admitPatient();

        patient.scheduleAppointment(appointment);
        appointment.completeAppointment(100.0);

        patient.dischargePatient();
    }

    @Test(expected = InvalidOperationException.class)
    public void testDischargePatientWithPendingAppointment() throws InvalidOperationException {
        patient.admitPatient();
        patient.scheduleAppointment(appointment);
        patient.dischargePatient(); // pending appointment not completed
    }

    @Test
    public void testUpdateMedicalHistoryValid() {
        patient.updateMedicalHistory("Updated history");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateMedicalHistoryInvalid() {
        patient.updateMedicalHistory("");
    }
}