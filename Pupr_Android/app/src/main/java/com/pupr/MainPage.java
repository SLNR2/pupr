package com.pupr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MainPage extends AppCompatActivity {
    EditText box;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        box = findViewById(R.id.testText);

        String welcomeName = getIntent().getStringExtra("value1");
        welcomeName = "Welcome to Pupr, " + welcomeName;


        box.setText(welcomeName); //test method

    }
}
