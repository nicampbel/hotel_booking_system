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

        // Update the UI
        SwingUtilities.invokeLater(() -> {
            bookingUI[0].updateUI();
        });
    }
}
