package com.example.pwanisoko.models;

public class ChatList {

    String userName;
    String lastMessage;
    String messageTime;
    String senderId;
    boolean newMessage = true;
    public ChatList() {
    }


    public ChatList(String userName, String lastMessage, String messageTime, String senderId, String receiverId, boolean newMessage) {
        this.userName = userName;
        this.lastMessage = lastMessage;
        this.messageTime = messageTime;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.newMessage = newMessage;
    }

    String receiverId;

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }


    public boolean isNewMessage() {
        return newMessage;
    }

    public void setNewMessage(boolean newMessage) {
        this.newMessage = newMessage;
    }

    public ChatList(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }
}
