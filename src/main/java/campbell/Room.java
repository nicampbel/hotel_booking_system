/*
 * ╔════════════════════════════════════════════════════╗
 * ║                  File: Room.java                   ║
 * ║  Description: Room Class                           ║
 * ║  Author: NE Campbell, 2024                         ║
 * ╚════════════════════════════════════════════════════╝
 */

package campbell;

public class Room {
    private String roomNumber;
    private String status;

    public Room(String roomNumber, String status) {
        this.roomNumber = roomNumber;
        this.status = status;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getStatus() {
        return status;
    }

    public boolean isAvailable() {
        return status.equals("Empty");
    }

    public void bookRoom() {
        status = "Booked";
    }

    public void cancelBooking() {
        status = "Empty";
    }
}
