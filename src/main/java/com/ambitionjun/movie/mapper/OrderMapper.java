package com.ambitionjun.movie.mapper;

import com.ambitionjun.movie.pojo.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {


    @Select("select * from t_order where aid = #{aid}")
    List<Order> querySeatsByAid(int aid);
    @Select("select * from t_order")
    List<Order> queryOrder();

    //根据用户id插入订单表
    @Insert("insert into t_order values(#{id},#{order_name},#{uid},#{aid},#{seats},#{price},#{status},#{create_time},#{pay_time},#{cover},#{trade_no})")
    void insertOrder(Order order);

    @Select("select * from t_order where id=#{id}")
    Order queryById(String id);

    //根据uid查询用户订单
    @Select("select * from t_order where uid=#{uid}")
    List<Order> queryByUid(int uid);

    //支付成功更新表
    @Update("update t_order set id=#{id},status=#{status},pay_time=#{pay_time},trade_no=#{trade_no} where id=#{id}")
    void updateOrder(String id,int status,String pay_time,String trade_no);

    @Delete("delete from t_order where id =#{id}")
    void deleteOrder(String id);

}
