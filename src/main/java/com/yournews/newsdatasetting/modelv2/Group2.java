package com.yournews.newsdatasetting.modelv2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity(name = "groups2")
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
public class Group2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "groups_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "division_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Division2 division;

    private String groupName;
}
