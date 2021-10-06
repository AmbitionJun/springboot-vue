package com.ambitionjun.movie.service.Impl;

import com.ambitionjun.movie.mapper.FilmMapper;
import com.ambitionjun.movie.mapper.OrderMapper;
import com.ambitionjun.movie.pojo.Film;
import com.ambitionjun.movie.pojo.Order;
import com.ambitionjun.movie.service.OrderService;
import com.ambitionjun.movie.support.DataTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private FilmMapper filmMapper;
    @Override
    public List<Integer> querySeatsByAid(@RequestParam("aid") int aid) {

      /*  //注入创建订单的时间
        Order order = new Order();
        order.setCreate_time(DataTimeUtil.getNowTimeString());*/

        List<Order> orders = orderMapper.querySeatsByAid(aid);
        //输出[Order(id=1, uid=1, aid=1, seats=20号9号, price=0.0, status=null, create_time=null, pay_time=null)]
        //System.out.println("order "+orders);
        //System.out.println(orderMapper.querySeatsByAid(aid));
        List<Integer> seats = new ArrayList<>();
        //遍历同一个场次的所有顾客的订单
        for (Order o : orders) {
            String[] split = o.getSeats().split("号");
           //String.split()方法返回一个数组
            for (String s : split) {
                //System.out.println(s);
                seats.add(Integer.parseInt(s));
                //Integer.parseInt() 将String字符类型数据转换为Integer整型数据
            }
        }
        //return seats;   /*{"code":200,"success":true,"msg":null,"data":[20,9]}*/
        return seats;
    }

    @Override
    public void insertOrder(Order order) {
        Film film = filmMapper.queryFilmByNameCover(order.getOrder_name());
        //获取电影链接存入订单表
        order.setCover(film.getCover());
        order.setStatus(0);
        order.setCreate_time(DataTimeUtil.getNowTimeString());
        orderMapper.insertOrder(order);
    }

    @Override
    public Order queryById(String id) {
        Order order = orderMapper.queryById(id);
        return order;
    }

    @Override
    public void updateOrder(String id,int status,String pay_time,String trade_no) {

        orderMapper.updateOrder(id,status,pay_time,trade_no);
    }

    @Override
    public List<Order> queryByUid(int uid) {
        return orderMapper.queryByUid(uid);
    }

    @Override
    public void deleteOrder(String id) {
        orderMapper.deleteOrder(id);
    }

    @Override
    public List<Order> queryOrder() {
        return orderMapper.queryOrder();
    }
}
