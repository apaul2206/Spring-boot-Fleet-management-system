package com.example.demo.Controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Model.Notifications_service;
import com.example.demo.Model.ServiceRequest;
import com.example.demo.Repository.ServiceRequestRepository;
import com.example.demo.Service.NotificationServiceService;
import com.example.demo.Service.ServiceRequestService;

@CrossOrigin
@Controller
@RequestMapping("/service")
public class ServiceController {
	
	@Autowired
	ServiceRequestService serviceRequestService;
	
	@Autowired
	ServiceRequestRepository serviceRequestRepository;
	
	@Autowired
	NotificationServiceService notificationServiceService;
	
	
	
	@PostMapping("/request")
	public ResponseEntity<HashMap<String, String>> sendRequest(@RequestBody HashMap<String, Object> body)
	{
		String res=serviceRequestService.addService(body);
		HashMap<String, String>  response  =new HashMap<>();
		response.put("status", res);
		
		return new ResponseEntity<HashMap<String,String>>(response,HttpStatus.OK);
	}
	
	@PostMapping("/findFreightBySrn")
	public ResponseEntity<HashMap<String, String>> findFreightBySrn(@RequestBody HashMap<String, Object> body)
	{
		long srn=Long.parseLong(body.get("srn").toString());
		HashMap<String, String>  response  =new HashMap<>();
		ServiceRequest sr= serviceRequestRepository.findBySrn(srn);
		if(sr==null)
			response.put("status", "No SRN Found");
		else{
			response.put("status", "Success");
			response.put("cid", ""+sr.getC_id().getId());
		}
			
		
		return new ResponseEntity<HashMap<String,String>>(response,HttpStatus.OK);
	}
	
//	@PostMapping("/notify_request")
//	public ResponseEntity<HashMap<String, String>>  notifyrequest(@RequestBody HashMap<String, Object> body)
//	{
//		Notifications_service noti = new Notifications_service();
//		noti.setType("transporter");
//		noti.setSubtype("new service request");
//		noti.setDescription(body.get("description").toString());
//		long srn = Long.parseLong(body.get("srn").toString());	
//		ServiceRequest sr = serviceRequestRepository.findBySrn(srn);
//		if(sr!=null)
//			noti.setSrn(sr);
//		
//		LocalDateTime dt=LocalDateTime.now();
//		noti.setInserttime(dt);
//		
//		String res= notificationServiceService.addNotification(noti);
//		HashMap<String, String>  response  =new HashMap<>();
//		response.put("status", res);
//		
//		return new ResponseEntity<HashMap<String,String>>(response,HttpStatus.OK);
//	}
	
	@GetMapping("/all_services")
	public ResponseEntity<List<ServiceRequest>> getNonAcceptedSR(){
		List<ServiceRequest> srns=serviceRequestService.getNonAcceptedSR();
		return new ResponseEntity<List<ServiceRequest>>(srns,HttpStatus.OK);
	}
	
	@PostMapping("/find-by-freight")
	public ResponseEntity<List<HashMap<Object, Object>>> viewAllRequest(@RequestBody HashMap<String, Object> body)
	{
		long cid=Long.parseLong(body.get("cid").toString());
		System.out.println(cid);
		
		List<HashMap<Object, Object>> srlist = new ArrayList<>();
		srlist = serviceRequestService.viewByClientId(cid);
		
		return new ResponseEntity<List<HashMap<Object, Object>>>(srlist,HttpStatus.OK);
				
	}

	@PostMapping("/srnlist")
	public ResponseEntity<List<Long>> getSRN(@RequestBody HashMap<String, Object> body) {
		long cid=Long.parseLong(body.get("cid").toString());
		
		List<Long> srnList = new ArrayList<>();
		
		srnList = serviceRequestService.getAllsrnByCid(cid);
		System.out.println(srnList);
		return new ResponseEntity<List<Long>>(srnList,HttpStatus.OK);

	}

}
