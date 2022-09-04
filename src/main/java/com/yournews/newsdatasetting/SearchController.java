package com.yournews.newsdatasetting;

import com.yournews.newsdatasetting.dto.NewsNoNormalDto;
import com.yournews.newsdatasetting.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SearchController {

    private final NewsRepository newsRepository;

    @GetMapping(path = "/api/searchv2")
    public List<NewsNoNormalDto> searchNewsV2ByKeyword(@RequestParam String keyword) {
        long timestampBefore = System.currentTimeMillis();
        List<NewsNoNormalDto> collect = newsRepository.findByKeywordContaining(keyword);
        long timestampAfter = System.currentTimeMillis();

        log.info("102286건 중 키워드 검색 : " + (timestampAfter - timestampBefore) + "ms");
        return collect;
    }

}
