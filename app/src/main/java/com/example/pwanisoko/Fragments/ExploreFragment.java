package com.example.pwanisoko.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pwanisoko.R;
import com.example.pwanisoko.adapters.MainActivityRecyclerAdapter;

public class ExploreFragment extends Fragment {
    RecyclerView recyclerView;
    public  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explore_layout,container,false);
        GridLayoutManager manager = new GridLayoutManager(getContext(),3);


        recyclerView = view.findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(manager);

        recyclerView.setAdapter(new MainActivityRecyclerAdapter(getContext()));
        return view;
    }


}
