package za.ac.cput.repository;

import za.ac.cput.entity.GymSession;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

    public void create(GymSession gymSession) {
        repository.put(gymSession.getSessionId(), gymSession);
    }

    public GymSession read(String sessionId) {
        return repository.get(sessionId);
    }

    public void delete(String sessionId) {
        repository.remove(sessionId);
    }

}
