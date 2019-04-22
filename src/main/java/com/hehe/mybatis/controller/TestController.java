package com.hehe.mybatis.controller;

import com.hehe.mybatis.entity.User;
import com.hehe.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TestController {
    @Autowired
    UserMapper userMapper;

    @GetMapping("test1")
    @ResponseBody
    public List<User> list2() {
        return userMapper.list2();
    }

    @GetMapping("test")
    @ResponseBody
    public String index(){
        String a =userMapper.list().toString();
        String b = userMapper.getOne("1").getUsername();
        return "x"+a+"---"+b;
    }

    @GetMapping("test3")
    public String lsit(Model model){
        List<User> users = userMapper.list2();
        model.addAttribute("userList",users);
        return "cache/user"; // 跳转到springboot03\src\main\resources\template
    }

    @RequestMapping("/test4")
    public String lsit2(Model model){
        List<User> users = userMapper.list2();
        model.addAttribute("userList",users);
        return "customer/list"; // 跳转到springboot03\src\main\resources\template
    }

    @RequestMapping("addUser")
    public String list2(User user) throws Exception{
        userMapper.addUser(user);
        return "redirect:user/list";
    }


}

