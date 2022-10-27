package com.hack.app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Table(name = "reactions")
@Entity
@Getter
@Setter
public class Reactions {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id", nullable = false)
    private Integer user_id;

    @ManyToOne
    @JoinColumn(name = "idea_id", nullable = true)
    private Ideas idea;

    @ManyToOne
    @JoinColumn(name = "comment_id", nullable = true)
    private Comments comment;

    @Column(name = "reaction", nullable = false)
    private boolean reaction;

}