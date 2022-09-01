package com.yournews.newsdatasetting.modelv2;

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
public class NewsNoNormal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "news_id")
    private Long id;
    private LocalDateTime date;
    private String press;
    private String author;
    private String title;
    @Lob
    private String person;
    @Lob
    private String location;
    @Lob
    private String organization;
    @Lob
    private String keyword;
    private String contents;
    private String url;
}
