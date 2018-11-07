package com.pupr;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class CreateProfile extends AppCompatActivity {
    private static final int RESULT_LOAD_IMAGE = 1; //Allows for image to be returned

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

        imageToUpload.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {
                                                 Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI); //Let user select an image from gallery
                                                 startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
                                             }
                                         });
               // submitProfile.setOnClickListener();
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
