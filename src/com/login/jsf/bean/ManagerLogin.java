package com.login.jsf.bean;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dao.LoginDao;
import dao.ManagerLoginDao;
import dao.dbconnection;
import vo.ManagerLoginVO;;

@ManagedBean(name="managerlogin")
@SessionScoped
public class ManagerLogin {

	ManagerLoginVO mloginvo= new ManagerLoginVO();
	
	ManagerLoginVO mupdVO = new ManagerLoginVO();
	
	ManagerLoginVO apVO = new ManagerLoginVO();
	
	ManagerLoginVO app = new ManagerLoginVO();
	
	ManagerLoginDao access = new ManagerLoginDao();
	

	public ManagerLoginVO getMloginvo() {
		return mloginvo;
	}

	public void setMloginvo(ManagerLoginVO mloginvo) {
		this.mloginvo = mloginvo;
	}

	public ManagerLoginVO getMupdVO() {
		return mupdVO;
	}

	public void setMupdVO(ManagerLoginVO mupdVO) {
		this.mupdVO = mupdVO;
	}

	
	
	public ManagerLoginVO getApVO() {
		return apVO;
	}

	public void setApVO(ManagerLoginVO apVO) {
		this.apVO = apVO;
	}

	public ManagerLoginVO getApp() {
		return app;
	}

	public void setApp(ManagerLoginVO app) {
		this.app = app;
	}

	public String SuccessLogin()
	{
		
		try {
			Boolean auth = access.managerlogon(mloginvo.username, mloginvo.password);
			mupdVO.refresh();
			if(auth) {
			
//				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", loginvo.getUsername());
				return "ManagerDashboard";
			}
			else {
//				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("error", "Verify Username or Password");
			}
		}	
		catch(Exception e){
			e.printStackTrace();
		}
		return "ManagerLogin";
	}
	
	public String updatemng() {
		ManagerLoginDao manDao = new ManagerLoginDao();
		try {
			boolean x = manDao.update(mupdVO);
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Profile Updated",""));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return "ManagerDashboard";
	}
	
	public String editmng() {
		ManagerLoginDao manDao = new ManagerLoginDao();
		try {
			boolean x = manDao.edit(mupdVO);
//			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Profile Updated",""));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return "ManagerUpdate";
	}
	
	
	public void manager() {
		try {
		ManagerLoginDao manAp = new ManagerLoginDao();
		ArrayList x = manAp.managerauth(apVO);
	}
	catch(Exception e) {
		e.printStackTrace();
	}
}
	
	public String managerlist() {
		try {
		ManagerLoginDao manAp = new ManagerLoginDao();
		ArrayList x = manAp.managerauth(apVO);
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	return "ListofManagers";
}
	
//	public ArrayList manager() {
//
//		ManagerLoginDao manAp = new ManagerLoginDao();
//		ArrayList x = manAp.managerauth(apVO);
//		return x;
//}
	
	public void approvemanager(String u) {
		try {
			System.out.println("Username in approve method:"+u);
			ManagerLoginDao approved = new ManagerLoginDao();
			boolean approval = approved.approve(u);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void declinemanager(String u) {
		try {
			System.out.println("Username in decline method:"+u);
			ManagerLoginDao Mandecline = new ManagerLoginDao();
			boolean dec = Mandecline.decline(u);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void selectmanager(int u) {
		try {
			ManagerLoginDao select = new ManagerLoginDao();
			boolean x = select.SelectManager(u);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public String redirect() {
		return "RequestManager";
	}
	
	public void reqManager(int u_id, int m_id, int amt) {
		try {
			ManagerLoginDao req = new ManagerLoginDao();
			boolean x = req.request(u_id,m_id,amt);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void logout() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesContext.getCurrentInstance().getExternalContext().redirect("ManagerLogin.xhtml");
    }
	
	
	
}
