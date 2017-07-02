package com.example.lion.ch030_mongodbcloud.Class;

/**
 * Created by Lion on 6/29/2017.
 */

public class User {

    private Id _id;
    private String user;

    public Id get_id() {
        return _id;
    }

    public void set_id(Id _id) {
        this._id = _id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
