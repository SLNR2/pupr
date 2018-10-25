/*

A class to test some of the User methods

When you are done testing this class, execute the following SQL statements:

1)   delete from user;
2)   alter table user auto_increment = 1;  
*/

//import java.util.Date;

import java.sql.*;
import java.io.IOException;
import java.util.Scanner;


public class TestUser{


   public static void main (String args[]) throws SQLNonTransientConnectionException, IOException, SQLException {
  Scanner s = new Scanner(System.in);
   System.out.print("fName? ");
   String fName = s.next();
   System.out.print("lName? ");
   String lName = s.next();
   System.out.print("dob? ");
   String dob = s.next();
   System.out.print("username? ");
   String username = s.next();
   System.out.print("email? ");
   String email = s.next();
   System.out.print("password? ");
   String password = s.next();
   
   User myUser = new User(fName, lName, dob, username, email, password);
   
  try{ Connection con = Database.connect();
   Statement stmt = con.createStatement();
   
            //  stmt.executeUpdate("INSERT INTO user(firstName, lastName, dateOfBirth, username, email, password) VALUES('Sloan', 'Lipman', '1234', 'slipman', 'sloan.lipman@gmail.com', 'test123')"); //The hardcoded one works
   ResultSet rs = stmt.executeQuery("SELECT firstName FROM user");
    while(rs.next()) 
         System.out.println(rs.getString(1));
      System.out.println();}catch (SQLSyntaxErrorException e) {
      e.printStackTrace();
      }
      

   }
}