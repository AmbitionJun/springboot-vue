package com.ambitionjun.movie.controller;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.ambitionjun.movie.config.AlipayConfig;
import com.ambitionjun.movie.pojo.Order;
import com.ambitionjun.movie.service.Impl.OrderServiceImpl;
import com.ambitionjun.movie.support.DataTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by  on 2019-9-09.
 */
@Controller
@RequestMapping(value = "/Alipay")
public class AlipayController {

    @Autowired
    private OrderServiceImpl orderService;

    /**
     * 购物车
     *
     * @return
     */
    @RequestMapping(value = "/Shopcart")
    public String Shopcart() {
        return "pay";
    }

    /**
     * 结算界面
     *
     * @return
     */
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/PayPage")
    public String payController(@RequestBody Order order) throws IOException {
        System.out.println(order);
        Order order1 = orderService.queryById(order.getId());
        System.out.println(order1);
        if (order1 != null) {
            String out_trade_no = order1.getId();
            //付款金额，必填  ShopName
            String total_amount = order1.getPrice();
            //订单名称，必填
            String subject = order1.getOrder_name();
            String uid = order1.getUid();
            String aid = order1.getAid();
            String seats = order1.getSeats();
            String create_time = DataTimeUtil.getNowTimeString();
            AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.APP_ID, AlipayConfig.APP_PRIVATE_KEY, "json", AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.sign_type);

            //设置请求参数
            AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
            alipayRequest.setReturnUrl(AlipayConfig.return_url);
            alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

            /*String out_trade_no = order.getId();
            //付款金额，必填  ShopName
            String total_amount = order.getPrice();
            //订单名称，必填
            String subject = order.getOrder_name();
            String uid = order.getUid();
            String aid = order.getAid();
            String seats = order.getSeats();
            String create_time = DataTimeUtil.getNowTimeString();*/
//        order.setId(order.getId());
//        order.setUid(order.getUid());
//        order.setAid(order.getAid());
//        order.setOrder_name(order.getOrder_name());
//        order.setSeats(order.getSeats());
//        order.setCreate_time(DataTimeUtil.getNowTimeString());
            //商品描述，可空
            //String body =request.getParameter("购物测试");
            // 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
            String timeout_express = "1c";
            alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                    + "\"total_amount\":\"" + total_amount + "\","
                    + "\"subject\":\"" + subject + "\","
                    + "\"uid\":\"" + uid + "\","
                    + "\"aid\":\"" + aid + "\","
                    + "\"seats\":\"" + seats + "\","
                    + "\"create_time\":\"" + create_time + "\","
                    + "\"timeout_express\":\"" + timeout_express + "\","
                    + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
            //请求
            String url = "";
            try {
                url = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单

               /* response.setContentType("text/html;charset=" + AlipayConfig.CHARSET);

                response.getWriter().write(url); // 直接将完整的表单html输出到页面
                response.getWriter().flush();
                response.getWriter().close();*/

            } catch (AlipayApiException e) {
                e.printStackTrace();
            }
            return url;
        } else {
            orderService.insertOrder(order);  //支付成功前存入id，uid，aid，seats，price，create_time
            //获得初始化的AlipayClient
            AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.APP_ID, AlipayConfig.APP_PRIVATE_KEY, "json", AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.sign_type);

            //设置请求参数
            AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
            alipayRequest.setReturnUrl(AlipayConfig.return_url);
            alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

