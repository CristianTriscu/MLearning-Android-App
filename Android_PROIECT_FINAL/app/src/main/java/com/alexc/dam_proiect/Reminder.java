package com.alexc.dam_proiect;

import java.io.Serializable;

enum COURSE_TYPE2 {JAVA_COURSE, ANDROID_COURSE, XML_COURSE}

public class Reminder implements Serializable {

    private String Message;
    private String date;
    private COURSE_TYPE2 course_type;

    public Reminder(String message, String date, COURSE_TYPE2 course_type) {
        Message = message;
        this.date = date;
        this.course_type = course_type;
    }

    @Override
    public String toString() {
        return "Reminder{" +
                "Message='" + Message + '\'' +
                ", date='" + date + '\'' +
                ", course_type=" + course_type +
                '}';
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public COURSE_TYPE2 getCourse_type() {
        return course_type;
    }

    public void setCourse_type(COURSE_TYPE2 course_type) {
        this.course_type = course_type;
    }
}
