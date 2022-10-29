package com.example.demo.Service;

import java.lang.annotation.Annotation;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Freight;
import com.example.demo.Model.Notifications_service;
import com.example.demo.Model.ServiceRequest;
import com.example.demo.Repository.Bid_statusRepository;
import com.example.demo.Repository.FreightRepository;
import com.example.demo.Repository.ServiceRequestRepository;


@Service
public class ServiceRequestServiceImpl implements ServiceRequestService {
	
	@Autowired
	ServiceRequestRepository serviceRequestRepository;
	
	@Autowired
	FreightRepository freightRepository;
	
	@Autowired
	Bid_statusRepository bid_statusRepository;
	
	@Autowired
	NotificationServiceService notificationServiceService;
	

	@Override
	public String addService(HashMap<String, Object> details) {
		// TODO Auto-generated method stub
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		ServiceRequest ser=new ServiceRequest();
		
		long f_id=Long.parseLong(details.get("f_id").toString());
		Freight frt=freightRepository.findById(f_id);
		if(frt==null)
			return "ERROR";
		ser.setC_id(frt);
		
		if(details.get("description")==null)
			return "No Description";
		
		ser.setDescription(details.get("description").toString());
		
//		if(details.get("DOS")==null)
//			return "No DOS";
//		
//		String date = details.get("DOS").toString();
//
//		//convert String to LocalDate
//		LocalDate localDate = LocalDate.parse(date, formatter);
		
		LocalDateTime dos=LocalDateTime.now();
		ser.setDOS(dos);
		
		if(details.get("commodity")==null)
			return "No commocity";
		ser.setCommodity(details.get("commodity").toString());
		
		if(details.get("tonnage")==null)
			return "No tonnage";
		ser.setTonnage(Float.parseFloat(details.get("tonnage").toString()));
		
		if(details.get("height")==null)
			return "no height";
		ser.setHeight(Float.parseFloat(details.get("height").toString()));
		
		if(details.get("width")==null)
			return "no width";
		ser.setWidth(Float.parseFloat(details.get("width").toString()));
		
		if(details.get("length")==null)
			return "no length";
		ser.setLength(Float.parseFloat(details.get("length").toString()));
		
		if(details.get("distance")==null)
			return "No distance";
		ser.setDist(Float.parseFloat(details.get("distance").toString()));
		
		if(details.get("pickup")==null)
			return "No pickup";
		ser.setPickup_loc(details.get("pickup").toString());
		
		if(details.get("dropoff")==null)
			return "No dropoff";
		ser.setDropoff_loc(details.get("dropoff").toString());
		
		if(details.get("pick_date")==null)
			return "NO pickup date";
		
		LocalDateTime p_date = LocalDateTime.parse(details.get("pick_date").toString(), formatter);
		ser.setPickup_date(p_date);
		
		if(details.get("drop_date")==null)
			return "NO dropoff date";
		LocalDateTime d_date = LocalDateTime.parse(details.get("drop_date").toString(), formatter);
		ser.setDropoff_date(d_date);
		
		Duration dur=  Duration.between(p_date, d_date);
		ser.setSla(dur.toDays());
		System.out.println(dur.toDays());
		 
		serviceRequestRepository.save(ser);
		
		notifyrequest(details,ser.getSrn());
		
		return "Success";
		
		
	}
	public boolean notifyrequest(HashMap<String, Object> body,long srn)
	{
		Notifications_service noti = new Notifications_service();
		noti.setType("transporter");
		noti.setSubtype("new service request");
		noti.setDescription(body.get("description").toString());
		//long srn = Long.parseLong(body.get("srn").toString());	
		ServiceRequest sr = serviceRequestRepository.findBySrn(srn);
		if(sr!=null)
			noti.setSrn(sr);
		
		
		LocalDateTime dt=LocalDateTime.now();
		noti.setInserttime(dt);
		
		String res= notificationServiceService.addNotification(noti);
		return true;
	}
	

	@Override
	public List<ServiceRequest> getNonAcceptedSR() {
		// TODO Auto-generated method stub
		List<Long> srns=new ArrayList<>();
		List<ServiceRequest> acc_sr=bid_statusRepository.getNotAcceptedSR(true);
		for(ServiceRequest sr : acc_sr) {
			srns.add(sr.getSrn());
		}
		if (acc_sr.size()==0)
			srns.add((long)-1);
		List<ServiceRequest> not_acc_sr=serviceRequestRepository.findBySrnNotIn(srns);
		
		if(not_acc_sr==null)
			return null;
		return not_acc_sr;
	}

	@Override
	public List<HashMap<Object, Object>> viewByClientId(long c_id) {
		// TODO Auto-generated method stub
		Freight fr = freightRepository.findById(c_id);
		
		List<HashMap<Object, Object>> res=new ArrayList<>();
		List<ServiceRequest> obj = serviceRequestRepository.findByC_id(fr);
		
		 System.out.println(obj);
		 for(int i=0;i<obj.size();i++)
		 {
			 HashMap<Object, Object> var=new HashMap<>();
			 var.put("srn",obj.get(i).getSrn());
			 var.put("description", obj.get(i).getDescription());
			 var.put("service_date", obj.get(i).getDOS());
			 var.put("commodity", obj.get(i).getCommodity());
			 var.put("tonnage", obj.get(i).getTonnage());
			 var.put("width", obj.get(i).getWidth());
			 var.put("height", obj.get(i).getHeight());
			 var.put("length", obj.get(i).getLength());
			 var.put("pickup_loc", obj.get(i).getPickup_loc());
			 var.put("dropoff_loc", obj.get(i).getDropoff_loc());
			 var.put("distance", obj.get(i).getDist());
			 var.put("pickup_date", obj.get(i).getPickup_date());
			 var.put("dropoff_date", obj.get(i).getDropoff_date());
			 
			 res.add(var);
		 }
		 return res;
	
	}

	@Override
	public List<Long> getAllsrnByCid(long c_id) {
		// TODO Auto-generated method stub
		Freight fr = freightRepository.findById(c_id);
		
		List<Long> obj = serviceRequestRepository.findSrnByCid(fr);
		return obj;
	}

}
