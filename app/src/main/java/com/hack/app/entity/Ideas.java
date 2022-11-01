package com.hack.app.entity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "ideas")
@Entity
@Getter
@Setter
public class Ideas {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "sphere", nullable = true)
    private String sphere;

    @Column(name = "tag", nullable = true)
    private String tag;

    @Column(name = "descript", nullable = true)
    private String Descript;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "project", nullable = false)
    private String project;

    @Column(name = "task", nullable = false)
    private String task;

}