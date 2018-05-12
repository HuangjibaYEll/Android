package com.example.administrator.chattiong.Bean;

import android.databinding.DataBindingUtil;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.chattiong.R;
import com.example.administrator.chattiong.databinding.FragmentNoteBinding;
import com.google.gson.annotations.SerializedName;

import org.litepal.crud.DataSupport;

import cn.bmob.v3.BmobObject;

/**
 * Created by Yz on 2018/4/10.
 */

public class NoteDate extends DataSupport {
    private String objectId;
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

    public void Click(View view){

    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

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


}
