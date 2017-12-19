package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.faces.context.FacesContext;

//import com.mysql.jdbc.Statement;

import vo.LoginVO;
import vo.ManagerLoginVO;

public class ManagerLoginDao {
	ArrayList managerauth;
	ManagerLoginVO ap;
Connection con = dbconnection.Open();
	
	PreparedStatement p;
	
	public Boolean managerlogon(String username, String password)throws Exception
	{
		
		try{	
			//Class.forName("com.mysql.jdbc.Driver");
			
		    String sql = "SELECT * FROM manager where username=? and password=?";
			p = con.prepareStatement(sql);    
		    p.setString(1, username);
		    p.setString(2, password);
		    ResultSet rs = p.executeQuery();
		    if(rs.getString("approved")!= "0") {
		if(rs.next())
			{
			
		 	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("mid", rs.getInt("mid"));
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("firstname", rs.getString("fname"));
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("lastname", rs.getString("lname"));
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("email", rs.getString("email"));
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("commission", rs.getInt("commission"));
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", rs.getString("username"));
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("password", rs.getString("password"));
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("approved", rs.getString("approved"));
		 	
//		 	Integer mid = Integer.parseInt((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("mid"));
//		 	System.out.println(mid);
					return true;
				}
				else 
				{
					return false;
				}
		}
		}
		catch (Exception e) {
			//return false
			e.printStackTrace();
		}
		return false;	
	}
	
	public ArrayList managerauth(ManagerLoginVO ap){
		
	ArrayList managerlist = new ArrayList();
		
        try {
        	
            Connection con = dbconnection.Open();
            PreparedStatement statement =  con.prepareStatement("SELECT * FROM manager");
//            statement.setString(1, mid);
//            int i=0;
            //get userid
            

//            System.out.println("symbol:" + symbol);
            ResultSet rs = statement.executeQuery();
           
            
            while(rs.next()) {
            	if(rs.getInt("approved")==0) {
            	System.out.println("Manager Id:"+rs.getInt("mid"));
            	ap.setMid(rs.getInt("mid"));
            	ap.setFirstname(rs.getString("fname"));
            	ap.setLastname(rs.getString("lname"));
            	ap.setUsername(rs.getString("username"));
            	ap.setEmail(rs.getString("email"));
            	ap.setCommission(rs.getInt("commission"));
//             	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("mid", rs.getString("mid"));
//            	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", rs.getString("username"));
//            	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("email", rs.getString("email"));
            	managerlist.add(ap);
            	}
            }
            System.out.println(managerauth);
        }
        
        catch (SQLException e) {
            e.printStackTrace();
        }
        return managerlist;
        
    }
	
	public boolean edit(ManagerLoginVO mupd)throws Exception
	{
		
		try{	
			
			Integer mid =  Integer.parseInt((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("mid"));
			System.out.println("In edit method:"+mid);
			//Class.forName("com.mysql.jdbc.Driver");
			Connection con = dbconnection.Open();
			
			PreparedStatement p =  con.prepareStatement("SELECT * FROM manager where mid = " +mid);
		    
		    ResultSet rs = p.executeQuery();
		
		    while(rs.next())
		    {
		    	mupd.setMid(rs.getInt("mid"));
		    	mupd.setFirstname(rs.getString("fname"));
		    	mupd.setLastname(rs.getString("lname"));
		    	mupd.setEmail(rs.getString("email"));
		    	mupd.setCommission(rs.getInt("commission"));
		    	mupd.setUsername(rs.getString("username"));
		    	mupd.setPassword(rs.getString("password"));
		    }

		}
		
		catch (Exception e) {
			//return false
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean update(ManagerLoginVO mupd){  
		//int result = 0;  
		try{   
		
			int mid =  (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("mid");
			System.out.println("In update method:"+mid);
			
		while(mupd.password.length()!=0) {
			Connection con = dbconnection.Open();	 
			PreparedStatement p;
			String sql = ("UPDATE manager set  password=? where mid="+mid);
			p = con.prepareStatement(sql);    
		   
			p.setString(1, mupd.getPassword());
			p.executeUpdate();  
			con.close(); 
//			dbconnection.Close();
			return true;}
		
		
		Connection con = dbconnection.Open();	 
		PreparedStatement p;
		String sql = ("UPDATE manager set fname=?, lname=?, email=?, commission=?, username=? where mid="+mid);
		p = con.prepareStatement(sql);    
	   
		p.setString(1, mupd.getFirstname());
		p.setString(2, mupd.getLastname());
		p.setString(3, mupd.getEmail());
		p.setInt(4, mupd.getCommission());
		p.setString(5, mupd.getUsername());
		p.executeUpdate();  
		con.close(); 
//		dbconnection.Close();
		return true;
		
		
		}
		
		catch(Exception e){  
		e.printStackTrace();  
		}  
		return false;    
	}  
	
	public boolean approve(String user){  
		//int result = 0;  
		try{   
		
//			int mid =  ap.getMid();
//			System.out.println("In approve method:"+mid);
		System.out.println("username in dao method:" +user);
		Connection con = dbconnection.Open();	 
		Statement p = con.createStatement();
		p.executeUpdate("UPDATE manager set approved='1' where username = '"+user+"'");    
	   
		
		  
//		con.close(); 
//		dbconnection.Close();
		return true;
	
		}
		
		catch(Exception e){  
		e.printStackTrace();  
		}  
		return false;
	}
	
	public boolean decline(String user){  
		//int result = 0;  
		try{   
		
//			int mid =  ap.getMid();
//			System.out.println("In approve method:"+mid);
		System.out.println("username in dao method:" +user);
		Connection con = dbconnection.Open();	 
		Statement p = con.createStatement();
		p.executeUpdate("DELETE from manager where username = '"+user+"'");    
	   
		
		  
//		con.close(); 
//		dbconnection.Close();
		return true;
	
		}
		
		catch(Exception e){  
		e.printStackTrace();  
		}  
		return false;
	}

	
	public boolean SelectManager(int id){  
		//int result = 0;  
		try{   
		
			int uid =  (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("uid");
//			int mid =  ap.getMid();
//			System.out.println("In approve method:"+mid);
		System.out.println("Manager id during select:" +id);
		Connection con = dbconnection.Open();	 
		PreparedStatement p;
		String sql = ("UPDATE user set mid = ? where uid = "+uid);
		p = con.prepareStatement(sql);
		p.setInt(1, id);   
	   
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
	
	public boolean request(int uid, int mid, int amount){  
		//int result = 0;  
		try{   
		
//			int uid =  (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("uid");
			Connection con = dbconnection.Open();	 
			PreparedStatement p;
			String sql = ("INSERT into request uid = ?, mid = ?, amount = ?");
			p = con.prepareStatement(sql);    
		   
			p.setInt(1, uid);
			p.setInt(2, mid);
			p.setInt(3, amount);
			p.executeUpdate();  
		return true;
	
		}
		
		catch(Exception e){  
		e.printStackTrace();  
		}  
		return false;
	}

}
