/*
 * ╔════════════════════════════════════════════════════╗
 * ║                  File: Floor.java                  ║
 * ║  Description: Floor Class                          ║
 * ║  Author: NE Campbell, 2024                         ║
 * ╚════════════════════════════════════════════════════╝
 */

package campbell;

import java.util.ArrayList;
import java.util.List;

public class Floor {
    private int floorNumber;
    private List<Room> rooms;

    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.rooms = new ArrayList<>();
        initializeRooms();
    }

    private void initializeRooms() {
        char roomLetter = 'a';
        for (int i = 0; i < 5; i++) {
            String roomNumber = String.valueOf(floorNumber) + roomLetter++;
            rooms.add(new Room(roomNumber, "Empty"));
        }
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public Room getRoom(String roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber().equals(roomNumber)) {
                return room;
            }
        }
        return null;
    }
}
