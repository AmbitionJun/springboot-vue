package com.ambitionjun.movie.service.Impl;

import com.ambitionjun.movie.mapper.FilmMapper;
import com.ambitionjun.movie.pojo.ArrangementFilm;
import com.ambitionjun.movie.pojo.Film;
import com.ambitionjun.movie.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    private FilmMapper filmMapper;
    public void insertFilm(Film film){

        filmMapper.insertFilm(film);
    }

    @Override
    public List<Film> queryFilm() {
        return filmMapper.queryFilm();
    }

    @Override
    public List<Film> queryFilmByName(String name) {
        return filmMapper.queryFilmByName(name);
    }

    @Override
    public List<Film> queryFilmByType(String type) {
        return filmMapper.queryFilmByType(type);
    }

    @Override
    public List<Film> queryFilmByRegion(String region) {
        return filmMapper.queryFilmByRegion(region);
    }

    @Override
    public List<Film> queryFilmByTypeRegion(String type, String region) {
        return filmMapper.queryFilmByTypeRegion(type, region);
    }

    @Override
    public List<ArrangementFilm> queryFilmById(int id) {
        return filmMapper.queryFilmById(id);
    }

    @Override
    public void deleteFilm(int id) {
        filmMapper.deleteFilm(id);
    }

    @Override
    public void updateFilm(Film film) {

        filmMapper.updateFilm(film);
    }
}
