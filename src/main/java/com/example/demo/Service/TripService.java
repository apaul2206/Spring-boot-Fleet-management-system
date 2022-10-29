package com.example.demo.Service;

import java.util.HashMap;
import java.util.List;

public interface TripService {
	
	String assignTrip(long driver_id,long truck_id,long trailer_id,long srn);
	List<HashMap<Object, Object>> viewActiveTripByTransId(long tid);
	List<Long> listSRNByTransId(long tid);
	String updatetripstatus(long tripid, String string);
	List<Long> trailercount1();
	List<Long> trailercount();
	Long totaltrip(long tid);
	Long totalactivetrip(long tid);
	Long totalactivefreighttrip(long tid);

}
