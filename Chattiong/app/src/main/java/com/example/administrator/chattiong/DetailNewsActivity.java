package com.example.administrator.chattiong;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.chattiong.Bean.News;
import com.example.administrator.chattiong.databinding.ActivityDetailNewsBinding;

public class DetailNewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetailNewsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_news);
        if (Build.VERSION.SDK_INT >=21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            );
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        News newsData = (News) getIntent().getSerializableExtra("news_data");
        binding.setNews(newsData);
    }
}
