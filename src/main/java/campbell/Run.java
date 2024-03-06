/*
 * ╔════════════════════════════════════════════════════╗
 * ║                  File: Run.java                    ║
 * ║  Description:                                      ║
 * ║  ------------                                      ║
 * ║  Runs the Server and App on seperate threads with  ║
 * ║  small delay to allow server to start              ║
 * ║                                                    ║
 * ║  Author: NE Campbell, 2024                         ║
 * ╚════════════════════════════════════════════════════╝
 */

package campbell;

public class Run {
    public static void main(String[] args) {
        Thread serverThread = new Thread(() -> {
            Server.main(args);
        });
        
        Thread appThread = new Thread(() -> {
            try {
                // Wait for 2 seconds before starting the App
                Thread.sleep(2000);
                App.main(args);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        
        serverThread.start();
        appThread.start();
    }
}