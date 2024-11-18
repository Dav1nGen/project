package cn.edu.nbut.chatroom.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonMessage {
    private Date time;
    private String id;
    private String name;
    private String message;

    public JsonMessage(Date time, User user, String message) {
        this.time = time;
        id = user.getId();
        name = user.getName();
        this.message = message;
    }
}
