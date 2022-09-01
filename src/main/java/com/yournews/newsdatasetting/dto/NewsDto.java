package com.yournews.newsdatasetting.dto;

import com.yournews.newsdatasetting.model.News;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class NewsDto {

    private final Long id;
    private final LocalDateTime date;
    private final String press;
    private final String author;
    private final String title;
    private final String contents;
    private final String url;

    @Builder
    public NewsDto(News news) {
        this.id = news.getId();
        this.date = news.getDate();
        this.press = news.getPress();
        this.author = news.getAuthor();
        this.title = news.getTitle();
        this.contents = news.getContents();
        this.url = news.getUrl();
    }
}
