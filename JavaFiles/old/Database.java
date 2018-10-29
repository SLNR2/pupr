/*A class to allow connection/disconnection to/from the Google Cloud SQL database via JDBC connection.
   Instead of having to connect 

 Jars to include in CLASSPATH: 
   mysql-socket-factory-1.0.11.jar
   mysql-connector-java-8.0.12.jar
   
   YOU MUST INCLUDE THESE OR IT WILL NOT COMPILE!!!
*/

import java.sql.*;
import java.io.IOException;

public class Database{
  private static Connection con; //Declare a private Connection named con
   
   public static Connection connect() throws SQLNonTransientConnectionException, IOException, SQLException{
      
      String instanceConnectionName = "pupr-218714:us-east1:pupr"; //Instance connection name
      String instanceIP = "35.196.89.66"; //instance connection from Google Cloud Platform
      String databaseName = "pupr"; //specify the database

//Server credientials
      String username = "pupr";
      String password = "pupr123";

//URL to connect to database   
      String jdbcUrl =  String.format("jdbc:mysql://35.196.89.66/%s?useSSL=false", databaseName);
    
//Returns a connection for other methods to use
      con = DriverManager.getConnection(jdbcUrl, username, password);

//Connection statement
  /*Might not need this block of code. We can just call the connection variable and make statements where needed. Inefficient use of memory to have a try/catch block here if we aren't even using it.
  
  /* try (Statement statement = con.createStatement()) {
      System.out.println("connection successful"); //delete later, just here for testing
   } catch (SQLException e) {
      e.printStackTrace();
   }*/
   return con; //return the connection so that we can submit queries to the DB
  }
  
//Method to disconnect

/*Might not need this block of code - can just use the .close() method */

/*   public static void disconnect() throws SQLNonTransientConnectionException, IOException, SQLException{
      try {
         con.close(); //close the connection
                  System.out.println("disconnected!"); //delete later, just here for testing

      }  catch (SQLException e) {
         e.printStackTrace();
         }
   } */
}