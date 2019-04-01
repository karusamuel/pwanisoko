package com.example.pwanisoko.objects;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;

public class LayoutManager {
    public LinearLayoutManager getLinearLayoutManager(Context context,int orientation){
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(orientation);
        return manager;
    }

    public GridLayoutManager getGridLayoutManager(Context context, int spanCount){
       GridLayoutManager manager = new GridLayoutManager(context,spanCount);
        return manager;
    }

}
