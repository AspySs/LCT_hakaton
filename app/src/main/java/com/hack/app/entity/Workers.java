package com.hack.app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "workers")
@Entity
@Getter
@Setter
public class Workers {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id", nullable = false)
    private String name;

    @Column(name = "resume", nullable = false)
    private String resume;

    @Column(name = "tags", nullable = true)
    private String tags;

}