package com.pupr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

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
        first.setImageDrawable(User.leaderboard.get(0).getPicture());
        second.setImageDrawable(User.leaderboard.get(1).getPicture());
        third.setImageDrawable(User.leaderboard.get(2).getPicture());

    //Set text
        firstText = findViewById(R.id.firstPlaceText);
        secondText = findViewById(R.id.secondPlaceText);
        thirdText = findViewById(R.id.thirdPlaceText);

    //Store top 3 dogs in User variables for easy access to their attributes
        User firstDog = User.leaderboard.get(0);
        User secondDog = User.leaderboard.get(1);
        User thirdDog = User.leaderboard.get(2);

    //Messages to display next to the pictures
        String firstPlace = firstDog.toString(1);
        String secondPlace = secondDog.toString(2);
        String thirdPlace = thirdDog.toString(3);

    //Make the text appear
        firstText.setText(firstPlace);
        secondText.setText(secondPlace);
        thirdText.setText(thirdPlace);

    //Home button
        home = findViewById(R.id.leaderboardHome);
        home.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent mainPage = new Intent(getBaseContext(), MainPage.class);
                startActivity(mainPage);
            }
        });
    }
}
