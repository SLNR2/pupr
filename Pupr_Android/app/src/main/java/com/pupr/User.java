package com.pupr;
/*
This program will define the User class.


*/

import android.graphics.drawable.Drawable;

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




    public Queue<User> votingQueue = new LinkedList<>(); //Individual voting queue for each user
    private static int numberOfUsers = 0; //Update the total number of users each time one is created or deleted... I forget why.
    private static int nextUser = 0;

     static ArrayList<User> userList = new ArrayList<User>(); //provide a list of users in an ArrayList structure for user authentication.
    //To log in, the system will have to trace the list to see if there is a match

     ArrayList<Integer> votedOn = new ArrayList<Integer>(); //An ArrayList that holds the id for which dogs a user has voted on


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
        numberOfUsers++; //increment # of users
        userList.add(this); //add this user to a list of all of the users

        this.votedOn.add(this.userId); //adds user's own id to the votedOn list so that a user cannot vote on his or her own dog
    }



    public void makeQueue() {
      /*
               Trace dogList
                  for i --> n (list of dogs)   //n can be size of dogList ArrayList
                     for j --> m (list of votes) // size of this.votedOn
                        if ownerid @ i != vote @ j
                           add dog to queue


      */

    }
    public void deleteAccount() {

      /*
            remove(Object o)
         Removes the first occurrence of the specified element from this list, if it is present.)

      */
        numberOfUsers--;
        //Make a call to delete dog
        //Make a call to delete this account from votedOn for all dogs -- might not be necessary. When generating queues, it might just pass over the number... reassess later
    }

//Getter methods for various fields
    String getFirstName() {return this.firstName;}
    public String getLastName()  {return this.lastName;}
    String getUsername() {return this.username;}
    int getUserId() {return this.userId;}
    public int getNumberOfUsers() {return numberOfUsers;}
    String getPassword() {return this.password;}
    void addUser() {userList.add(this);}    //adds a specified user to the userList ArrayList

    //Setter methods for various fields
    void setFirstName(String newFirstName) {this.firstName = newFirstName;}
    void setLastName(String newLastName) {this.lastName = newLastName;}
    void setUsername(String newUsername) {this.username = newUsername;}
    void setPassword(String newPassword) {this.password = newPassword;}


    public User getUser() {return this;} //returns the entire user
}