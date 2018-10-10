/*This program will allow the creation of new users.

The user will specify an email and userName, and the program will perform a SQL query to see if they are available.

If available, another query will be performed to find the highest userId in the database, increment it by 1, and store that new value as the userId for the new user.

The password will be required to meet certain criteria to be considered valid.
Finally, the dogId will be left as NULL for now, until a dog has been created. The dogId will be updated similarly to as described above.

If all is good, the new user will then be uploaded into the database.
*/

import java.sql.*;
import java.io.IOException;

import java.util.Scanner;

public class NewUser{
   public static void main(String args[]) throws SQLNonTransientConnectionException, IOException, SQLException{
   
    String instanceConnectionName = "pupr-218714:us-east1:pupr";
    String instanceIP = "35.196.89.66"; //instance connection from Google Cloud Platform
    String databaseName = "pupr"; //specify the database

   //login inf
    String username = "pupr";
    String password = "pupr123";

    if (instanceConnectionName.equals("<insert_connection_name>")) {
      System.err.println("Please update the sample to specify the instance connection name.");
      System.exit(1);
    }

    if (password.equals("<insert_password>")) {
      System.err.println("Please update the sample to specify the mysql password.");
      System.exit(1);
    }

    //[START doc-example]
   String jdbcUrl = String.format(
         "jdbc:mysql://google/%s?cloudSqlInstance=%s"
            + "&socketFactory=com.google.cloud.sql.mysql.SocketFactory&useSSL=false"
            + "&user=%s&password=%s",
        databaseName,
        instanceConnectionName,
        username,
        password
    
        );
 
 
    Connection connection = DriverManager.getConnection(jdbcUrl);
   //[END doc-example]

    try (Statement statement = connection.createStatement()) {
      ResultSet resultSet = statement.executeQuery("desc dog");
      while (resultSet.next()) {
        System.out.println(resultSet.getString(1));
      }
    }
  }

}