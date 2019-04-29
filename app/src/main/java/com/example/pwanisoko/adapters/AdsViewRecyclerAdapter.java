package com.example.pwanisoko.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pwanisoko.FullAdView;
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
    public void onBindViewHolder(@NonNull MyHolder myHolder, final int position) {

         myHolder.adTitle.setText(list.get(position).getaTitle());
         myHolder.adPrice.setText(Integer.toString(list.get(position).getAdPrice()));
         Glide.with(context.getApplicationContext()).load(list.get(position).getUrl()).placeholder(R.drawable.chat_icon).into(myHolder.adImage);



        myHolder.ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FullAdView.class);
                Bundle bundle =  new Bundle();
                bundle.putSerializable("Ad",list.get(position));
                intent.putExtra("Ad",bundle);
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        CardView ad;
        ImageView adImage;
        TextView adTitle,adPrice;
        public MyHolder(@NonNull View itemView) {
            super(itemView);

            ad = itemView.findViewById(R.id.ad);
            adImage = itemView.findViewById(R.id.adImage);
            adTitle = itemView.findViewById(R.id.adTitle);
            adPrice = itemView.findViewById(R.id.adPrice);



        }
    }
}
