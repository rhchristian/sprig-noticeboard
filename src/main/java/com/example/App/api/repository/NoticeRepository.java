package com.example.App.api.repository;

import com.example.App.api.model.NoticeModel;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NoticeRepository extends PagingAndSortingRepository<NoticeModel, Integer> {

}
