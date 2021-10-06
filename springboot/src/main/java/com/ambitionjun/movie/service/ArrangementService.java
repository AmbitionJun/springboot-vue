package com.ambitionjun.movie.service;

import com.ambitionjun.movie.pojo.Arrangement;
import com.ambitionjun.movie.pojo.ArrangementFilm;

import java.util.List;


public interface ArrangementService {

    void insertArrangement(Arrangement arrangement);
    List<Arrangement> queryArrangement();
    //查询电影的所有场次
    List<ArrangementFilm> findByFilmFid(int fid);

    ArrangementFilm findByFilmId(int id);

    void deleteArrangement(int id);
    void updateArrangement(Arrangement arrangement);
}
