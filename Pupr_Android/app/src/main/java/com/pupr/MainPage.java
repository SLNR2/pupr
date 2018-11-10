package com.pupr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainPage extends AppCompatActivity {
    Button goToVoting;
    Button editProfile;
    Button viewLeaderboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);

    //Add a line here to call a method to generate voting queue

        goToVoting = findViewById(R.id.goToVoting);
        editProfile = findViewById(R.id.editProfile);
        viewLeaderboard = findViewById(R.id.goToLeaderboard);

    //Go to voting
        goToVoting.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Go to voting page
            }
        });

    //Edit profile
        editProfile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Go to edit profile

                Intent editProfile = new Intent(getBaseContext(), EditProfile.class);
                startActivity(editProfile);
            }
        });

    //View leaderboard
        viewLeaderboard.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                //Go to leaderboard page
            }
        });


    }
}
