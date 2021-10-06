package com.ambitionjun.movie.service.Impl;

import com.ambitionjun.movie.mapper.ArrangementMapper;
import com.ambitionjun.movie.mapper.FilmMapper;
import com.ambitionjun.movie.pojo.Arrangement;
import com.ambitionjun.movie.pojo.ArrangementFilm;
import com.ambitionjun.movie.service.ArrangementService;
import com.ambitionjun.movie.support.DataTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArrangementServiceImpl implements ArrangementService {

    @Autowired
    private ArrangementMapper arrangementMapper;


    @Override
    public void insertArrangement(Arrangement arrangement) {

        arrangement.setCreate_time(DataTimeUtil.getNowTimeString());
        arrangementMapper.insertArrangement(arrangement);
    }

    @Override
    public List<Arrangement> queryArrangement() {

        return arrangementMapper.queryArrangement();
    }

    @Override
    public List<ArrangementFilm> findByFilmFid(int fid) {

        List<ArrangementFilm> arrangementFilms = arrangementMapper.findByFilmFid(fid);
        //System.out.println(arrangementFilms);

        return arrangementFilms;
    }

    @Override
    public ArrangementFilm findByFilmId(int id) {
        return arrangementMapper.findByFilmId(id);
    }

    @Override
    public void deleteArrangement(int id) {

        arrangementMapper.deleteArrangement(id);
    }

    @Override
    public void updateArrangement(Arrangement arrangement) {
        arrangementMapper.updateArrangement(arrangement);
    }
}
