package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Transporter;

@Repository
public interface TransporterRepository extends JpaRepository<Transporter, Long> {

	Transporter findById(long id);
	List<Transporter> findByIsactive(boolean isactive);
	
	@Query("select u.id from Transporter u ")
	List<Long> getAllIds();

}
