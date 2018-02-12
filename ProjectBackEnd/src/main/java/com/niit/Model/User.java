package com.niit.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "details")
public class User {

	@Id
	private String user_Name;
	private String use_password;
	private String user_firstName;
	private String user_lastName;
	private long user_phoneNumber;
	private String user_role;
	private boolean user_online;
	@Column(unique = true, nullable = false)
	private String user_email;

	public String getUser_Name() {
		return user_Name;
	}

	public void setUser_Name(String user_Name) {
		this.user_Name = user_Name;
	}

	public String getUse_password() {
		return use_password;
	}

	public void setUse_password(String use_password) {
		this.use_password = use_password;
	}

	public String getUser_firstName() {
		return user_firstName;
	}

	public void setUser_firstName(String user_firstName) {
		this.user_firstName = user_firstName;
	}

	public String getUser_lastName() {
		return user_lastName;
	}

	public void setUser_lastName(String user_lastName) {
		this.user_lastName = user_lastName;
	}

	public long getUser_phoneNumber() {
		return user_phoneNumber;
	}

	public void setUser_phoneNumber(long user_phoneNumber) {
		this.user_phoneNumber = user_phoneNumber;
	}

	public String getUser_role() {
		return user_role;
	}

	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}

	public boolean isUser_online() {
		return user_online;
	}

	public void setUser_online(boolean user_online) {
		this.user_online = user_online;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

}
