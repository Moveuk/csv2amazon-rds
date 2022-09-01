package com.yournews.newsdatasetting.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class NewsNoNormalDto {

    private Long id;
    private LocalDateTime date;
    private String press;
    private String author;
    private String title;
    private String person;
    private String location;
    private String organization;
    private String keyword;
    private String contents;
    private String url;

}
