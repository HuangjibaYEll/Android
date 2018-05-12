package com.example.administrator.chattiong.Util;

import android.content.SharedPreferences;
import android.databinding.BindingAdapter;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.chattiong.App.NoteApplication;
import com.example.administrator.chattiong.Bean.News;
import com.example.administrator.chattiong.Bean.Note;
import com.example.administrator.chattiong.Bean.NoteDate;
import com.example.administrator.chattiong.R;

import org.litepal.crud.DataSupport;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UploadFileListener;

/**
 * Created by Administrator on 2017/6/1.
 */

public class Util {


    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String url){
        if (url == null){
            view.setImageResource(R.drawable.xm7);
        }else{
            //用Glide 加载图片
            Glide.with(view.getContext()).load(url).into(view);
        }
    }

    //将近15天的新闻存入本地数据库
    public static void setNewsToDate() {
        BmobQuery<News> query = new BmobQuery<News>();
        ArrayList<BmobQuery<News>> order = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        String start = formatter.format(new Date(curDate.getTime() - 60 * 60 * 1000 * 24 * 15L));
        Date date  = null;
        try {
            date = formatter.parse(start);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        query.addWhereGreaterThanOrEqualTo("createdAt",new BmobDate(date));
        query.setLimit(100);
        query.findObjects(new FindListener<News>() {
            @Override
            public void done(List<News> list, BmobException e) {
                if (e==null){
                    for (News news : list){
                        if (isOldNews(news)) {
                            news.save();
                        }
                    }
                }
            }
        });

    }

    //取出该用户的日记存入本地数据库
    public static void setNoteDate(){
        SharedPreferences sp = NoteApplication.getUtils().getSpUtil();
        String username = sp.getString("username", null);
        BmobQuery<Note> query = new BmobQuery<Note>();
        query.addWhereEqualTo("author", username);
        query.setLimit(50);
        //执行查询方法
        query.findObjects(new FindListener<Note>() {
            @Override
            public void done(List<Note> list, BmobException e) {
                if (e == null) {
                    for (Note note : list){
                        NoteDate noteDate = note.NoteToDate(note);
                        if (isOldNote(noteDate)) {
                            noteDate.save();
                        }
                    }
                }else{
                    Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }

    public static boolean isOldNote(NoteDate n){
        boolean TT = true;
        List<NoteDate> list = DataSupport.findAll(NoteDate.class);
        if (!list.isEmpty()) {
            for (NoteDate note : list) {
                if (n.getObjectId().equals(note.getObjectId())) {
                    TT = false;
                }
            }
        }
        return TT;
    }



    //是否
    public static boolean isOldNews(News n){
        boolean TT = true;
        // List<News> list = DataSupport.select("objectId").find(News.class);
        List<News> list = DataSupport.findAll(News.class);
        for (News news:list) {
            if (n.getObjectId().equals(news.getObjectId())){
                TT = false;
            }
        }
        return TT;
    }

}
