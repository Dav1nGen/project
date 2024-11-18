package cn.edu.nbut.chatroom.dao.mapper;

import cn.edu.nbut.chatroom.model.UserData;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface IUserDataMapper {
        @Insert("INSERT INTO users_data(name, id,password_hash_val) VALUES(#{name}, #{id},#{passwordHashVal})")
        void insertUserData(UserData userData);

        @Select("SELECT id, name,password_hash_val as passwordHashVal FROM users_data WHERE id = #{id}")
        UserData selectUserData(String id);

        @Update("UPDATE users_data SET name = #{name} ,id = #{id},password_hash_val = #{passwordHashVal} WHERE id = #{id}")
        void updateUserData(UserData userData);

        @Delete("DELETE FROM users_data WHERE id = #{id}")
        void deleteUserData(String id);
}
