package campbell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.google.gson.Gson;

public class Server {
    public static void main(String[] args) {
        final int PORT = 12345;

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started. Waiting for clients...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);

                // Handle client in a separate thread
                new Thread(new ClientHandler(clientSocket)).start();
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

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
            this.gson = new Gson();
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
                    System.out.println("Received from client: " + request.getType());

                    // Process request based on type
                    if (request.getType().equals("makeBooking")) {
                        String roomNumber = request.getRoomNumber();
                        System.out.println("Book room " + roomNumber);

                        // Booking Code

                        ResponseMessage response = new ResponseMessage("Booked");
                        out.println(gson.toJson(response));
                    } else if (request.getType().equals("cancelBooking")) {
                        String roomNumber = request.getRoomNumber();
                        System.out.println("Cancel room " + roomNumber);

                        // Cancel Code

                        ResponseMessage response = new ResponseMessage("Cancelled");
                        out.println(gson.toJson(response));
                    } else {
                        System.out.println("Invalid request type");
                        // Handle invalid request type
                    }


                }

                in.close();
                out.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}




