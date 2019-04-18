package com.hehe.mybatis.mapper;

import com.hehe.mybatis.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

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
    User getOne(String userId);

    @Delete("delete from t_user where user_id like #{userId}")
    int delete(String userId);
}
