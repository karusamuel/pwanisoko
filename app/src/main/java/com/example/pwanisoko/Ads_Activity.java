package com.example.pwanisoko;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.example.pwanisoko.adapters.AdsViewRecyclerAdapter;
import com.example.pwanisoko.models.Advert;
import com.example.pwanisoko.objects.LayoutManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Ads_Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference reference;
    ArrayList<Advert> list;
    String category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ads_);
        reference = FirebaseDatabase.getInstance().getReference().child("adverts");
        recyclerView = findViewById(R.id.ads_display_recyclerView);
        recyclerView.setLayoutManager(new LayoutManager().getGridLayoutManager(this,2));
        category = getIntent().getStringExtra("Category");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                for(DataSnapshot shot:dataSnapshot.getChildren()){
                    if(category==null) {
                        list.add(shot.getValue(Advert.class));
                    }else{


                    }




                }
                recyclerView.setAdapter(new AdsViewRecyclerAdapter(getApplicationContext(),list));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });







    }
}
