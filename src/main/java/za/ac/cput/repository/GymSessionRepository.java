package za.ac.cput.repository;

import za.ac.cput.entity.GymSession;

import java.util.HashMap;
import java.util.Map;

public class GymSessionRepository {
    private static Map<String, GymSession> repository;
    private static GymSessionRepository current;

    private GymSessionRepository() {
        repository = new HashMap<>();
    }

    public static GymSessionRepository getRepository() {
        if (current == null) current = new GymSessionRepository();
        return current;
    }

    public boolean exists() {
        return (current != null);
    }

    public static void create(GymSession gymSession) {
        getRepository();
        repository.put(gymSession.getSessionId(), gymSession);
    }

    public static GymSession getSession(String sessionId) {
        getRepository();
        return repository.get(sessionId);
    }

    public static void delete(String sessionId) {
        getRepository();
        repository.remove(sessionId);
    }

}
