package com.example.demo.Model;

import java.io.Serializable;
import java.time.Duration;
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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Service")
@NamedQuery(name="Service.findAll", query="SELECT s FROM ServiceRequest s")
public class ServiceRequest implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="SERVICE_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SERVICE_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private long srn;

	@Column(name="description")
	private String description;

	@Column(name="service_date")
	private LocalDateTime DOS;
	
	@Column(name="commodity")
	private String commodity;
	
	@Column(name="tonnage")
	private float tonnage;

	@Column(name="width")
	private float width;
	

	@Column(name="height")
	private float height;

	@Column(name="length")
	private float length;

	@Column(name="pickup_loc")
	private String pickup_loc;
	
	@Column(name="dropoff_loc")
	private String dropoff_loc;
	
	@Column(name="distance")
	private float dist;

	@Column(name="pickup_date")
	private LocalDateTime pickup_date;
	
	@Column(name="dropoff_date")
	private LocalDateTime dropoff_date;

	@Column(name="sla")
	private long sla;
	
	//@JsonIgnore
	@ManyToOne
	@JoinColumn(name="client_id")
	private Freight c_id;

	@JsonIgnore
	@OneToMany(mappedBy="srn")
	private List<Notifications_service> n_s;
	
	@JsonIgnore
	@OneToMany(mappedBy="srn")
	private List<Notifications_trip> n_t;
	
	@JsonIgnore
	@OneToMany(mappedBy="srn")
	private List<Bid_status> bid_id;
	
	@JsonIgnore
	@OneToOne(mappedBy="srn")
	private Trip trip_id;
	
	public ServiceRequest() {
		
	}

	public long getSrn() {
		return srn;
	}

	public void setSrn(long srn) {
		this.srn = srn;
	}


	public LocalDateTime getDOS() {
		return DOS;
	}

	public void setDOS(LocalDateTime dOS) {
		DOS = dOS;
	}

	public String getCommodity() {
		return commodity;
	}

	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}

	public float getTonnage() {
		return tonnage;
	}

	public void setTonnage(float tonnage) {
		this.tonnage = tonnage;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getLength() {
		return length;
	}

	public void setLength(float length) {
		this.length = length;
	}

	public String getPickup_loc() {
		return pickup_loc;
	}

	public void setPickup_loc(String pickup_loc) {
		this.pickup_loc = pickup_loc;
	}

	public String getDropoff_loc() {
		return dropoff_loc;
	}

	public void setDropoff_loc(String dropoff_loc) {
		this.dropoff_loc = dropoff_loc;
	}

	public float getDist() {
		return dist;
	}

	public void setDist(float dist) {
		this.dist = dist;
	}

	public LocalDateTime getPickup_date() {
		return pickup_date;
	}

	public void setPickup_date(LocalDateTime pickup_date) {
		this.pickup_date = pickup_date;
	}

	public LocalDateTime getDropoff_date() {
		return dropoff_date;
	}

	public void setDropoff_date(LocalDateTime dropoff_date) {
		this.dropoff_date = dropoff_date;
	}

	public Freight getC_id() {
		return c_id;
	}

	public void setC_id(Freight c_id) {
		this.c_id = c_id;
	}

	public List<Notifications_service> getN_s() {
		return n_s;
	}

	public void setN_s(List<Notifications_service> n_s) {
		this.n_s = n_s;
	}

	public List<Notifications_trip> getN_t() {
		return n_t;
	}

	public void setN_t(List<Notifications_trip> n_t) {
		this.n_t = n_t;
	}

	public List<Bid_status> getBid_id() {
		return bid_id;
	}

	public void setBid_id(List<Bid_status> bid_id) {
		this.bid_id = bid_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Trip getTrip_id() {
		return trip_id;
	}

	public void setTrip_id(Trip trip_id) {
		this.trip_id = trip_id;
	}

	public long getSla() {
		return sla;
	}

	public void setSla(long l) {
		this.sla = l;
	}


	
}
