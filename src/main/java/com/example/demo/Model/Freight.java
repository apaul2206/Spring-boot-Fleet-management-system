package com.example.demo.Model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Freight")
@NamedQuery(name="Freight.findAll", query="SELECT f FROM Freight f")
public class Freight implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="FREIGHT_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FREIGHT_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private long id;

	@Column(name="company_name")
	private String c_name;

	@Column(name="person_name")
	private String person_name;
	
	@Column(name="telephone")
	private String telephone;
	
	@Column(name="email")
	private String email;
	
	@Column(name="address")
	private String addr;	

	@Column(name = "isactive")
	private boolean isactive;
	
	@JsonIgnore
	@OneToMany(mappedBy="c_id")
	private List<ServiceRequest> srn;
	
	@JsonIgnore
	@OneToMany(mappedBy="c_id")
	private List<Bid_status> bid_id;
	
	@JsonIgnore
	@OneToOne(mappedBy="freight_id")
	private Login login_id;
	
	public Freight() {
		
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	public String getPerson_name() {
		return person_name;
	}

	public void setPerson_name(String person_name) {
		this.person_name = person_name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public List<ServiceRequest> getSrn() {
		return srn;
	}

	public void setSrn(List<ServiceRequest> srn) {
		this.srn = srn;
	}

	public List<Bid_status> getBid_id() {
		return bid_id;
	}

	public void setBid_id(List<Bid_status> bid_id) {
		this.bid_id = bid_id;
	}

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public Login getLogin_id() {
		return login_id;
	}

	public void setLogin_id(Login login_id) {
		this.login_id = login_id;
	}
	
	
}