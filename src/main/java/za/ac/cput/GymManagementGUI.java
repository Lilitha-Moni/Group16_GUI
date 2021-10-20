package za.ac.cput;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GymManagementGUI {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 8080;

    public static String serverAddress() {
        return String.format("http://%s:%d", SERVER_ADDRESS, SERVER_PORT);
    }

    public static void main(String[] args) {
        SpringApplication.run(GymManagementGUI.class, args);
    }
}
