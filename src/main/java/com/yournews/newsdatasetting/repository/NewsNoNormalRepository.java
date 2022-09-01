package com.yournews.newsdatasetting.repository;

import com.yournews.newsdatasetting.dto.NewsNoNormalDto;
import com.yournews.newsdatasetting.modelv2.NewsNoNormal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsNoNormalRepository extends JpaRepository<NewsNoNormal, Long> {

    List<NewsNoNormalDto> findByKeywordContaining(String keyword);
}
