package com.aliaboubakr.spacechat.models;

public class UsersModel {

    private String id,userName,imgURL;

    public UsersModel(String id, String userName, String imgURL) {
        this.id = id;
        this.userName = userName;
        this.imgURL = imgURL;
    }

    public UsersModel(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }
}
