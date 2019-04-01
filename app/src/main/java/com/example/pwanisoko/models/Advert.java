package com.example.pwanisoko.models;

public class Advert {
    private String url;
    private String aTitle;
    private String adDescription;
    private int adPrice;
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getaTitle() {
        return aTitle;
    }

    public void setaTitle(String aTitle) {
        this.aTitle = aTitle;
    }

    public String getAdDescription() {
        return adDescription;
    }

    public void setAdDescription(String adDescription) {
        this.adDescription = adDescription;
    }

    public int getAdPrice() {
        return adPrice;
    }

    public void setAdPrice(int adPrice) {
        this.adPrice = adPrice;
    }

    public String getAdlocation() {
        return adlocation;
    }

    public void setAdlocation(String adlocation) {
        this.adlocation = adlocation;
    }

    public String getAdCategory() {
        return adCategory;
    }

    public void setAdCategory(String adCategory) {
        this.adCategory = adCategory;
    }

    private String adlocation;
    private String adCategory;


}
