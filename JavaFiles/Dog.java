/*This program will define the Dog class.*/

import java.util.ArrayList;

public class Dog {
   protected int ownerId;
   private String name = "";
   private String bio = "";
   private int totalScore = 0;
   private int numberOfRatings = 0;
   private double averageRating = 0;
   private int ranking;
   private Picture pic;
   
   public LinkedList votedOnBy;
  
//Constructor method
   public Dog(String newName, String newBio, Picture newPic)  {
      this.name = newName;
      this.bio = newBio;
      this.pic = newPic;
   }
   
   public void updateDog() {
      //Make this part of the update_dog activity
   } 
   
   public void vote(int score)  {} //Put voting algorithm here.
   //First user will get access to the next dog via the VotingQueue.front() method. When a vote is cast, this method will call VotingQueue.dequeue() to remove the dog. The score will also be updated at this point.
   //We will need a different method for the app to just show the user the dog before they vote.
   

//Getter methods
   public int getOwnerId() {return this.ownerId;}
   public String getName() {return this.name;} 
   public String getBio() {return this.bio;}  
   public Picture getPicture()  {return this.pic;} 
   public int getScore()  {return this.totalScore;} 
   public int getRatings() {return this.numberOfRatings;}   
   public double getAverage() {return this.averageRating;}


//Setter methods
   public void setName(String newName) {this.name = newName;}
   public void setBio(String newBio) {this.bio = newBio;}
   public void setPicture(Picture newPic) {this.pic = newPic;}
   
   public void deleteDog(){}   
   
 }