package com.hehe.mybatis.controller;

import com.hehe.mybatis.entity.User;
import com.hehe.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserMapper userMapper;


    @GetMapping("list")
    public ModelAndView list(){
        List<User> userList = userMapper.list2();
        //viewName:"user/user",文件夹路径名,"userList"是html中，userList传入的
        return new ModelAndView("user/list","userList", userList);
    }

    @RequestMapping("/listtest")
    public String lsit(Model model){
        List<User> users = userMapper.list2();
        model.addAttribute("userList",users);
        return "user/list"; // 跳转到springboot03\src\main\resources\template
    }


    @GetMapping("list/{username}")  //将地址的username解析，传入方法中
    public List<User> listByUsername(@PathVariable("username") String username){
        return userMapper.findByUsername(username);
    }

    @GetMapping("user/{userid}")
    public ModelAndView listByUserid(@PathVariable("userid") String userid){
        List<User> oneUser = userMapper.findByUserId(userid);
        User aUser = oneUser.get(0);
        return new ModelAndView("user/oneUser", "user", aUser);
    }


}
