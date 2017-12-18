package vo;

import javax.faces.context.FacesContext;

public class LoginVO {
	public String firstname;
	public String lastname;
	public String email;
	public String username;
	public String password;
	public int uid;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
		System.out.println("In vo firstname:"+firstname);
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
		System.out.println("In vo lastname:"+lastname);
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
		System.out.println("In vo email:"+email);
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
		System.out.println("In VO:"+uid);
	}
	
	public void refresh() {
		this.uid=(Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("uid");
		this.firstname=(String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("firstname");
		this.lastname=(String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("lastname");
		this.email=(String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("email");
		this.username=(String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
		this.password=(String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("password");
		
	}
	
	
	
	
}
