package com.example.pwanisoko;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;

public class SellActivity extends AppCompatActivity {

    TextInputLayout adTitleTextInputLayout;
    BottomNavigationView next;
    Boolean moveNext = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);
        adTitleTextInputLayout = findViewById(R.id.ad_title_textInputLayout);
        next = findViewById(R.id.sell_next);

        adTitleTextInputLayout.setCounterEnabled(true);
        adTitleTextInputLayout.setCounterMaxLength(70);
        adTitleTextInputLayout.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(s.toString().length()<5){
                    adTitleTextInputLayout.setError("Ad tittle can not be less than 5 characters");
                    moveNext = false;

                }else {
                    next.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    moveNext = true;
                }

            }
        });


        next.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                String adTitle = adTitleTextInputLayout.getEditText().getText().toString();
                if(adTitle.length()>5 & adTitle.length()<70 & moveNext){
                    Intent intent = new Intent(getApplicationContext(),ChooseCategory.class);
                    intent.putExtra("Title",adTitle);
                    startActivity(intent);


                }else {
                    adTitleTextInputLayout.setError("Title length must >5 and <70");
                }


                return false;
            }
        });


    }
}
