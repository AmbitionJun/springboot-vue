package com.ambitionjun.movie.service.Impl;

import com.ambitionjun.movie.mapper.UploadMapper;
import com.ambitionjun.movie.pojo.Upload;
import com.ambitionjun.movie.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    private UploadMapper uploadMapper;

    @Override
   public String uploadImg(MultipartFile file){
       try {
           InputStream is = file.getInputStream(); //获取文件流
           byte[] data = new byte[(int) file.getSize()]; //获取文件大小
           int i = is.read(data);  //读取文件
           System.out.println("上传的文件大小=" + i);
           //不存在则存入数据库
           String id = UUID.randomUUID().toString();
           uploadMapper.uploadImg(id,data);
           return id; //返回图片id
       } catch (IOException e) {
           e.printStackTrace();
       }
       return null;
   }

    @Override
    public void delete(String id) {
        uploadMapper.delete(id);
    }
}
