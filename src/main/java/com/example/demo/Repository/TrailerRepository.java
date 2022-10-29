package com.example.demo.Repository;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Trailer;
import com.example.demo.Model.Transporter;

@Repository
public interface TrailerRepository extends JpaRepository<Trailer, Long> {

	Trailer findById(long id);
	List<Trailer> findByIsactive(boolean isactive);
	
	@Query("Select u from Trailer u where u.t_id=?1")
	List<Trailer> findByT_id(Transporter tr);
	
	@Query("Select u.id from Trailer u where u.t_id=?1 and u.status=true")
	List<Long> findInactiveIDByTid(Transporter tr);
	
	@Query("select count(*) from Trailer where transporter_id = 101")
	List<Long> getCount();
	
	
	@Query("select count(*) from Trailer where tid=?1")

	List<Long> getCount(Transporter id);
	
	@Modifying
	@Transactional
	@Query("Update Trailer u set u.vl_status=false where u.VL_expydate< ?1")
	void updateVLstatus(LocalDate cur);
	
	@Modifying
	@Transactional
	@Query("Update Trailer u set u.rw_status=false where u.RW_expydate< ?1")
	void updateRWstatus(LocalDate cur);
}
