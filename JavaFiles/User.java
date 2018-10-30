/*
This program will define the User class.


*/
import java.util.ArrayList;
import java.util.Arrays;

public class User { 
   private int userId;
   private String firstName = "";
   private String lastName = "";
   private String username = "";
   private String password = "";
   public Queue votingQueue = new Queue(); //Individual voting queue for each user
   private static int numberOfUsers = 0; //Update the total number of users each time one is created or deleted... I forget why.
   private static int nextUser = 0;
   public Dog dog = new Dog(); //Instantiate a dog that belongs to each user
   
   protected static ArrayList<User> userList = new ArrayList<User>(); //provide a list of users in a LinkedList structure for user authentication.
                                                            //To log in, the system will have to trace the list to see if there is a match

   
//Constructor method that will use passed values to create a new account and log it into the database
   public User(String fName, String lName, String uname, String pass) {
   
 
      this.firstName = fName;
      this.lastName = lName;
      this.username = uname;
      this.password = pass;
      this.userId = nextUser; //assign the next available userid to this user
      
      nextUser++; //increment the next available ID
      numberOfUsers++; //increment # of users
      userList.add(this); //add this user to a list of all of the users
                            //NEED TO GO BACK AND TEST THAT USING THE this OPERATOR ADDS THE OBJECT CORRECTLY!!!
                            
      this.dog.setId(userId);
     }
     
     
   public void getQueue() {
      /*
               Trace dogList
                  for i --> n (list of dogs)
                     for j --> m (list of votes)
                        if ownerid @ i != vote @ j
                           add dog to queue 
      
      
      */
   
   }     
   
   public static void login(){               
      //make part of the login activity
      
      /*
         Trace userList
            for i --> n (usernames only)
               for j ---> m (passwords only)
                  if username = password
                     success!
 
      */
      

   }
   
   public void logout(){
      //Click a buttton, move to logout confirmation screen, make any necessary updates to the user's statistics. Maybe clear out the queue or something as a way of doing our own garbage collection.
      
      //Make part of the logout activity
   }
   
   public void viewAccount(){
   //Make calls to the different getter methods and display the correct information where it needs to go
   
   //This method might serve better as part of the view_account activity. Have each field become populated by calling the appropriate getter method.
   
   }
   
   public void updateAccount(){
     //Need to make overloaded methods for this to update different aspects
     
    //This method might better serve as part of the update_account activity or whatever we call it. Each field can call a setter method.
   
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
   public String getFirstName() {return this.firstName;}
   public String getLastName()  {return this.lastName;}   
   public String getUsername() {return this.username;}   
   public int getUserId() {return this.userId;}   
   public int getNumberOfUsers() {return numberOfUsers;}
   
//Setter methods for various fields
   public void setFirstName(String newFirstName) {this.firstName = newFirstName;}
   public void setLastName(String newLastName) {this.lastName = newLastName;}
   public void setUsername(String newUsername) {this.username = newUsername;}
   public void setPassword(String newPassword) {this.password = newPassword;}
   

   //toString method - I'm just using it for some testing purposes.   
   @Override
   public String toString() {
   String output = this.getUsername() + "/" + this.getUserId();
   return output;
   }
}