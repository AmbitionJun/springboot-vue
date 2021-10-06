package com.ambitionjun.movie.service;

import com.ambitionjun.movie.pojo.Admin;
import com.ambitionjun.movie.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    List<User> queryAllUser();
    //增加管理员
    void insertUser(User user);
    //管理员登录
    User queryUser(String username, String password);
    //删除管理员
    int deleteUser(int id);
    //修改管理员
    int updateUser(User user);
}
