package com.example.administrator.chattiong.Bean;

import com.google.gson.annotations.SerializedName;

import cn.bmob.v3.BmobObject;



public class Note extends BmobObject {
    @SerializedName("description")
    private String content;
    @SerializedName("date")
    private String date;
    @SerializedName("author")
    private String author;
    @SerializedName("longitude")
    private double longitude;
    @SerializedName("latitude")
    private double latitude;
    @SerializedName("img_url")
    private String imgUrl;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "Note{" +
                "content='" + content + '\'' +
                ", date='" + date + '\'' +
                ", author='" + author + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }

    public NoteDate NoteToDate(Note note){
        NoteDate noteDate = new NoteDate();
        noteDate.setObjectId(note.getObjectId());
        noteDate.setContent(note.getContent());
        noteDate.setDate(note.getDate());
        noteDate.setAuthor(note.getAuthor());
        noteDate.setLatitude(note.getLatitude());
        noteDate.setLongitude(note.getLongitude());
        noteDate.setImgUrl(note.getImgUrl());
        return noteDate;
    }
}
