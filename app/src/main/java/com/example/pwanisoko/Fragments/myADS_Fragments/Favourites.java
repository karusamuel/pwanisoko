package com.example.pwanisoko.Fragments.myADS_Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pwanisoko.R;

public class Favourites extends Fragment {
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);


    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        View view = inflater.inflate(R.layout.basic_recycler_view_layout,container,false);

        return view;
    }
}
