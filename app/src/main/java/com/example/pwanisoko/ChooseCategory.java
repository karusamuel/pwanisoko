package com.example.pwanisoko;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.pwanisoko.adapters.CustomList;

public class ChooseCategory extends AppCompatActivity {
    ListView category;

    String[] categoryArray = new String[]{"Electronics","Food & Beverages","Books","Stationary","Entertainment","Fashion and Design","Services e.g salon","Others"};
    int[] images = new int[]{R.drawable.electronics_makenzi,R.drawable.food_makenzi,R.drawable.books_makenzi,R.drawable.stationery_makenzi,R.drawable.entertaintment
    ,R.drawable.makenzi_fashion,R.drawable.services_makenzi,R.drawable.others_makenzi};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_category);
        final String title = getIntent().getStringExtra("Title");

        category = findViewById(R.id.choose_category);
        category.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),AddDescriptionActivity.class);
                intent.putExtra("Title",title);
                intent.putExtra("Category",categoryArray[position]);

                startActivity(intent);

            }
        });

        category.setAdapter(new CustomList(images,categoryArray,this));
    }

}
