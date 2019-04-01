package com.example.pwanisoko;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pwanisoko.models.Advert;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class ConfirmAdd extends AppCompatActivity {
    FirebaseDatabase mdb;
    DatabaseReference reference;
    FirebaseStorage storage;
    TextView price,descrption,title,category;
    Uri uri1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_add);
        FirebaseApp.initializeApp(this);
        mdb = FirebaseDatabase.getInstance();
        reference = mdb.getReference().child("adverts");

        String title = getIntent().getStringExtra("Title");
        String category = getIntent().getStringExtra("Category");
        String description = getIntent().getStringExtra("Description");
        String uri =getIntent().getStringExtra("Image");

         uri1= Uri.parse(uri);
         Toast.makeText( this ,uri1.getLastPathSegment(),Toast.LENGTH_LONG).show();


        storage = FirebaseStorage.getInstance();
    }
    public boolean post(View view){
         postImage();
        return false;
    }

    private boolean postImage(){

       final StorageReference reference= storage.getReference().child("adverts");
                reference.
                putFile(uri1).
                addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Log.e("Url",reference.getDownloadUrl().toString());
                        postData();
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
