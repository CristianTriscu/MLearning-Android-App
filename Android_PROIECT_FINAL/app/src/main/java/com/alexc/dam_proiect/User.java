package com.alexc.dam_proiect;

import java.io.Serializable;

public class User implements Serializable {
    public User(String username, String password, String id,int trackJava1) {
        this.username = username;
        this.password = password;
        this.id = id;
    }

    public  User(){

    }
    private String username;
    private String password;
    private String id;
    private int trackJava1;
    private int trackJava2;
    private int trackJavaQuiz;
    private int trackAndroid1;
    private int trackAndroid2;
    private int trackAndroidQuiz;
    private int trackXML1;
    private int trackXML2;
    private int trackXMLQuiz;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                '}';
    }

    public int getTrackJava1() {
        return trackJava1;
    }

    public void setTrackJava1(int trackJava1) {
        this.trackJava1 = trackJava1;
    }

    public int getTrackJava2() {
        return trackJava2;
    }

    public void setTrackJava2(int trackJava2) {
        this.trackJava2 = trackJava2;
    }

    public int getTrackJavaQuiz() {
        return trackJavaQuiz;
    }

    public void setTrackJavaQuiz(int trackJavaQuiz) {
        this.trackJavaQuiz = trackJavaQuiz;
    }

    public int getTrackAndroid1() {
        return trackAndroid1;
    }

    public void setTrackAndroid1(int trackAndroid1) {
        this.trackAndroid1 = trackAndroid1;
    }

    public int getTrackAndroid2() {
        return trackAndroid2;
    }

    public void setTrackAndroid2(int trackAndroid2) {
        this.trackAndroid2 = trackAndroid2;
    }

    public int getTrackAndroidQuiz() {
        return trackAndroidQuiz;
    }

    public void setTrackAndroidQuiz(int trackAndroidQuiz) {
        this.trackAndroidQuiz = trackAndroidQuiz;
    }

    public int getTrackXML1() {
        return trackXML1;
    }

    public void setTrackXML1(int trackXML1) {
        this.trackXML1 = trackXML1;
    }

    public int getTrackXML2() {
        return trackXML2;
    }

    public void setTrackXML2(int trackXML2) {
        this.trackXML2 = trackXML2;
    }

    public int getTrackXMLQuiz() {
        return trackXMLQuiz;
    }

    public void setTrackXMLQuiz(int trackXMLQuiz) {
        this.trackXMLQuiz = trackXMLQuiz;
    }
}
