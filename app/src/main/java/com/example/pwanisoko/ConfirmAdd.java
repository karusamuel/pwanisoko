package com.example.pwanisoko;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.pwanisoko.models.Advert;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

public class ConfirmAdd extends AppCompatActivity {
    FirebaseDatabase mdb;
    DatabaseReference reference;
    FirebaseStorage storage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_add);
        FirebaseApp.initializeApp(this);
        mdb = FirebaseDatabase.getInstance();
        reference = mdb.getReference().child("adverts");

        storage = FirebaseStorage.getInstance();
    }
    public boolean post(View view){
      postData();
        return false;
    }

    private boolean postImage(){

        storage.getReference().child("adverts").
                putFile(null).
                addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

        return false;
    }

    private boolean postData(){

        String key = reference.push().getKey();
        Advert advert = new Advert();
        advert.setUrl("xxx");
        advert.setAdCategory("yyy");
        advert.setAdDescription("zxzxzxzx");
        advert.setAdPrice(100000);
        advert.setAdCategory("food");
        advert.setAdlocation("kibaoni");


        reference.child(key).setValue(advert)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(),"posted succesfully",Toast.LENGTH_LONG).show();
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_LONG).show();

            }
        });


        return false;
    }

}
