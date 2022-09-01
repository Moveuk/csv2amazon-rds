package com.yournews.newsdatasetting.modelv2;

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
public class Section2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "section_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "news_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private NewsNoNormal news;

    private String sectionName;
}
