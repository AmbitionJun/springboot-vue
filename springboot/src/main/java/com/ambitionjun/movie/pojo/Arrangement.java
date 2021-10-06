package com.ambitionjun.movie.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Arrangement {

    private int id;

    //电影id
    private int fid;

    //电影名
    private String name;

    //开放多少座位
    private Integer seat_number;

    //价格
    private double price;

    //放映类型 2D 3D
    private String type;

    //电影开始时间 2021-01-01
    private String date;

    //电影开始时间 19:30:00
    private String start_time;

    //结束时间
    private String end_time;

    //创建时间
    private String create_time;

}
