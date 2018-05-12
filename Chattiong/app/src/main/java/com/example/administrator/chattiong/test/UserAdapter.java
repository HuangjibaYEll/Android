package com.example.administrator.chattiong.test;


import com.example.administrator.chattiong.BR;
import com.example.administrator.chattiong.R;
import com.example.administrator.chattiong.adapter.RecyclerBaseAdapter;
import com.example.administrator.chattiong.adapter.RecyclerViewHolder;


import java.util.List;

/**
 * Created by Administrator on 2016/2/28.
 */
public class UserAdapter extends RecyclerBaseAdapter<User> {

    public UserAdapter(List<User> mData) {
        super(mData);
    }

    @Override
    public int getItemLayoutId(int viewType) {
        return R.layout.main_item;
    }

    @Override
    public int getVariableId(int viewType) {
                return BR.users;


    }

    @Override
    public int getItemTypePosition(int position) {
        return mData.get(position).getTitle() == null ? 1 : 2;
    }

    @Override
    public void bindCustomData(RecyclerViewHolder holder, int position, User item) {

    }

    @Override
    public int getStartMode() {
        return 0;
    }
}
