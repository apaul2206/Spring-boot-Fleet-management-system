package com.example.demo.Repository;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Driver;
import com.example.demo.Model.Transporter;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
	
	Driver findById(long id);
	List<Driver> findByIsactive(boolean isactive);
	
	@Query("Select u from Driver u where u.t_id=?1")
	List<Driver> findByT_id(Transporter dr);

	@Query("Select u from Driver u where u.t_id=?1 and u.status=false")
	List<Driver> findByActiveT_id(Transporter dr);

	@Query("Select u.id from Driver u where u.t_id=?1 and u.status=true")
	List<Long> findInactiveIDByTid(Transporter tr);
	
	@Modifying
	@Transactional
	@Query("Update Driver u set u.dl_status=false where u.DL_expydate< ?1")
	void updateDLstatus(LocalDate cur);
}
