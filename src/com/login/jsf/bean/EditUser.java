package com.login.jsf.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import dao.EditUserDao;
import vo.LoginVO;

@ManagedBean(name="edituser")
public class EditUser {
	
	EditUserDao editdao = new EditUserDao();
	LoginVO editvo = new LoginVO();
	
	public String editProfile()
	{
		String userinfo = editdao.edit(editvo);
		System.out.println(userinfo);
		return userinfo;

	}
}
