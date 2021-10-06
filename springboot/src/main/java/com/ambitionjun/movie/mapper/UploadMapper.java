package com.ambitionjun.movie.mapper;

import com.ambitionjun.movie.pojo.Upload;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UploadMapper {
    @Insert("insert into upload values(#{id},#{bytes})")
    int uploadImg(String id,byte[] bytes);

    @Select("select * from upload where id = #{id}")
    Upload get(String id);

    @Delete("delete from upload where id = #{id}")
    void delete(String id);
}
