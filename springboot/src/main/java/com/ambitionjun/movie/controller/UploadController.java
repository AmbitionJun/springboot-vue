package com.ambitionjun.movie.controller;

import com.ambitionjun.movie.mapper.UploadMapper;
import com.ambitionjun.movie.pojo.Upload;
import com.ambitionjun.movie.service.Impl.UploadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;

@CrossOrigin
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private UploadServiceImpl uploadService;
    @Autowired
    private UploadMapper uploadMapper;


    @PostMapping("/insert")
    public String uploadImg(MultipartFile file){

        if (file.isEmpty()) {
            System.out.println("上传失败");
        }
        //System.out.println(uploadService.uploadImg(file));
        return "http://101.200.193.211:8088" + "/upload/check?id=" + uploadService.uploadImg(file);
    }

    @GetMapping("/check")
    public void get(@RequestParam("id") String id, HttpServletResponse response) throws Exception {
        if ("".equals(id)) {
            return;
        }
        Upload upload = uploadMapper.get(id);
        if (upload == null) {
            throw new Exception("图片不存在");
        }
        byte[] data = upload.getBytes();
        response.setContentType("image/jpeg");
        response.setCharacterEncoding("UTF-8");
        OutputStream outputStream = response.getOutputStream();
        InputStream in = new ByteArrayInputStream(data);
        int len;
        byte[] buf = new byte[1024];
        while ((len = in.read(buf, 0, 1024)) != -1) {
            outputStream.write(buf, 0, len);
        }
        outputStream.close();

    }
    @DeleteMapping("/delete")
    @CrossOrigin
    public void delete(@RequestParam("id") String id) {
        uploadService.delete(id);
    }

}
