package com.example.pwanisoko;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class AddadImage extends AppCompatActivity {
    public static int REQUEST_CODE= 100;
    public static int  RESULT_LOAD_IMAGE= 200;
    ImageView imageView;
    Uri uri =  null;
    String title;
    String category;
    String description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addad_image);
        imageView = findViewById(R.id.advert_image);
         title= getIntent().getStringExtra("Title");
         category= getIntent().getStringExtra("Category");
         description= getIntent().getStringExtra("Description");

    }
    public void next(View view){

        if(uri!=null){
        Intent intent = new Intent(this,ConfirmAdd.class);
        intent.putExtra("Title",title);
        intent.putExtra("Category",category);
        intent.putExtra("Description",description);
        intent.putExtra("Image",uri.toString());
        startActivity(intent);
        }else {

            Toast.makeText(this,"please select an image to Continue",Toast.LENGTH_LONG).show();

        }






    }
    public void addImage(View view){

        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M){

            checkPermission();

        }else {
            loadImage();

        }


}

@TargetApi(Build.VERSION_CODES.M)
private void checkPermission(){
    if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){

        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_CODE);


    }else{
        loadImage();
    }



}

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==REQUEST_CODE){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                loadImage();
            }

        }


}

public void loadImage(){

    Intent galleryIntent = new Intent(Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
    galleryIntent.setType("image/*");
    startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
}
public void onActivityResult(int requestCode,int resultCode,Intent data){

        if(requestCode==RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data!=null){

            Uri selectedImageUri = data.getData();
            imageView.setImageURI(selectedImageUri);
            uri = data.getData();






        }

}




}

