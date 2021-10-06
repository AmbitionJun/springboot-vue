package com.ambitionjun.movie.service;

import com.ambitionjun.movie.pojo.Notice;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface NoticeService {
    void insertNotice(Notice notice);
    List<Notice> queryNotice();
    void updateNotice(Notice notice);
    void deleteNotice(int id);
}
