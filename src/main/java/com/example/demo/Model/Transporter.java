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
@Table(name="Transporter")
@NamedQuery(name="Transporter.findAll", query="SELECT t FROM Transporter t")
public class Transporter implements Serializable{
private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="TRANSPORTER_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TRANSPORTER_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private long id;

	@Column(name="t_name")
	private String t_name;

	@Column(name="person_name")
	private String person_name;
	
	@Column(name="telephone")
	private String telephone;
	
	@Column(name="email")
	private String email;
	
	@Column(name="address")
	private String addr;

	@Column(name="level")
	private String level;
	
	@Column(name = "isactive")
	private boolean isactive;
	
	@JsonIgnore
	@OneToMany(mappedBy="t_id")
	private List<Bid_status> bid_id;

	@JsonIgnore
	@OneToMany(mappedBy="t_id")
	private List<Driver> driver_id;
	
	@JsonIgnore
	@OneToMany(mappedBy="t_id")
	private List<Truck> truck_id;
	
	@JsonIgnore
	@OneToMany(mappedBy="t_id")
	private List<Trailer> trailer_id;

	@JsonIgnore
	@OneToOne(mappedBy="transporter_id")
	private Login login_id;
	
	public Transporter() {
		
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getT_name() {
		return t_name;
	}

	public void setT_name(String t_name) {
		this.t_name = t_name;
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

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public List<Trailer> getTrailer_id() {
		return trailer_id;
	}

	public void setTrailer_id(List<Trailer> trailer_id) {
		this.trailer_id = trailer_id;
	}
//
	public List<Bid_status> getBid_id() {
		return bid_id;
	}

	public void setBid_id(List<Bid_status> bid_id) {
		this.bid_id = bid_id;
	}
//
	public List<Driver> getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(List<Driver> driver_id) {
		this.driver_id = driver_id;
	}

	public List<Truck> getTruck_id() {
		return truck_id;
	}

	public void setTruck_id(List<Truck> truck_id) {
		this.truck_id = truck_id;
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
