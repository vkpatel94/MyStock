package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.faces.context.FacesContext;

import com.sun.faces.context.SessionMap;

import vo.LoginVO;

public class LoginDao {
	
	Connection con = dbconnection.Open();
	
	PreparedStatement p;
	
	public Boolean login(String username,String password)throws Exception
	{

		try{	
			//Class.forName("com.mysql.jdbc.Driver");
			
		    String sql = "SELECT * FROM user where username=? and password=?";
			p = con.prepareStatement(sql);    
		    p.setString(1, username);
		    p.setString(2, password);
		    ResultSet rs = p.executeQuery();
		if(rs.next())
			{	
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("uid", rs.getInt("uid"));
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("firstname", rs.getString("fname"));
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("lastname", rs.getString("lname"));
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("email", rs.getString("email"));
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", rs.getString("username"));
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("password", rs.getString("password"));
			 	
//			 	log.setUid(rs.getInt("uid"));
////			 	System.out.println("uid: " + rs.getString("uid"));
//			 	log.setFirstname(rs.getString("fname"));
//			 	log.setLastname(rs.getString("lname"));
//			 	log.setEmail(rs.getString("email"));
//			 
					return true;
				}
				else 
				{
					return false;
				}
		}
		
		catch (Exception e) {
			//return false
			e.printStackTrace();
		}
		return false;	
	}
	
	public boolean edit(LoginVO upd)throws Exception
	{
		
		try{	
			
			Integer uid =  Integer.parseInt((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("uid"));
			System.out.println("In edit method:"+uid);
			//Class.forName("com.mysql.jdbc.Driver");
			Connection con = dbconnection.Open();
			
			PreparedStatement p =  con.prepareStatement("SELECT * FROM user where uid = " +uid);
		    
		    ResultSet rs = p.executeQuery();
		
		    while(rs.next())
		    {
		    	upd.setUid(rs.getInt("uid"));
		    	upd.setFirstname(rs.getString("fname"));
		    	upd.setLastname(rs.getString("lname"));
		    	upd.setEmail(rs.getString("email"));
		    	upd.setUsername(rs.getString("username"));
		    	upd.setPassword(rs.getString("password"));
		    }

		}
		
		catch (Exception e) {
			//return false
			e.printStackTrace();
		}
		return false;
	}

	
	public boolean update(LoginVO upd){  
		//int result = 0;  
		try{   
		
			int uid =  (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("uid");
			System.out.println("In update method:"+uid);
			
		while(upd.password.length()!=0) {
			Connection con = dbconnection.Open();	 
			PreparedStatement p;
			String sql = ("UPDATE user set  password=? where uid="+uid);
			p = con.prepareStatement(sql);    
		   
			p.setString(1, upd.getPassword());
			p.executeUpdate();  
//			con.close(); 
//			dbconnection.Close();
			return true;}
		
		
		Connection con = dbconnection.Open();	 
		PreparedStatement p;
		String sql = ("UPDATE user set fname=?, lname=?, email=?, username=? where uid="+uid);
		p = con.prepareStatement(sql);    
	   
		p.setString(1, upd.getFirstname());
		p.setString(2, upd.getLastname());
		p.setString(3, upd.getEmail());
		p.setString(4, upd.getUsername());
		p.executeUpdate();  
//		con.close(); 
//		dbconnection.Close();
		return true;
		
		
		}
		
		catch(Exception e){  
		e.printStackTrace();  
		}  
		return false;    
	}  
 
}
