

package com.pupr;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;


class UserSaver {


     static void saveUsers() {
        File file = new File(Environment.getExternalStorageDirectory(), "pupr/users.csv");

        BufferedWriter bw;
        FileWriter fw;

        try {
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            String fname;
            String lname;
            String username;
            String password;
            String dogName;
            String bio;
            String totalScore;
            String numberOfRatings;
            String averageRating;
            String votes = "";
            for (int i = 0; i < User.userList.size(); i++ ){
                fname = User.userList.get(i).getFirstName();
                lname = User.userList.get(i).getLastName();
                username = User.userList.get(i).getUsername();
                password = User.userList.get(i).getPassword();
                dogName = User.userList.get(i).getDogName();
                bio = User.userList.get(i).getBio();
                totalScore = "" + User.userList.get(i).getScore();
                numberOfRatings = "" + User.userList.get(i).getRatings();
                averageRating = "" + User.userList.get(i).getAverage();

                for (int j = 0; j < User.userList.get(i).votedOn.size(); j++){
                   votes = votes + User.userList.get(i).votedOn.get(j).getUserId() + ",";
                }


                votes = votes.substring(0, votes.length() - 1); //remove last comma
                bw.write(fname + ',' + lname + "," + username + "," + password + "," +  totalScore + "," + numberOfRatings + "," + averageRating + "," + dogName + "," + bio + "," + votes);
                bw.newLine();
                votes = "";
            }
            bw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    static void loadUsers(){
         File file = new File(Environment.getExternalStorageDirectory(), "pupr/users.csv");
         FileReader fr;
         BufferedReader reader = null;
         String line ="";

         try {
             fr = new FileReader(file);
             reader = new BufferedReader(fr);
             while((line = reader.readLine()) != null) {

             //Read the file, set the corresponding field to a User attribute
                 String[] tokens = line.split(",");  //split by ',' since this is a CSV file

                 User newUser = new User(tokens[0], tokens[1], tokens[2], tokens[3]); //reads the data and saves the information as a defaultpicture user
                 User.userList.add(newUser); //add user to userList
             //Set more attributes
                 newUser.setTotalScore(Double.parseDouble(tokens[4]));
                 newUser.setRatings(Integer.parseInt(tokens[5]));
                 newUser.setAverage(Double.parseDouble(tokens[6]));
                 newUser.setDogName(tokens[7]); //set dog name
                 newUser.setBio((tokens[8])); //set dog bio



            //Add code here to retrieve the user's picture

             }
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }
    }

    static void loadVotes() throws IOException {
        File file = new File(Environment.getExternalStorageDirectory(), "pupr/users.csv");
        FileReader fr;
        BufferedReader reader = null;
        String line = "";

        try {
            fr = new FileReader(file);
            reader = new BufferedReader(fr);
            while ((line = reader.readLine()) != null) {

                //Read the file, set the corresponding field to a User attribute
                String[] tokens = line.split(",");  //split by ',' since this is a CSV file

                //Set the user's votedOn ArrayList
                for (int userId = 0; userId < User.userList.size(); userId++) { //trace all users
                    for (int i = 9; i < tokens.length; i++) { //start at 9 because 9 has the first vote
                        int vote = Integer.parseInt(tokens[i]); //returns the userId for the vote
                        User voted = User.userList.get(vote); //finds the User for the userId
                        User.userList.get(userId).votedOn.add(voted); //adds the vote to the list
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
