package com.yournews.newsdatasetting.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity(name = "Groups2")
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "groups2_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "division_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Division division;

    private String groupName;
}
