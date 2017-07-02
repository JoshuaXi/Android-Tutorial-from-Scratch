package com.example.lion.ch030_mongodbcloud.Class;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Lion on 6/29/2017.
 */

public class Id {
    @SerializedName("$old")
    private String old;

    public String getOld() {
        return old;
    }

    public void setOld(String old) {
        this.old = old;
    }
}
