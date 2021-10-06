package com.ambitionjun.movie.mapper;

import com.ambitionjun.movie.pojo.Notice;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoticeMapper {

    @Insert("insert into notice values (#{id},#{name},#{introduction},#{type},#{type_d},#{actors},#{director},#{region},#{date},#{duration},#{cover},#{create_time})")
    void insertNotice(Notice notice);
    @Select("select * from notice")
    List<Notice> queryNotice();

    @Update("update notice set id=#{id},name=#{name},introduction=#{introduction}," +
            "type=#{type},type_d=#{type_d},actors=#{actors}," +
            "director=#{director},region=#{region},date=#{date},duration=#{duration}," +
            "cover=#{cover},create_time=#{create_time} where id =#{id}")
    void updateNotice(Notice notice);

    @Delete("delete from notice where id =#{id}")
    void deleteNotice(int id);

}
