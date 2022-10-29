package com.example.demo.Model;

import java.io.Serializable;
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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Trip")
@NamedQuery(name="Trip.findAll", query="SELECT t FROM Trip t")
public class Trip implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="SERVICE_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SERVICE_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private long id;

	@Column(name="status")
	private String status;
	
//	@Column(name = "isactive")
//	private boolean isactive;
	
	@JsonIgnore
	@OneToMany(mappedBy="trip_id")
	private List<Notifications_trip> n_t;
	
	@ManyToOne
	@JoinColumn(name="driver_id")
	private Driver driver_id;

	@ManyToOne
	@JoinColumn(name="truck_id")
	private Truck truck_id;
	
	@ManyToOne
	@JoinColumn(name="trailer_id")
	private Trailer trailer_id;
	
//	@ManyToOne
//	@JoinColumn(name="transporter_id")
//	private Transporter t_id;
	
	@OneToOne
	@JoinColumn(name="srn")
	private ServiceRequest srn;
	
	public Trip() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Notifications_trip> getN_t() {
		return n_t;
	}

	public void setN_t(List<Notifications_trip> n_t) {
		this.n_t = n_t;
	}

	public Driver getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(Driver driver_id) {
		this.driver_id = driver_id;
	}

	public Truck getTruck_id() {
		return truck_id;
	}

	public void setTruck_id(Truck truck_id) {
		this.truck_id = truck_id;
	}

	public Trailer getTrailer_id() {
		return trailer_id;
	}

	public void setTrailer_id(Trailer trailer_id) {
		this.trailer_id = trailer_id;
	}

	public ServiceRequest getSrn() {
		return srn;
	}

	public void setSrn(ServiceRequest srn) {
		this.srn = srn;
	}

//	public boolean isIsactive() {
//		return isactive;
//	}
//
//	public void setIsactive(boolean isactive) {
//		this.isactive = isactive;
//	}
	
	
}
