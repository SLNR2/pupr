package com.pupr;
/*
This program will define the User class.


*/

import android.graphics.drawable.Drawable;
import android.util.Log;

import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class User {
    private int userId;
    private String firstName = "";
    private String lastName = "";
    private String username = "";
    private String password = "";


    private String dogName = "";
    private String bio = "";
    private int totalScore = 0;
    private int numberOfRatings = 0;
    private double averageRating = 0;
    private int ranking; //Might not need ranking? Can just return its position on leaderboard?
    private Drawable pic;

    static User activeUser; // defines current user of the session
    static User nextDog; //defines the next Dog for a User to vote on



    public String getDogName() {return this.dogName;}
    public String getBio() {return this.bio;}
    public Drawable getPicture()  {return this.pic;}
    public int getScore()  {return this.totalScore;}
    public int getRatings() {return this.numberOfRatings;}
    public double getAverage() {return this.averageRating;}


    public static void setActiveUser(User user) {
        activeUser = user;} //update the current user of the session
    //redundant if activeUser is a public variable, but we can keep this for now

    public void setUser(int i){
        userList.get(i);
    }



    //Setter methods
    void setDogName(String newDogName) {this.dogName = newDogName;}
    void setBio(String newBio) {this.bio = newBio;}
    void setPic(Drawable newPic) {this.pic = newPic;}
    void incrementRatings() {this.numberOfRatings++;}
    void setScore(int i) {
        this.totalScore = this.totalScore + i;
        this.averageRating = this.totalScore / this.numberOfRatings;
    }





    public Queue<User> votingQueue = new LinkedList<>(); //Individual voting queue for each user
    private static int nextUser = 0;

     static ArrayList<User> userList = new ArrayList<>(10); //provide a list of users in an ArrayList structure for user authentication.
    //To log in, the system will have to trace the list to see if there is a match

     protected ArrayList<User> votedOn = new ArrayList<>(10); //An ArrayList that holds the id for which dogs a user has voted on


//Constructors
    User() {} //empty constructor

    //Constructor method with specified names and password -- useful for default objects
     User(String fName, String lName, String uname, String pass) {
        this.firstName = fName;
        this.lastName = lName;
        this.username = uname;
        this.password = pass;
        this.userId = nextUser; //assign the next available userid to this user

        nextUser++; //increment the next available ID
        userList.add(this); //add this user to a list of all of the users

        this.votedOn.add(this); //adds user's own id to the votedOn list so that a user cannot vote on his or her own dog
    }



    void makeQueue() {

        for (int i = 0; i < userList.size(); i++) { //trace the userList
            for (int j = 0; j < activeUser.votedOn.size(); j++) { //trace the list of dogs the user has already voted on
                if (userList.get(i).equals(votedOn.get(j)))  //if the user at index i has not been voted on yet
                    break;
                else {
                    votingQueue.add(userList.get(i)); //add that user to the queue

                    Log.d("New element in Queue", "" + userList.get(i).getDogName());
                    Log.d("Queue size", "" + User.activeUser.votingQueue.size());
                }
            }
        }
    }


//Getter methods for various fields
    String getFirstName() {return this.firstName;}
    public String getLastName()  {return this.lastName;}
    String getUsername() {return this.username;}
    int getUserId() {return this.userId;}
    String getPassword() {return this.password;}

    public User getUser() {return this;} //returns the entire user
}