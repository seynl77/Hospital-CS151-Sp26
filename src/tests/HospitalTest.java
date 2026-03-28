package src.tests;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;

import src.exceptions.InvalidOperationException;
import src.exceptions.MaxCapacityException;
import src.model.*;

public class HospitalTest {

    private Hospital hospital;

    private Patient makePatient(String name, int id) {
        return new Patient(name, id, 30, "Male", "5550000", "None");
    }

    private Doctor makeDoctor(String name, int id) {
        return new Doctor(name, id, 45, "Female", "5551111", 120000, "People", false);
    }

    private Appointment makeAppointment(int id, Patient p, Doctor d)
            throws InvalidOperationException {
        return new Appointment(id, p, d,
                LocalDate.of(2025, 6, 15),
                LocalTime.of(10, 0));
    }

    @BeforeEach
    void setUp() {
        hospital = new Hospital("Test Hospital");
    }

    @Test
    void testHospitalName() {
        assertEquals("Test Hospital", hospital.getName());
    }

    @Test
    void testNewHospitalIsEmpty() {
        assertDoesNotThrow(() -> hospital.hospitalInfo());
    }


    @Test
    void testAddPatientSuccess() {
        Patient p = makePatient("Name", 1);
        assertDoesNotThrow(() -> hospital.addPatient(p));
    }

    @Test
    void testAddMultiplePatients() {
        assertDoesNotThrow(() -> {
            for (int i = 1; i <= 5; i++) {
                hospital.addPatient(makePatient("Patient" + i, i));
            }
        });
    }

    @Test
    void testAddPatientMaxCapacity() throws MaxCapacityException {
        for (int i = 1; i <= 100; i++) {
            hospital.addPatient(makePatient("Name", i));
        }
        Patient extra = makePatient("Overflow", 101);
        assertThrows(MaxCapacityException.class, () -> hospital.addPatient(extra));
    }

    @Test
    void testAddDoctorSuccess() {
        Doctor d = makeDoctor("Doctor", 10);
        assertDoesNotThrow(() -> hospital.addDoctor(d));
    }

    @Test
    void testAddDoctorMaxCapacity() throws MaxCapacityException {
        for (int i = 1; i <= 100; i++) {
            hospital.addDoctor(makeDoctor("Doctor", i));
        }
        Doctor extra = makeDoctor("Overflow", 999);
        assertThrows(MaxCapacityException.class, () -> hospital.addDoctor(extra));
    }


    @Test
    void testAddAppointmentSuccess() throws InvalidOperationException {
        Patient p = makePatient("Name", 2);
        Doctor d = makeDoctor("Doctor", 20);
        Appointment a = makeAppointment(1, p, d);
        assertDoesNotThrow(() -> hospital.addAppointment(a));
    }

    @Test
    void testAddAppointmentMaxCapacity() throws MaxCapacityException, InvalidOperationException {
        Patient p = makePatient("Name", 3);
        Doctor d = makeDoctor("Doctor", 30);
        for (int i = 1; i <= 100; i++) {
            Appointment a = new Appointment(i, p, d,
                    LocalDate.of(2025, 1, 1).plusDays(i),
                    LocalTime.of(9, 0));
            hospital.addAppointment(a);
        }
        Appointment extra = new Appointment(101, p, d,
                LocalDate.of(2026, 1, 1), LocalTime.of(9, 0));
        assertThrows(MaxCapacityException.class, () -> hospital.addAppointment(extra));
    }

}