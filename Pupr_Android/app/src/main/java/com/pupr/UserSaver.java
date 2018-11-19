

package com.pupr;

import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;



class UserSaver {
     static void saveUsers() throws IOException {
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


    static void loadUsers() {}


}
