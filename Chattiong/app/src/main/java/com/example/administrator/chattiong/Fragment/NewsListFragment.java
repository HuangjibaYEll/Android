package com.example.administrator.chattiong.Fragment;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;
import com.example.administrator.chattiong.Bean.News;
import com.example.administrator.chattiong.R;
import com.example.administrator.chattiong.Util.CommonAdapter;
import com.example.administrator.chattiong.databinding.FragmentNewsListBinding;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * 显示新闻列表
 */
public class NewsListFragment extends Fragment {

    private List<News> list = new ArrayList<>();

    public NewsListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentNewsListBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news_list, container, false);
        list = DataSupport.findAll(News.class);
        Collections.reverse(list);
        CommonAdapter<News> adapter = new CommonAdapter<>(getContext(), list, R.layout.item_list_news, BR.News);
        binding.setAdapter(adapter);
        return binding.getRoot();

    }


    public void init(){

    }

}
