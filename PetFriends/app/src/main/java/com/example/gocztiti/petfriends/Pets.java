package com.example.gocztiti.petfriends;

/**
 * Created by Gocz Titi on 09.11.2016.
 */

public class Pets {
    private String name;
    private String desc;
    private boolean accepted;

    public Pets(String name, String desc, Boolean accepted){
        this.name = name;

        this.desc = desc;
        this.accepted = accepted;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
    public Boolean getAccepted() {
        return accepted;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String desc) {
        this.desc = desc;
    }
    public void setAccepted(Boolean Accepted) {
        this.accepted = accepted;
    }

    @Override
    public String toString() {
        return name;
    }
}

