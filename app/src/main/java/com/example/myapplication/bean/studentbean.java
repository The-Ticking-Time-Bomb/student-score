package com.example.myapplication.bean;

import android.media.Image;

public class studentbean {
    private String _id;
    private String password;
    private String name;
    private String className;
    private String xID;
    private String age;
    private String phone;
    private String address;
    private String Math, computer, English, Allsore;

    public studentbean() {
    }

    public studentbean(String _id, String password, String name, String className, String xID, String age, String phone, String address, String math, String computer, String english, String allsore) {
        this._id = _id;
        this.password = password;
        this.name = name;
        this.className = className;
        this.xID = xID;
        this.age = age;
        this.phone = phone;
        this.address = address;
        Math = math;
        this.computer = computer;
        English = english;
        Allsore = allsore;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getxID() {
        return xID;
    }

    public void setxID(String xID) {
        this.xID = xID;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMath() {
        return Math;
    }

    public void setMath(String math) {
        Math = math;
    }

    public String getComputer() {
        return computer;
    }

    public void setComputer(String computer) {
        this.computer = computer;
    }

    public String getEnglish() {
        return English;
    }

    public void setEnglish(String english) {
        English = english;
    }

    public String getAllsore() {
        return Allsore;
    }

    public void setAllsore(String allsore) {
        Allsore = allsore;
    }
}
