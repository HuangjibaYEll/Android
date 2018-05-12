package com.example.administrator.chattiong;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.chattiong.App.NoteApplication;
import com.example.administrator.chattiong.Bean.News;
import com.example.administrator.chattiong.Bean.Note;
import com.example.administrator.chattiong.Bean.NoteDate;

import org.litepal.crud.DataSupport;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

import static com.example.administrator.chattiong.Util.Util.setNewsToDate;
import static com.example.administrator.chattiong.Util.Util.setNoteDate;

public class WelcomeActivity extends AppCompatActivity {
    private SharedPreferences sp;
    private Handler handler;
    private static final String TAG = "MainActivityLog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >=21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            );
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_welcome);

        NoteApplication utils = NoteApplication.getUtils();
        sp = utils.getSpUtil();
        handler = new Handler();

        handler.postDelayed(startAct,5000);
    }

    Runnable startAct = new Runnable() {
        @Override
        public void run() {
            setNewsToDate();
            setNoteDate();
            if (!TextUtils.isEmpty(sp.getString("username",null))){
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
            }else {
                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
            finish();
        }
    };



}

