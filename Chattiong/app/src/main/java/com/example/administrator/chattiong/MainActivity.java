package com.example.administrator.chattiong;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.example.administrator.chattiong.Fragment.LayoutManager.NoteFragment;
import com.example.administrator.chattiong.Fragment.MapFragment;
import com.example.administrator.chattiong.Fragment.MyselfFragment;
import com.example.administrator.chattiong.Fragment.NewsListFragment;
import com.example.administrator.chattiong.test.BlankFragment;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private Fragment newsListFragment;
    private Fragment noteFragment;
    private Fragment myselfFragment;
    private Fragment mapFragment;
    private FragmentManager manager;
    private RadioGroup groupView;


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
        } //影藏顶栏
        setContentView(R.layout.activity_main);
        autoBindBaiduYunTuiSong();
        initFragment();
        groupView = ((RadioGroup) findViewById(R.id.group));
        groupView.setOnCheckedChangeListener(this);


    }






    @Override
    protected void onNewIntent(Intent intent) {
        String result = intent.getStringExtra("result");
        if (result != null) {
            System.out.println(result);
            Toast.makeText(this,"有最新的新闻",Toast.LENGTH_SHORT).show();
        }
    }

    public void initFragment(){
        myselfFragment = new MyselfFragment();
        noteFragment = new NoteFragment();
        newsListFragment = new NewsListFragment();
        mapFragment = new MapFragment();
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.main_layout,newsListFragment);
        transaction.add(R.id.main_layout,noteFragment);
        transaction.add(R.id.main_layout,myselfFragment);
        transaction.add(R.id.main_layout,mapFragment);
        transaction.hide(noteFragment);
        transaction.hide(myselfFragment);
        transaction.hide(mapFragment);
        transaction.commit();
    }





    public void autoBindBaiduYunTuiSong(){
        PushManager.startWork(getApplicationContext()
                ,PushConstants.LOGIN_TYPE_API_KEY
                ,"iTnpBatl0IRY5nu6WxLrIGGn");
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        FragmentTransaction transaction = manager.beginTransaction();
        switch (checkedId){
            case R.id.news:
                transaction.show(newsListFragment);
                transaction.hide(noteFragment);
                transaction.hide(myselfFragment);
                transaction.hide(mapFragment);
                break;
            case R.id.note:
                transaction.hide(newsListFragment);
                transaction.show(noteFragment);
                transaction.hide(myselfFragment);
                transaction.hide(mapFragment);
                break;
            case R.id.myself:
                transaction.hide(newsListFragment);
                transaction.hide(noteFragment);
                transaction.show(myselfFragment);
                transaction.hide(mapFragment);
                break;
            case R.id.map:
                transaction.hide(newsListFragment);
                transaction.hide(noteFragment);
                transaction.hide(myselfFragment);
                transaction.show(mapFragment);
                break;
        }
        transaction.commit();
    }




}
