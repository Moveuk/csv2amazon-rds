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
public class Division2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "division_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "section_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Section2 section;

    private String divisionName;
}
