package com.example.demo.Service;

import java.util.HashMap;
import java.util.List;

import com.example.demo.Model.Truck;

public interface TruckService {

	List<HashMap<Object, Object>> viewByTransId(long tid);

	List<Long> listIDByTransId(long tid);

	Truck addTruck(Truck tr);

	List<Truck> getAll();

	String deletetruck(long id);

	List<Long> trucklist(long tid);

	
	List<Long> truckcount();

	List<HashMap<Object, Object>> viewtruckdetails(long tid);

	List<Long> totaltrucks(long tid);

	List<Long> getCount();


}
