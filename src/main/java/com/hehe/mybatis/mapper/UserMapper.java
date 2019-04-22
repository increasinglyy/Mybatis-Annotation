package com.hehe.mybatis.mapper;

import com.hehe.mybatis.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {
    //方法1
    @Select("select * from t_user")
    List<User> list();
    //方法2
    @SelectProvider(type = UserSqlProvider.class, method = "list22")
    List<User> list2();

    @Select("select * from t_user where username like #{username}")
    List<User> findByUsername(String username);

    @Select("select * from t_user where user_id like #{userId}")
    List<User> findByUserId(String userId);

    @Select("select * from t_user where user_id like #{userId}")
    User getOne(String userId);

    @Insert("insert into t_user (user_id,username,password) values(null, #{username}, #{password})")
    int addUser(User user);

    @Update("update t_user set username=#{username},password=#{password} where user_id=#{userId}")
    //@Update("update t_user set username='admin',password='xx' where user_id='1'")
    int editUser(User user);


    @Delete("delete from t_user where user_id like #{userId}")
    int delete(String userId);
}
