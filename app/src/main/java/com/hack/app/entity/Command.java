package com.hack.app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "command")
@Entity
@Getter
@Setter
public class Command {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idea_id", nullable = false)
    private Ideas idea;

    @ManyToOne
    @JoinColumn(name = "worker_id", nullable = false)
    private Workers worker;

    @Column(name = "status", nullable = false)
    private String status;

}