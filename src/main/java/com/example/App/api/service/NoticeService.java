package com.example.App.api.service;

import com.example.App.api.model.NoticeModel;

import java.util.List;
import java.util.Optional;

public interface NoticeService {

    List<NoticeModel> findPaginated(Integer pageNo, Integer pageSize, String sortBy);
    Optional<NoticeModel> getNotice(Integer id);
    NoticeModel save(NoticeModel notice);
    void deleteNoticeById(Integer id);
    Iterable<NoticeModel> getAllNotices();
}
