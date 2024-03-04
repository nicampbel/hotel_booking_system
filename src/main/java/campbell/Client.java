package campbell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.google.gson.Gson;

public class Client {
    public static void main(String[] args) {
        final String SERVER_IP = "localhost";
        final int SERVER_PORT = 12345;
        Gson gson = new Gson();

        try (
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            // Create and send RequestMessage object to server
            RequestMessage request = new RequestMessage("bookRoom");
            String jsonRequest = gson.toJson(request);
            out.println(jsonRequest);

            // Receive and parse ResponseMessage object from server
            String jsonResponse = in.readLine();
            ResponseMessage response = gson.fromJson(jsonResponse, ResponseMessage.class);
            System.out.println("Received from server: " + response.getStatus());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


