

public class Dog {
   private String name = "Dog";
   private String bio = "This is a profile for a dog!";
   private Picture pic = new Picture("Megan.jpg");
   private double totalScore = 0;
   private double averageScore = 0;
   private int numberOfVotes = 0;
   private String owner = "";
   
   private int dogID = 0;
  
   public Dog(String newName, String newBio, String newOwner, Picture newPic) {
      this.name = newName;
      this.bio = newBio;
      this.owner = newOwner; //When we have a class for a user to upload a dog, we will need to have a method to pull the identity of the owner from the user's email address
      this.pic = newPic;
      this.dogID = dogID; //assign a unique identifier to the object
      dogID++; //increment the unique identifier
      
      
   }
 
    public Dog(String newName, String newBio) {
      this.name = newName;
      this.bio = newBio;
      this.dogID = dogID;
      dogID++;      
   }  
   
   public void vote (int numberOfStars) {
      if (numberOfVotes == 0)
         this.totalScore = numberOfStars;
         
      else
         this.totalScore = totalScore + numberOfStars;
         
      this.averageScore = totalScore / numberOfVotes;
   }
}