package com.example.gocztiti.petfriends;

/**
 * Created by Gocz Titi on 11.11.2016.
 */

public class PetTypes {

    private String name;
    private String minidesc;


    public PetTypes(String name, String minidesc){
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
