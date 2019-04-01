package com.example.pwanisoko.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pwanisoko.Ads_Activity;
import com.example.pwanisoko.R;

public class MainActivityRecyclerAdapter extends RecyclerView.Adapter<MainActivityRecyclerAdapter.MyHolder> {
    Context context;

    public MainActivityRecyclerAdapter(Context context){
      this.context = context;

    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.category_grid_layout,viewGroup,false);
        return new MyHolder(view);
        
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        myHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, Ads_Activity.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return 9;
    }

    class MyHolder extends RecyclerView.ViewHolder{
         ConstraintLayout layout;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.choose_category);



        }
    }
}
