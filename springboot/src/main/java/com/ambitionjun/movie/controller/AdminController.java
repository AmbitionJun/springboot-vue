package com.ambitionjun.movie.controller;

import com.ambitionjun.movie.pojo.Admin;
import com.ambitionjun.movie.service.Impl.AdminServiceImpl;
import com.ambitionjun.movie.support.JwtTokenUtil;
import com.ambitionjun.movie.support.Roles;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminServiceImpl adminService;

    @ResponseBody
    @GetMapping("/admin")
    public Admin getAdminById(@RequestParam("id") Long admin_id){
       return adminService.getById(admin_id);
    }
    //查询所有管理员
    @CrossOrigin
    @ResponseBody
    @GetMapping("/select")
    public List<Admin> queryAllAdmin(){
        List<Admin> adminList = adminService.queryAllAdmin();
        return adminList;
    }
    //增加管理员
    @CrossOrigin
    @ResponseBody
    @PostMapping("/add")
    public Map<String,Boolean> insertAdmin(@RequestBody Admin admin){
        adminService.insertAdmin(admin);
        HashMap<String, Boolean> map = new HashMap<>();
        map.put("success",true);
        return map;
    }

    //管理员登录
    @CrossOrigin
    @ResponseBody
    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody Admin admin){

        Admin admin_re = adminService.queryAdmin(admin.getAdmin_name(),admin.getAdmin_password());
        Map<String, Object> map = new HashMap<>();
        long exp = JwtTokenUtil.REMEMBER_EXPIRATION_TIME;
        List<String> roles = new ArrayList<>();
        roles.add(Roles.ROLE_ADMIN);
        if(admin_re!=null){
            map.put("token", JwtTokenUtil.createToken(admin.getAdmin_name(), roles, exp));
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
    public Map<String,Boolean> deleteAdmin(@RequestParam("admin_id") Long admin_id /*@RequestBody Admin admin*/){
        adminService.deleteAdmin(admin_id);
        HashMap<String, Boolean> map = new HashMap<>();
        map.put("success",true);
        return map;
    }
    //修改管理员
    @CrossOrigin
    @ResponseBody
    @PutMapping("/update")
    public Map<String,Boolean> updateAdmin(@RequestBody Admin admin){
        adminService.updateAdmin(admin);
        HashMap<String, Boolean> map = new HashMap<>();
        map.put("success",true);
        return map;
    }

}
