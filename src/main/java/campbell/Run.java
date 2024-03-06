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