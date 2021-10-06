package com.ambitionjun.movie.service;


import com.ambitionjun.movie.pojo.Upload;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    String uploadImg(MultipartFile file);

    void delete(String id);
}
