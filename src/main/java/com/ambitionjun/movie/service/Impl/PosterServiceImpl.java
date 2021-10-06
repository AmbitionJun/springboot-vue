package com.ambitionjun.movie.service.Impl;

import com.ambitionjun.movie.mapper.PosterMapper;
import com.ambitionjun.movie.pojo.Film;
import com.ambitionjun.movie.pojo.Poster;
import com.ambitionjun.movie.service.PosterService;
import com.ambitionjun.movie.support.DataTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PosterServiceImpl implements PosterService {

    @Autowired
    private PosterMapper posterMapper;
    @Override
    public void insertPoster(Poster poster) {
        poster.setCreate_time(DataTimeUtil.getNowTimeString());
       posterMapper.insertPoster(poster);
    }

    @Override
    public List<Poster> queryPoster() {
        return posterMapper.queryPoster();
    }

    @Override
    public void deletePoster(int id) {

        posterMapper.deletePoster(id);
    }
}
