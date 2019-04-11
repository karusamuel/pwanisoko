package com.example.pwanisoko.models;

public class ChatsModel{
    private String text;
    private String receiverId;
    private String senderId;




    public ChatsModel() {
    }

    public ChatsModel(String text, String receiverId, String senderId) {
        this.text = text;
        this.receiverId = receiverId;
        this.senderId = senderId;
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
}
