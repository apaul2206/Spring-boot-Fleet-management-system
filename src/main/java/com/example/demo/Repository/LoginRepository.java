package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Login;
import com.example.demo.Model.Transporter;

@Repository
public interface LoginRepository extends JpaRepository<Login, String>{

	Login findByUsernameAndPassword(String username, String password);
	
//	@Modifying
//	@Query("delete from Login u where u.transporter_id=?1")
//	int deleteByTransporter_id(Transporter id);

}
