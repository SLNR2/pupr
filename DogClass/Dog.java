

public class Dog {
   private String name = "Dog";
   private String bio = "This is a profile for a dog!";
   private Picture pic = new Picture("Megan.jpg");
   private double score = 0;
   private int numberOfVotes = 0;
   private String owner = "";
  
   public Dog(String newName, String newBio, String newOwner, Picture newPic) {
      this.name = newName;
      this.bio = newBio;
      this.owner = newOwner; //When we have a class for a user to upload a dog, we will need to have a method to pull the identity of the owner from the user's email address
      this.pic = newPic;
      
      
   }
 
    public Dog(String newName, String newBio) {
      this.name = newName;
      this.bio = newBio;      
   }  
   
   public void vote (int numberOfStars) {
      if (numberOfVotes == 0)
         this.score = numberOfStars;
         
      else
         this.score = (score * numberOfVotes + numberOfStars) / (numberOfVotes + 1);
   }
}