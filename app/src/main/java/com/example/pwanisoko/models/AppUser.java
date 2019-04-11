package com.example.pwanisoko.models;

public class AppUser {
    String name;
    String photoUrl;
    String description;
    String phoneNumber;


    public AppUser() {
    }

    public AppUser(String name, String photoUrl, String description, String phoneNumber) {
        this.name = name;
        this.photoUrl = photoUrl;
        this.description = description;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
