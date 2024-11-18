package cn.edu.nbut.chatroom.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

    private int code;
    private String message;
    private T data;

    public static <T> Result<T> success() {
        return new Result<>(1, "success", null);
    }

    public static <T> Result<T> success(String message) {
        return new Result<>(1, message, null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(1, "success", data);
    }
}