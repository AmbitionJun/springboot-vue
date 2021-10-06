package com.ambitionjun.movie.mapper;

import com.ambitionjun.movie.pojo.Film;
import com.ambitionjun.movie.pojo.Poster;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PosterMapper {
    @Insert("insert into poster values(#{id},#{fid},#{title},#{url},#{create_time})")
    int insertPoster(Poster poster);

    @Select("select * from poster")
    List<Poster> queryPoster();

    @Delete("delete from poster where id = #{id}")
    void deletePoster(int id);
}
