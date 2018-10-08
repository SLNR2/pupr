/*
This program will define the User class.

To create new users, a different class with a main method will invoke the setter method here and then upload to the MySQL databsae using JDBC

*/

public class User {
   private int userId = 0;
   private int dogId = 0;
   private String userName = "";
   private String email = "";
   private String password = "";
   
   public User(int newUserId, String newUserName, String newEmail, String newPassword) {
      this.userId = newUserId;
      this.userName = newUserName;
      this.email = newEmail;
      this.password = newPassword;
   
   }
}