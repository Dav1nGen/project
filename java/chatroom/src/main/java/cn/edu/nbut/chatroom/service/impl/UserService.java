package cn.edu.nbut.chatroom.service.impl;

import cn.edu.nbut.chatroom.dao.DaoOperate;
import cn.edu.nbut.chatroom.model.User;
import cn.edu.nbut.chatroom.model.UserData;
import cn.edu.nbut.chatroom.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService implements IUserService {
    // 验证注册
    @Override
    public boolean validateSignup(String id, String password, String verifyPassword) {
        // 确认id不重复
        try {
            boolean idNotExist = !checkIdIsExist(id);
            boolean passwordEqual = password.equals(verifyPassword);
            if(!(idNotExist && passwordEqual))
                return false;
            User user = new User(id, "用户"+id);
            UserData userData = new UserData(user.getId(),user.getName(),getHashValByPassword(password));
            return DaoOperate.insertUser(user) && DaoOperate.insertUserData(userData);
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    // 验证登录
    @Override
    public boolean validateSignIn(String id, String password) {
        try {
            // 确认id存在
            boolean idExist = checkIdIsExist(id);
            // 确认id密码是否匹配
            boolean passwordRight = checkPasswordIsRight(id,password);
            return idExist && passwordRight;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    // 验证修改密码
    @Override
    public boolean validateChangePassword(String id, String oldPassword, String newPassword, String verifyNewPassword) {
        try {
            // 确认id存在
            boolean idExist = checkIdIsExist(id);
            // 确认id密码是否匹配
            boolean oldPasswordRight = checkPasswordIsRight(id,oldPassword);
            // 新密码二次校验
            boolean passwordRight = newPassword.equals(verifyNewPassword);
            if(!(idExist && oldPasswordRight && passwordRight))
                return false;
            User user = getUserByID(id);
            String passwordHashVal = getHashValByPassword(newPassword);
            return DaoOperate.updateUser(user) && DaoOperate.updateUserData(user,passwordHashVal);
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    // 验证忘记密码
    @Override
    public boolean validateForgetPassword(String id, String newPassword, String verifyNewPassword) {
        try {
            // 确认id存在
            boolean idExist = checkIdIsExist(id);
            // 确认新旧密码匹配
            boolean passwordRight = newPassword.equals(verifyNewPassword);
            return idExist && passwordRight;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    public String getHashValByPassword(String password){
        return DaoOperate.getHashValByPassword(password);
    }

    // 根据ID查找username
    @Override
    public String getUsernameByID(String id) {
        return DaoOperate.selectUser(id).getName();
    }

    // 根据ID查找user
    public User getUserByID(String id) {
        return DaoOperate.selectUser(id);
    }

    private boolean checkIdIsExist(String id) {
        return DaoOperate.selectUser(id) != null;
    }

    private String getPasswordHashVal(String id){
        return DaoOperate.selectUserData(id).getPasswordHashVal();
    }

    private boolean checkPasswordIsRight(String id,String password){
        return DaoOperate.matchPassword(password,getPasswordHashVal(id));
    }
}
