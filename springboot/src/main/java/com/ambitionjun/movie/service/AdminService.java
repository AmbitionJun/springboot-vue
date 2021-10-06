package com.ambitionjun.movie.service;

import com.ambitionjun.movie.pojo.Admin;

import java.util.List;

public interface AdminService {
    Admin getById(Long admin_id);

    List<Admin> queryAllAdmin();

    //增加管理员
    void insertAdmin(Admin admin);

    //管理员登录
    Admin queryAdmin(String admin_name, String admin_password);


    //删除管理员
    int deleteAdmin(Long admin_id);

    //修改管理员
    int updateAdmin(Admin admin);
}
