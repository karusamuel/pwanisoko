package com.example.pwanisoko.models;

public class ChatList {

    String userName;
    String lastMessage;
    String messageTime;
    boolean newMessage = true;

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

    public ChatList(String userName, String lastMessage, String messageTime, boolean newMessage) {
        this.userName = userName;
        this.lastMessage = lastMessage;
        this.messageTime = messageTime;
        this.newMessage = newMessage;
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
