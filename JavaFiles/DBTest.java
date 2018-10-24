//Testing Database class

import java.sql.*;
import java.io.IOException;


public class DBTest {
   public static void main (String args[]) throws SQLNonTransientConnectionException, IOException, SQLException {
     Connection con = Database.connect();
      Statement stmt = con.createStatement();
      Database.disconnect();
   }

}