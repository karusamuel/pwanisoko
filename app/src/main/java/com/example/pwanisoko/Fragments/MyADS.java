package com.example.pwanisoko.Fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pwanisoko.Fragments.myADS_Fragments.ADS;
import com.example.pwanisoko.Fragments.myADS_Fragments.Favourites;
import com.example.pwanisoko.Fragments.myADS_Fragments.MyAdsFragmentAdapter;
import com.example.pwanisoko.R;

public class MyADS extends Fragment {
    FragmentManager manager;
    ViewPager viewPager;
    TabLayout tabLayout;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

         manager = getFragmentManager();




    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstance){
        View view = inflater.inflate(R.layout.fragment_myads_layout,container,false);
        viewPager = view.findViewById(R.id.my_ads_view_pager);
        tabLayout = view.findViewById(R.id.myads_tabLayout);


        tabLayout.setupWithViewPager(viewPager);
        MyAdsFragmentAdapter adsFragmentAdapter = new MyAdsFragmentAdapter(manager);

        adsFragmentAdapter.addFragment(new ADS(),"ADS");
        adsFragmentAdapter.addFragment(new Favourites(),"Favorites");

        viewPager.setAdapter(adsFragmentAdapter);




        return view;
    }
}
