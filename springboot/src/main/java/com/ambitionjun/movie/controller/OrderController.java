package com.ambitionjun.movie.controller;

import com.ambitionjun.movie.pojo.Order;
import com.ambitionjun.movie.service.Impl.OrderServiceImpl;
import com.ambitionjun.movie.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderServiceImpl orderService;

    @CrossOrigin
    @ResponseBody
    @GetMapping("/select")
    public List<Order> queryOrder(){
        List<Order> orders = orderService.queryOrder();
        return orders;
    }
    @CrossOrigin
    @ResponseBody
    @GetMapping("/selectByAid")
    public List<Integer> querySeatsByAid(@RequestParam("aid") int aid){
        return orderService.querySeatsByAid(aid);
    }

//    @CrossOrigin
//    @ResponseBody
//    @PostMapping("/insert")
//    public void insertOrder(@RequestBody Order order){
//        orderService.insertOrder(order);
//    }

    @CrossOrigin
    @ResponseBody
    @GetMapping("/selectByUid")
    public List<Order> queryByUid(@RequestParam("uid") int uid){

       return orderService.queryByUid(uid);
    }

    @CrossOrigin
    @ResponseBody
    @GetMapping("/selectById")
    public Order queryById(@RequestParam("id") String id){

        Order order = orderService.queryById(id);

        return order;
    }
    @CrossOrigin
    @ResponseBody
    @GetMapping("/deleteOrder")
    public Map<String, Object> deleteOrder(@RequestParam("id") String id){
   orderService.deleteOrder(id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("success",true);
        return map;


    }

}
