package com.hehe.mybatis.controller;

import com.hehe.mybatis.entity.User;
import com.hehe.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping("/list2")
    public String lsit(Model model){
        List<User> users = userMapper.list2();
        model.addAttribute("userList",users);//将后端数据传给前端
        return "user/list"; // 跳转到springboot03\src\main\resources\template
    }


    @GetMapping("list/{username}")  //将地址的username解析，传入方法中
    public List<User> listByUsername(@PathVariable("username") String username){
        return userMapper.findByUsername(username);
    }

    //@GetMapping("user/{userid}")
    public ModelAndView listByUserid(@PathVariable("userid") String userid){
        List<User> oneUser = userMapper.findByUserId(userid);
        User aUser = oneUser.get(0);
        return new ModelAndView("user/oneUser", "user", aUser);
    }

    @ResponseBody
    @GetMapping("user/{userid}")
    public String getOneUser(@PathVariable("userid") String userid){
        User oneUser = userMapper.getOne(userid);
        if (oneUser==null || oneUser.getUserId()==null){
            return "fail";
        }
        else {
            return oneUser.getUsername();
        }
    }


    //编辑页面，当点击list页面的编辑后跳转到edit页面
    @RequestMapping("edit")
    public String edit(ModelMap map, @RequestParam(defaultValue = "0") String id){//从list页面获取useId
        //isAdd : 向前端页面返回一个是新增与编辑的标识
        if(Integer.valueOf(id).intValue() > 0){
            //Attribute用于edit页面
            map.addAttribute("isAdd",false);
            map.addAttribute("user",userMapper.getOne(id));
        }else{
            map.addAttribute("isAdd",true);
            map.addAttribute("user",new User());
        }
        return "user/edit";//跳转页面
    }

    //新增和编辑
    @ResponseBody
    @RequestMapping("save")
    public String save(@ModelAttribute User user){
        if(user == null){
            return "fail";
        }
        int i=-1;
        if(user.getUserId() != null) {
            i = userMapper.editUser(user);
            System.out.println(i+ user.getUserId() + "。");
        }
        if (i==0 && user.getUserId()!=null){

            i = userMapper.addUser(user);
            System.out.println(i);
            return "success add ";
        }
        return "success" + user.getUserId() + "。";
    }

    //先要跳转到add页面
    //add，将前端数据传给后端
    //不能添加id为空的数据
    @RequestMapping("add")
    public String add(ModelMap map){
        map.addAttribute("isAdd",true);
        map.addAttribute("user",new User());
        return "user/edit";
    }

    //删除
    @ResponseBody
    @RequestMapping("del/{id}")
    public String delete(@PathVariable("id") String id){
        User user = userMapper.getOne(id);
        if (user==null || user.getUserId()==null){
            return "fail";
        }
        else if (user.getUserId()!=null){
            userMapper.deleteById(id);
            return "delete " + user.getUsername();
        }
        return "fail";
    }


}
