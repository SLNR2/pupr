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


public class EditProfile extends AppCompatActivity {
    private static final int RESULT_LOAD_IMAGE = 1; //Allows for image to be returned

    ImageView imageToUpload;
    EditText nameToUpload;
    EditText bioToUpload;
    Button submitProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);
        imageToUpload = findViewById(R.id.imageToUpload);
        nameToUpload = findViewById(R.id.new_dog_name);
        bioToUpload = findViewById(R.id.new_dog_bio);
        submitProfile = findViewById(R.id.submitDog);
        //ClickListener to let you upload a picture

        //If user has already uploaded profile information, it should get loaded here

        nameToUpload.setText(User.activeUser.getDogName());
        bioToUpload.setText(User.activeUser.getBio());
        Drawable userPic = User.activeUser.getPicture();
        imageToUpload.setImageDrawable(userPic); //loads the dog's picture into the ImageView




        imageToUpload.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {
                                                   uploadImage();



                                             }
                                         });
        submitProfile.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {

                                //Save image
                                    Bitmap bmap = ((BitmapDrawable)imageToUpload.getDrawable()).getBitmap();
                                    Drawable newPic = imageToUpload.getDrawable();
                                    new ImageSaver(v.getContext()).setExternal(true).setDirectoryName("pupr_pictures").setFileName(User.activeUser.getUserId() + ".png").save(bmap); //saves the image in /Pictures/pupr on the internal storage of the android device
                                //Set image as an attribute for the user
                                        //User.activeUser.setPic(new ImageSaver(v.getContext()).setExternal(true).setDirectoryName("pupr_pictures").setFileName(User.activeUser.getUserId() + ".png").load()); //UNTESTED
                                        User.activeUser.setPic(newPic); //trying this a different way than the line above
                                        User.activeUser.setBio(bioToUpload.getText().toString());
                                        User.activeUser.setDogName(nameToUpload.getText().toString());
                                //Load the Main Page
                                    Intent mainPage = new Intent(getBaseContext(), MainPage.class);
                                    mainPage.putExtra("value1", User.activeUser.getFirstName());
                                    startActivity(mainPage);

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
