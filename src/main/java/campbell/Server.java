package campbell;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.google.gson.Gson;

public class Server {
    public static void main(String[] args) {
        final int PORT = 12345;

        // Load Hotel Data from json File
        Hotel hotel = HotelDataLoader.loadHotelData();
        if (hotel == null) {
            System.out.println("Failed to load hotel data. Exiting...");
            return; // Exit the application if hotel data loading failed
        }
        // Create Server
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started. Waiting for clients...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);
                // Handle client in a separate thread
                new Thread(new ClientHandler(clientSocket, hotel)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClientHandler implements Runnable {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;
        private Gson gson;
        private Hotel hotel;

        public ClientHandler(Socket socket, Hotel hotel) {
            this.clientSocket = socket;
            this.gson = new Gson();
            this.hotel = hotel;
        }

        @Override
        public void run() {
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    // Parse JSON message to RequestMessage object
                    RequestMessage request = gson.fromJson(inputLine, RequestMessage.class);
                    System.out.println("Received from client: " + request.getType() + " " + request.getRoomNumber());

                    // Process request based on type
                    if (request.getType().equals("makeBooking")) {
                        processBooking(request.getRoomNumber(), true);
                        ResponseMessage response = new ResponseMessage("Booked");
                        out.println(gson.toJson(response));

                    } else if (request.getType().equals("cancelBooking")) {
                        processBooking(request.getRoomNumber(), false);
                        ResponseMessage response = new ResponseMessage("Cancelled");
                        out.println(gson.toJson(response));

                    } else if (request.getType().equals("getData")) {
                        // Send hotel data to the client
                        sendHotelDataToClient(clientSocket, hotel);
                    } else {
                        System.out.println("Invalid request type");
                    }
                }
                // sendHotelDataToClient(clientSocket, hotel);
                in.close();
                out.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private synchronized void processBooking(String roomNumber, boolean book) {
            for (Floor floor : hotel.getFloors()) {
                for (Room room : floor.getRooms()) {
                    if (room.getRoomNumber().equals(roomNumber)) {
                        if (book)
                            room.bookRoom();
                        else
                            room.cancelBooking();
                        saveHotelData();
                        return;
                    }
                }
            }
        }

        private void saveHotelData() {
            // Save the updated hotel data back to the JSON file
            Gson gson = new Gson();
            try (FileWriter writer = new FileWriter("src/main/resources/hotel_data.json")) {
                gson.toJson(hotel, writer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private static void sendHotelDataToClient(Socket clientSocket, Hotel hotel) throws IOException {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            Gson gson = new Gson();
            String hotelDataJson = gson.toJson(hotel);
            out.println(hotelDataJson);
            out.flush();
        }
    }
}




