package com.hack.app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Table(name = "comments")
@Entity
@Getter
@Setter
public class Comments {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id", nullable = false)
    private Integer user_id;

    @Column(name = "idea_id", nullable = false)
    private Integer idea_id;

    @Column(name = "comment", nullable = false)
    private String comment;

    @Column(name = "time", nullable = false)
    private Date time;
}