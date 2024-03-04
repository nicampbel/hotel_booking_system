package campbell;

import java.util.List;

public class App
{
    public static void main(String[] args) {
    // Initialize the hotel
    Hotel hotel = new Hotel();

    // Access the floors and rooms in the hotel
    List<Floor> floors = hotel.getFloors();
    for (Floor floor : floors) {
        System.out.println("Floor " + floor.getFloorNumber() + ":");
        List<Room> rooms = floor.getRooms();
        for (Room room : rooms) {
            System.out.println("Room " + room.getRoomNumber() + " - Status: " + room.getStatus());
        }
    }

    // Book a room and display the updated status
    Room roomToBook = hotel.getRoom("1a");
    if (roomToBook != null && roomToBook.isAvailable()) {
        roomToBook.bookRoom();
    }
    System.out.println("\nAfter booking Room 1a:");
    for (Floor floor : floors) {
        List<Room> rooms = floor.getRooms();
        for (Room room : rooms) {
            System.out.println("Room " + room.getRoomNumber() + " - Status: " + room.getStatus());
        }
    }

    // Cancel the booking and display the updated status
    if (roomToBook != null) {
        roomToBook.cancelBooking();
    }
    System.out.println("\nAfter canceling booking for Room 1a:");
    for (Floor floor : floors) {
        List<Room> rooms = floor.getRooms();
        for (Room room : rooms) {
            System.out.println("Room " + room.getRoomNumber() + " - Status: " + room.getStatus());
        }
    }
}
}
