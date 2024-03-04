package campbell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        final String SERVER_IP = "localhost";
        final int SERVER_PORT = 12345;

        try (
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            // Example: Send JSON data to server
            String jsonData = "{\"message\": \"Hello from client!\"}";
            out.println(jsonData);

            // Receive response from server
            String response;
            while ((response = in.readLine()) != null) {
                // Handle incoming JSON response
                System.out.println("Received from server: " + response);
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

