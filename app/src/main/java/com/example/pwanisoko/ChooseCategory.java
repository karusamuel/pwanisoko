package com.example.pwanisoko;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ChooseCategory extends AppCompatActivity {
    ListView category;

    String[] categoryArray = new String[]{"sam","sam","sam","sam","sam","sam","sam","sam",};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_category);
        final String title = getIntent().getStringExtra("Title");

        category = findViewById(R.id.choose_category);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.category_strip,R.id.categoryTextview,categoryArray);
        category.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),AddDescriptionActivity.class);
                intent.putExtra("Title",title);
                intent.putExtra("Category",categoryArray);

                startActivity(intent);

            }
        });

        category.setAdapter(adapter);
    }

}
