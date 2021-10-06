package com.ambitionjun.movie.controller;

import com.ambitionjun.movie.pojo.Admin;
import com.ambitionjun.movie.pojo.User;
import com.ambitionjun.movie.service.Impl.UserServiceImpl;
import com.ambitionjun.movie.support.JwtTokenUtil;
import com.ambitionjun.movie.support.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;


    //查询所有管理员
    @CrossOrigin
    @ResponseBody
    @GetMapping("/selectAll")
    public List<User> queryAllUser(){
        List<User> userList = userService.queryAllUser();
        return userList;
    }
    //增加管理员
    @CrossOrigin
    @ResponseBody
    @PostMapping("/add")
    public Map<String,Boolean> insertUser(@RequestBody User user){
       userService.insertUser(user);
        HashMap<String, Boolean> map = new HashMap<>();
        map.put("success",true);
        return map;
    }

    //管理员登录
    @CrossOrigin
    @ResponseBody
    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody User user){

        User user_re = userService.queryUser(user.getUsername(),user.getPassword());
        Map<String, Object> map = new HashMap<>();
        long exp = JwtTokenUtil.REMEMBER_EXPIRATION_TIME;
        List<String> roles = new ArrayList<>();
        roles.add(Roles.ROLE_ADMIN);
        if(user_re!=null){
            map.put("uid",user_re.getId());
            map.put("token", JwtTokenUtil.createToken(user.getUsername(),roles, exp));
            map.put("success",true);
            return map;
        }else {
            map.put("success",false);
            return map;
        }
    }
    //删除管理员
    @CrossOrigin
    @ResponseBody
    @GetMapping("/delete")
    public Map<String,Boolean> deleteUser(@RequestParam("id") int id /*@RequestBody Admin admin*/){
        userService.deleteUser(id);
        HashMap<String, Boolean> map = new HashMap<>();
        map.put("success",true);
        return map;
    }
    //修改管理员
    @CrossOrigin
    @ResponseBody
    @PutMapping("/update")
    public Map<String,Boolean> updateUser(@RequestBody User user){
        userService.updateUser(user);
        HashMap<String, Boolean> map = new HashMap<>();
        map.put("success",true);
        return map;
    }

}
