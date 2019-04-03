package com.example.pwanisoko;

import android.database.Cursor;
import android.net.Uri;
import android.provider.OpenableColumns;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ConfirmAdd extends AppCompatActivity {
    FirebaseDatabase mdb;
    DatabaseReference reference;
    FirebaseStorage storage;
    TextView priceText,descrptionText,titleText,categoryText;
    Uri uri1;
    String category;
    String title;
    String fileName;
    String description;
    String uri;
    int price;
    String date="";
    String userId;
    String location="kibaoni";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_add);
        FirebaseApp.initializeApp(this);
        mdb = FirebaseDatabase.getInstance();
        reference = mdb.getReference().child("adverts");

        title= getIntent().getStringExtra("Title");
         category= getIntent().getStringExtra("Category");
         description= getIntent().getStringExtra("Description");
         uri=getIntent().getStringExtra("Image");
         price= getIntent().getIntExtra("price",0);
         date= new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
         uri1= Uri.parse(uri);
         Uri returnUri = uri1;
        Cursor returnCursor =
                getContentResolver().query(returnUri, null, null, null, null);
        /*
         * Get the column indexes of the data in the Cursor,
         * move to the first row in the Cursor, get the data,
         * and display it.
         */
        int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);

        returnCursor.moveToFirst();

        fileName = returnCursor.getString(nameIndex);



        storage = FirebaseStorage.getInstance();
    }
    public boolean post(View view){
         postImage();
        return false;
    }

    private boolean postImage(){

       final StorageReference reference= storage.getReference().child("images").child(fileName);
                reference.
                putFile(uri1).
                addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Log.e("Url",reference.getDownloadUrl().toString());
                        postData(reference.getDownloadUrl().toString());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

        return false;
    }

    private boolean postData(String url){

        String key = reference.push().getKey();
        Advert advert = new Advert(url,title,description,price,userId,date,location,category);
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
