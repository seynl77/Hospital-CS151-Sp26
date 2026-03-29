package src.model;

import src.exceptions.InvalidOperationException;
import src.exceptions.MaxCapacityException;

public class Room {
    public static final int MAX_ROOMS = 100;

    private static int roomCount = 0;
    private int roomNumber;
    private boolean isOccupied;
    private String type;
    private boolean underMaintenance;
    private Patient assignedPatient;

    public Room(int roomNumber, String type) throws MaxCapacityException, InvalidOperationException {
        if (roomCount >= MAX_ROOMS) {
            throw new MaxCapacityException("Cannot create more than 100 rooms.");
        }

        if (roomNumber <= 0) {
            throw new InvalidOperationException("Room number must be positive.");
        }
        this.roomNumber = roomNumber;

        if (type == null || type.trim().isEmpty()) {
            throw new InvalidOperationException("Room type cannot be empty.");
        }
        this.type = type;
        this.isOccupied = false;
        this.underMaintenance = false;
        this.assignedPatient = null;
        roomCount++;
    }

    public void assignPatient(Patient patient) throws InvalidOperationException {
        if (underMaintenance) {
            throw new InvalidOperationException("Room is under maintenance.");
        }
        if (isOccupied) {
            throw new InvalidOperationException("Room already occupied.");
        }
        if (patient == null) {
            throw new InvalidOperationException("Invalid patient.");
        }

        assignedPatient = patient;
        isOccupied = true;
        System.out.println("Patient assigned to room " + roomNumber + ".");
    }

    public void vacateRoom() {
        if (!isOccupied) {
            System.out.println("Room is already empty.");
        } else {
            assignedPatient = null;
            isOccupied = false;
            System.out.println("Room is now empty.");
        }
    }

    public void transferPatient(Room newRoom) throws InvalidOperationException {
        if (assignedPatient == null) {
            throw new InvalidOperationException("No patient to transfer.");
        }
        if (newRoom == null) {
            throw new InvalidOperationException("Target room does not exist.");
        }
        if (!newRoom.isAvailable()) {
            throw new InvalidOperationException("Target room is not available.");
        }

        Patient temp = assignedPatient;
        newRoom.assignPatient(temp);
        assignedPatient = null;
        isOccupied = false;

        System.out.println("Patient transferred to room " + newRoom.getRoomNumber() + ".");
    }

    public void markUnderMaintenance() throws InvalidOperationException {
        if (isOccupied) {
            throw new InvalidOperationException("Cannot put occupied room under maintenance.");
        }
        underMaintenance = true;
        System.out.println("Room " + roomNumber + " is now under maintenance.");
    }

    public void releaseMaintenance() {
        underMaintenance = false;
        System.out.println("Room " + roomNumber + " is now available.");
    }

    public boolean isAvailable() {
        return !isOccupied && !underMaintenance;
    }

    public boolean hasPatient() {
        return assignedPatient != null;
    }

    public void changeRoomType(String newType) {
        if (newType == null || newType.trim().isEmpty()) {
            System.out.println("Invalid room type.");
        } else if (isOccupied) {
            System.out.println("Cannot change type while room is occupied.");
        } else {
            type = newType;
            System.out.println("Room type updated successfully.");
        }
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public String getType() {
        return type;
    }

    public boolean isUnderMaintenance() {
        return underMaintenance;
    }

    public Patient getAssignedPatient() {
        return assignedPatient;
    }

    public static int getRoomCount() {
        return roomCount;
    }

    public void setRoomNumber(int roomNumber) {
        if (roomNumber > 0) {
            this.roomNumber = roomNumber;
        } else {
            System.out.println("Invalid room number.");
        }
    }

    public void setType(String type) {
        if (type != null && !type.trim().isEmpty()) {
            this.type = type;
        } else {
            System.out.println("Invalid room type.");
        }
    }

    public void displayRoomInfo() {
        System.out.println("Room Number: " + roomNumber);
        System.out.println("Type: " + type);
        System.out.println("Occupied: " + isOccupied);
        System.out.println("Under Maintenance: " + underMaintenance);

        if (assignedPatient != null) {
            System.out.println("Assigned Patient: " + assignedPatient.getName());
        } else {
            System.out.println("Assigned Patient: None");
        }
    }

    @Override
    public String toString() {
        String patientName = "None";
        if (assignedPatient != null) {
            patientName = assignedPatient.getName();
        }
        return "Room Number: " + roomNumber + "\n" +
            "Type: " + type + "\n" +
            "Occupied: " + isOccupied + "\n" +
            "Under Maintenance: " + underMaintenance + "\n" +
            "Assigned Patient: " + patientName + "\n";
    }
}