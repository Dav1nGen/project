package cn.edu.nbut.chatroom.controller;

import cn.edu.nbut.chatroom.dao.mapper.IUserMapper;
import cn.edu.nbut.chatroom.model.User;
import cn.edu.nbut.chatroom.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.nbut.chatroom.service.impl.UserService;
import cn.edu.nbut.chatroom.model.Result;

import java.util.Date;

@Slf4j
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public String userForm(Model model) {
        model.addAttribute("user", new User());
        return "user";
    }

    // 测试登录返回jwt token
    @GetMapping("/login")
    public Result<String> login() {

        String jwt = JwtUtil.createToken(new User("123", "test"),
                new Date(System.currentTimeMillis() + 1000 * 600));
        return new Result<>(1, "success!", jwt);

    }

    //注册用户
    @PostMapping("/user/signUp")
    public Result<String> signUp(@RequestParam String id, @RequestParam String password,@RequestParam String verifyPassword) {
        if (userService.validateSignup(id, password,verifyPassword)){
            return Result.success("sign up successful");
        } else {
            return new Result<>(0, "sign up failed", null);
        }
    }

    //登录控制
    @PostMapping("/user/signIn")
    public Result<String> signIn(@RequestParam String id, @RequestParam String password) {
        if (userService.validateSignIn(id, password)) {
            String jwt = JwtUtil.createToken(userService.getUserByID(id),
                    new Date(System.currentTimeMillis() + 1000 * 600));
            return new Result<>(1, "sign in successful!", jwt);
        } else {
            log.info("登陆失败，请检查用户名和密码");
            return new Result<>(0, "sign in failed", null);
        }
    }

    //修改密码控制
    @PostMapping("/user/changePassword")
    public Result<String> changePassword(@RequestParam String id,@RequestParam String oldPassword, @RequestParam String newPassword,@RequestParam String verifyNewPassword) {
        if (userService.validateChangePassword(id, oldPassword, newPassword, verifyNewPassword)) {
            return Result.success("change password successful");
        } else {
            return new Result<>(0, "change password failed", null);
        }
    }

    //忘记密码控制
    @PostMapping("/user/forgetPassword")
    public Result<String> forgetPassword(@RequestParam String id, @RequestParam String newPassword,@RequestParam String verifyNewPassword) {
        if (userService.validateForgetPassword(id, newPassword, verifyNewPassword)) {
            return Result.success("password change successful");
        } else {
            return new Result<>(0, "password change failed", null);
        }
    }
}
