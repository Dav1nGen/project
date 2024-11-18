package cn.edu.nbut.chatroom.controller;

import cn.edu.nbut.chatroom.model.User;
import cn.edu.nbut.chatroom.service.impl.MessageService;
import cn.edu.nbut.chatroom.service.impl.UserService;
import cn.edu.nbut.chatroom.utils.SessionPool;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Slf4j
@Controller
@ServerEndpoint(value = "/chat/{userId}")
public class WebSocketController {

    private static final SessionPool SOCKET_POOL = new SessionPool();

    private User user;
    private MessageService messageService = new MessageService();
    @Autowired
    private UserService userService = new UserService();

    @OnOpen
    public void onOpen(@PathParam("userId") String userId, Session session) {
        SOCKET_POOL.add(userId, session);
        // 查找到用户
        user = userService.getUserByID(userId);
        log.info("WebSocket连接成功，用户ID：{}, 当前用户数量：{}", userId, SOCKET_POOL.countSessions());
    }

    @OnClose
    public void onClose(@PathParam("userId") String userId, Session session) {
        SOCKET_POOL.remove(userId);
        log.info("WebSocket连接关闭，用户ID：{}, 当前用户数量：{}", userId, SOCKET_POOL.countSessions());
    }

    @OnMessage
    public void onMessage(@PathParam("userId") String userId, String message) {
        log.info("收到消息，用户ID：{}, 消息内容：{}", userId, message);
        String jsonMessage = messageService.getJsonMessage(user, message);
        messageService.sendMessageAll(userId, jsonMessage, SOCKET_POOL);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误,原因：{}", error.getMessage());
    }
}
