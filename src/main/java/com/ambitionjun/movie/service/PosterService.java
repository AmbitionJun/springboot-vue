package com.ambitionjun.movie.service;

import com.ambitionjun.movie.pojo.Film;
import com.ambitionjun.movie.pojo.Poster;

import java.util.List;

public interface PosterService {

    void insertPoster(Poster poster);
    List<Poster> queryPoster();
    void deletePoster(int id);
}
