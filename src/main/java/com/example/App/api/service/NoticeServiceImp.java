package com.example.App.api.service;

import com.example.App.api.model.NoticeModel;
import com.example.App.api.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NoticeServiceImp implements NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;


    public Optional<NoticeModel> getNotice(Integer id){

        return noticeRepository.findById(id);
    }

    public NoticeModel save(NoticeModel notice) {
        return noticeRepository.save(notice);
    }

    public void deleteNoticeById(Integer id) {
        this.noticeRepository.deleteById(id);
    }

    public Iterable<NoticeModel> getAllNotices() {
        return noticeRepository.findAll();
    }


    public  List<NoticeModel>  findPaginated(Integer pageNo, Integer pageSize, String sortBy) {

        Pageable paging = PageRequest.of(pageNo, pageSize);

        Page<NoticeModel> pagedResult = noticeRepository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }
}
