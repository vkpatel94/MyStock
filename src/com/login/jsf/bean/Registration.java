package com.login.jsf.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dao.LoginDao;
import dao.RegistrationDao;
import vo.LoginVO;

@ManagedBean
@SessionScoped
public class Registration {
	
	LoginVO regVO = new LoginVO();
	
	LoginVO updVo = new LoginVO();

//	RegistrationVO regVO= new RegistrationVO();
//	
//	RegistrationVO updVo = new RegistrationVO();
//  
//	public RegistrationVO getRegVO() {
//		return regVO;
//	}
//
//	public void setRegVO(RegistrationVO regVO) {
//		this.regVO = regVO;
//	}
//	
//	public RegistrationVO getUpdVo() {
//		return updVo;
//	}
//
//	public void setUpdVo(RegistrationVO updVo) {
//		this.updVo = updVo;
//	}

	
	
	public LoginVO getUpdVo() {
		return updVo;
	}

	public LoginVO getRegVO() {
		return regVO;
	}

	public void setRegVO(LoginVO regVO) {
		this.regVO = regVO;
	}

	public void setUpdVo(LoginVO updVo) {
		this.updVo = updVo;
	}

	

	public String reguser()
	{
		RegistrationDao reg = new RegistrationDao();
		
		try {
			boolean x = reg.register(regVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "index";		
	}	
}