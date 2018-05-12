package com.example.administrator.chattiong.Fragment.LayoutManager;


import android.content.SharedPreferences;

import android.databinding.DataBindingUtil;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import android.view.animation.OvershootInterpolator;
import android.widget.TextView;
import android.widget.Toast;


import com.baidu.platform.comapi.map.N;
import com.bakerj.infinitecards.AnimationTransformer;
import com.bakerj.infinitecards.CardItem;
import com.bakerj.infinitecards.InfiniteCardView;
import com.bakerj.infinitecards.ZIndexTransformer;
import com.bakerj.infinitecards.transformer.DefaultCommonTransformer;

import com.bakerj.infinitecards.transformer.DefaultTransformerToFront;

import com.example.administrator.chattiong.App.NoteApplication;

import com.example.administrator.chattiong.Bean.News;
import com.example.administrator.chattiong.Bean.Note;
import com.example.administrator.chattiong.Bean.NoteDate;
import com.example.administrator.chattiong.MainActivity;
import com.example.administrator.chattiong.R;

import com.example.administrator.chattiong.adapter.NoteRecycleAdapter;
import com.example.administrator.chattiong.adapter.RecyclerBaseAdapter;
import com.example.administrator.chattiong.databinding.FragmentNoteBinding;
import com.nineoldandroids.view.ViewHelper;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;


/**
 * A simple {@link Fragment} subclass.
 * 写日记
 */
public class NoteFragment extends Fragment {

    private List<NoteDate> notes = new ArrayList<>();
    private NoteRecycleAdapter adapter;
    private InfiniteCardView cardView;
    private boolean mIsAdapter1 = false;
    FragmentNoteBinding binding;
    public NoteFragment() {
        // Required empty public constructor
    }

    public NoteRecycleAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(NoteRecycleAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_note, container, false);
        SharedPreferences sp = NoteApplication.getUtils().getSpUtil();
        String username = sp.getString("username", null);
        //取出不同账号的数据 方法一切换账号时清空数据库，方法二按类取出
        List<NoteDate> dates = DataSupport.findAll(NoteDate.class);
        for (NoteDate n:dates){
            if (n.getAuthor().equals(username)) {
                notes.add(n);
            }
        }

        Config config = new Config();
        config.secondaryScale = 0.8f;
        config.scaleRatio = 0.4f;
        config.maxStackCount = 4;
        config.initialStackCount = 2;
        config.space = 15;
        config.align = Align.LEFT;
        adapter = new NoteRecycleAdapter(notes);

