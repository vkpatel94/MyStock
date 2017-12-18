package dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

import javax.faces.context.FacesContext;

import com.sun.faces.context.SessionMap;

import java.sql.Connection;

import vo.LoginVO;

public class EditUserDao {
	
	private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	
	Connection con = dbconnection.Open();  
	Statement stmt;
	
	// Used to fetch record to update  
	public String edit(LoginVO user){    
	 
	System.out.println(sessionMap.get("uid"));  
	try{      
	ResultSet rs=stmt.executeQuery("SELECT * FROM user where uid = "+sessionMap.get("uid"));  
	rs.next();   
	user.setUid(rs.getInt("uid"));
	System.out.println("In edit method:"+user.getUid());
	user.setFirstname(rs.getString("fname"));
	user.setLastname(rs.getString("lname"));  
	user.setUsername(rs.getString("username"));  
	user.setEmail(rs.getString("email"));  
	user.setPassword(rs.getString("password"));    
	sessionMap.put("editUser", user);  
	con.close();  
	}catch(Exception e){  
	e.printStackTrace();  
	}         
	return "";  
	}
}
