package com.example.administrator.chattiong.adapter;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.chattiong.BR;
import com.example.administrator.chattiong.Bean.Note;
import com.example.administrator.chattiong.Bean.NoteDate;
import com.example.administrator.chattiong.R;


import java.util.List;

public class NoteRecycleAdapter extends RecyclerBaseAdapter<NoteDate>  {

    public NoteRecycleAdapter(List<NoteDate> mData) {
        super(mData);
    }

    @Override
    public int getItemLayoutId(int viewType) {
        return R.layout.item_card_note;
    }

    @Override
    public int getVariableId(int viewType) {
        return BR.NoteDate;
    }

    @Override
    public int getItemTypePosition(int position) {
        return mData.get(position).getContent() == null ? 1 : 2;
    }

    @Override
    public void bindCustomData(RecyclerViewHolder holder, int position, NoteDate item) {
        Log.d("NoteRecycleAdapter :",position+"");
    }


    @Override
    public int getStartMode() {
        return 0;
    }





}
