package com.pupr;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class EditProfile extends AppCompatActivity {
    private static final int RESULT_LOAD_IMAGE = 1; //Allows for image to be returned

    ImageView imageToUpload;
    EditText nameToUpload;
    EditText bioToUpload;
    Button submitProfile;
    Button cancel;
    static Bitmap imageCheck; //A variable that stores user's current picture to see if it has been changed

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);
        imageToUpload = findViewById(R.id.imageToUpload);
        nameToUpload = findViewById(R.id.new_dog_name);
        bioToUpload = findViewById(R.id.new_dog_bio);
        submitProfile = findViewById(R.id.submitDog);
        cancel = findViewById(R.id.cancelProfileChanges);
        imageCheck = ((BitmapDrawable)User.activeUser.getPicture()).getBitmap(); //Serves as a flag to see if the User has uploaded a new image

    //Load current information and picture for the user
        nameToUpload.setText(User.activeUser.getDogName());
        bioToUpload.setText(User.activeUser.getBio());
        Drawable userPic = User.activeUser.getPicture();
        imageToUpload.setImageDrawable(userPic); //loads the dog's picture into the ImageView

    //ClickListener for Cancel button
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imageToUpload.getDrawable() == null)
                    Toast.makeText(EditProfile.this, "You must upload a picture of your dog before proceeding", Toast.LENGTH_LONG).show();
                else {
                    Intent mainPage = new Intent(getBaseContext(), MainPage.class);
                    startActivity(mainPage);
                }
            }
        });
        //ClickListener to let you upload a picture
        imageToUpload.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {
                                                   uploadImage();



                                             }
                                         });
        submitProfile.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    if(imageToUpload.getDrawable() == null)
                                        Toast.makeText(EditProfile.this, "You must upload a picture of your dog before proceeding", Toast.LENGTH_LONG).show();
                                    else {

                                        Drawable newPic = imageToUpload.getDrawable(); //set pic on ImageView
                                        savePicture(newPic, v); //save pic to phone

                                        User.activeUser.setPic(newPic); //Set image as an attribute for the user
                                        User.activeUser.setBio(bioToUpload.getText().toString()); //set bio
                                        User.activeUser.setDogName(nameToUpload.getText().toString()); //set name

                                    //Load the Main Page
                                        Intent mainPage = new Intent(getBaseContext(), MainPage.class);
                                        startActivity(mainPage);
                                    }

                                }
        });
    }

    public static void savePicture(Drawable newPic, View v) {
        Bitmap bmap = ((BitmapDrawable) newPic).getBitmap();
        if (bmap != imageCheck)
            new ImageSaver(v.getContext()).setExternal(true).setDirectoryName("pupr_pictures").setFileName("img" + User.activeUser.getUserId() + ".png").save(bmap); //saves the image in /Pictures/pupr on the internal storage of the android device
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
