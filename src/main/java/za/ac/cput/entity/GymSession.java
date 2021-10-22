package za.ac.cput.entity;

import org.springframework.boot.web.servlet.server.Session;

import javax.servlet.http.HttpSession;
import java.util.Base64;

public class GymSession {
    String sessionId;
    String username;
    String password;

    public GymSession() {}

    public GymSession(String sessionId, String username, String password) {
        this.sessionId = sessionId;
        this.username = username;
        this.password = password;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String encoded() {
        return "Basic "+Base64.getUrlEncoder().encodeToString(String.format("%s:%s", username, password).getBytes());
    }

    @Override
    public String toString() {
        return "GymSession{" +
                "sessionId='" + sessionId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
