package com.licenta.firebase;

/**
 * Created by Gocz Titi on 18.01.2017.
 */

public class Pets {


    private String name;
    private String minidesc;


    public Pets(String name, String minidesc){
        this.name=name;
        this.minidesc=minidesc;
    }

    public String getName(){
        return this.name;
    }
    public String getMinidesc(){ return this.minidesc;}

    public void setName(String name){ this.name=name;}

    public void setMinidesc(String minidesc) {this.minidesc=minidesc;}

    public String toString(){
        return "Name: "+this.name+"\nDescription: "+this.minidesc;
    }
}
