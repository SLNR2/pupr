/*
This program will define the User class.


Make sure to add throws SQLNonTransientConnectionException, IOException, SQLException when appropriate

*/

//import java.util.Date;

import java.sql.*;
import java.io.IOException;


public class User { 
   private int userId;
   
   private String firstName = "";
   private String lastName = "";
   private String dateOfBirth = ""; //change to Date class eventually?
   private String username = "";
   private String email = "";
   private String password = "";
   
   public User (String fName, String lName, String dob, String uname, String emailAddr, String pass) throws SQLNonTransientConnectionException, IOException, SQLException {
   //Connect to database
   
      this.firstName = fName;
      this.lastName = lName;
      this.dateOfBirth = dob;
      this.username = uname;
      this.email = emailAddr;
      this.password = pass;
      Connection con = Database.connect();
      Statement stmt = con.createStatement();      
      
      String sql = String.format("INSERT INTO user(firstName, lastName, dateOfBirth, username, email, password) VALUES('%s', '%s', '%s', '%s', '%s', '%s'", this.firstName, this.lastName, this.dateOfBirth, this.username, this.email, this.password);
      

      //Store values in database
      stmt.executeUpdate(sql);
      
//    }   
}     
   
   public void login(){
   
   }
   
   public void logout(){
   
   }
   
   public void viewAccount(){
   
   
   }
   
   public void updateAccount(){
   
   
   }
   
   public void deleteAccount() {
   
   
   }
   /*
   public String getFirstName() {
   //return first name
   }
   
   public String getLastName() {
   
   //return last name
   }
   
   public String getUsername() {
   //return username
   
   }*/
   
}