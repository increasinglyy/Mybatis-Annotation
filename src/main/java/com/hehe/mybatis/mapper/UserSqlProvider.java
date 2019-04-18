package com.hehe.mybatis.mapper;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.hehe.mybatis.entity.User;

import java.lang.reflect.Field;

public class UserSqlProvider {
    /*
    * 手工编写返回String
    *
    */
    public String list22(){
        return "select * from t_user";
    }
    /**
     * 1.用于获取结果集的映射关系
     */
    public static String getResultsStr(Class origin) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("@Results({\n");
        for (Field field : origin.getDeclaredFields()) {
            String property = field.getName();
            //映射关系：对象属性(驼峰)->数据库字段(下划线)
            String column = new PropertyNamingStrategy.SnakeCaseStrategy().translate(field.getName()).toUpperCase();
            stringBuilder.append(String.format("@Result(property = \"%s\", column = \"%s\"),\n", property, column));
        }
        stringBuilder.append("})");
        return stringBuilder.toString();
    }

    /**
     * 2.打印结果集的映射关系. 例如：
     *
     * @Results({
     * @Result(property = "userId", column = "USER_ID"),
     * @Result(property = "username", column = "USERNAME"),
     * @Result(property = "password", column = "PASSWORD"),
     * @Result(property = "mobileNum", column = "PHONE_NUM"),
     * })
     */
    public static void main(String[] args) {
        System.out.println(getResultsStr(User.class));
    }
}
