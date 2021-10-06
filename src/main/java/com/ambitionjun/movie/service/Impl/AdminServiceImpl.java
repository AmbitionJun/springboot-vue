package com.ambitionjun.movie.service.Impl;

import com.ambitionjun.movie.mapper.AdminMapper;
import com.ambitionjun.movie.pojo.Admin;
import com.ambitionjun.movie.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    public Admin getById(Long admin_id) {
        return adminMapper.getById(admin_id);
    }

    public List<Admin> queryAllAdmin() {
        return adminMapper.queryAllAdmin();
    }

    //增加管理员
    public void insertAdmin(Admin admin) {
        adminMapper.insertAdmin(admin);
    }

    //管理员登录
    public Admin queryAdmin(String admin_name, String admin_password) {
        return adminMapper.queryAdmin(admin_name, admin_password);
    }

    //删除管理员
    public int deleteAdmin(Long admin_id) {
        return adminMapper.deleteAdmin(admin_id);
    }

    //修改管理员
    public int updateAdmin(Admin admin) {
        return adminMapper.updateAdmin(admin);
    }
}
