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
@Table(name="Bid_status")
@NamedQuery(name="Bid_status.findAll", query="SELECT b FROM Bid_status b")
public class Bid_status implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="BID_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BID_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private long id;

	@Column(name="status")
	private boolean status;

	@Column(name="response_date")
	private LocalDateTime DOR;
	
	@Column(name="bid_amt")
	private float bid_amt;

	@Column(name="accepted_date")
	private LocalDateTime acc_dt;
	
	@Column(name="confirmed_date")
	private LocalDateTime comfirmed_dt;
	
	@ManyToOne
	@JoinColumn(name="srn")
	private ServiceRequest srn;
	
	@ManyToOne
	@JoinColumn(name="transporter_id")
	private Transporter t_id;
	
	@ManyToOne
	@JoinColumn(name="client_id")
	private Freight c_id;
	
	@JsonIgnore
	@OneToMany(mappedBy="bid_id")
	private List<Notifications_service> b_id;
	
	public Bid_status(){
		acc_dt=null;
		status=false;
		
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public LocalDateTime getDOR() {
		return DOR;
	}

	public void setDOR(LocalDateTime dOR) {
		DOR = dOR;
	}

	public float getBid_amt() {
		return bid_amt;
	}

	public void setBid_amt(float bid_amt) {
		this.bid_amt = bid_amt;
	}

	public LocalDateTime getAcc_dt() {
		return acc_dt;
	}

	public void setAcc_dt(LocalDateTime acc_dt) {
		this.acc_dt = acc_dt;
	}

	public ServiceRequest getSrn() {
		return srn;
	}

	public void setSrn(ServiceRequest srn) {
		this.srn = srn;
	}

	public Transporter getT_id() {
		return t_id;
	}

	public void setT_id(Transporter t_id) {
		this.t_id = t_id;
	}

	public Freight getC_id() {
		return c_id;
	}

	public void setC_id(Freight c_id) {
		this.c_id = c_id;
	}

	public LocalDateTime getComfirmed_dt() {
		return comfirmed_dt;
	}

	public void setComfirmed_dt(LocalDateTime comfirmed_dt) {
		this.comfirmed_dt = comfirmed_dt;
	}

	
}
