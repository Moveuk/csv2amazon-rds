package com.yournews.newsdatasetting.repository;

import com.yournews.newsdatasetting.model.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {
    List<Keyword> findByKeywordContaining(String keyword);
}
