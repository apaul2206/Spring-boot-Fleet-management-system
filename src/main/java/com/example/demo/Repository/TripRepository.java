package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Driver;
import com.example.demo.Model.Freight;
import com.example.demo.Model.ServiceRequest;
import com.example.demo.Model.Transporter;
import com.example.demo.Model.Trip;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
	
	Trip findById(long id);
	Trip findBySrn(ServiceRequest sr);
	
	@Query("Select u from Trip u where u.driver_id in ?1 and u.status!=?2")
	List<Trip> findByDriver_idIn(List<Driver> dr , String s);
	
	
    @Query("Select count(u) from Trip u where u.driver_id in ?1")
	Long getCount(List<Driver> dr);
	
	@Query("Select count(u) from Trip u where u.driver_id in ?1 and u.status!= 'delevered'")
	Long getCount1(List<Driver> dr);

	@Query("Select count(u) from Trip u where u.srn in ?1 and u.status!= 'delevered'")
	Long getfreightcount(List<ServiceRequest> dr);

	
//	List<Long> getCount(Transporter id);
//	@Query("select count(*) from Trip where transporter_id = 101")
//	List<Long> getCount1();
//	
	
//	@Query("select count(*) from Trip where transporter_id = 101 && isactive = 1")
//	List<Long> getCount();
	
}

