package com.pupr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class VotingPage extends AppCompatActivity {

    ImageView votingImage;
    EditText votingName;
    EditText votingBio;
    Button one;
    Button two;
    Button three;
    Button four;
    Button five;
    Button home;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting_page);


        votingImage = findViewById(R.id.votingImage);
        votingName = findViewById(R.id.votingName);
        votingBio = findViewById(R.id.votingBio);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        home = findViewById(R.id.votingHome);

        serveDog(); //show first dog

        home.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent mainPage = new Intent(getBaseContext(), MainPage.class);
                startActivity(mainPage);
            }
        });
        one.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                User.nextDog.incrementRatings();
                User.activeUser.votedOn.add(User.nextDog);

                //Debugging log
                for (int i = 0; i < User.activeUser.votedOn.size(); i++)
                    Log.d("Dogs voted on", "" + User.activeUser.votedOn.get(i).getUserId());

                User.nextDog.setScore(1); //add one to the score
                Log.d("Score set to", "" + User.nextDog.getScore());

                Log.d("Front of Q before vote:", "" + User.activeUser.votingQueue.element().getDogName());
                User.activeUser.votingQueue.remove(); //remove dog from queue
                if(!User.activeUser.votingQueue.isEmpty())
                    Log.d("Front of Q after vote:", "" + User.activeUser.votingQueue.element().getDogName());
               serveDog();

            }
        });
    }




//Serve up next dog from queue
    public void serveDog() {
        String endOfQueue = "No more dogs left. Please try again later. Redirecting home."; //message to be displayed when the queue is empty

        if (!User.activeUser.votingQueue.isEmpty()) {
            User.nextDog = User.activeUser.votingQueue.peek(); //Looks at the first element in the votingQueue for the activeUser
            votingImage.setImageDrawable(User.nextDog.getPicture()); //Loads picture
            votingName.setText(User.nextDog.getDogName()); //load the dog's name
            votingBio.setText(User.nextDog.getBio()); //load the bio
        }

        else {
        //Redirect back to main page
            Toast.makeText(getApplicationContext(), endOfQueue, Toast.LENGTH_LONG).show();
            Intent mainPage = new Intent (getBaseContext(), MainPage.class);
            startActivity(mainPage);

        }
    }

}


