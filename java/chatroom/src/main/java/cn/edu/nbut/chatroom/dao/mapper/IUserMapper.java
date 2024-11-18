package cn.edu.nbut.chatroom.dao.mapper;

import org.apache.ibatis.annotations.*;

import cn.edu.nbut.chatroom.model.User;

public interface IUserMapper {
    @Insert("INSERT INTO users(name, id) VALUES(#{name}, #{id})")
    void insertUser(User user);

    @Select("SELECT id, name FROM users WHERE id = #{id}")
    User selectUser(String id);

    @Update("UPDATE users SET name = #{name}, id = #{id} WHERE id = #{id}")
    void updateUser(User user);

    @Delete("DELETE FROM users WHERE id = #{id}")
    void deleteUser(String id);

}
