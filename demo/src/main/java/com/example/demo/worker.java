package com.example.demo;

import javafx.scene.image.Image;

public class worker {
    private String name,lname;

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    private Image imgSrc;

    private String  year;
    private String  mail;

    public String getMail(){ return mail;}
    public void  setMail(String mail){this.mail=mail;}
    public String getYear(){ return year;}
    public void  setYear(String year){this.year=year;}

    public  String getName() {
        return name;
    }

    public void setName(String name1) {
        this.name = name1;
    }

    public Image getImgSrc(){return imgSrc;}
    public void setImgSrc(Image imgSrc){this.imgSrc = imgSrc ;}
}