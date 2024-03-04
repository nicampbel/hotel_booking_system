package campbell;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class HotelRoomBookingUI extends JFrame {

    private Hotel hotel;

    public HotelRoomBookingUI(Hotel hotel) {
        this.hotel = hotel;
        initComponents();
    }

private void initComponents() {
    setTitle("Hotel Room Booking System");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    JPanel roomStatusPanel = new JPanel(new GridLayout(0, 5, 10, 10)); // Increased gap between buttons
    List<Floor> floors = hotel.getFloors();
    for (Floor floor : floors) {
        List<Room> rooms = floor.getRooms();
        for (Room room : rooms) {
            JButton roomButton = new JButton(room.getRoomNumber());

            // Set button size
            roomButton.setPreferredSize(new Dimension(100, 100));

            // Set button colors based on room status
            if (room.isAvailable()) {
                roomButton.setBackground(Color.LIGHT_GRAY); // Empty rooms are light grey
                roomButton.setForeground(Color.BLACK); // Set text color to black
            } else {
                roomButton.setBackground(Color.DARK_GRAY); // Booked rooms are blue
                roomButton.setForeground(Color.WHITE); // Set text color to white
            }

            roomButton.addActionListener(new RoomButtonListener(room));
            roomStatusPanel.add(roomButton);
        }
    }
    add(new JScrollPane(roomStatusPanel), BorderLayout.CENTER);

    pack();
    setLocationRelativeTo(null);
}


    public void updateUI() {
        getContentPane().removeAll();
        initComponents();
        revalidate();
        repaint();
    }

    private class RoomButtonListener implements ActionListener {
        private Room room;

        public RoomButtonListener(Room room) {
            this.room = room;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (room.isAvailable()) {
                room.bookRoom();
            } else {
                room.cancelBooking();
            }
            updateUI();
        }
    }
}
