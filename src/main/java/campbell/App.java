package campbell;

import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        // Initialize the hotel
        Hotel hotel = new Hotel();

        // Create and display the UI
        SwingUtilities.invokeLater(() -> {
            HotelRoomBookingUI bookingUI = new HotelRoomBookingUI(hotel);
            bookingUI.setVisible(true);
            bookingUI.updateUI();
        });
    }
}
