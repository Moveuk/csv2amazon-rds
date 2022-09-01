package com.yournews.newsdatasetting.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "news_id")
    private Long id;
    private LocalDateTime date;
    private String press;
    private String author;
    private String title;
    private String contents;
    private String url;
}
