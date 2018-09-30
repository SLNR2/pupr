

public class Dog {
   private String name = "Dog";
   private String bio = "This is a profile for a dog!";
   private Picture pic = new Picture("Megan.jpg");
   private double score = 0;
   private int numberOfVotes = 0;
  
   public Dog(String newName, String newBio, Picture newPic) {
      this.name = newName;
      this.bio = newBio;
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