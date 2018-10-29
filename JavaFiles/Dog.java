/*This program will define the Dog class.*/

import java.util.ArrayList;

public class Dog {
   private int ownerId;
   private String name = "";
   private String bio = "";
   private int totalScore = 0;
   private int numberOfRatings = 0;
   private double averageRating = 0;
   private int ranking; //Might not need ranking? Can just return its position on leaderboard?
   private Picture pic;
   
   public LinkedList votedOnBy = new LinkedList(); //A LL for which users have voted on this particular dog
  
//Constructor method invoked for when a User creates a profile. This constructor will associate the user with that dog.
   public Dog(int id) {
      this.ownerId = id;      
      this.votedOnBy.addLastNode(ownerId);
      }


   
   public void updateDog() {
      //Make this part of the update_dog activity
   } 

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
   public void setPic(Picture newPic) {this.pic = newPic;}
   
   public void deleteDog(){}   
   
   
   //Test method
   
   @Override
   public String toString() {
      return ("Woof woof " + this.ownerId);
   }
}