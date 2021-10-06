package com.ambitionjun.movie.controller;

import com.ambitionjun.movie.pojo.Film;
import com.ambitionjun.movie.pojo.Poster;
import com.ambitionjun.movie.service.Impl.PosterServiceImpl;
import com.ambitionjun.movie.service.PosterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/poster")
public class PosterController {

    @Autowired
    private PosterServiceImpl posterService;

    @CrossOrigin
    @ResponseBody
    @PostMapping("/insert")
    public Map<String,Object> insertPoster(@RequestBody Poster poster){
        posterService.insertPoster(poster);
        HashMap<String, Object> map = new HashMap<>();
        map.put("success",true);
        return map;
    }

    @CrossOrigin
    @ResponseBody
    @GetMapping("/select")
    public List<Poster> queryAllPoster(){
        List<Poster> poster = posterService.queryPoster();
        return poster;
    }

    @CrossOrigin
    @ResponseBody
    @GetMapping("/delete")
    public Map<String,Object> deletePoster(@RequestParam("id") int id){
        posterService.deletePoster(id); HashMap<String, Object> map = new HashMap<>();
        map.put("success",true);
        return map;
    }

}