            String out_trade_no = order.getId();
            //付款金额，必填  ShopName
            String total_amount = order.getPrice();
            //订单名称，必填
            String subject = order.getOrder_name();
            String uid = order.getUid();
            String aid = order.getAid();
            String seats = order.getSeats();
            String create_time = DataTimeUtil.getNowTimeString();
//        order.setId(order.getId());
//        order.setUid(order.getUid());
//        order.setAid(order.getAid());
//        order.setOrder_name(order.getOrder_name());
//        order.setSeats(order.getSeats());
//        order.setCreate_time(DataTimeUtil.getNowTimeString());
            //商品描述，可空
            //String body =request.getParameter("购物测试");
            // 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
            String timeout_express = "1c";
            alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                    + "\"total_amount\":\"" + total_amount + "\","
                    + "\"subject\":\"" + subject + "\","
                    + "\"uid\":\"" + uid + "\","
                    + "\"aid\":\"" + aid + "\","
                    + "\"seats\":\"" + seats + "\","
                    + "\"create_time\":\"" + create_time + "\","
                    + "\"timeout_express\":\"" + timeout_express + "\","
                    + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
            //请求
            String url = "";
            try {
                url = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单

               /* response.setContentType("text/html;charset=" + AlipayConfig.CHARSET);

                response.getWriter().write(url); // 直接将完整的表单html输出到页面
                response.getWriter().flush();
                response.getWriter().close();*/

            } catch (AlipayApiException e) {
                e.printStackTrace();
            }
            System.out.println(url);
            return url;
        }
    }

    /**
     * 同步跳转
     *
     * @param request
     * @throws Exception
     */
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/returnUrl")
    public ModelAndView returnUrl(HttpServletRequest request) throws Exception {

        //创建订单时间
        //order.setCreate_time(DataTimeUtil.getNowTimeString());
        ModelAndView mav = new ModelAndView();
        // 获取支付宝GET过来反馈信息（官方固定代码）
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);

        }
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, AlipayConfig.sign_type); // 调用SDK验证签名

        // 返回界面
        if (signVerified) {

            //System.out.println("前往支付成功页面");
            //System.out.println(out_trade_no + trade_no + total_amount);
            //mav.setViewName("Shopcart");
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
            System.out.println("支付宝交易号" + trade_no);
            if (orderService.queryById(out_trade_no) != null) {
                int status = 2;
                String pay_time = DataTimeUtil.getNowTimeString();
                orderService.updateOrder(out_trade_no, status, pay_time, trade_no);
                System.out.println("前往支付成功页面");
                mav.setViewName("redirect:http://101.200.193.211:8086/home");
            }

        } else {
            System.out.println("前往支付失败页面");
            mav.setViewName("failReturn");
        }
        return mav;

    }

    /**
     * 支付宝服务器异步通知
     *
     * @param request
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/notifyUrl")
    public void notifyUrl(HttpServletRequest request) throws Exception {
        // 获取支付宝GET过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, AlipayConfig.sign_type); // 调用SDK验证签名

        if (signVerified) { // 验证成功 更新订单信息
            System.out.println("异步通知成功");
            // 商户订单号
            String id = request.getParameter("out_trade_no");
            System.out.println(id);
            // 交易状态
            String trade_status = request.getParameter("trade_status");
            // 修改数据库
        } else {
            System.out.println("异步通知失败");
        }
    }

    @CrossOrigin
    @ResponseBody
    @GetMapping("/refund")
    public Map<String,Object> refundOrder(@RequestParam("id") String id,@RequestParam("price") String price) throws AlipayApiException {
        ModelAndView mav = new ModelAndView();
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.APP_ID, AlipayConfig.APP_PRIVATE_KEY, "json", AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.sign_type);
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        JSONObject bizContent = new JSONObject();
        Order order = orderService.queryById(id);
        System.out.println(order);
        bizContent.put("trade_no", order.getTrade_no());
        bizContent.put("refund_amount", price);
        bizContent.put("out_request_no", id);


        //// 返回参数选项，按需传入
        //JSONArray queryOptions = new JSONArray();
        //queryOptions.add("refund_detail_item_list");
        //bizContent.put("query_options", queryOptions);

        request.setBizContent(bizContent.toString());
        AlipayTradeRefundResponse response = alipayClient.execute(request);
        HashMap<String, Object> map = new HashMap<>();
        if (response.isSuccess()) {
            System.out.println("调用成功");
            orderService.deleteOrder(id); //删除订单
           map.put("success",true);

        } else {
            map.put("success",false);
            System.out.println("调用失败");

        }
       return map;
    }


}