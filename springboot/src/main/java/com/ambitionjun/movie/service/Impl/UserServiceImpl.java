package com.ambitionjun.movie.service.Impl;

import com.ambitionjun.movie.mapper.UserMapper;
import com.ambitionjun.movie.pojo.Admin;
import com.ambitionjun.movie.pojo.User;
import com.ambitionjun.movie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> queryAllUser() {
        return userMapper.queryAllUser();
    }

    @Override
    public void insertUser(User user) {

        userMapper.insertUser(user);
    }

    @Override
    public User queryUser(String username, String password) {
        return userMapper.queryUser(username,password);
    }

    @Override
    public int deleteUser(int id) {
        return userMapper.deleteUser(id);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }
}
