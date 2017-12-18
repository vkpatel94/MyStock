package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import vo.ManagerLoginVO;

public class ManagerRegistrationDao {
	
Connection con = dbconnection.Open();
	
	PreparedStatement p;
	
	public boolean register(ManagerLoginVO reg) throws Exception
	{
		
		try{
	
	    String sql = "INSERT INTO manager(fname, lname, email, commission, username, password) VALUES (?,?,?,?,?,?)";
	    PreparedStatement p = con.prepareStatement(sql);
	    p.setString(1, reg.getFirstname());
	    p.setString(2, reg.getLastname());
	    p.setString(3, reg.getEmail());
	    p.setString(4, reg.getCommission());
	    p.setString(5, reg.getUsername());
	    p.setString(6, reg.getPassword());
	    
	    
	    
	    p.executeUpdate();

		}
		
		
		catch(Exception e)
		{
			throw e;
		}	
		return false;	
	}

}
