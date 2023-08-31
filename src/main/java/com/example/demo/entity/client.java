package com.example.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
public class client {
@Id 
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String name;
private String address;
private String email;
private String phoneNumber;
private String role;

@OneToOne(mappedBy = "client" , cascade = CascadeType.ALL,fetch = FetchType.EAGER)
private passwordresettoken passwordresettoken;


private String password;

public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPhoneNumber() {
	return phoneNumber;
}
public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
}
public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}

public client() {
	super();
	// TODO Auto-generated constructor stub
}
@Override
public String toString() {
	return "client [id=" + id + ", name=" + name + ", address=" + address + ", email=" + email + ", phoneNumber="
			+ phoneNumber + ", role=" + role + ", password=" + password + "]";
}

	
}
