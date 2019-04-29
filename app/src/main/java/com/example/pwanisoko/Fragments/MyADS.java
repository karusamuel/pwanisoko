package com.example.pwanisoko.Fragments;

import android.os.Bundle;
import android.os.TestLooperManager;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pwanisoko.Fragments.myADS_Fragments.ADS;
import com.example.pwanisoko.Fragments.myADS_Fragments.Favourites;
import com.example.pwanisoko.Fragments.myADS_Fragments.MyAdsFragmentAdapter;
import com.example.pwanisoko.R;
import com.example.pwanisoko.adapters.AdsViewRecyclerAdapter;
import com.example.pwanisoko.models.Advert;
import com.example.pwanisoko.objects.LayoutManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyADS extends Fragment {
    RecyclerView recyclerView;
    DatabaseReference reference;
    ArrayList<Advert> list;
    String category;
    FirebaseAuth mAuth;
    TextView hide;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);




    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstance){
        View view = inflater.inflate(R.layout.fragment_myads_layout,container,false);
        reference = FirebaseDatabase.getInstance().getReference().child("Adverts");
        recyclerView =  view.findViewById(R.id.ads_display_recyclerView);
        hide = view.findViewById(R.id.hide);
        mAuth = FirebaseAuth.getInstance();
        recyclerView.setLayoutManager(new LayoutManager().getGridLayoutManager(getContext(),2));
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                for(DataSnapshot shot:dataSnapshot.getChildren()){
                    Advert advert = shot.getValue(Advert.class);
                    if(advert.getUserId().equals(mAuth.getUid())){
                       list.add(advert);
                    }



                }
                if (list.size()>0){
                    recyclerView.setAdapter(new AdsViewRecyclerAdapter(getContext(),list));
                    hide.setVisibility(View.GONE);
                }else{
                    hide.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        return view;
    }
}
