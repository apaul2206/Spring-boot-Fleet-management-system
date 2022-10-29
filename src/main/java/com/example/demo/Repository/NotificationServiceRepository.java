package com.example.demo.Repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Model.Notifications_service;
import com.example.demo.Model.ServiceRequest;

public interface NotificationServiceRepository extends JpaRepository<Notifications_service, Long> {

	@Query("select u from Notifications_service u where u.inserttime > ?1 and subtype=?2 order by u.inserttime desc")
	List<Notifications_service> getNewSrn(LocalDateTime dt , String subtype);
	
	@Query("select u from Notifications_service u where u.inserttime > ?1 order by u.inserttime desc")
	List<Notifications_service> getNewSrn(LocalDateTime dt);
	
	@Query("select u from Notifications_service u where u.inserttime > ?1 and u.srn in ?2 order by u.inserttime desc")
	List<Notifications_service> getSrnSatusFreight(LocalDateTime dt,List<ServiceRequest> srns);
	
	@Query("select u from Notifications_service u where u.srn in ?1 and u.type=?2 order by u.inserttime desc")
	List<Notifications_service> getFreightNoti(List<ServiceRequest> srns,String type);
	
	@Query("select u from Notifications_service u where u.srn in ?1 and u.type=?2 or u.subtype=?3 order by u.inserttime desc")
	List<Notifications_service> getTransporterNoti(List<ServiceRequest> srns, String type, String subtype);
}
