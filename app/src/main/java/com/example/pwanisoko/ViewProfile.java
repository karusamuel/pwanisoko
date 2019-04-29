package com.example.pwanisoko;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pwanisoko.models.AppUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewProfile extends AppCompatActivity {
    ImageView profileImage;
    boolean allGood;
    String url = "xxxxx";
    TextInputLayout username;
    TextView phoneNo;
    TextInputLayout description;
    DatabaseReference reference;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        username = findViewById(R.id.userName);
        phoneNo = findViewById(R.id.phoneNo);
        description = findViewById(R.id.description);
        profileImage = findViewById(R.id.profile_image);
        mAuth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference().child("Users").child(getIntent().getStringExtra("AdOwner"));
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               AppUser user = dataSnapshot.getValue(AppUser.class);
                username.getEditText().setText(user.getName());
                phoneNo.setText(user.getPhoneNumber());
                Glide.with(getApplicationContext()).load(user.getPhotoUrl()).dontAnimate().placeholder(R.drawable.user).into(profileImage);
                description.getEditText().setText(user.getDescription());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
