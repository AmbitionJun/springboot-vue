package com.ambitionjun.movie.mapper;

import com.ambitionjun.movie.pojo.Arrangement;
import com.ambitionjun.movie.pojo.ArrangementFilm;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArrangementMapper {

    @Insert("insert into arrangement values(#{id},#{fid}," +
            "#{name},#{seat_number},#{price},#{type},#{date},#{start_time},#{end_time},#{create_time})")
    void insertArrangement(Arrangement arrangement);

    @Select("select * from arrangement")
    List<Arrangement> queryArrangement();

    //查询电影的所有场次
    @Select("select a.*,f.introduction,f.actors,f.director,f.region,f.duration," +
            "f.cover from arrangement a INNER JOIN film f on a.fid = f.id where fid = #{fid}")
    List<ArrangementFilm> findByFilmFid(int fid);

    @Select("select a.*,f.introduction,f.actors,f.director,f.region,f.duration," +
            "f.cover from arrangement a INNER JOIN film f on a.fid = f.id where a.id= #{a.id}")
    ArrangementFilm findByFilmId(int id);


    @Delete("delete from arrangement where id = #{id}")
    void deleteArrangement(int id);

    @Update("update arrangement set id = #{id},fid=#{fid},name=#{name},seat_number=#{seat_number}," +
            "price=#{price},type=#{type},date=#{date},start_time=#{start_time},end_time=#{end_time}," +
            "create_time=#{create_time} where id =#{id}")
    void updateArrangement(Arrangement arrangement);


}
