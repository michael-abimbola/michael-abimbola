package com.fontys.onlineyearbook.nl.fontys.sem3.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "farewellMessage")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class FarewellMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String msg;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private GraduatingClass graduatingClass;

    @ManyToOne
    private Teacher teacher;

    public FarewellMessage(String msg){
        this.msg = msg;
    }
}
