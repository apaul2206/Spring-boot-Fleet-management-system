package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Driver;
import com.example.demo.Model.Freight;
import com.example.demo.Model.ServiceRequest;
import com.example.demo.Model.Transporter;

@Repository
public interface FreightRepository extends JpaRepository<Freight, Long> {
	
	Freight findById(long id);
	List<Freight> findByIsactive(boolean isactive);
	

	@Query("Select u from Driver u where u.t_id=?1 and u.status=false")
	List<Freight> findByActiveT_id(ServiceRequest tr);
	
	
}
