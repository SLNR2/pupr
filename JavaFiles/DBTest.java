/*Testing Database class

1) Instantiate a Connection object as Database.connect()
2) Instantiate a Statement object as con.createStatement()
3) Use executeQuery for QUERIES (e.g. desc, select... anything where you want to ask for information)
   3a) How to do this is to do ResultSet rs = stmt.executeQuery("your query");
4) You can use stmt.executeUpdate("command"), where command is some kind of data manipulation.
5) There's also one that's just execute, and it returns a boolean value of true if a ResultSet object can be retrieved. We probably won't need to worry about this one.


More info here:  https://www.tutorialspoint.com/jdbc/jdbc-statements.htm


*/



import java.sql.*;
import java.io.IOException;


public class DBTest {
   public static void main (String args[]) throws SQLNonTransientConnectionException, IOException, SQLException {
     
     
   //First, define a Connection and use it to create a Statement
      Connection con = Database.connect();
      Statement stmt = con.createStatement();

 //Our first query!
      ResultSet rs = stmt.executeQuery("desc user");
      System.out.println("\nFields in the table USER:\n-------------------------"); 
      

//Keep printing stuff out until you get to the end   
      while(rs.next()) 
         System.out.println(rs.getString(1));
      System.out.println();
      
//Next, let's create a table.
     stmt.executeUpdate("CREATE TABLE TEST (ID INT NOT NULL DEFAULT 0, NAME VARCHAR (60))");

//And let's describe it again. 
     rs = stmt.executeQuery("desc TEST");
      System.out.println("\nFields in the table TEST:\n-------------------------"); 
      while(rs.next()) 
         System.out.println(rs.getString(1));
      System.out.println("-------------------------");
//Now let's add some data to it!
   stmt.executeUpdate("INSERT INTO TEST (ID, NAME)"
                        + "VALUES (10, 'Johnny')");


//Now let's see if it worked!
  ResultSet rs2 = stmt.executeQuery("SELECT NAME FROM TEST");
     while(rs2.next()) 
         System.out.println(rs2.getString(1));
      System.out.println();

  
//Ok, we don't actually want this table in our final project though, so let's just do an executeUpdate to get rid of it!
   stmt.executeUpdate("drop table TEST");
   System.out.println("Dropped test table\n And made it to the end too!!!");
   
      con.close(); //disconnect from the DB
   }

}