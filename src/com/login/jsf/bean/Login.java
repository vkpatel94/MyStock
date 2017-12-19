package com.login.jsf.bean;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dao.LoginDao;
import dao.RegistrationDao;
import dao.dbconnection;
import vo.LoginVO;

/**
 * @author vaiku
 *
 */
@ManagedBean(name="login")
@SessionScoped
public class Login {

	LoginVO loginvo= new LoginVO();
	
	LoginVO edtVO = new LoginVO();
	
	LoginVO updVo = new LoginVO();
	
	LoginDao access = new LoginDao();
	
	public LoginVO getLoginvo() {
		return loginvo;
	}


	public void setLoginvo(LoginVO loginvo) {
		this.loginvo = loginvo;
	}
	
	public LoginVO getEdtVO() {
		return edtVO;
	}


	public void setEdtVO(LoginVO edtVO) {
		this.edtVO = edtVO;
	}
	
	public LoginVO getUpdVo() {
		return updVo;
	}


	public void setUpdVo(LoginVO updVo) {
		this.updVo = updVo;
	}


	public String SuccessLogin()
	{
		
		try {
			Boolean auth = access.login(loginvo.username,loginvo.password);
			updVo.refresh();
			if(auth) {
//			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("uid");
//			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("firstname");
//			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("lastname");
//			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("email");
//			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
//			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("password");
				return "UserDashboard";
			}
			else {
//				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("error", "Verify Username or Password");
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN, "Incorrect Username and Password.",""));
			}
		}	
		catch(Exception e){
			e.printStackTrace();
		}
		return "index";
	
	}  
	
	
	public String updateusr() {
		LoginDao updDao = new LoginDao();
		try {
			boolean x = updDao.update(updVo);
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Profile Updated",""));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return "UserDashboard";
	}
	
	public String editusr() {
		LoginDao updDao = new LoginDao();
		try {
			boolean x = updDao.edit(updVo);
//			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Profile Updated",""));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return "edit";
	}
		
	
	public void logout() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
    }
}
