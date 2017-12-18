package com.login.jsf.bean;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dao.LoginDao;
import dao.dbconnection;
import vo.AdminLoginVO;
import vo.LoginVO;
import vo.ManagerLoginVO;

@ManagedBean(name="adminlogin")
@SessionScoped
public class AdminLogin {

	AdminLoginVO loginvo = new AdminLoginVO();
	private String table4Markup = "";
	ManagerLoginVO mlist = new ManagerLoginVO();
	ArrayList managerList;

	public AdminLoginVO getLoginvo() {
		return loginvo;
	}



	public void setLoginvo(AdminLoginVO loginvo) {
		this.loginvo = loginvo;
	}


	public ManagerLoginVO getMlist() {
		return mlist;
	}



	public void setMlist(ManagerLoginVO mlist) {
		this.mlist = mlist;
	}



	public String SuccessLogin()
	{
		
		try {
			if(loginvo.getUsername().equals("Admin") && loginvo.getPassword().equals("Admin"))
			{
//			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", loginvo.getUsername());
				return "AdminDashboard";
			}
			
			else
			{
//			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("error", "Verify Username or Password");		
				return "AdminLogin";
			}
		}	
		catch(Exception e){
			e.printStackTrace();
		}
		return "AdminLogin";
	}  
	
	public ArrayList managerList(){
        try {
        	
            Connection con = dbconnection.Open();
            PreparedStatement statement =  con.prepareStatement("SELECT * FROM manager");
//            statement.setString(1, mid);
            int i=0;
            //get userid
            

//            System.out.println("symbol:" + symbol);
            ResultSet rs = statement.executeQuery();
           
            while(rs.next()) {
            	
            	mlist.setMid(rs.getInt("mid"));
            	mlist.setUsername(rs.getString("username"));
            	mlist.setEmail(rs.getString("email"));
            	managerList.add(mlist);
            	
            }
            	 
            
//           
//            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully added to watchlist",""));
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return managerList;
    }
	
	public void logout() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesContext.getCurrentInstance().getExternalContext().redirect("AdminLogin.xhtml");
    }
}
