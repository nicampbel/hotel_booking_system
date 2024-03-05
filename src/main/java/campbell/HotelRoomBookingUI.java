package campbell;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.google.gson.Gson;

public class HotelRoomBookingUI extends JFrame {

    private Hotel hotel;
    private final String SERVER_IP = "localhost";
    private final int SERVER_PORT = 12345;
    private PrintWriter out;
    private BufferedReader in;
    private Gson gson;

    public HotelRoomBookingUI(Hotel hotel) {
        // this.hotel = hotel;
        initializeClient();
        initComponents();
        // Populate GUI from JSON file in server

        // String jsonHotel = hotel.toJson();
        // System.out.println(jsonHotel);
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

    private void initializeClient() {
        Socket socket = null; // Declare the socket variable outside the try block
        try {
            socket = new Socket(SERVER_IP, SERVER_PORT);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // Initialize BufferedReader
            gson = new Gson();

            RequestMessage request = new RequestMessage("getData");
            String jsonRequest = gson.toJson(request);
            out.println(jsonRequest);

            // Read the hotel data sent by the server upon connection
            String hotelDataJson = in.readLine();
            this.hotel = gson.fromJson(hotelDataJson, Hotel.class);
            // Indicate that the client has received hotel data
            System.out.println("Received hotel data from the server.");
            System.out.println("Number of floors: " + this.hotel.getFloors().size());

        } catch (IOException e) {
            e.printStackTrace();
        }
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
            //SEND ROOM NUMBER
            if (room.isAvailable()) {
                RequestMessage request = new RequestMessage("makeBooking");
                request.setRoomNumber(room.getRoomNumber()); // Set room number
                String jsonRequest = gson.toJson(request);
                out.println(jsonRequest);
            } else {
                RequestMessage request = new RequestMessage("cancelBooking");
                request.setRoomNumber(room.getRoomNumber()); // Set room number
                String jsonRequest = gson.toJson(request);
                out.println(jsonRequest);
            }
            // Receive and process response from the server
            try {
                String jsonResponse = in.readLine();
                ResponseMessage response = gson.fromJson(jsonResponse, ResponseMessage.class);
                // Process response here (e.g., update UI based on response)
                if (response.getStatus().equals("Booked"))
                    room.bookRoom();
                if (response.getStatus().equals("Cancelled"))
                    room.cancelBooking();
                updateUI();
                System.out.println("Server response: " + response.getStatus());
                } catch (IOException ex) {
                    ex.printStackTrace();
            }
        }
    }
}
