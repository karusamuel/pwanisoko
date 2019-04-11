package com.example.pwanisoko;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pwanisoko.models.AppUser;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfile extends AppCompatActivity {
  boolean allGood;
     String url ="xxxxx";
     TextInputLayout usename;
     TextView phoneNo;
     TextInputLayout description;
     DatabaseReference reference;
     FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        usename = findViewById(R.id.userName);
        phoneNo = findViewById(R.id.phoneNo);
        description = findViewById(R.id.description);
        allGood = test();
        mAuth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getUid());

    }

    public  void update(View view){
        if(test()){
            reference.setValue(new AppUser(usename.getEditText().getText().toString(),url,description.getEditText().getText().toString(),mAuth.getCurrentUser().getPhoneNumber()))
                    .addOnSuccessListener(new OnSuccessListener<Void>() {

                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(getApplicationContext(),"updated",Toast.LENGTH_LONG).show();

                }
            });





        }else {

            Toast.makeText(this,"pleas check out all the error",Toast.LENGTH_LONG).show();
        }


    }
    public  boolean test(){
        if(usename.getEditText().getText().toString().length()<4) {
            usename.setError("User name should be more than four Characters");
            return false;
        }else {
            return true;
        }

        }
}
