package com.example.demo.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Freight;
import com.example.demo.Model.Notifications_service;
import com.example.demo.Model.Notifications_trip;
import com.example.demo.Model.ServiceRequest;
import com.example.demo.Repository.Bid_statusRepository;
import com.example.demo.Repository.FreightRepository;
import com.example.demo.Repository.NotificationsTripRepository;

@Service
public class NotificationsTripServiceImpl implements NotificationsTripService {
	
	@Autowired
	FreightRepository freightRepository;
	
	@Autowired
	Bid_statusRepository bid_statusRepository;
	
	@Autowired
	NotificationsTripRepository notificationsTripRepository;

	@Override
	public List<HashMap<String, Object>> sendTripStatus(long c_id,String dt) {
		// TODO Auto-generated method stub
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime date_time= LocalDateTime.parse(dt, formatter);
		List<HashMap<String, Object>> response=new ArrayList<HashMap<String,Object>>();
		
		Freight fr=freightRepository.findById(c_id);
		
		List<ServiceRequest> srns=bid_statusRepository.getNotAcceptedSR(true,fr);
		
		List<Notifications_trip> notifications = notificationsTripRepository.getTripUpdates(date_time, srns);  
		
		for(Notifications_trip notification : notifications) {
			
			HashMap<String, Object> hashmap=new HashMap<>();
			hashmap.put("type", "trip");
			hashmap.put("status", notification.getTrip_id().getStatus());
			hashmap.put("srn", notification.getSrn().getSrn());
			hashmap.put("subtype", notification.getSubtype());
			hashmap.put("for", notification.getType());
			hashmap.put("description", notification.getDescription());
			hashmap.put("not_id", notification.getId());
			
			response.add(hashmap);
			
		}
		
		return response;	
		
	}
	
	@Override
	public List<HashMap<String, Object>> sendTripNoti(long c_id) {
		// TODO Auto-generated method stub
		List<HashMap<String, Object>> response=new ArrayList<HashMap<String,Object>>();
		
		Freight fr=freightRepository.findById(c_id);
		
		List<ServiceRequest> srns=bid_statusRepository.getFreightSR(fr);
		
		List<Notifications_trip> notifications = notificationsTripRepository.getTripNoti(srns);  
		
		for(Notifications_trip notification : notifications) {
			
			HashMap<String, Object> hashmap=new HashMap<>();
//			hashmap.put("type", "trip");
//			hashmap.put("status", notification.getTrip_id().getStatus());
			hashmap.put("srn", notification.getSrn().getSrn());
			hashmap.put("subtype", notification.getSubtype());
			hashmap.put("for", notification.getType());
			hashmap.put("description", notification.getDescription());
			hashmap.put("not_id", notification.getId());
			
			response.add(hashmap);
			
		}
		
		return response;	
	}

	@Override
	public String addNotification(Notifications_trip noti) {
		// TODO Auto-generated method stub
		Notifications_trip n = notificationsTripRepository.save(noti);
		if (n==null)
			return null;
		else
			return "success";
	}	

}
