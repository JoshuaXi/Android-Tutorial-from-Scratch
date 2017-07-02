package com.example.lion.ch030_mongodbcloud;

import com.example.lion.ch030_mongodbcloud.Class.User;

/**
 * Created by Lion on 6/29/2017.
 */

public class Common {
    private static String DB_NAME = "mydb";
    private static String COLLECTION_NAME = "user";
    public static String API_KEY = "gmrXNg2sbdFz9Ki8C0giZke1roEI9Jb_";

    public static String getAddressSingle(User user){
        String baseUrl = String.format("https://api.mlab.com/api/1/databases/%s/collections/%s", DB_NAME, COLLECTION_NAME);
        StringBuilder stringBuilder = new StringBuilder(baseUrl);
        stringBuilder.append("/" + user.get_id().getOld() + "?apiKey=" + API_KEY);
        return stringBuilder.toString();
    }

    public static String getAddressAPI(){
        String baseUrl = String.format("https://api.mlab.com/api/1/databases/%s/collections/%s", DB_NAME, COLLECTION_NAME);
        StringBuilder stringBuilder = new StringBuilder(baseUrl);
        stringBuilder.append("?apiKey=" + API_KEY);
        return stringBuilder.toString();
    }
}
