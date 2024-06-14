package com.battlejawn.Entities.Talent;

import lombok.Data;
import lombok.Getter;

@Getter
public class Talent {
    private String name;
    private String description;
    private boolean active;

    public Talent(String name, String description, boolean active){
        this.name = name;
        this.description = description;
        this.active = active;
    }

    public void setActive(boolean active){
        this.active = active;
    }
}
