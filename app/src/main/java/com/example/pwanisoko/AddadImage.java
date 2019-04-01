package com.example.pwanisoko;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AddadImage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addad_image);
    }
    public void next(View view){

        startActivity(new Intent(this,ConfirmAdd.class));



    }
}
