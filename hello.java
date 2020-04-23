import java.io.Console;
import java.sql.*;
import java.util.Scanner;

public class hello {
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/sonoo?characterEncoding=utf-8";

   //  Database credentials
   static final String USER = "root";
   static final String PASS = "password";
   
   public static void main(String[] args) 
   {
   Connection conn = null;
   Statement stmt = null;
   try
   {
      Class.forName("com.mysql.jdbc.Driver");
      System.out.println("Connecting to a selected database...");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      System.out.println("Connected database successfully...");
      
      System.out.println("Inserting records into the table...");
      stmt = conn.createStatement();
      stmt.executeUpdate("Drop table users");
      stmt.executeUpdate("Drop table books");
      String sql_u = "CREATE TABLE users " + "(id VARCHAR(20) primary key, " + " first VARCHAR(255))"; 
      String sql_b = "CREATE TABLE books " + "(isbn VARCHAR(20), " + " name VARCHAR(255), " + " PRIMARY KEY ( isbn ))"; 
      stmt.executeUpdate(sql_u);
      stmt.executeUpdate(sql_b);
      while(true)
      {
    	  System.out.println("Welcome\n1. Sign In\n2. Sign Up\n3. Exit");
    	  Scanner sc = new Scanner(System.in);
    	  int s = sc.nextInt();
    	  if(s == 2)
    	  {
    		  System.out.println("HELLO\nEnter your Username followed by your password");
    	  sc.nextLine();
    	  String uname = sc.nextLine();
    	  //sc.nextLine();
    	  String pass = sc.nextLine();
    	  try {
    	  String sql = "insert into users " + " (id, first)" + " values (?, ?)";
    	  PreparedStatement myStmt = null;
    	  myStmt = conn.prepareStatement(sql);
    	  
    	  myStmt.setString(1, uname);
    	  myStmt.setString(2, pass);
    	  myStmt.executeUpdate();
    	  }
    	  catch(Exception e)
    	  {
    		  System.out.println("Username Already taken Please Select Another Username");
    	  }
      }
    	  else if(s==1)
    	  {
    		  System.out.println("HELLO\nEnter your Username followed by your password");
        	  sc.nextLine();
        	  String uname = sc.nextLine();
        	  //sc.nextLine();
        	  String pass = sc.nextLine();
        	  
        	  String query = "select count(*) from users WHERE id = ? AND first = ?";
        	  PreparedStatement prepStmt = conn.prepareStatement(query);
        	  prepStmt.setString(1, uname);
        	  prepStmt.setString(2, pass);
        	  ResultSet rs = prepStmt.executeQuery();
        	  rs.next();
    	      int asd = rs.getInt(1);
    	      if(asd == 1)
    	      {
    	    	  System.out.println("Success\n");
    	    	  if((uname == "admin" || pass == "admin"))
    	    	  {
    	    		  System.out.println("1. Add books\n2. Delete Books\n3. Show all data\n4. Exit");
    	    		  int q = sc.nextInt();
    	    		  if(q == 1)
    	    		  {
    	    			  
    	    			  sc.nextLine();
    	            	  String isbn = sc.nextLine();
    	            	  //sc.nextLine();
    	            	  String name = sc.nextLine();
    	            	  try
    	            	  {
    	            	  String sql = "insert into books " + " (isbn, name)" + " values (?, ?)";
    	            	  PreparedStatement myStmt = null;
    	            	  myStmt = conn.prepareStatement(sql);
    	            	  myStmt.setString(1, isbn);
    	            	  myStmt.setString(2, name);
    	            	  myStmt.executeUpdate();
    	    			  }
    	    			  catch(Exception e)
    	    			  {
    	    				  System.out.println("Book already exist");
    	    			  }
    	    		  }
    	    		  else if(q==2)
    	    		  {
    	    			  try {
    	    			  sc.nextLine();
    	            	  String isbn = sc.nextLine();
    	            	  String sql = "delete from books where isbn = ?";
    	            	  PreparedStatement myStmt = null;
    	            	  myStmt = conn.prepareStatement(sql);
    	            	  
    	            	  myStmt.setString(1, isbn);
    	            	  myStmt.executeUpdate();
    	    			  }
    	    			  catch(Exception e)
    	    			  {
    	    				  System.out.println("No such Book already exist");
    	    			  }
    	    		  }
    	    		  else if(q==3)
    	    		  {
    	    			  String sql = "Select * from books";
    	    			  ResultSet rst = stmt.executeQuery(sql);
    	    			  while(rst.next())
    	    			  {
    	    				  System.out.println(rst.getString(1) + rst.getString(2));
    	    			  }
    	    		  }
    	    			  
    	    	  }
    	    	  else
    	    	  {
    	    		  System.out.println("1. Search books\n2. See all books");
    	    		  int xyz = sc.nextInt();
    	    		  if(xyz == 2)
    	    		  {
    	    		  String sql = "Select * from books";
	    			  ResultSet rst = stmt.executeQuery(sql);
	    			  while(rst.next())
	    			  {
	    				  System.out.println(rst.getString(1) + rst.getString(2));
	    			  }
    	    		  }
    	    		  else if(xyz == 2)
    	    		  {
    	    			  String lic = sc.nextLine();
    	    			  String abc = "Select * from books where isbn = ?";
    	    			  PreparedStatement mno = conn.prepareStatement(abc);
    	    			  mno.setString(1,lic);
    	    			  ResultSet def = mno.executeQuery();
    	    			  while(def.next())
    	    			  {
    	    				  System.out.println(def.getString(1) + " " + def.getString(2));
    	    			  }
    	    		  }
    	    	  }
    	      }
    	      else
    	      {
    	    	  System.out.println("Invalid Username or Password");
    	      }
    	  }
    	  else if(s==3)
    		  break;
      }
      //rs.close();
      //stmt.executeUpdate("Drop table users");
      //stmt.executeUpdate("Drop table books");
      conn.close();
      //sc.close();
   }
   catch(Exception e)
   {
      System.out.println(e);
   }
   System.out.println("Goodbye!");
}
}