package com.example.pwanisoko.adapters;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pwanisoko.Ads_Activity;
import com.example.pwanisoko.R;

public class MainActivityRecyclerAdapter extends RecyclerView.Adapter<MainActivityRecyclerAdapter.MyHolder> {
    Context context;
    String[] categoryArray = new String[]{"All","Electronics","Food & Beverages","Books","Stationary","Entertainment",
            "Fashion and Design","Services e.g salon","Others"};
    int[] icons = new int[]{R.drawable.all_makenzi,R.drawable.electronics_makenzi,R.drawable.food_makenzi,R.drawable.books_makenzi,R.drawable.stationery_makenzi,
    R.drawable.entertaintment,R.drawable.makenzi_fashion,R.drawable.services_makenzi,R.drawable.others_makenzi
    };
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
    public void onBindViewHolder(@NonNull MyHolder myHolder, final int i) {
        myHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Ads_Activity.class);
                intent.putExtra("Category",categoryArray[i]);
                context.startActivity(intent);
            }
        });
        myHolder.textView.setText(categoryArray[i]);
        myHolder.category.setImageResource(icons[i]);

    }

    @Override
    public int getItemCount() {
        return 9;
    }

    class MyHolder extends RecyclerView.ViewHolder{
         ConstraintLayout layout;
         TextView textView;
         ImageView category;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.choose_category);
            textView = itemView.findViewById(R.id.Category);
            category = itemView.findViewById(R.id.imageViewCategory);



        }
    }
}
