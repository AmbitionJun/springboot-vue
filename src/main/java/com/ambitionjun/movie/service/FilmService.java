package com.ambitionjun.movie.service;

import com.ambitionjun.movie.pojo.ArrangementFilm;
import com.ambitionjun.movie.pojo.Film;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface FilmService {

    void insertFilm(Film film);
    List<Film> queryFilm();
    List<Film> queryFilmByName(String name);
    //根据电影类型
    List<Film> queryFilmByType(String type);
    //根据电影的地区
    List<Film> queryFilmByRegion(String region);
    List<Film> queryFilmByTypeRegion(String type,String region);
    List<ArrangementFilm> queryFilmById(int id);
    void deleteFilm(int id);
    void updateFilm(Film film);
}
