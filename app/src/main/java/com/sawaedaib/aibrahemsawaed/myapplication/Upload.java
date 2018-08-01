package com.sawaedaib.aibrahemsawaed.myapplication;

public class Upload {
    private String food;
    private String category;
    private String imageUri;
    private String city;
    private String address;
    private String phone;
    private String start_hour;
    private String end_hour;
    private String detailes;


    public Upload(){

    }


    public Upload(String food, String category, String imageUri, String city, String address, String phone, String start_hour, String end_hour, String detailes) {
        this.food = food;
        this.category = category;
        this.imageUri = imageUri;
        this.city = city;
        this.address = address;
        this.phone = phone;
        this.start_hour = start_hour;
        this.end_hour = end_hour;
        this.detailes = detailes;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStart_hour() {
        return start_hour;
    }

    public void setStart_hour(String start_hour) {
        this.start_hour = start_hour;
    }

    public String getEnd_hour() {
        return end_hour;
    }

    public void setEnd_hour(String end_hour) {
        this.end_hour = end_hour;
    }

    public String getDetailes() {
        return detailes;
    }

    public void setDetailes(String detailes) {
        this.detailes = detailes;
    }
}
