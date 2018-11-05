package com.pupr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;


public class MainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        User user0 = new User ("Sloan", "Lipman", "slipman", "pupr123");
        String testUserText = "User " + user0.getFirstName() + " " + user0.getLastName() + " has the username " + user0.getUsername();
        EditText box = findViewById(R.id.testText);
        box.setText(testUserText);
    }
}
