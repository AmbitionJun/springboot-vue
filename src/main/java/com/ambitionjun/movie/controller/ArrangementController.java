package com.ambitionjun.movie.controller;

import com.ambitionjun.movie.mapper.FilmMapper;
import com.ambitionjun.movie.pojo.Arrangement;
import com.ambitionjun.movie.pojo.ArrangementFilm;
import com.ambitionjun.movie.service.ArrangementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/arrangement")
public class ArrangementController {

    @Autowired
    private ArrangementService arrangementService;
    @Autowired
    private FilmMapper filmMapper;

    @CrossOrigin
    @ResponseBody
    @PostMapping("/add")
    public Map<String,Boolean> insertArrangement(@RequestBody Arrangement arrangement){
        arrangementService.insertArrangement(arrangement);
        HashMap<String, Boolean> map = new HashMap<>();
        map.put("success",true);
        return map;
    }

    @CrossOrigin
    @ResponseBody
    @GetMapping("/select")
    public List<Arrangement> queryArrangement(){
        List<Arrangement> arrangements = arrangementService.queryArrangement();
        return arrangements;
    }
    @CrossOrigin
    @ResponseBody
    @GetMapping( "/selectByFid")
    public Map<String,Object> findByFilmFid(@RequestParam("fid") int fid){

        List<ArrangementFilm> arrangementFilmList = arrangementService.findByFilmFid(fid);
        HashMap<String, Object> map = new HashMap<>();
        if(arrangementFilmList.isEmpty()){
           map.put("flag",false);
           map.put("arrangementFilmList",filmMapper.queryFilmById(fid));
           return map;
        }else{
            map.put("flag",true);
            map.put("arrangementFilmList",arrangementFilmList);
            return map;
        }

    }

    @CrossOrigin
    @ResponseBody
    @GetMapping( "/selectById")
    public ArrangementFilm findByFilmId(@RequestParam("id") int id){

        ArrangementFilm arrangementServiceByFilmId = arrangementService.findByFilmId(id);
        return arrangementServiceByFilmId;
    }

    @CrossOrigin
    @ResponseBody
    @GetMapping("/delete")
    public Map<String,Object> deleteArrangement(@RequestParam("id") int id){
        arrangementService.deleteArrangement(id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("success",true);
        return map;
    }

    @CrossOrigin
    @ResponseBody
    @PostMapping("/update")
    public Map<String,Object> updateArrangement(@RequestBody Arrangement arrangement){
        arrangementService.updateArrangement(arrangement);
        HashMap<String, Object> map = new HashMap<>();
        map.put("success",true);
        return map;
    }
}
