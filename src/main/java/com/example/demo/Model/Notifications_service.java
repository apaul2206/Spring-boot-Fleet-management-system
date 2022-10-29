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
@Table(name="Notifications_service")
@NamedQuery(name="Notifications_service.findAll", query="SELECT n FROM Notifications_service n")
public class Notifications_service implements Serializable{
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
	@JoinColumn(name="bid_id")
	private Bid_status bid_id;	
	
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

	public Bid_status getBid_id() {
		return bid_id;
	}

	public void setBid_id(Bid_status bid_id) {
		this.bid_id = bid_id;
	}

	
}

