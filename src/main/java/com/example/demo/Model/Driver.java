package com.example.demo.Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Driver")
@NamedQuery(name="Driver.findAll", query="SELECT d FROM Driver d")
public class Driver implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="DRIVER_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DRIVER_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private long id;

	@Column(name="driver_name")
	private String d_name;

	@Column(name="phone")
	private String phone;
	
	@Column(name="nationality")
	private String nationality;
	
	@Column(name="id_proof")
	private String ID_proof;

	@Column(name="dl_no")
	private String DL_no;
	
	@Column(name="dl_expirydate")
	private LocalDate DL_expydate;
	
	@Column(name = "dl_status")
	private boolean dl_status;

	@Column(name="status")
	private boolean status;
	
	@Column(name = "isactive")
	private boolean isactive;
	
	public List<Trip> getTrip_id() {
		return trip_id;
	}

	public void setTrip_id(List<Trip> trip_id) {
		this.trip_id = trip_id;
	}

	@ManyToOne
	@JoinColumn(name="transporter_id")
	private Transporter t_id;
	
	@JsonIgnore
	@OneToMany(mappedBy="driver_id")
	private List<Trip> trip_id;
	
	public Driver() {
		
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getD_name() {
		return d_name;
	}

	public void setD_name(String d_name) {
		this.d_name = d_name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getID_proof() {
		return ID_proof;
	}

	public void setID_proof(String iD_proof) {
		ID_proof = iD_proof;
	}

	public String getDL_no() {
		return DL_no;
	}

	public void setDL_no(String dL_no) {
		DL_no = dL_no;
	}

	public LocalDate getDL_expydate() {
		return DL_expydate;
	}

	public void setDL_expydate(LocalDate dL_expydate) {
		DL_expydate = dL_expydate;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Transporter getT_id() {
		return t_id;
	}

	public void setT_id(Transporter t_id) {
		this.t_id = t_id;
	}

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public boolean isDl_status() {
		return dl_status;
	}

	public void setDl_status(boolean dl_status) {
		this.dl_status = dl_status;
	}
	

}