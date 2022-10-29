package com.example.demo.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Login")
@NamedQuery(name="Login.findAll", query="SELECT u FROM Login u")
public class Login {
	@Id
	@Column (name = "username", unique = true,  nullable=false)
	private String username;	
	
	@Column (name = "password")
	private String password;
	
	@Column (name = "type")
	private String type;
	
	@Column (name = "emailID")
	private String emailID;
	
	@OneToOne
	@JoinColumn(name="freight_id")
	private Freight freight_id;
	
	@OneToOne
	@JoinColumn(name="transporter_id")
	private Transporter transporter_id;

	public Login() {
		freight_id=null;
		transporter_id=null;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public Freight getFreight_id() {
		return freight_id;
	}

	public void setFreight_id(Freight freight_id) {
		this.freight_id = freight_id;
	}

	public Transporter getTransporter_id() {
		return transporter_id;
	}

	public void setTransporter_id(Transporter transporter_id) {
		this.transporter_id = transporter_id;
	}
	
	
	
}
