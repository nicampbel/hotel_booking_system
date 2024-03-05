/*
 * ╔════════════════════════════════════════════════════╗
 * ║                  File: App.java                    ║
 * ║  Description:                                      ║
 * ║  ------------                                      ║
 * ║  Runs the setup of the UI/Client                   ║
 * ║                                                    ║
 * ║  Author: NE Campbell, 2024                         ║
 * ╚════════════════════════════════════════════════════╝
 */

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
