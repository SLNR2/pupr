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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.opencsv.CSVWriter;


public class EditProfile extends AppCompatActivity {
    private static final int RESULT_LOAD_IMAGE = 1; //Allows for image to be returned

    ImageView imageToUpload;
    EditText nameToUpload;
    EditText bioToUpload;
    Button submitProfile;
    Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);
        imageToUpload = findViewById(R.id.imageToUpload);
        nameToUpload = findViewById(R.id.new_dog_name);
        bioToUpload = findViewById(R.id.new_dog_bio);
        submitProfile = findViewById(R.id.submitDog);
        cancel = findViewById(R.id.cancelProfileChanges);

        //Path information for a default picture
        String imagePath = "drawable/defaultpicture"; //path for default picture, the P part of the pupr logo
        int imageKey = getResources().getIdentifier(imagePath, "drawable", "com.pupr"); //imageKey for the default pic
        Drawable defaultPicture = getResources().getDrawable(imageKey); //turn image into a drawable
        final Bitmap defaultBit = ((BitmapDrawable)defaultPicture).getBitmap(); //default image as a bitmap

    //Load current information and picture for the user
        nameToUpload.setText(User.activeUser.getDogName());
        bioToUpload.setText(User.activeUser.getBio());
        Drawable userPic = User.activeUser.getPicture();
        imageToUpload.setImageDrawable(userPic);

        //ClickListener for Cancel button
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap currentBit = ((BitmapDrawable)imageToUpload.getDrawable()).getBitmap(); //current picture as a Bitmap
                if (currentBit.equals(defaultBit)){
                    Toast.makeText(EditProfile.this, "You must upload a picture of your dog before proceeding", Toast.LENGTH_LONG).show();
                    Log.d("Image", "equals default, could not cancel");
                }
                else {
                    UserSaver.saveUsers("pupr/users.csv"); //Save users
                    Intent mainPage = new Intent(getBaseContext(), HomePage.class);
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
                                    Bitmap currentBit = ((BitmapDrawable)imageToUpload.getDrawable()).getBitmap(); //current picture as a Bitmap
                                    Bitmap userBit = ((BitmapDrawable)User.activeUser.getPicture()).getBitmap();

                                    if(currentBit.equals(defaultBit)){ //default picture was not changed
                                        Toast.makeText(EditProfile.this, "You must upload a picture of your dog before proceeding", Toast.LENGTH_LONG).show();
                                        Log.d("Image", "equals default, could not complete profile submission");
                                    }

                                    else { //user not using default picture
                                        Drawable newPic = imageToUpload.getDrawable(); //set pic on ImageView
                                        savePicture(userBit, newPic, v); //save pic to phone if a new picture was uploaded

                                        User.activeUser.setPic(newPic); //Set image as an attribute for the user
                                        User.activeUser.setBio(bioToUpload.getText().toString()); //set bio
                                        User.activeUser.setDogName(nameToUpload.getText().toString()); //set name

                                //If user has uploaded an image for the first time, add to userList
                                    boolean alreadyAdded = false;
                                    for (int i = 0; i < User.userList.size(); i++) {
                                        if (User.userList.get(i).equals(User.activeUser))
                                            alreadyAdded = true; //hit found
                                    }

                                    Log.d("Userlist", "Before adding");
                                    User.printUserList(); //print out the userList before adding

                                    if (!alreadyAdded) //no hit, add user
                                        User.userList.add(User.activeUser); //add this user to a list of all of the users

                                    Log.d("Userlist", "After adding");
                                    User.printUserList();
                                            UserSaver.saveUsers("pupr/users.csv"); //Save users


                                        //Load the Main Page
                                        Intent mainPage = new Intent(getBaseContext(), HomePage.class);
                                        startActivity(mainPage);
                                    }

                                }
        });
    }

    public static void savePicture(Bitmap oldPic, Drawable newPic, View v) {
        Bitmap bmap = ((BitmapDrawable)newPic).getBitmap();
        if (!bmap.equals(oldPic)) {
            new ImageSaver(/*v.getContext()*/).setExternal(true).setDirectoryName("").setFileName("img" + User.activeUser.getUserId() + ".png").save(bmap); //saves the image in /pupr on the internal storage of the android device
            Log.d("Image", "saved");
        }
        else
            Log.d("Image", "same image, not saved");
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
//Disable user from hitting back button
    @Override
    public void onBackPressed() {}
}
