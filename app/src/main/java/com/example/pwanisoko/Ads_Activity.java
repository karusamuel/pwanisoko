package com.example.pwanisoko;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.example.pwanisoko.adapters.AdsViewRecyclerAdapter;
import com.example.pwanisoko.models.Advert;
import com.example.pwanisoko.objects.LayoutManager;

import java.util.ArrayList;

public class Ads_Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ads_);
        recyclerView = findViewById(R.id.ads_display_recyclerView);
        recyclerView.setLayoutManager(new LayoutManager().getGridLayoutManager(this,2));

        recyclerView.setAdapter(new AdsViewRecyclerAdapter(this,new ArrayList<Advert>()));

    }
}
