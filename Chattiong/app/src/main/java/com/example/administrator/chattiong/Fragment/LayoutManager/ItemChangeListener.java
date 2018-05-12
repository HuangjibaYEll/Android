package com.example.administrator.chattiong.Fragment.LayoutManager;

import android.view.View;



public interface ItemChangeListener {

    /**
     *
     * @param itemView the new item in the base position
     * @param position the item's  position in list
     */
    void onItemChange(View itemView, int position);
}
