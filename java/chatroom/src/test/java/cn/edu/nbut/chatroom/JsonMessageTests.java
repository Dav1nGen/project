package cn.edu.nbut.chatroom;

import cn.edu.nbut.chatroom.model.User;
import cn.edu.nbut.chatroom.service.impl.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class JsonMessageTests {

    @Autowired
    private MessageService messageService;


    @Test
    public void test(){
        String jsonMessage =  messageService.getJsonMessage(new User("123", "test"),
                "testMessage");
        log.info(jsonMessage);
    }
}
