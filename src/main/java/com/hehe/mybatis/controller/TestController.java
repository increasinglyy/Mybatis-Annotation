package com.hehe.mybatis.controller;

import com.hehe.mybatis.entity.User;
import com.hehe.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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

    @RequestMapping("edit")
    public String edit(ModelMap map, @RequestParam(defaultValue = "0") String id){
        //isAdd : 向前端页面返回一个是新增与编辑的标识
        if(Integer.valueOf(id).intValue() > 0){
            map.addAttribute("isAdd",false);
            map.addAttribute("customer",userMapper.getOne(id));
        }else{
            map.addAttribute("isAdd",true);
            map.addAttribute("customer",new User());
        }
        return "customer/edit";
    }

    //新增和编辑
    @ResponseBody
    @RequestMapping("save")
    public String save(@ModelAttribute User user){
        if(user == null){
            return "fail";
        }
        if(user.getUserId() != null){
            userMapper.editUser(user);
        }
        return "success";
    }
}

