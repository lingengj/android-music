package com.example.apple.wyymusic.model;

/**
 * Created by ASUS on 2019/6/21.
 */

public class Users {

    private int id;
    private String userName;
    private String userPwd;

    public Users(int id, String userName, String userPwd) {
        this.id = id;
        this.userName = userName;
        this.userPwd = userPwd;
    }

    public Users() {
    }

    public Users(String userName, String userPwd) {
        this.userName = userName;
        this.userPwd = userPwd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                '}';
    }

}
