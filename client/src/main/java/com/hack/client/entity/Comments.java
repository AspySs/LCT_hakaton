package com.hack.client.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Comments {
    private Integer id;
    private Integer user_id;
    private Integer idea_id;
    private String comment;
    private Date time;

    @Override
    public String toString() {
        return "Charge{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", idea_id=" + idea_id +
                ", comment=" + comment +
                ", time=" + time +
                '}';
    }
}
