package com.example.demo.Service;

import java.util.HashMap;
import java.util.List;

import com.example.demo.Model.ServiceRequest;

public interface ServiceRequestService {
	
	String addService(HashMap<String, Object> details);
	List<ServiceRequest> getNonAcceptedSR();
	
	List<HashMap<Object, Object>> viewByClientId(long c_id);	
	List<Long> getAllsrnByCid(long c_id);

}
