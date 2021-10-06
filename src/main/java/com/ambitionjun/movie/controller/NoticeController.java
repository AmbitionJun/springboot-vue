package com.ambitionjun.movie.controller;


import com.ambitionjun.movie.pojo.Notice;
import com.ambitionjun.movie.service.Impl.NoticeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    private NoticeServiceImpl noticeService;
    //插入公告
    @CrossOrigin
    @ResponseBody
    @PostMapping("/insert")
    public Map<String,Object> insertNotice(@RequestBody Notice notice){
        noticeService.insertNotice(notice);
        HashMap<String, Object> map = new HashMap<>();
        map.put("success",true);
        return map;
    }
    //查询所有公告
    @CrossOrigin
    @ResponseBody
    @GetMapping("/select")
    public List<Notice> queryNotice(){
        List<Notice> notices = noticeService.queryNotice();
        return notices;
    }
    //查询所有公告
    @CrossOrigin
    @ResponseBody
    @PostMapping("/update")
    public Map<String,Object> updateNotice(@RequestBody Notice notice){
        noticeService.updateNotice(notice);
        HashMap<String, Object> map = new HashMap<>();
        map.put("success",true);
        return map;
    }
    //查询所有公告
    @CrossOrigin
    @ResponseBody
    @GetMapping("/delete")
    public Map<String,Object> deleteNotice(@RequestParam("id") int id){
        noticeService.deleteNotice(id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("success",true);
        return map;
    }
}
