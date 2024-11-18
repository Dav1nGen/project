package cn.edu.nbut.chatroom;

import cn.edu.nbut.chatroom.dao.DaoOperate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import cn.edu.nbut.chatroom.model.User;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DaoTests {
    final String id ="110110110";
    final String name = "luoyebai";
    final String password = "123123123";
    @Test
    public void testUser() {
        User user = new User(id,name);
        DaoOperate.insertUser(user);
        assert(Objects.equals(DaoOperate.selectUser(id).getName(), name));
        DaoOperate.deleteUser(id);
        assertNull(DaoOperate.selectUser(id));
    }

    @Test
    public void testUserData() {
        User user = new User(id,name);
        DaoOperate.insertUserData(user,password);
        assert(Objects.equals(DaoOperate.selectUserData(id).getPasswordHashVal(),
                DaoOperate.getHashValByPassword(password)));
        DaoOperate.deleteUserData(id);
        assertNull(DaoOperate.selectUser(id));
    }

}
