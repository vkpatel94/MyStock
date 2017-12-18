package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.faces.context.FacesContext;

import vo.LoginVO;


public class RegistrationDao {
 
Connection con = dbconnection.Open();
	
	PreparedStatement p;
	
	public boolean register(LoginVO reg) throws Exception
	{
		
		try{
	
	    String sql = "INSERT INTO user(fname, lname, email, username, password) VALUES (?,?,?,?,?)";
	    PreparedStatement p = con.prepareStatement(sql);
	    p.setString(1, reg.getFirstname());
	    p.setString(2, reg.getLastname());
	    p.setString(3, reg.getEmail());
	    p.setString(4, reg.getUsername());
	    p.setString(5, reg.getPassword());
	    
	    
	    p.executeUpdate();

		}
		
		
		catch(Exception e)
		{
			e.printStackTrace();
		}	
		return false;	
	}
	
}
