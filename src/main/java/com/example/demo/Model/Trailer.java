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
@Table(name="Trailer")
@NamedQuery(name="Trailer.findAll", query="SELECT t FROM Trailer t")
public class Trailer implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="TRAILER_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TRAILER_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private long id;

	@Column(name="type")
	private String type;
	
	@Column(name="registration")
	private String registration;
	
	@Column(name="vl")
	private LocalDate VL_expydate;
	
	@Column(name = "vl_status")
	private boolean vl_status;

	@Column(name="roadworthy_expirydate")
	private LocalDate RW_expydate;

	@Column(name = "rw_status")
	private boolean rw_status;

	@Column(name="status")
	private boolean status;
	
	@Column(name = "isactive")
	private boolean isactive;
	
	@ManyToOne
	@JoinColumn(name="transporter_id")
	private Transporter t_id;

	@JsonIgnore
	@OneToMany(mappedBy="trailer_id")
	private List<Trip> trip_id;
	
	public Trailer() {
		
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRegistration() {
		return registration;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
	}

	public LocalDate getVL_expydate() {
		return VL_expydate;
	}

	public void setVL_expydate(LocalDate vL_expydate) {
		VL_expydate = vL_expydate;
	}

	public LocalDate getRW_expydate() {
		return RW_expydate;
	}

	public void setRW_expydate(LocalDate rW_expydate) {
		RW_expydate = rW_expydate;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Transporter getT_id() {
		return t_id;
	}

	public void setT_id(Transporter t_id) {
		this.t_id = t_id;
	}

	public List<Trip> getTrip_id() {
		return trip_id;
	}

	public void setTrip_id(List<Trip> trip_id) {
		this.trip_id = trip_id;
	}

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public boolean isVl_status() {
		return vl_status;
	}

	public void setVl_status(boolean vl_status) {
		this.vl_status = vl_status;
	}

	public boolean isRw_status() {
		return rw_status;
	}

	public void setRw_status(boolean rw_status) {
		this.rw_status = rw_status;
	}
	
	
}

