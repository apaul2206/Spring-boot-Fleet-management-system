package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Freight;
import com.example.demo.Model.ServiceRequest;

@Repository
public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Long> {
	
	ServiceRequest findBySrn(long id);
	List<ServiceRequest> findBySrnNotIn(List<Long> srns);
	
	@Query("Select u from ServiceRequest u where u.c_id=?1 order by u.DOS desc ")
	List<ServiceRequest> findByC_id(Freight cid);	
	
	@Query("Select u from ServiceRequest u where u.c_id=?1")
	List<ServiceRequest> findSrnByCidId(Freight c_id);
	
	@Query("Select u.srn from ServiceRequest u where u.c_id=?1")
	List<Long> findSrnByCid(Freight c_id);
}
