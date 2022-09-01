package com.yournews.newsdatasetting.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
public class Keyword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "keyword_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "news_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private News news;

    private String keyword;
}
