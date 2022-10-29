package com.example.demo.Model;


import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Notifications_trip")
@NamedQuery(name="Notifications_trip.findAll", query="SELECT n FROM Notifications_trip n")
public class Notifications_trip implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="NOTIFICATIONS_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="NOTIFICATIONS_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private long id;

	@Column(name="type")
	private String type;

	@Column(name="subtype")
	private String subtype;
	
	@Column(name="description")
	private String description;
	
	@Column(name="insert_time")
	private LocalDateTime inserttime;
	
	@ManyToOne
	@JoinColumn(name="srn")
	private ServiceRequest srn;
	
	@ManyToOne
	@JoinColumn(name="trip_id")
	private Trip trip_id;
	
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

	public String getSubtype() {
		return subtype;
	}

	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getInserttime() {
		return inserttime;
	}

	public void setInserttime(LocalDateTime inserttime) {
		this.inserttime = inserttime;
	}

	public ServiceRequest getSrn() {
		return srn;
	}

	public void setSrn(ServiceRequest srn) {
		this.srn = srn;
	}

	public Trip getTrip_id() {
		return trip_id;
	}

	public void setTrip_id(Trip trip_id) {
		this.trip_id = trip_id;
	}

	
	
}
