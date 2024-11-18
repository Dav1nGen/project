package cn.edu.nbut.chatroom.utils;


import jakarta.websocket.Session;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionPool {
    private final Map<String, Session> SESSIONS = new ConcurrentHashMap<>();

    public void add(String userId, Session session) {
        assert (!userId.isEmpty() && session != null);
        SESSIONS.put(userId, session);
    }

    public void remove(String userId) {
        assert (!userId.isEmpty());
        SESSIONS.remove(userId);
    }

    public int countSessions() {
        return SESSIONS.size();
    }

    public Map<String, Session> getSESSIONS() {
        return SESSIONS;
    }
}
