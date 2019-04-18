package com.hehe.mybatis.controller;

import com.hehe.mybatis.entity.User;
import com.hehe.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/*")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("list")
    public List<User> list(){
        return userMapper.list2();
    }

    @GetMapping("list/{username}")  //将地址的username解析，传入方法中
    public List<User> listByUsername(@PathVariable("username") String username){
        return userMapper.findByUsername(username);
    }




    public String index(){
        String a =userMapper.list().toString();
        String b = userMapper.getOne("1").getUsername();
        return "x"+a+"---"+b;
    }
}
