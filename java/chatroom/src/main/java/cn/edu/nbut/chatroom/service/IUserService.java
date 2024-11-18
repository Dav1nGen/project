package cn.edu.nbut.chatroom.service;

public interface IUserService {
    boolean validateSignup(String id, String password, String verifyPassword);
    boolean validateSignIn(String id, String password);
    boolean validateChangePassword(String id, String oldPassword,String newPassword,String verifyNewPassword);
    boolean validateForgetPassword(String id,String newPassword,String verifyNewPassword);
    String getUsernameByID(String id);
}
