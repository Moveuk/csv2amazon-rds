package com.yournews.newsdatasetting;

import com.yournews.newsdatasetting.dto.NewsDto;
import com.yournews.newsdatasetting.dto.NewsNoNormalDto;
import com.yournews.newsdatasetting.repository.KeywordRepository;
import com.yournews.newsdatasetting.repository.NewsNoNormalRepository;
import com.yournews.newsdatasetting.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SearchController {

    private final NewsRepository newsRepository;
    private final KeywordRepository keywordRepository;
    private final NewsNoNormalRepository newsNoNormalRepository;

    @GetMapping(path = "/api/search")
    public List<NewsDto> searchNewsByKeyword(@RequestParam String keyword) {
        long timestampBefore = System.currentTimeMillis();
//        List<News> byKeywordContaining = keywordRepository.findByKeywordContaining(keyword).stream().map(Keyword::getNews).toList();
        List<Long> byKeywordContaining = keywordRepository.findByKeywordContaining(keyword).stream().map(keyword1 -> keyword1.getNews().getId()).toList();
        List<NewsDto> collect = newsRepository.findByIdIn(byKeywordContaining).stream().map(NewsDto::new).collect(Collectors.toList());
        long timestampAfter = System.currentTimeMillis();

        log.info("200건 중 키워드 검색 : " + (timestampAfter - timestampBefore) + "ms");
        return collect;
    }

    @GetMapping(path = "/api/searchv2")
    public List<NewsNoNormalDto> searchNewsV2ByKeyword(@RequestParam String keyword) {
        long timestampBefore = System.currentTimeMillis();
        List<NewsNoNormalDto> collect = newsNoNormalRepository.findByKeywordContaining(keyword);
        long timestampAfter = System.currentTimeMillis();

        log.info("5000건 중 키워드 검색 : " + (timestampAfter - timestampBefore) + "ms");
        return collect;
    }

    @GetMapping(path = "/api/searchv3")
    public List<NewsDto> searchNewsV3ByKeyword(@RequestParam String keyword) {
        long timestampBefore = System.currentTimeMillis();
//        List<News> byKeywordContaining = keywordRepository.findByKeywordContaining(keyword).stream().map(Keyword::getNews).toList();
        List<Long> byKeywordContaining = keywordRepository.findByKeywordContaining(keyword).stream().map(keyword1 -> keyword1.getNews().getId()).toList();
        List<NewsDto> collect = newsRepository.findByIdIn(byKeywordContaining).stream().map(NewsDto::new).collect(Collectors.toList());
        long timestampAfter = System.currentTimeMillis();

        log.info("200건 중 키워드 검색 : " + (timestampAfter - timestampBefore) + "ms");
        return collect;
    }
}
