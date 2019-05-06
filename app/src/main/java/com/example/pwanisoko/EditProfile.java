package com.example.pwanisoko;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pwanisoko.models.AppUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import static com.example.pwanisoko.AddadImage.REQUEST_CODE;
import static com.example.pwanisoko.AddadImage.RESULT_LOAD_IMAGE;

public class EditProfile extends AppCompatActivity {
    ImageView profileImage;
    boolean allGood;
    String url = "xxxxx";
    TextInputLayout username;
    TextView phoneNo;
    TextInputLayout description;
    DatabaseReference reference;
    FirebaseAuth mAuth;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        username = findViewById(R.id.userName);
        phoneNo = findViewById(R.id.phoneNo);
        description = findViewById(R.id.description);
        profileImage = findViewById(R.id.profile_image);

        allGood = test();
        mAuth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getUid());
        storageReference = FirebaseStorage.getInstance().getReference();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot!=null) {
                    AppUser appUser = dataSnapshot.getValue(AppUser.class);
                    if(appUser!=null) {
                        Glide.with(getApplicationContext()).load(appUser.getPhotoUrl()).placeholder(R.drawable.user).dontAnimate().into(profileImage);
                        phoneNo.setText(appUser.getPhoneNumber());
                        description.getEditText().setText(appUser.getDescription());
                        username.getEditText().setText(appUser.getName());

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void update(View view) {


        if (test()) {
            reference.setValue(new AppUser(username.getEditText().getText().toString(), url, description.getEditText().getText().toString(), mAuth.getCurrentUser().getPhoneNumber()))
                    .addOnSuccessListener(new OnSuccessListener<Void>() {

                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getApplicationContext(), "updated", Toast.LENGTH_LONG).show();

                        }
                    });


        } else {

            Toast.makeText(this, "please check out all the error", Toast.LENGTH_LONG).show();
        }


    }

    public boolean test() {
        if (username.getEditText().getText().toString().length() < 4) {
            username.setError("User name should be more than four Characters");
            return false;
        } else {
            return true;
        }

    }

    public void addImage(View view) {

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {

            checkPermission();

        } else {
            loadImage();

        }


    }

    @TargetApi(Build.VERSION_CODES.M)
    private void checkPermission() {
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {

            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE);


        } else {
            loadImage();
        }


    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                loadImage();
            }

        }


    }

    public void loadImage() {

        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {

            Uri selectedImageUri = data.getData();
            profileImage.setImageURI(selectedImageUri);

            setProfilePic(selectedImageUri);

        }

    }

    public boolean setProfilePic(Uri uri) {

        if (mAuth.getCurrentUser() != null) {
            Uri returnUri = uri;
            Cursor returnCursor =
                    getContentResolver().query(returnUri, null, null, null, null);
            /*
             * Get the column indexes of the data in the Cursor,
             * move to the first row in the Cursor, get the data,
             * and display it.
             */
            int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);

            returnCursor.moveToFirst();

            String fileName = returnCursor.getString(nameIndex);
            StorageReference reference1 =storageReference.child("profile_pics").child(fileName);
                    reference1.putFile(uri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(getApplicationContext(), "profile picture updated", Toast.LENGTH_LONG).show();

                        }
                    });

            reference1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    update(uri.toString());
                }
            });



        }

    return true;
    }

    public void update(String url){
        reference.child("photoUrl").setValue(url)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "profile updated", Toast.LENGTH_LONG).show();


                        }
                    }
                });
    }
}
