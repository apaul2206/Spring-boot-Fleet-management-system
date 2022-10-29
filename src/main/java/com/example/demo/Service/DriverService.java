package com.example.demo.Service;

import java.util.HashMap;
import java.util.List;

import com.example.demo.Model.Driver;
import com.example.demo.Model.Transporter;

public interface DriverService {

	String toggleStatus(long id);
	
	List<HashMap<Object, Object>> viewByTransId(long tid);

	List<Long> listIDByTransId(long tid);

	List<HashMap<Object, Object>> viewByDriverId(long tid);

	Driver addDriver(Driver dr);

	List<Driver> getAll();

	String deletedriver(long id);


}
