package com.ambitionjun.movie.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Film {

    private int id;
    private String name;
    private String introduction;
    private String type;
    private String actors;
    private String director;
    private String region;
    private String duration;
    private String cover;
}
