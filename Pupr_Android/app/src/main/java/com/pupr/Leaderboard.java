package com.pupr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import java.util.Collections;

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

        Log.d("Leaderboard", "Before swap");
        for (int i = 0; i < User.leaderboard.size(); i++)
            Log.d("Leaderboard", printLeaderboardText(i));
        updateLeaderboard();

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
            stats[i].setText(printLeaderboardText(i));
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

//String method that takes an integer argument, used to print out leaderboard information
    private String printLeaderboardText(int i) {
            User curr = User.leaderboard.get(i);
            String average = String.format(java.util.Locale.US, "%.1f", curr.getAverage()); //round average score to 1 decimal
            return(
                    "Ranking # " + (i+1) + "\nName: " + curr.getDogName() + "\nOwner: " + curr.getFirstName() + " " + curr.getLastName() + "\n" +
                    "Average Score: " + average + "\n" +
                    "Total Score: " + (int) curr.getScore() + "\n" +
                    "Total Votes: " + curr.getRatings());
    }

//Bubble sort method to sort the leaderboard from biggest to smallest
    private void updateLeaderboard() {
        for (int i = 0; i < User.userList.size(); i++) {
            Log.d("Leaderboard", "i = " + i);
            Log.d("Leaderboard", "Size of userList is " + User.userList.size());
            for (int j = User.userList.size() - 1; j > i; j--) {
                Log.d("Leaderboard", "j = " + j);
                User current = User.leaderboard.get(j);
                User previous = User.leaderboard.get(j - 1);

            //define some local variables for commonly used comparisons
                double currAvg = current.getAverage();
                double prevAvg = previous.getAverage();
                double currTot = current.getScore();
                double prevTot = previous.getScore();
                int currVotes = current.getRatings();
                int prevVotes = previous.getRatings();

                if (currAvg > prevAvg)
                    Collections.swap(User.leaderboard, j, j - 1);
                else if (currAvg == prevAvg) {
                    if (currTot > prevTot)
                        Collections.swap(User.leaderboard, j, j - 1);
                    else if (currTot == prevTot) {
                        if (currVotes > prevVotes)
                            Collections.swap(User.leaderboard, j, j  -1);
                    }
                }
                Log.d("Leaderboard", previous.getDogName() + " now at " + User.leaderboard.indexOf(previous));
                Log.d("Leaderboard", current.getDogName() + " now at " + User.leaderboard.indexOf(current));

            }
        }
        //Print whole leaderboard to check results
        Log.d("Leaderboard", "After swap");
        for (int i = 0; i < User.leaderboard.size(); i++)
            Log.d("Leaderboard", printLeaderboardText(i));
    }
}
