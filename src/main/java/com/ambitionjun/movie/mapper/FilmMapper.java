package com.ambitionjun.movie.mapper;

import com.ambitionjun.movie.pojo.Arrangement;
import com.ambitionjun.movie.pojo.ArrangementFilm;
import com.ambitionjun.movie.pojo.Film;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FilmMapper {
    //增加电影
    @Insert("insert into film values(#{id},#{name},#{introduction},#{type},#{actors},#{director},#{region},#{duration},#{cover})")
    void insertFilm(Film film);
    //搜索电影
    @Select("select * from film")
    List<Film> queryFilm();

    //根据电影名称
    @Select("select * from film where name like concat('%',#{name},'%')")
    List<Film> queryFilmByName(String name);
    //根据电影名称，目的是获取电影封面链接
    @Select("select * from film where name= #{name}")
    Film queryFilmByNameCover(String name);
    //根据电影类型
    @Select("select * from film where type = #{type}")
    List<Film> queryFilmByType(String type);
    //根据电影的地区
    @Select("select * from film where region = #{region}")
    List<Film> queryFilmByRegion(String region);

    @Select("select * from film where id = #{id} ")
    List<ArrangementFilm> queryFilmById(int id);

    @Select("select * from film where type= #{type}  and region = #{region}")
    List<Film> queryFilmByTypeRegion(String type,String region);
    @Delete("delete from film where id = #{id}")
    void deleteFilm(int id);

    @Update("update film set id=#{id},name=#{name},introduction=#{introduction}," +
            "type=#{type},actors=#{actors},director=#{director},region=#{region}," +
            "duration=#{duration},cover=#{cover} where id = #{id}")
    void updateFilm(Film film);
}
