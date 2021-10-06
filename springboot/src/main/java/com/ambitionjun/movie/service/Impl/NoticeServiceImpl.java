package com.ambitionjun.movie.service.Impl;

import com.ambitionjun.movie.mapper.NoticeMapper;
import com.ambitionjun.movie.pojo.Notice;
import com.ambitionjun.movie.service.NoticeService;
import com.ambitionjun.movie.support.DataTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;
    @Override
    public void insertNotice(Notice notice) {
        notice.setCreate_time(DataTimeUtil.getNowTimeString());
        noticeMapper.insertNotice(notice);
    }

    @Override
    public List<Notice> queryNotice() {
        return noticeMapper.queryNotice();
    }

    @Override
    public void updateNotice(Notice notice) {
        noticeMapper.updateNotice(notice);
    }

    @Override
    public void deleteNotice(int id) {
        noticeMapper.deleteNotice(id);
    }
}
