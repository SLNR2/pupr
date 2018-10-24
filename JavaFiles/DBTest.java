//Testing Database class

import java.sql.*;
import java.io.IOException;


public class DBTest {
   public static void main (String args[]) throws SQLNonTransientConnectionException, IOException, SQLException {
      Connection con = Database.connect(); //connects to DB and also stores the connection as a variable in this class so we can do some manipulations
      Statement stmt = con.createStatement(); //
      ResultSet rs = stmt.executeQuery("desc user");
  
//Keep printing stuff out until you get to the end   
      while(rs.next()) 
         System.out.println(rs.getString(1));
      
      Database.disconnect(); //disconnect from the DB
   }

}