package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class dbconnection {

static Connection con = null;

	
	public static Connection Open() 
	{
		if (con != null)
			return con;
	
	
	String url = "jdbc:mysql://localhost:3306/vp766231" ;
	String username = "root";
	String password = "root";
	
	try {
		Class.forName("com.mysql.jdbc.Driver");

		con = DriverManager.getConnection(url, username, password);
		} 
	
	catch (Exception e) 
	{
		e.printStackTrace();
	}
	
	return con;
	
}




	public static void Close()
	{
		
		try 
		{
			con.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	
}

