package com.example.App.api.controller;

import com.example.App.api.model.NoticeModel;
import com.example.App.api.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

@RestController
public class NoticesController {

    @Autowired
    private NoticeRepository repository;


    @GetMapping(path = "/api/notice/{id}")
    public ResponseEntity read(@PathVariable("id")  Integer id){
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/api/notice/save")
    public NoticeModel save(@RequestBody NoticeModel notice) {
        return repository.save(notice);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "/api/notices")
    public List<NoticeModel> findAll() {
        Iterable<NoticeModel> it = repository.findAll();
        ArrayList notices = new ArrayList<NoticeModel>();
        it.forEach(e -> notices.add(e));

        return notices;
    }

    @PutMapping(path = "/api/notice/update/{id}")
    public ResponseEntity  update(@PathVariable("id") Integer id, @RequestBody NoticeModel notice) {
        return repository.findById(id)
                .map(record -> {
                    record.setTitle(notice.getTitle());
                    record.setDescription(notice.getDescription());
                    record.setPublicationDate(notice.getPublicationDate());
                    NoticeModel updated = repository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/api/notice/delete/{id}")
    public void delete(@PathVariable("id") Integer id) {
        repository.deleteById(id);
    }

    @GetMapping(path = "/api/noticesp")
    public Page<NoticeModel> getAllEmployees()
    {
        int page = 0;
        int size = 1;
        PageRequest pageRequest = PageRequest.of(
                page,
                size,
                Sort.Direction.ASC,
                "name");

        Iterable<NoticeModel> it = repository.findAll();
        ArrayList notices = new ArrayList<NoticeModel>();
        it.forEach(e -> notices.add(e));

        return new PageImpl<NoticeModel>(
                notices,
                pageRequest, size);
    }
}
