package com.hack.client.entity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private Long id;
    private String login;
    private String token;

    private String name;

    private String info;

    private String status;

    private String birth_date;

    private String country;

    private String city;

    private String grajd;

    private boolean gender;

    private String contact;

    private String education;

    private String busyness;

    private String expWork;

    private String skill;

    private String achievements;

    private boolean command;

    private String role;

    public User() {
    }

    public User(long id, String login, String token) {
        this.id = id;
        this.login = login;
        this.token = token;
    }
}
