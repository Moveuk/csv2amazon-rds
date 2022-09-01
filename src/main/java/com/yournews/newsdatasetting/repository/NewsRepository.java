package com.yournews.newsdatasetting.repository;

import com.yournews.newsdatasetting.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News,Long> {

    List<News> findByIdIn(List<Long> byKeywordContaining);
}
