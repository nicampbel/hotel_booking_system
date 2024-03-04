package campbell;

import javax.swing.SwingUtilities;

public class App
{
    public static void main(String[] args) {
        // Initialize the hotel
        Hotel hotel = new Hotel();

        // Declare bookingUI as final variable
        final HotelRoomBookingUI[] bookingUI = {null};

        // Create the UI
        SwingUtilities.invokeLater(() -> {
            bookingUI[0] = new HotelRoomBookingUI(hotel);
            bookingUI[0].setVisible(true);
        });

        // // Simulate booking and canceling rooms after 3 seconds
        // try {
        //     Thread.sleep(3000); // Wait for 3 seconds
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }

        // // Simulate booking Room 1a
        // Room room1a = hotel.getRoom("1a");
        // if (room1a != null && room1a.isAvailable()) {
        //     room1a.bookRoom();
        // }

        // // Simulate canceling booking for Room 2c
        // Room room2c = hotel.getRoom("2c");
        // if (room2c != null && !room2c.isAvailable()) {
        //     room2c.cancelBooking();
        // }

        // Update the UI
        SwingUtilities.invokeLater(() -> {
            bookingUI[0].updateUI();
        });
    }
}
