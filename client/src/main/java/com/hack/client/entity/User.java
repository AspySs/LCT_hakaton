package com.hack.client.entity;

public class User {
    private Long id;
    private String login;
    private String token;

    private String name;

    private String info;
    private String status;

    public User() {
    }

    public User(long id, String login, String token) {
        this.id = id;
        this.login = login;
        this.token = token;
    }

    public String getLogin() {
        return login;
    }

    public String getToken() {
        return token;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName(){return name;}
    public String getInfo(){return info;}
    public void setInfo(String info){this.info = info;}
    public void setName(String name){this.name = name;}
    public String getStatus(){return status;}
    public void setStatus(String status){this.status = status;}
}
