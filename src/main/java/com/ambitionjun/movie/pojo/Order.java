package com.ambitionjun.movie.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String id;

    //用户id
    private String order_name;

    private String uid;
    //场次id
    private String aid;
    //座位
    private String seats;
    //金额
    private String price;
    //状态
    private int status;
    //创建时间
    private String create_time;
    //更新时间
    private String pay_time;
    private String cover;
    private String trade_no;
//    private String timeout_express="10m";
//    private String product_code="FAST_INSTANT_TRADE_PAY";
}
