package com.example.administrator.chattiong.Bean;

import android.content.Intent;
import android.view.View;

import com.example.administrator.chattiong.DetailNewsActivity;
import com.google.gson.annotations.SerializedName;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/24.
 */

public class News extends DataSupport implements Serializable{
    private String objectId;
    @SerializedName("title")
    private String title;
    @SerializedName("content")
    private String content;
    @SerializedName("createdAt")
    private String createdAt;
    @SerializedName("author")
    private String author;
    @SerializedName("longitude")
    private double longitude;
    @SerializedName("latitude")
    private double latitude;
    @SerializedName("ImgUrl")
    private String ImgUrl;


    public void GetImage(View view){

    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getImgUrl() {
        return ImgUrl;
    }

    public void setImgUrl(String imgUrl) {
        ImgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
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


    @Override
    public String toString() {
        return "News{" +
                "objectId='" + objectId + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", author='" + author + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", ImgUrl='" + ImgUrl + '\'' +
                '}';
    }

    public void DetailNews(View view) {
        News news = new News();
        news.setContent(content);
        news.setTitle(title);
        news.setCreatedAt(createdAt);
        news.setAuthor(author);
        news.setImgUrl(ImgUrl);
        Intent intent = new Intent(view.getContext(), DetailNewsActivity.class);
        intent.putExtra("news_data",news);
        view.getContext().startActivity(intent);
    }
}
