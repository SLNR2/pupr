package pupr.pupr;
/*
This program will define the User class.


Make sure to add throws, IOException, SQLException when appropriate for each method

*/

//import java.util.Date;

import java.sql.*;
import java.io.IOException;


public class User {
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    //Constructor method that will use passed values to create a new account and log it into the database
    public User (String fName, String lName, String emailAdd, String pass) throws IOException, SQLException {

        //Use the values passed into the call
        this.firstName = fName;
        this.lastName = lName;
        this.email = emailAdd;
        this.password = pass;

        //Connect to database

        Connection con = Database.connect();
        Statement stmt = con.createStatement();

        //Check if user is already in system
        String sql = String.format("SELECT FROM user %s", email);
        if (!stmt.execute(sql)) {
            //Change the query to add the user
            sql = String.format("INSERT INTO user(firstName, lastName, dateOfBirth, username, email, password) VALUES('%s', '%s', '%s', '%s'')", this.firstName, this.lastName, this.email, this.password);
            //Store values in database
            stmt.executeUpdate(sql);
        }
    }

    public void login(){
      /*Use a select statement for the entered username and password. If the combination returns a row in the table, access the next screen.
               Otherwise, display an error screen.*/
    }

    public void logout(){
        //Click a buttton, move to logout confirmation screen, make any necessary updates to the user's statistics. Maybe clear out the queue or something as a way of doing our own garbage collection.
    }

    public void viewAccount(){
        //Make calls to the different getter methods and display the correct information where it needs to go

    }

    public void updateAccount(){
        //Need to make overloaded methods for this to update different aspects

    }

    public void deleteAccount() {
      /*Access database, select the user's ID, delete that row from the table.
            Need to test how this works first. With some foreign key dependencies, we might need to delete their dog first. Also will need to delete the dogs from the vote list?
      */

    }

    //Need to go back and add connection method here to return the appropriate names.
//Do these methods need to pass a userId to return the correct user?
    public String getFirstName() throws SQLNonTransientConnectionException, IOException, SQLException  {return this.firstName;}

    public String getLastName() throws SQLNonTransientConnectionException, IOException, SQLException  {return this.lastName;}



}