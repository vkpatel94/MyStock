package vo;

import javax.faces.context.FacesContext;

public class ManagerLoginVO {
	
	public String username;
	public String password;
	public String firstname;
	public String lastname;
	public String email;
	public int commission;
	public String approved;
	public int mid;
	public int amount;
	
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
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	
	
	public int getCommission() {
		return commission;
	}
	public void setCommission(int commission) {
		this.commission = commission;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	
	public String getApproved() {
		return approved;
	}
	public void setApproved(String approved) {
		this.approved = approved;
	}
	
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public void refresh() {
		this.mid=(Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("mid");
		this.firstname=(String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("firstname");
		this.lastname=(String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("lastname");
		this.email=(String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("email");
		this.commission= (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("commission");
		this.username=(String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
		this.password=(String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("password");
		this.approved=(String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("approved");
	}

}
