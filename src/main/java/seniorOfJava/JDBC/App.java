package seniorOfJava.JDBC;

import java.sql.*;

public class App 
{
	static final String userName = "root";
	static final String password = "11111";
	static final String url = "jdbc:mysql://localhost/test";
	
	static Connection conn = null;
	
	public static void main (String[] args){
	
	try{
	 
	//Class.forName ("com.mysql.jdbc.Driver").newInstance ();
	conn = DriverManager.getConnection (url, userName, password);
	System.out.println ("Database connection established");
	}
	catch (Exception e){
	System.err.println ("Cannot connect to database server");
	e.printStackTrace();
	}
	finally{
		if (conn != null){
	try{
	conn.close ();
	System.out.println ("Database connection terminated"); 
	}
	catch (Exception e) { }
	}
	}
	}
    
}
