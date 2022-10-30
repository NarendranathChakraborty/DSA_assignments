package com.example.demo;

public class ModelTable {

    String id,fname,lname,mail,number,location,service,gender;

    public String getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public ModelTable(String id, String fname, String lname, String mail, String number,String location,String service){
        this.id=id;
        this.fname= fname;
        this.lname=lname;
        this.mail=mail;
        this.number=number;
        this.service=service;
        this.location=location;
    }

}