        if (notes.size() > 0) {
            binding.recyclerview.setLayoutManager(new StackLayoutManager(config));
            binding.recyclerview.setAdapter(adapter);
            adapter.setMyItemClickListener(new RecyclerBaseAdapter.MyItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    NoteDate noteDate = notes.get(position);
                    binding.setNoteDate(noteDate);
                }
            });
        }

        return binding.getRoot();
    }

    public ArrayList<Note> Load() {
        SharedPreferences sp = NoteApplication.getUtils().getSpUtil();
        String username = sp.getString("username", null);
        BmobQuery<Note> query = new BmobQuery<>();
        query.addWhereEqualTo("author", username);
        query.setLimit(50);
        //执行查询方法
        query.findObjects(new FindListener<Note>() {
            @Override
            public void done(List<Note> list, BmobException e) {
                if (e == null) {
                    // myNote = (ArrayList<Note>) list;
                } else {
                    Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                }
            }
        });
        return null;
    }


    public boolean isOldNews(News n) {
        boolean TT = true;
        // List<News> list = DataSupport.select("objectId").find(News.class);
        List<News> list = DataSupport.findAll(News.class);
        for (News news : list) {
            if (n.getObjectId().equals(news.getObjectId())) {
                TT = false;
            }
        }
        return TT;
    }

    //cardView   作废
    private void setStyle2() {
        cardView.setClickable(true);
        cardView.setAnimType(InfiniteCardView.ANIM_TYPE_SWITCH);
        cardView.setAnimInterpolator(new OvershootInterpolator(-18));
        cardView.setTransformerToFront(new DefaultTransformerToFront());
        cardView.setTransformerToBack(new AnimationTransformer() {
            @Override
            public void transformAnimation(View view, float fraction, int cardWidth, int cardHeight, int fromPosition, int toPosition) {
                int positionCount = fromPosition - toPosition;
                float scale = (0.8f - 0.1f * fromPosition) + (0.1f * fraction * positionCount);
                ViewHelper.setScaleX(view, scale);
                ViewHelper.setScaleY(view, scale);
                if (fraction < 0.5) {
                    ViewCompat.setRotationX(view, 180 * fraction);
                } else {
                    ViewCompat.setRotationX(view, 180 * (1 - fraction));
                }
            }

            @Override
            public void transformInterpolatedAnimation(View view, float fraction, int cardWidth, int cardHeight, int fromPosition, int toPosition) {
                int positionCount = fromPosition - toPosition;
                float scale = (0.8f - 0.1f * fromPosition) + (0.1f * fraction * positionCount);
                ViewHelper.setTranslationY(view, -cardHeight * (0.8f - scale) * 0.5f - cardWidth * (0.02f *
                        fromPosition - 0.02f * fraction * positionCount));
            }
        });
        cardView.setZIndexTransformerToBack(new ZIndexTransformer() {
            @Override
            public void transformAnimation(CardItem card, float fraction, int cardWidth, int cardHeight, int fromPosition, int toPosition) {
                if (fraction < 0.4f) {
                    card.zIndex = 1f + 0.01f * fromPosition;
                } else {
                    card.zIndex = 1f + 0.01f * toPosition;
                }
            }

            @Override
            public void transformInterpolatedAnimation(CardItem card, float fraction, int cardWidth, int cardHeight, int fromPosition, int toPosition) {

            }
        });
    }

    //cardView   作废
    private void setStyle3() {
        cardView.setClickable(false);
        cardView.setAnimType(InfiniteCardView.ANIM_TYPE_FRONT_TO_LAST);
        cardView.setAnimInterpolator(new OvershootInterpolator(-8));
        cardView.setTransformerToFront(new DefaultCommonTransformer());
        cardView.setTransformerToBack(new AnimationTransformer() {
            @Override
            public void transformAnimation(View view, float fraction, int cardWidth, int cardHeight, int fromPosition, int toPosition) {
                int positionCount = fromPosition - toPosition;
                float scale = (0.8f - 0.1f * fromPosition) + (0.1f * fraction * positionCount);
                ViewHelper.setScaleX(view, scale);
                ViewHelper.setScaleY(view, scale);
                if (fraction < 0.5) {
                    ViewCompat.setTranslationX(view, cardWidth * fraction * 1.5f);
                    ViewCompat.setRotationY(view, -45 * fraction);
                } else {
                    ViewCompat.setTranslationX(view, cardWidth * 1.5f * (1f - fraction));
                    ViewCompat.setRotationY(view, -45 * (1 - fraction));
                }
            }

            @Override
            public void transformInterpolatedAnimation(View view, float fraction, int cardWidth, int cardHeight, int fromPosition, int toPosition) {
                int positionCount = fromPosition - toPosition;
                float scale = (0.8f - 0.1f * fromPosition) + (0.1f * fraction * positionCount);
                ViewHelper.setTranslationY(view, -cardHeight * (0.8f - scale) * 0.5f - cardWidth * (0.02f *
                        fromPosition - 0.02f * fraction * positionCount));
            }
        });
        cardView.setZIndexTransformerToBack(new ZIndexTransformer() {
            @Override
            public void transformAnimation(CardItem card, float fraction, int cardWidth, int cardHeight, int fromPosition, int toPosition) {
                if (fraction < 0.5f) {
                    card.zIndex = 1f + 0.01f * fromPosition;
                } else {
                    card.zIndex = 1f + 0.01f * toPosition;
                }
            }

            @Override
            public void transformInterpolatedAnimation(CardItem card, float fraction, int cardWidth, int cardHeight, int fromPosition, int toPosition) {

            }
        });
    }
}
