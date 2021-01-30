package com.alexc.dam_proiect;

import java.io.Serializable;

public class FeedBack implements Serializable {
    private  String Id;
    private  String Content;
    private  String Type;
    private float rating;

    @Override
    public String toString() {
        return "FeedBack{" +
                "Id='" + Id + '\'' +
                ", Content='" + Content + '\'' +
                ", Type='" + Type + '\'' +
                ", rating=" + rating +
                '}';
    }


    public FeedBack(){

    }

    public FeedBack(String id, String content, String type, float rating) {
        Id = id;
        Content = content;
        Type = type;
        this.rating =  rating;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
