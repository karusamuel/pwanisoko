package com.example.pwanisoko.models;

public class ChatsModel{
    private String text;
    private String receiverId;
    private String senderId;
    private String date;
    private String name;




    public ChatsModel() {
    }

    public ChatsModel(String text, String receiverId, String senderId, String date, String name) {
        this.text = text;
        this.receiverId = receiverId;
        this.senderId = senderId;
        this.date = date;
        this.name = name;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
