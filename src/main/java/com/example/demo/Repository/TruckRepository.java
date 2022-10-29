package com.example.demo.Repository;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Transporter;
import com.example.demo.Model.Truck;

@Repository
public interface TruckRepository extends JpaRepository<Truck, Long>{
	
	Truck findById(long id);
	List<Truck> findByIsactive(boolean isactive);
	
	@Query("Select u from Truck u where u.t_id=?1")
	List<Truck> findByT_id(Transporter tr);

	@Query("Select u.id from Truck u where u.t_id=?1 and u.status=true")
	List<Long> findInactiveIDByTid(Transporter tr);
	
//	@Query("select count(*) from Truck where transporter_id ='transporter_id '")
//	List<Long> getCount();
//	
	@Query("select count(*) from Truck where tid=?1")

	List<Long> getCount(Transporter id);
	

	@Modifying
	@Transactional
	@Query("Update Truck u set u.vl_status=false where u.VL_expydate< ?1")
	void updateVLstatus(LocalDate cur);
	
	@Modifying
	@Transactional
	@Query("Update Truck u set u.rw_status=false where u.RW_expydate< ?1")
	void updateRWstatus(LocalDate cur);
}
