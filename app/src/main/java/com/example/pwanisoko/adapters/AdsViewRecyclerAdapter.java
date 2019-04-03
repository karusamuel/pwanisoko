package com.example.pwanisoko.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pwanisoko.R;
import com.example.pwanisoko.models.Advert;

import java.util.ArrayList;

public class AdsViewRecyclerAdapter extends RecyclerView.Adapter<AdsViewRecyclerAdapter.MyHolder> {

    private Context context;
    private ArrayList<Advert> list;
    public AdsViewRecyclerAdapter(Context context, ArrayList<Advert> list) {
        this.context = context;
        this.list = list;


    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater =  LayoutInflater.from(context);


        return new MyHolder(inflater.inflate(R.layout.ad_strip,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {

    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        public MyHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
