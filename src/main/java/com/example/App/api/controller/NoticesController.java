package com.example.App.api.controller;

import com.example.App.api.service.NoticeService;
import com.example.App.api.model.NoticeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class NoticesController {

    @Autowired
    private NoticeService noticeService;


    @GetMapping(path = "/api/notice/{id}")
    public ResponseEntity read(@PathVariable("id")  Integer id){
        Date date = new Date();
        return noticeService.getNotice(id)
                .map(record -> {
                    record.setPublicationDate(date);
                    NoticeModel updated = noticeService.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/api/notice/save")
    public NoticeModel save(@RequestBody NoticeModel notice) {
        return noticeService.save(notice);
    }


    @GetMapping(path = "/api/notices")
    public List<NoticeModel> findAll() {
        Iterable<NoticeModel> it = noticeService.getAllNotices();
        ArrayList notices = new ArrayList<NoticeModel>();
        it.forEach(e -> notices.add(e));

        return notices;
    }


    @PutMapping(path = "/api/notice/update/{id}")
    public ResponseEntity  update(@PathVariable("id") Integer id, @RequestBody NoticeModel notice) {
        return noticeService.getNotice(id)
                .map(record -> {
                    record.setTitle(notice.getTitle());
                    record.setDescription(notice.getDescription());
                    record.setPublicationDate(notice.getPublicationDate());
                    NoticeModel updated = noticeService.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/api/notice/delete/{id}")
    public void delete(@PathVariable("id") Integer id) {
        noticeService.deleteNoticeById(id);
    }


    @GetMapping(path = "/api/noticesTest/{pageNo}")
    public ResponseEntity<List<NoticeModel>> getAllNotices(
            @PathVariable("pageNo") Integer pageNo,
            @RequestParam(defaultValue = "1") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {
        sortBy = "id";

        List<NoticeModel> list = noticeService.findPaginated(pageNo, pageSize, sortBy);

        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }
}
