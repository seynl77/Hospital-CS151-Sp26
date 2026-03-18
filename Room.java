public class Room {
    private int roomNumber;
    private boolean isOccupied;
    private String type;

    public Room(int roomNumber, String type) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.isOccupied = false;
    }

    public void assignPatient() {
        if (isOccupied) {
            System.out.println("Room already occupied.");
        } else {
            isOccupied = true;
            System.out.println("Patient assigned to room.");
        }
    }

    public void vacateRoom() {
        isOccupied = false;
        System.out.println("Room is now empty.");
    }

    public void displayRoomInfo() {
        System.out.println("Room Number: " + roomNumber);
        System.out.println("Type: " + type);
        System.out.println("Occupied: " + isOccupied);
    }

    public int getRoomNumber() {
        return roomNumber;
    }
}