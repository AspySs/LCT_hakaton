package com.hack.client.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ideas {
    private Integer id;
    private String name;
    private String sphere;
    private String status;
    private String tag;
    private String project;
    private String descript;
    private String task;


    @Override
    public String toString() {
        return "Ideas{" +
                ", name=" + name +
                ", sphere=" + sphere +
                ", tag=" + tag +
                ", descript=" + descript +
                ", status=" + status +
                ", project=" + project +
                ", task=" + task +
                '}';
    }

}
