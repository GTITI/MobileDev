package com.example.gocztiti.petfriends;

/**
 * Created by Gocz Titi on 09.11.2016.
 */

public class ListModel {

    private  String Name="";
    //private  String Image="";
    private  String Desciption="";

    /*********** Set Methods ******************/

    public void setName(String Name)
    {
        this.Name = Name;
    }

//    public void setImage(String Image)
//    {
//        this.Image = Image;
//    }

    public void setDesciption(String Url)
    {
        this.Desciption = Url;
    }

    /*********** Get Methods ****************/

    public String getName()
    {
        return this.Name;
    }

//    public String getImage()
//    {
//        return this.Image;
//    }

    public String getDesciption()
    {
        return this.Desciption;
    }
}
