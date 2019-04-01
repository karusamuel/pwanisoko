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

public class AddDescriptionActivity extends AppCompatActivity {

    TextInputLayout adDescription;
    BottomNavigationView next;
    boolean moveNext = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_description);

        adDescription = findViewById(R.id.addDescription);
        next = findViewById(R.id.description_next);
        final String title = getIntent().getStringExtra("Title");
        final String category = getIntent().getStringExtra("Category");


        adDescription.setCounterEnabled(true);
        adDescription.setCounterMaxLength(4036);
        adDescription.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(s.toString().length()<20){
                    adDescription.setError("Ad Description can not be less than 5 characters");
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
                String addDescription = adDescription.getEditText().getText().toString();
                if(addDescription.length()>20 & addDescription.length()<4036 & moveNext){
                    Intent intent = new Intent(getApplicationContext(),AddadImage.class);
                    intent.putExtra("Description",addDescription);
                    intent.putExtra("Title",category);
                    intent.putExtra("Category",title);
                    startActivity(intent);


                }else {
                    adDescription.setError("Title length must >20 and <4036");
                }


                return false;
            }
        });

    }
}
