package com.example.pwanisoko.Fragments.myADS_Fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class MyAdsFragmentAdapter extends FragmentStatePagerAdapter {
    ArrayList<Fragment> list = new ArrayList<>();
    ArrayList<String> title = new ArrayList<>();

    public void addFragment(Fragment fragment,String title) {
        this.list.add(fragment);
        this.title.add(title);
    }



    public MyAdsFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return list.get(i);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
