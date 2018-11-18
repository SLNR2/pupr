package com.pupr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Leaderboard extends AppCompatActivity {
    Button home;
    ImageView first;
    ImageView second;
    ImageView third;

    EditText firstText;
    EditText secondText;
    EditText thirdText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

    //Set pictures
        first = findViewById(R.id.firstPlacePic);
        second = findViewById(R.id.secondPlacePic);
        third = findViewById(R.id.thirdPlacePic);

    //Set Drawables for pictures
        ImageView[] images = new ImageView[] {first, second, third}; //store ImageViews in an array for easy access
        for (int i = 0; i < images.length; i++)
            images[i].setImageDrawable(User.leaderboard.get(i).getPicture()); //update each image


    //Set text
        firstText = findViewById(R.id.firstStats);
        secondText = findViewById(R.id.secondStats);
        thirdText = findViewById(R.id.thirdStats);

        EditText[] stats = new EditText[] {firstText, secondText, thirdText};
        for (int i = 0; i < stats.length; i++) {
            String text = User.leaderboard.get(i).toLeaderboard(i+1);
            stats[i].setText(text);
        }
    //Home button
        home = findViewById(R.id.leaderboardHome);
        home.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent mainPage = new Intent(getBaseContext(), HomePage.class);
                startActivity(mainPage);
            }
        });
    }
}
