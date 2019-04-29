package com.example.pwanisoko;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pwanisoko.models.Advert;

public class FullAdView extends AppCompatActivity {

    FloatingActionButton button;
    Button profileButton;
    Intent intent;
    ImageView imageView;
    TextView adTitle,adCategory,adDescription,adPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_ad_view);

        button = findViewById(R.id.uploadAd);
        imageView = findViewById(R.id.confirm_imageView);
        adTitle = findViewById(R.id.confirm_adTitle);
        adPrice = findViewById(R.id.adprice);
        adCategory = findViewById(R.id.confirm_AdCategory);
        adDescription = findViewById(R.id.confirm_adDescription);
        profileButton = findViewById(R.id.viewProfile);


        Bundle bundle = getIntent().getBundleExtra("Ad");
        final Advert advert =  (Advert) bundle.getSerializable("Ad");

        Glide.with(this).load(advert.getUrl()).placeholder(R.drawable.chat_icon).into(imageView);
        adTitle.setText(advert.getaTitle());
        adCategory.setText(advert.getAdCategory());
        adDescription.setText(advert.getAdCategory());
        adPrice.setText(Integer.toString(advert.getAdPrice()));

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),ViewProfile.class);
                intent.putExtra("AdOwner",advert.getUserId());

            startActivity(intent);



            }
        });













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
