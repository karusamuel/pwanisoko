package com.example.pwanisoko;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.OpenableColumns;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pwanisoko.models.Advert;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
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
    ImageView imageView;
    int price;
    String date="";
    String userId;
    String location="kibaoni";
    Button button;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_add);
        FirebaseApp.initializeApp(this);
        priceText = findViewById(R.id.confirmAdPrice);
        descrptionText = findViewById(R.id.confirm_adDescription);
        categoryText = findViewById(R.id.confirm_AdCategory);
        titleText = findViewById(R.id.confirm_adTitle);
        imageView = findViewById(R.id.confirm_imageView);
        progressBar = findViewById(R.id.progressBarConfirmAdd);





        mdb = FirebaseDatabase.getInstance();
        reference = mdb.getReference().child("Adverts");
        userId = FirebaseAuth.getInstance().getUid();
        button = findViewById(R.id.uploadAd);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                post();

            }
        });

        title= getIntent().getStringExtra("Title");
         category= getIntent().getStringExtra("Category");
         description= getIntent().getStringExtra("Description");
         uri=getIntent().getStringExtra("Image");
         price= getIntent().getIntExtra("Price",0);
         date= new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
         uri1= Uri.parse(uri);
         Uri returnUri = uri1;


//         set values to ui
        priceText.setText(Integer.toString(price));
        categoryText.setText(category);
        titleText.setText(title);
        descrptionText.setText(description);
        Glide.with(this).load(uri1).placeholder(R.drawable.chat_icon)
                .into(imageView);















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
    public boolean post(){
         postImage();
        return false;
    }

    private boolean postImage(){

       final StorageReference storageReference = storage.getReference().child("images").child(fileName);
                storageReference.
                putFile(uri1).
                addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Log.e("Url",storageReference.getDownloadUrl().toString());

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();

                    }
                });

                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                            postData(uri.toString());
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
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(),"posted successfully",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressBar.setVisibility(View.GONE);

                Toast.makeText(getApplicationContext(),"Failed Please Try Again Later",Toast.LENGTH_LONG).show();

            }
        });


        return false;
    }

}
