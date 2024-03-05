package campbell;

import java.io.File;
import java.io.IOException;

public class Run {
    public static void main(String[] args) {
        try {
            // Start the Server.java
            ProcessBuilder serverProcessBuilder = new ProcessBuilder("java", "Server");
            serverProcessBuilder.directory(new File("C:\\Users\\campb\\OneDrive - Stellenbosch University\\3 Modules\\Holonics\\D1\\hotel_booking_system\\src\\main\\java\\campbell"));
            serverProcessBuilder.start();
            
            // Wait for 2 seconds
            Thread.sleep(2000);
            
            // Start the App.java
            ProcessBuilder appProcessBuilder = new ProcessBuilder("java", "App");
            appProcessBuilder.directory(new File("C:\\Users\\campb\\OneDrive - Stellenbosch University\\3 Modules\\Holonics\\D1\\hotel_booking_system\\src\\main\\java\\campbell"));
            appProcessBuilder.start();
            
            // Optionally, you can wait for both processes to finish
            // serverProcess.waitFor();
            // appProcess.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
