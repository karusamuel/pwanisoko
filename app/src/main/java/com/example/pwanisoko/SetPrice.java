package com.example.pwanisoko;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class SetPrice extends AppCompatActivity {

    private String title;
    private String category;
    private String description;
    private String image;
    TextInputLayout price;
    BottomNavigationView next;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_price);
        title= getIntent().getStringExtra("Title");
        category= getIntent().getStringExtra("Category");
        description= getIntent().getStringExtra("Description");
        image = getIntent().getStringExtra("Image");
        price = findViewById(R.id.set_price_textInput_layout);
        next = findViewById(R.id.price_next);
        next.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int adPrice =Integer.parseInt(price.getEditText().getText().toString());
                if(adPrice>0){
                    Intent intent = new Intent(getApplicationContext(),ConfirmAdd.class);
                    intent.putExtra("Description",description);
                    intent.putExtra("Title",category);
                    intent.putExtra("Category",title);
                    intent.putExtra("Image",image);
                    intent.putExtra("Price",adPrice);
                    startActivity(intent);


                }else {
                    price.setError("Ad price cannot be empty");
                }


                return false;
            }
        });
    }

    public void next(View view){


    }
}
