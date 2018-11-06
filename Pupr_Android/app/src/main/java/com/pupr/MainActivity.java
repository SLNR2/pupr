package com.pupr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import com.opencsv.CSVReader;


public class MainActivity extends AppCompatActivity {
    Button signIn;
    Button signUp;
    Button forgotPass;
    EditText userText;
    EditText passwordText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DefaultUsers.createUsers(); //Right now this will keep generating every time you load the page
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        signIn = findViewById(R.id.signin_button);
        signUp = findViewById(R.id.signup_button);
        forgotPass = findViewById(R.id.forgot_pass_button);
        userText = findViewById(R.id.login_uname);
        passwordText = findViewById(R.id.login_pass);

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
                    // setContentView(R.layout.activity_main_page); //navigate to the Main Page
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

        final EditText registered = findViewById(R.id.good_reg); //here for testing purposes

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
                        String success = newUser.getFirstName() + " is registered!";
                        registered.setText(success); //here for testing purposes
                        setContentView(R.layout.login_screen);
                        Intent createProfile = new Intent(getBaseContext(), CreateProfile.class);
                        startActivity(createProfile);
                    }
                    else
                            Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_LONG).show();
                }
            }

        });


    }
}
