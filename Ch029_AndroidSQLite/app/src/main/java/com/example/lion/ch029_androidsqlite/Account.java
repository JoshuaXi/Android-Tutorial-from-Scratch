package com.example.lion.ch029_androidsqlite;

/**
 * Created by Lion on 6/29/2017.
 */

public class Account {

    private String UserName;
    private String Email;

    public Account(){

    }

    public Account(String userName, String email) {
        UserName = userName;
        Email = email;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
