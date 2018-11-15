package com.pupr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainPage extends AppCompatActivity {
    Button goToVoting;
    Button editProfile;
    Button viewLeaderboard;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);
        User.activeUser.makeQueue(); //generate the queue every time the User comes to the main page

        goToVoting = findViewById(R.id.goToVoting);
        editProfile = findViewById(R.id.editProfile);
        viewLeaderboard = findViewById(R.id.goToLeaderboard);
        logout = findViewById(R.id.logOut);


    //Go to voting
        goToVoting.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            String endOfQueue = "No more dogs left. Please try again later."; //message to be displayed when the queue is empty
                if(User.activeUser.votingQueue.isEmpty())
                    Toast.makeText(getApplicationContext(), endOfQueue, Toast.LENGTH_LONG).show(); //display a message if queue is empty

            //Otherwise, enter voting screen
                else{
                    Intent vote = new Intent(getBaseContext(), VotingPage.class);
                    startActivity(vote);
                }
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

        //Log out
        logout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                User.activeUser.votingQueue.clear(); //clear the active user's voting queue
                User temp = new User();


                User.setActiveUser(temp);
                Intent loginPage = new Intent(getBaseContext(), LoginPage.class);
                startActivity(loginPage);

            }
        });

    }
}
