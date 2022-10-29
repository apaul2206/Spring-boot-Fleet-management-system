package com.example.demo.Repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Bid_status;
import com.example.demo.Model.Freight;
import com.example.demo.Model.ServiceRequest;
import com.example.demo.Model.Transporter;



@Repository
public interface Bid_statusRepository extends JpaRepository<Bid_status, Long>{

	Bid_status findById(long id);
	
	List<Bid_status> findBySrn(ServiceRequest sr);
	
	@Query("select bs from Bid_status bs where bs.srn not in ?1 and bs.c_id=?2 order by bs.DOR desc")
	List<Bid_status> findBySrnNotInC_id(List<ServiceRequest> sr,Freight fr);
	
	@Query("select bs from Bid_status bs where status=?1 and c_id=?2 order by bs.acc_dt desc")
	List<Bid_status> findByStatusAndC_id(boolean st,Freight f);
	
	@Query("select bs.srn from Bid_status bs where status=?1 order by bs.DOR desc")
	List<ServiceRequest> getNotAcceptedSR(boolean st);
	
	@Query("select bs.srn from Bid_status bs where status=?1 and bs.c_id=?2 order by bs.DOR desc")
	List<ServiceRequest> getNotAcceptedSR(boolean st,Freight c_id);
	
	@Query("select bs.srn from Bid_status bs where bs.c_id=?1 order by bs.DOR desc")
	List<ServiceRequest> getFreightSR(Freight c_id);
	
	@Query("select bs from Bid_status bs where bs.c_id=?1 order by bs.DOR desc")
	List<Bid_status> getFreightBids(Freight c_id);
	
	@Query("select bs from Bid_status bs where status=?1 and bs.c_id=?2")
	List<Bid_status> getNotAcceptedBids(boolean st,Freight c_id);
	
	@Query("select bs from Bid_status bs where bs.srn=?1 and bs.t_id=?2")
	Bid_status getTransporterBid(ServiceRequest srn,Transporter t_id);
	
	@Query("select bs from Bid_status bs where bs.srn=?1")
	Bid_status getAcceptedBidBySrn(ServiceRequest srn);
	
//	@Query("SELECT wgh.grpid from WorkerGrouphirarchy wgh WHERE wgh.parentgrpid=:parentid")
//	Collection<WorkerGroup> getChildren(@Param("parentid")  WorkerGroup parentid);
//	@Query("Select w.id from WorkerGroup w where w.grptype!=?1 and w.isactive=1")
//	List<Long> findByParentGrpid(String type);
//	4444
	
	@Query("Select u from Bid_status u where u.c_id=?1 and u.status=true order by u.DOR desc")
	List<Bid_status> findByC_id(Freight cid);

	@Query("Select u.srn from Bid_status u where u.t_id=?1 and u.status=true")
	List<ServiceRequest> findAcceptedSRNByTid(Transporter tr);

	@Query("select bs.srn from Bid_status bs where bs.t_id=?1 order by bs.DOR desc")
	List<ServiceRequest> getTransporterSR(Transporter tr);

}
