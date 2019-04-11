package com.example.pwanisoko;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pwanisoko.models.Advert;

public class FullAdView extends AppCompatActivity {

    Button button;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_ad_view);

        button = findViewById(R.id.uploadAd);


        Bundle bundle = getIntent().getBundleExtra("Ad");
        Advert advert =  (Advert) bundle.getSerializable("Ad");
        intent=new Intent(this,ChatActivity.class);
        intent.putExtra("ReceiverID",advert.getUserId());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(intent);
            }
        });
    }


}
