package com.example.pwanisoko.models;

public class ChatsModel{
private String  userName;
    private String text;
    private String time;

    public ChatsModel() {
    }

    public ChatsModel(String userName, String text, String time, String senderID) {
        this.userName = userName;
        this.text = text;
        this.time = time;
        this.senderID = senderID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSenderID() {
        return senderID;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }

    private String senderID;

}
