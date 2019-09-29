package com.goar.otrs.userservice.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class User extends BaseEntity<String> {

	@Column(name = "USERNAME")
	private String username;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "ENABLED")
	private boolean enabled;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "CITY")
	private String city;

	@Column(name = "PHONE_NO")
	private String phoneNo;

	public User() {
		super();
	}

	public User(String id, String name) {
		super(id, name);
	}

	public User(String username, String password, boolean enabled, String email, String address, String city,
			String phoneNo) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.email = email;
		this.address = address;
		this.city = city;
		this.phoneNo = phoneNo;
	}

	public static User getDummyUser() {
		return new User(null, null);
	}

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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

}
