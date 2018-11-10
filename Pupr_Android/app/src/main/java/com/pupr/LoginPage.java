package com.pupr;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;


public class LoginPage extends AppCompatActivity {
    //Declare UI elements
    Button signIn;
    Button signUp;
    Button forgotPass;
    EditText userText;
    EditText passwordText;

//Used for granting permissions
    private int requestCode;
    private int grantResults[];



    @Override
    protected void onCreate(Bundle savedInstanceState){
        DefaultUsers.createUsers(); //Right now this will keep generating every time you load the page
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        signIn = findViewById(R.id.signin_button);
        signUp = findViewById(R.id.signup_button);
        forgotPass = findViewById(R.id.forgot_pass_button);
        userText = findViewById(R.id.login_uname);
        passwordText = findViewById(R.id.login_pass);
        //checkUsers(); //test method

    //Used for permissions
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},requestCode);
        onRequestPermissionsResult(requestCode,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},grantResults);

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
                signUp(); //call method to register
            }
        });

    //Forgot password
        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {Toast.makeText(getApplicationContext(), "Not implemented for this project", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Need to update this to do something actually useful.
    private void checkUsers() {
        InputStream is = getResources().openRawResource(R.raw.users);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

        String line = "";
        try {
            while ((line = reader.readLine()) != null) {

                //split by ','
                String[] tokens = line.split(",");
                //read the data

                User user = new User();
                user.setFirstName(tokens[0]);
                user.setLastName(tokens[1]);
                user.setUsername(tokens[2]);
                user.setPassword(tokens[3]);
                userText.setText(tokens[0]);

                Log.d("MyActivity", "Just created: " + user);

            }
        } catch (IOException e) {
            Log.wtf("MyActivity", "Error reading data file on line " + line, e);
            e.printStackTrace();
        }
    }

    protected void signIn() {

                boolean flag = false;
                User currUser = new User(); //Declare a User to be later stored as the current user
                for (int i = 0; i < User.userList.size(); i++) {
                    User thisUser = User.userList.get(i); //thisUser is used as a pointer to traverse the list
                    String user = thisUser.getUsername().toLowerCase(); //pull the username
                    String pass = thisUser.getPassword().toLowerCase(); //pull the password
                    //Compare username and password of the given user to what you have typed in the boxes
                    if (userText.getText().toString().toLowerCase().equals(user) && passwordText.getText().toString().toLowerCase().equals(pass)) {
                        flag = true;
                        currUser = thisUser;
                    }

                }

                if (flag) {
                    Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();
                    User.setUser(currUser); //sets active User
                    Intent mainPage = new Intent(getBaseContext(), MainPage.class);
                    mainPage.putExtra("value1", currUser.getFirstName());
                    startActivity(mainPage);
                } else
                    Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();
            }

    protected void signUp() {
        //Define the EditText boxes and the Register button
        setContentView(R.layout.create_account);
        final EditText uname = findViewById(R.id.register_uname);
        final EditText fname = findViewById(R.id.register_fname);
        final EditText lname = findViewById(R.id.register_lname);
        final EditText password = findViewById(R.id.register_pass);
        final EditText confPass = findViewById(R.id.register_conf_pass);
        Button register = findViewById(R.id.register_button);
       final User newUser = new User(); //Just putting this line here to test


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            boolean flag = false; //false means that the user does not yet exist
                for (int i = 0; i < User.userList.size(); i++) {
                    User thisUser = User.userList.get(i);
                    String user = thisUser.getUsername().toLowerCase(); //pull the username

                    if(uname.getText().toString().toLowerCase().equals(user)) {
                        Toast.makeText(getApplicationContext(), "This username is already taken", Toast.LENGTH_LONG).show();

                        flag = true;
                    }
            }
                if (!flag) {
                    if (password.getText().toString().equals(confPass.getText().toString())){
                        newUser.setFirstName(fname.getText().toString());
                        newUser.setLastName(lname.getText().toString());
                        newUser.setUsername(uname.getText().toString());
                        newUser.setPassword(uname.getText().toString());
                        newUser.addUser(); //Adds User to the userList
                        User.activeUser = newUser; //sets the new user to the active user

                        Intent editProfile = new Intent(getBaseContext(), EditProfile.class);
                        startActivity(editProfile);
                    }
                    else
                            Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_LONG).show();
                }
            }

        });


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

        Log.d("permission","granted");
        } else {

        // permission denied, boo! Disable the
        // functionality that depends on this permission.uujm
        Toast.makeText(LoginPage.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();

        //app cannot function without this permission for now so close it...
        onDestroy();
        }
        return;
        }

        // other 'case' line to check fosr other
        // permissions this app might request
        }
        }
}