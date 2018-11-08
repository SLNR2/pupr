package com.pupr;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class CreateProfile extends AppCompatActivity {
    private static final int RESULT_LOAD_IMAGE = 1; //Allows for image to be returned
    private static final String appName = "pupr"; //name of app, used for a directory name

    private static Long tsLong = System.currentTimeMillis() / 1000; //used to generate a unique file name
    private static String ts = tsLong.toString(); //put the time toString


    ImageView imageToUpload;
    EditText nameToUpload;
    EditText bioToUpload;
    Button submitProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
        imageToUpload = findViewById(R.id.imageToUpload);
        nameToUpload = findViewById(R.id.new_dog_name);
        bioToUpload = findViewById(R.id.new_dog_bio);
        submitProfile = findViewById(R.id.submitDog);
    //ClickListener to let you upload a picture
        imageToUpload.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {
                                                   uploadImage();
                                                   new ImageSaver(v.getContext());

                                             }
                                         });
        submitProfile.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    //Save image
                                    //Set image as an attribute for the user
                                    //Load the Main Page


                                }
        });
    }

    //Two methods that are used for uploading images
    protected void uploadImage() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI); //Let user select an image from gallery
        startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);

    }

    @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) { //check everything is good
                Uri selectedImage = data.getData(); //select image
                imageToUpload.setImageURI(selectedImage); //set image and display it
            }
        }
}
