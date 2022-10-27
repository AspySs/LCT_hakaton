package com.hack.client.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ideas {
    private Integer id;
    private String name;
    private String sphere;
    private String tag;
    private String Descript;
    private String status;

    @Override
    public String toString() {
        return "Charge{" +
                "id=" + id +
                ", name=" + name +
                ", sphere=" + sphere +
                ", tag=" + tag +
                ", Descript=" + Descript +
                ", status=" + status +
                '}';
    }

}
