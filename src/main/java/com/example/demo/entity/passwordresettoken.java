package com.example.demo.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "passwordresettoken")
public class passwordresettoken {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String token;
	private LocalDateTime expirydatetime;
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id", referencedColumnName = "id" , nullable = false)
	
	private client client;
	public long  getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public LocalDateTime getExpirydatetime() {
		return expirydatetime;
	}
	public void setExpirydatetime(LocalDateTime expirydatetime) {
		this.expirydatetime = expirydatetime;
	}
	public client getClient() {
		return client;
	}
	public passwordresettoken() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setClient(client client) {
		this.client = client;
	}
	@Override
	public String toString() {
		return "passwordresettoken [id=" + id + ", token=" + token + ", expirydatetime=" + expirydatetime + ", client="
				+ client + "]";
	}
	
	
}
