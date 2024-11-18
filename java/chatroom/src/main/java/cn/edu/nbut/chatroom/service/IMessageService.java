package cn.edu.nbut.chatroom.service;

import cn.edu.nbut.chatroom.model.User;
import cn.edu.nbut.chatroom.utils.SessionPool;
import jakarta.websocket.Session;

public interface IMessageService {
    void sendMessage(String message, Session session);
    void sendMessageAll(String userId, String message, SessionPool sessionPool);
    String getJsonMessage(User user, String message);
}
