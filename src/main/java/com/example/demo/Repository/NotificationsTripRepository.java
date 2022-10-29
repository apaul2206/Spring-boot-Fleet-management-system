package com.example.demo.Repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Model.Notifications_trip;
import com.example.demo.Model.ServiceRequest;

public interface NotificationsTripRepository extends JpaRepository<Notifications_trip, Long> {
	
	@Query("select u from Notifications_trip u where u.inserttime > ?1 and u.srn in ?2")
	List<Notifications_trip> getTripUpdates(LocalDateTime dt,List<ServiceRequest> srns);
	
	@Query("select u from Notifications_trip u where u.srn in ?1 order by u.inserttime desc")
	List<Notifications_trip> getTripNoti(List<ServiceRequest> srns);

//	select u from Notifications_trip u where insert_time > ?1 and srn in ?2
}

