package com.example.administrator.chattiong.Fragment;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.chattiong.App.NoteApplication;
import com.example.administrator.chattiong.Bean.User;
import com.example.administrator.chattiong.NoteActivity;
import com.example.administrator.chattiong.R;
import com.example.administrator.chattiong.databinding.FragmentMyselfBinding;

/**
 * A simple {@link Fragment} subclass.
 * 显示个人信息查看历史记录
 */
public class MyselfFragment extends Fragment {


    private TextView text;

    public MyselfFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentMyselfBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_myself, container, false);
        User user = new User();
        binding.setUser(user);
        return binding.getRoot();
    }



}
