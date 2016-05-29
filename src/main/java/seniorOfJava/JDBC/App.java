package seniorOfJava.JDBC;

import java.sql.*;

public class App 
{
	public static void main (String[] args)
	{
	Connection conn = null;
	try
	{
	String userName = "root";
	String password = "11111";
	String url = "jdbc:mysql://localhost/test"; 
	Class.forName ("com.mysql.jdbc.Driver").newInstance ();
	conn = DriverManager.getConnection (url, userName, password);
	System.out.println ("Database connection established");
	}
	catch (Exception e)
	{
	System.err.println ("Cannot connect to database server");
	e.printStackTrace();
	}
	finally
	{
	if (conn != null)
	{
	try
	{
	conn.close ();
	System.out.println ("Database connection terminated"); 
	}
	catch (Exception e) { }
	}
	}
	}
    
}
