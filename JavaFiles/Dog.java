/*This program will define the Dog class.


Make sure to add throws SQLNonTransientConnectionException, IOException, SQLException when appropriate

*/
import java.sql.*;
import java.io.IOException;
import java.util.Scanner;


public class Dog {
   private int ownerId;
   private String name = "";
   private String bio = "";
   private int totalScore = 0;
   private int numberOfRatings = 0;
   private double averageRating = 0;
   private int ranking;
   private Picture pic;
  
//Constructor method
   public Dog(String newName, String newBio, Picture newPic) throws SQLNonTransientConnectionException, IOException, SQLException {
      this.name = newName;
      this.bio = newBio;
      this.pic = newPic;
   }
   
   public void updateDog() {} //Update dog w/ new bio and/or new picture.
   
   public void vote(int score) throws SQLNonTransientConnectionException, IOException, SQLException  {} //Put voting algorithm here.
   //First user will get access to the next dog via the VotingQueue.front() method. When a vote is cast, this method will call VotingQueue.dequeue() to remove the dog. The score will also be updated at this point.
   //We will need a different method for the app to just show the user the dog before they vote.
   
//Go back and add DB methods to call the appropriate dog. Will also need to pass a userId as a parameter to get the right dog.
   public String getName() throws SQLNonTransientConnectionException, IOException, SQLException  {return this.name;} //return dog name
   
   public String getBio() throws SQLNonTransientConnectionException, IOException, SQLException  {return this.bio;} //return bio
   
   public Picture getPicture() throws SQLNonTransientConnectionException, IOException, SQLException  {return this.pic;} //return picture
   
   public int getScore() throws SQLNonTransientConnectionException, IOException, SQLException  {return this.totalScore;} //Connect to DB to find total score
   
   public double getAverage() throws SQLNonTransientConnectionException, IOException, SQLException {return this.averageRating;}
   
   
   public void deleteDog(){} //Delete dog. Will need to access DB to delete the dog. 
   
   
 }