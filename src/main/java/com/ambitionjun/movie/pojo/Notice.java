package com.ambitionjun.movie.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notice {
    private int id;
    private String name;
    private String introduction;
    private String type;
    private String type_d;
    private String actors;
    private String director;
    private String region;
    private String date;
    private String duration;
    private String cover;
    private String create_time;
}
