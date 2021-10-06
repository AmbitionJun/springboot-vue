package com.ambitionjun.movie.mapper;

import com.ambitionjun.movie.pojo.Admin;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdminMapper {

    @Select("select * from admin where admin_id = #{admin_id}")
    Admin getById(Long admin_id);
    @Select("select * from admin")
    List<Admin> queryAllAdmin();
    //增加管理员
   @Insert("insert into admin values (#{admin_id},#{admin_name},#{admin_password})")
    void insertAdmin(Admin admin);

    //管理员登录
    @Select("select * from admin where admin_name=#{name} and admin_password=#{pwd}")
    Admin queryAdmin(@Param("name") String admin_name, @Param("pwd") String admin_password);
    //删除管理员
    @Delete("delete from admin where admin_id = #{id}")
    int deleteAdmin(Long id);
    //修改管理员
    @Update("update admin set admin_name=#{admin_name}," +
            "admin_password=#{admin_password} where admin_id=#{admin_id}")
    int updateAdmin(Admin admin);

}
