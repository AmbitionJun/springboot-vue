package com.ambitionjun.movie.controller;

import com.ambitionjun.movie.pojo.ArrangementFilm;
import com.ambitionjun.movie.pojo.Film;
import com.ambitionjun.movie.service.Impl.FilmServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/film")
public class FilmController {

    @Autowired
    private FilmServiceImpl filmService;

    //插入电影
    @CrossOrigin
    @ResponseBody
    @PostMapping("/insert")
    public Map<String,Object> insertFilm(@RequestBody  Film film){
        filmService.insertFilm(film);
        HashMap<String, Object> map = new HashMap<>();
        map.put("success",true);
        return map;
    }
    //查询所有电影
    @CrossOrigin
    @ResponseBody
    @GetMapping("/select")
    public List<Film> queryFilm(){
        List<Film> films = filmService.queryFilm();
        return films;
    }
    //查询所有电影
    @CrossOrigin
    @ResponseBody
    @GetMapping("/selectById")
    public List<ArrangementFilm> queryFilmById(@RequestParam("id") int id){
        List<ArrangementFilm> arrangementFilms = filmService.queryFilmById(id);
        return arrangementFilms;
    }
    //根据电影名称查询
    @CrossOrigin
    @ResponseBody
    @GetMapping("/selectByFilmName")
    public List<Film> queryFilmByName(@RequestParam("name") String name) {
        List<Film> films = filmService.queryFilmByName(name);
        return films;
    }
    //根据电影类型查询
    @CrossOrigin
    @ResponseBody
    @GetMapping("/selectByFilmType")
    public List<Film> queryFilmByType(@RequestParam("type") String type) {
        List<Film> films = filmService.queryFilmByType(type);
        return films;
    }
    //根据电影名称查询
    @CrossOrigin
    @ResponseBody
    @GetMapping("/selectByFilmRegion")
    public List<Film> queryFilmByRegion(@RequestParam("region") String region) {
        List<Film> films = filmService.queryFilmByRegion(region);
        return films;
    }
    //根据电影名称查询
    @CrossOrigin
    @ResponseBody
    @GetMapping("/selectFilmByTypeRegion")
    public List<Film> queryFilmByTypeRegion(@RequestParam("type") String type, @RequestParam("region") String region) {


       // System.out.println(type + region);
        if(type.equals("全部") && region.equals("全部")){
            System.out.println("都是全部");
            List<Film> films = filmService.queryFilm();
            return films;
        }else if(type.equals("全部")){
            List<Film> films = filmService.queryFilmByRegion(region);
            System.out.println("地区的所有类型");
            return films;

        }else if(region.equals("全部")){

            List<Film> films = filmService.queryFilmByType(type);
            System.out.println("类型的所有地区");
            return films;
        }else{
            System.out.println("都有");
            List<Film> films = filmService.queryFilmByTypeRegion(type, region);
            return films;
        }
        //List<Film> films = filmService.queryFilmByRegion(region);
       // return films;
    }

    @CrossOrigin
    @ResponseBody
    @GetMapping("/delete")
    public Map<String,Object> deleteFilm(@RequestParam("id") int id){
        filmService.deleteFilm(id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("success",true);
        return map;
    }

    @CrossOrigin
    @ResponseBody
    @PostMapping("/update")
    public Map<String,Object> updateFilm(@RequestBody Film film){
        filmService.updateFilm(film);
        HashMap<String, Object> map = new HashMap<>();
        map.put("success",true);
        return map;
    }
}
