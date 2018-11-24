package com.pupr;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class LoginPage extends AppCompatActivity {
    //Declare UI elements
    Button signIn;
    Button signUp;
    EditText userText;
    EditText passwordText;

    //Used for granting permissions -- do not delete
    private int requestCode;
    private int grantResults[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        //Used for permissions
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, requestCode);
        onRequestPermissionsResult(requestCode, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, grantResults);

        //Assign buttons
        signIn = findViewById(R.id.signin_button);
        signUp = findViewById(R.id.signup_button);
        userText = findViewById(R.id.login_uname);
        passwordText = findViewById(R.id.login_pass);

        File users = new File(Environment.getExternalStorageDirectory(), "pupr/users.csv");
        if(!users.exists()) //app has not been run yet
            createDefaultUsers();
        else if(User.userList.size() == 0) //app has been run before but has just been launched
            UserSaver.loadUsers();

        UserSaver.saveUsers(); //Save users



        //Sign in
        signIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        //Sign up
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(getBaseContext(), SignUp.class);
                register.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(register);
            }
        });
    }

    protected void signIn() {

        boolean flag = false;
        User currUser = new User(); //Declare a User to be later stored as the current user
        for (int i = 0; i < User.userList.size(); i++) {
            User iUser = User.userList.get(i); //thisUser is used as a pointer to traverse the list
            String user = iUser.getUsername().toLowerCase(); //pull the username
            String pass = iUser.getPassword().toLowerCase(); //pull the password
            //Compare username and password of the given user to what you have typed in the boxes
            if (userText.getText().toString().toLowerCase().equals(user) && passwordText.getText().toString().toLowerCase().equals(pass)) {
                flag = true;
                currUser = iUser;
            }

        }

        if (flag) {
            Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();
            User.setActiveUser(currUser); //sets active User
            Intent home = new Intent(getBaseContext(), HomePage.class);
            home.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(home);
        } else
            Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();
    }



    //Request permissions
    @Override // android recommended class to handle permissions
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Log.d("permission", "granted");
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.uujm
                    Toast.makeText(LoginPage.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();

                    //app cannot function without this permission for now so close it...
                    onDestroy();
                }
            }
        }
    }

    //Method to read CSV raw file and create default users from it
    private void createDefaultUsers() {
        InputStream is = getResources().openRawResource(R.raw.users); //read the raw CSV file
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8"))); //reader for the CSV file
        String line = ""; //used to iterate the CSV file
        String imagePath; //will be used to locate the drawable file that I put into the project folder
        View v = new View(getBaseContext()); //need this View for saving the Bitmap
        int i = 0; //will be used to increment by userId
        try {

            //Start reading the file
            while ((line = reader.readLine()) != null) { //read until the end
                //Add users
                String[] tokens = line.split("@@");  //split by ',' since this is a CSV file
                User newUser = new User(tokens[0], tokens[1], tokens[2], tokens[3]); //reads the data and saves the information as a defaultpicture user
                newUser.setDogName(tokens[4]); //set dog name
                newUser.setBio((tokens[5])); //set dog bio
                User.userList.add(newUser);
                Log.d("MyActivity", "Just created: " + newUser.getUserId() + ", " + newUser.getDogName()); //puts userId into the log so we can make sure this method is just called one time
                newUser.setDefaultFalse();

                //Add dogs
                imagePath = "drawable/img" + i;
                int imageKey = getResources().getIdentifier(imagePath, "drawable", "com.pupr"); //generate a key for each image corresponding to each user
                Drawable d = getResources().getDrawable(imageKey); //turn image into a drawable
                Bitmap b0 = ((BitmapDrawable) d).getBitmap(); //get Bitmap for drawable
                ImageSaver.rescale(b0);
                Uri u0 = ImageSaver.getImageUri(getApplicationContext(), b0, i); //convert b0 into a uri
                try {
                    b0 = ImageSaver.getCorrectlyOrientedImage(getApplicationContext(), u0); //properly orient and reformat b0

                    new ImageSaver(v.getContext()).setExternal(true).setDirectoryName("").setFileName("img" + i + ".png").save(b0); //save Bitmap to device
                    UserSaver.loadPictures(i); //load properly formatted image from the newly saved bitmap
                } catch (IOException e) {
                    e.printStackTrace();
                }
                i++; //increment to next user

            }

        } catch (IOException e) {
            Log.wtf("MyActivity", "Error reading data file on line " + line, e);
            e.printStackTrace();
        }
    }
    //close app if the user hits the back button
    @Override
    public void onBackPressed() {
        UserSaver.saveUsers();
        finish();
    }
}