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
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);

        User.activeUser.makeQueue(); //generate the voting queue for the user

        goToVoting = findViewById(R.id.goToVoting);
        editProfile = findViewById(R.id.editProfile);
        viewLeaderboard = findViewById(R.id.goToLeaderboard);
        logout = findViewById(R.id.logOut);

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

        //Log out
        logout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                User temp = new User();
               /* for (int i = 0; i < User.userList.size(); i++) {
                   if (User.userList.get(i).getUserId() == User.activeUser.getUserId())

                        temp = User.userList.get(i).getUser();

                }*/

                User.setActiveUser(temp);
                Intent loginPage = new Intent(getBaseContext(), LoginPage.class);
                startActivity(loginPage);

            }
        });

    }
}
