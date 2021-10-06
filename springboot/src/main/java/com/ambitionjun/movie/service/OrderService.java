package com.ambitionjun.movie.service;


import com.ambitionjun.movie.pojo.Order;

import java.util.List;

public interface OrderService {
    List<Integer> querySeatsByAid(int aid);
    List<Order> queryOrder();

    void insertOrder(Order order);

    Order queryById(String id);
    List<Order> queryByUid(int uid);

    void updateOrder(String id,int status,String pay_time,String trade_no);
    void deleteOrder(String id);


}
