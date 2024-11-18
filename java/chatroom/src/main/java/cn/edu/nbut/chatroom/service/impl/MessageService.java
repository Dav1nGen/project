package cn.edu.nbut.chatroom.service.impl;

import cn.edu.nbut.chatroom.model.JsonMessage;
import cn.edu.nbut.chatroom.model.User;
import cn.edu.nbut.chatroom.service.IMessageService;
import cn.edu.nbut.chatroom.utils.SessionPool;
import com.alibaba.fastjson2.JSON;
import jakarta.websocket.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class MessageService implements IMessageService {

    /**
     * 发送消息
     * @param message
     * @param session
     */
    @Override
    public void sendMessage(String message, Session session) {
        assert (message != null && session != null);
        // 发送消息
        try {
            session.getBasicRemote().sendText(message);
        } catch (Exception e) {
            log.error("发送消息失败", e);
            log.error(e.getMessage());
        }
    }

    /**
     * 发送消息给除自己以外的所有用户
     * @param userId
     * @param message
     * @param sessionPool
     */
    @Override
    public void sendMessageAll(String userId, String message, SessionPool sessionPool) {
        for (Session session : sessionPool.getSESSIONS().values()) {
            if (session != sessionPool.getSESSIONS().get(userId)) {
                sendMessage(message, session);
            }
        }
    }

    /**
     * 获取Json格式的消息
     * @param user
     * @param message
     * @return String
     */
    @Override
    public String getJsonMessage(User user, String message) {
        Date currentTime = new Date();
        JsonMessage jsonMessage = new JsonMessage(currentTime, user, message);
        return JSON.toJSONString(jsonMessage);
    }
}

