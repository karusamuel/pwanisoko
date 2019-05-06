package com.example.pwanisoko.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pwanisoko.R;

public class CustomList extends ArrayAdapter<String> {
   int[] images;
   String[] text;
   Activity context;

   public CustomList(int[] images,String[] text,Activity context){
       super(context, R.layout.category_strip,text);
       this.images = images;
       this.text = text;
       this.context = context;



   }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.category_strip, parent, false);
        ImageView imageView = rowView.findViewById(R.id.imageView6);
        TextView category = rowView.findViewById(R.id.categoryTextview);
        imageView.setImageResource(images[position]);
        category.setText(text[position]);

        return rowView;
    }


}
