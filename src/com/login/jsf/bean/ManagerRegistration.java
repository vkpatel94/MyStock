package com.login.jsf.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dao.ManagerRegistrationDao;
import vo.ManagerLoginVO;

@ManagedBean(name="manager")
@SessionScoped
public class ManagerRegistration {
	
	ManagerLoginVO regVO= new ManagerLoginVO();
	
	public ManagerLoginVO getRegVO() {
		return regVO;
	}

	public void setRegVO(ManagerLoginVO regVO) {
		this.regVO = regVO;
	}



	public String reguser()
	{
		ManagerRegistrationDao reg = new ManagerRegistrationDao();
		
		try {
			boolean x = reg.register(regVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "index";
	}
}
