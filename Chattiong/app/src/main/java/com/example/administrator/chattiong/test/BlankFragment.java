package com.example.administrator.chattiong.test;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.administrator.chattiong.Bean.NoteDate;
import com.example.administrator.chattiong.R;
import com.example.administrator.chattiong.adapter.NoteRecycleAdapter;
import com.example.administrator.chattiong.adapter.RecyclerBaseAdapter;
import com.example.administrator.chattiong.databinding.FragmentBlankBinding;

import java.util.ArrayList;
import java.util.List;


public class BlankFragment extends Fragment {

    private NoteRecycleAdapter mAdapter;
    private List<NoteDate> mUsers;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    public BlankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment newInstance(String param1, String param2) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         final FragmentBlankBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_blank, container, false);
        mUsers = new ArrayList<>();
        setData();
        mAdapter = new NoteRecycleAdapter(mUsers);

        binding.recyclerView1.setLayoutManager(new LinearLayoutManager(inflater.getContext()));
        binding.recyclerView1.setAdapter(mAdapter);


        mAdapter.setMyItemClickListener(new RecyclerBaseAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.d("TAG","---"+position);
                Toast.makeText(inflater.getContext(),"+++++++++++++++"+position, Toast.LENGTH_SHORT).show();
                binding.Text24.setText("+++++++++++++++"+position);
            }
        });

        // Inflate the layout for this fragment
        return binding.getRoot();
    }


    private void setData(){

        for(int i = 0; i < 20; i++){
            NoteDate user = new NoteDate();
            if(i==5){
                user.setDate("标题");
            }
            user.setImgUrl("http://g.hiphotos.baidu.com/image/h%3D200/sign=4d3fabc3cbfc1e17e2bf8b317a91f67c/6c224f4a20a446230761b9b79c22720e0df3d7bf.jpg");
            user.setContent("小米" + i);
            user.setAuthor(String.valueOf(i));
            mUsers.add(user);
        }
    }



}
