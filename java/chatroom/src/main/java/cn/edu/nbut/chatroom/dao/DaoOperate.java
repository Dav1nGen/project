package cn.edu.nbut.chatroom.dao;

import cn.edu.nbut.chatroom.dao.mapper.IUserDataMapper;
import cn.edu.nbut.chatroom.dao.mapper.IUserMapper;

import cn.edu.nbut.chatroom.model.User;
import cn.edu.nbut.chatroom.model.UserData;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.io.InputStream;

public class DaoOperate {
    private static final String resource = "mybatis/mybatis-config.xml";
    private static final InputStream inputStream;
    private static final Logger log = LoggerFactory.getLogger(DaoOperate.class);
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    static {
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static final SqlSessionFactory  sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

    public static String getHashValByPassword(String password){
        return passwordEncoder.encode(password);
    }

    public static boolean matchPassword(String password,String hashVal){
        return passwordEncoder.matches(password,hashVal);
    }

    public static boolean insertUser(User user) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IUserMapper mapper = session.getMapper(IUserMapper.class);
            mapper.insertUser(user);
            session.commit();
            return true;
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
    }

    public static User selectUser(String userId) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IUserMapper mapper = session.getMapper(IUserMapper.class);
            return mapper.selectUser(userId);
        }
    }

    public static boolean updateUser(User user) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IUserMapper mapper = session.getMapper(IUserMapper.class);
            mapper.updateUser(user);
            session.commit();
            return true;
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
    }

    public static boolean deleteUser(String id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IUserMapper mapper = session.getMapper(IUserMapper.class);
            mapper.deleteUser(id);
            session.commit();
            return true;
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
    }


    public static boolean insertUserData(UserData userData) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IUserDataMapper mapper = session.getMapper(IUserDataMapper.class);
            mapper.insertUserData(userData);
            session.commit();
            return true;
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
    }

    public static boolean insertUserData(User user,String password) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IUserDataMapper mapper = session.getMapper(IUserDataMapper.class);
            UserData userData = new UserData(user.getId(), user.getName(),getHashValByPassword(password));
            mapper.insertUserData(userData);
            session.commit();
            return true;
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
    }

    static public UserData selectUserData(String userId) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IUserDataMapper mapper= session.getMapper(IUserDataMapper.class);
            return mapper.selectUserData(userId);
        }
    }

    static public boolean updateUserData(UserData userData) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IUserDataMapper mapper= session.getMapper(IUserDataMapper.class);
            mapper.updateUserData(userData);
            session.commit();
            return true;
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
    }

    static public boolean updateUserData(User user,String passwordHashVal) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IUserDataMapper mapper= session.getMapper(IUserDataMapper.class);
            mapper.updateUserData(new UserData(user.getId(),user.getName(),passwordHashVal));
            session.commit();
            session.close();
            return true;
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
    }

    public static boolean deleteUserData(String id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IUserDataMapper mapper = session.getMapper(IUserDataMapper.class);
            mapper.deleteUserData(id);
            session.commit();
            return true;
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
    }

}
