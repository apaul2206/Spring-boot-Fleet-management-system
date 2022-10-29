package com.example.demo.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Bid_status;
import com.example.demo.Model.Freight;
import com.example.demo.Model.Notifications_service;
import com.example.demo.Model.Notifications_trip;
import com.example.demo.Model.ServiceRequest;
import com.example.demo.Model.Transporter;
import com.example.demo.Repository.Bid_statusRepository;
import com.example.demo.Repository.FreightRepository;
import com.example.demo.Repository.NotificationServiceRepository;
import com.example.demo.Repository.ServiceRequestRepository;
import com.example.demo.Repository.TransporterRepository;

@Service
public class NotificationServiceServiceImpl implements NotificationServiceService {

	@Autowired
	NotificationServiceRepository notificationServiceRepository;
	
	@Autowired
	FreightRepository freightRepository;
	
	@Autowired
	ServiceRequestRepository serviceRequestRepository;
	
	@Autowired
	Bid_statusRepository bid_statusRepository;
	
	@Autowired
	TransporterRepository transporterRepository;
	
	@Override
	public List<HashMap<String, Object>> getSrnStatusFreight(long c_id,String dt) {
		// TODO Auto-generated method stub
		Freight fr= freightRepository.findById(c_id);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime date_time= LocalDateTime.parse(dt, formatter);
		List<HashMap<String, Object>> response=new ArrayList<HashMap<String,Object>>();
		
		List<ServiceRequest> srns=bid_statusRepository.getNotAcceptedSR(false,fr);
		
		List<Bid_status> bids=bid_statusRepository.getNotAcceptedBids(false,fr);
		
		List<Notifications_service> notifications = notificationServiceRepository.getSrnSatusFreight(date_time, srns);  
		
		for(Notifications_service notification : notifications) {
			
			HashMap<String, Object> hashmap=new HashMap<>();
			hashmap.put("type", "service");
//			hashmap.put("status", notification.get);
			hashmap.put("srn", notification.getSrn().getSrn());
			hashmap.put("subtype", notification.getSubtype());
			hashmap.put("for", notification.getType());
			hashmap.put("description", notification.getDescription());
			hashmap.put("not_id", notification.getId());
			
//			Bid_status bs=bid_statusRepository.getAcceptedBidBySrn(notification.getSrn());
//			if(bs!=null) {
//				hashmap.put("t_name", bs.getT_id().getT_name());
//				hashmap.put("t_id", bs.getT_id().getId());
//			}			
			response.add(hashmap);
			
		}		
		return response;		
		
	}
	
	@Override
	public List<HashMap<String, Object>> getFreightNoti(long c_id) {
		// TODO Auto-generated method stub
		Freight fr= freightRepository.findById(c_id);
		List<HashMap<String, Object>> response=new ArrayList<HashMap<String,Object>>();
		
		List<ServiceRequest> srns=bid_statusRepository.getFreightSR(fr);
		
		List<Notifications_service> notifications = notificationServiceRepository.getFreightNoti(srns,"freight");  
		
		for(Notifications_service notification : notifications) {
			
			HashMap<String, Object> hashmap=new HashMap<>();
//			hashmap.put("type", "service");
//			hashmap.put("status", notification.get);
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
	public List<HashMap<String, Object>> getSrnStatustransporter(long t_id,String dt) {
		// TODO Auto-generated method stub
		Transporter tr= transporterRepository.findById(t_id);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime date_time= LocalDateTime.parse(dt, formatter);
		List<HashMap<String, Object>> response=new ArrayList<HashMap<String,Object>>();
		
		List<ServiceRequest> srns=bid_statusRepository.getNotAcceptedSR(false);
		
		List<Notifications_service> notifications = notificationServiceRepository.getNewSrn(date_time);  
		
		for(Notifications_service notification : notifications) {
			
			HashMap<String, Object> hashmap=new HashMap<>();
			hashmap.put("type", "service");
//			hashmap.put("status", notification.get);
			hashmap.put("srn", notification.getSrn().getSrn());
			Bid_status bs= bid_statusRepository.getTransporterBid(notification.getSrn(), tr);
			if(bs==null)
			{
				hashmap.put("type2", "new record");
			}
			else {
				if(bs.isStatus())
					hashmap.put("type2", "acccepted");
				else {
					hashmap.put("type2","none");
				}
			}
			hashmap.put("subtype", notification.getSubtype());
			hashmap.put("for", notification.getType());
			hashmap.put("description", notification.getDescription());
			hashmap.put("not_id", notification.getId());
			hashmap.put("t_name", notification.getSrn().getC_id().getC_name());
			
			response.add(hashmap);
			
		}
		
		return response;
		//return null;
	}
	
	@Override
	public List<HashMap<String, Object>> getTransporterNoti(long t_id) {
		// TODO Auto-generated method stub
		Transporter tr= transporterRepository.findById(t_id);
		List<HashMap<String, Object>> response=new ArrayList<HashMap<String,Object>>();
		List<ServiceRequest> srns=bid_statusRepository.getTransporterSR(tr);
		
		List<Notifications_service> notifications = notificationServiceRepository.getTransporterNoti(srns,"transporter","new service request");  
		
		for(Notifications_service notification : notifications) {
			
			HashMap<String, Object> hashmap=new HashMap<>();

			hashmap.put("subtype", notification.getSubtype());
			hashmap.put("for", notification.getType());
			hashmap.put("description", notification.getDescription());
			hashmap.put("not_id", notification.getId());
						
			response.add(hashmap);
			
		}
		
		return response;

	}
	
	@Override
	public String addNotification(Notifications_service noti) {
		// TODO Auto-generated method stub
		Notifications_service n = notificationServiceRepository.save(noti);
		if (n==null)
			return null;
		else
			return "success"; 
	}

}
