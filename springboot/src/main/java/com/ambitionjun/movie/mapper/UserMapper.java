package com.ambitionjun.movie.mapper;

import com.ambitionjun.movie.pojo.Admin;
import com.ambitionjun.movie.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface UserMapper {

    @Select("select * from user")
    List<User> queryAllUser();
    //增加管理员
    @Insert("insert into user values (#{id},#{username},#{password})")
    void insertUser(User user);

    //管理员登录
    @Select("select * from user where username=#{username} and password=#{password}")
    User queryUser(String username, String password);
    //删除管理员
    @Delete("delete from user where id = #{id}")
    int deleteUser(int id);
    //修改管理员
    @Update("update user set username=#{username}," +
            "password=#{password} where id=#{id}")
    int updateUser(User user);
}
