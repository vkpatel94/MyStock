package com.login.jsf.bean;

import javax.faces.application.FacesMessage;

import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.sql.Statement;  
import java.sql.ResultSet;  
import java.util.ArrayList;  
import java.util.Map;  
import javax.faces.bean.ManagedBean;  
import javax.faces.bean.RequestScoped;  
import javax.faces.context.FacesContext;

import dao.dbconnection;
import vo.LoginVO; 

@ManagedBean(name="user")
@RequestScoped  

public class User{  
int uid;  
String firstname;
String lastname;
String email;  
String username;
String password; 
ArrayList usersList ;  
private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();  
Connection con = dbconnection.Open();  
Statement stmt;
  

   
public int getUid() {
	return uid;
}
public void setUid(int uid) {
	this.uid = uid;
}
public String getFirstname() {
	return firstname;
}
public void setFirstname(String firstname) {
	this.firstname = firstname;
}
public String getLastname() {
	return lastname;
}
public void setLastname(String lastname) {
	this.lastname = lastname;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getEmail() {  
return email;  
}  
public void setEmail(String email) {  
this.email = email;  
}  
public String getPassword() {  
return password;  
}  
public void setPassword(String password) {  
this.password = password;  
}  
 

// Used to fetch all records  
public ArrayList usersList(){  
try{  
usersList = new ArrayList();  
LoginVO user = new LoginVO();   
ResultSet rs=stmt.executeQuery("SELECT * FROM user");    
while(rs.next()){  
user.setUid(rs.getInt("uid"));
user.setFirstname(rs.getString("fname"));
System.out.println(rs.getString("fname"));
user.setLastname(rs.getString("lastname"));
user.setEmail(rs.getString("email"));  
user.setUsername(rs.getString("username"));
user.setPassword(rs.getString("password"));    
usersList.add(user);  
}  
con.close();          
}catch(Exception e){  
e.printStackTrace(); 
}  
return usersList;  
}  
 
  
// Used to update user record  
public String update(LoginVO u){  
//int result = 0;  
try{    
PreparedStatement stmt=con.prepareStatement(  
"UPDATE user set fname=?, lname=?, email=?, username=?, password=? where uid=?");    
stmt.setString(1, u.getFirstname());
stmt.setString(2, u.getLastname());
stmt.setString(3,u.getEmail());
stmt.setString(4, u.getUsername());
stmt.setString(5,u.getPassword());       
//stmt.setInt(6,u.getUid());    
stmt.executeUpdate();  
con.close();  
}catch(Exception e){  
e.printStackTrace();  
}  
return "/index.xhtml?faces-redirect=true";        
}  
}