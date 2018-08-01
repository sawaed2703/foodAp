package com.sawaedaib.aibrahemsawaed.myapplication.Utils;

import com.google.firebase.database.Exclude;

public class FoodAdater {

    private String name;
    private String lastName;
    private String city;
    private String address;
    private String desc;
    private String phoneNum;
    private String mImageUrl;
    private String mKey;


    public FoodAdater() {
    }


    public FoodAdater(String name,String lastName, String city, String address, String desc, String phoneNum, String mImageUrl) {
        this.name = name;
        this.lastName = lastName;
        this.city = city;
        this.address = address;
        this.desc = desc;
        this.phoneNum = phoneNum;
        this.mImageUrl = mImageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }


    @Exclude
    public String getKey() {
        return mKey;
    }
    @Exclude
    public void setKey(String key){
        mKey = key;
    }
}
