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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Model.Notifications_service;
import com.example.demo.Model.ServiceRequest;
import com.example.demo.Repository.ServiceRequestRepository;
import com.example.demo.Service.Bid_statusService;
import com.example.demo.Service.NotificationServiceService;

@CrossOrigin
@Controller
@RequestMapping("/bid")
public class Bid_statusController {
	
	@Autowired
	Bid_statusService bid_statusService;
	
	@Autowired
	ServiceRequestRepository serviceRequestRepository;
	
	@Autowired
	NotificationServiceService notificationServiceService; 
	
	@PostMapping("/submit")
	public ResponseEntity<HashMap<String, String>> submitBid(@RequestBody HashMap<String, Object> body){
		HashMap<String, String> response =new HashMap<>();
		String str;
		if(body.get("srn")==null)
			str="no srn";
		else if(body.get("amt")==null)
			str="no amt";
		else if(body.get("t_id")==null)
			str="no transporter";
		else {
			long srn=Long.parseLong(body.get("srn").toString());
			int amt=Integer.parseInt(body.get("amt").toString());
			long t_id=Long.parseLong(body.get("t_id").toString());
			str=bid_statusService.addBid(srn, amt, t_id);
		}
		response.put("status", str);
		return new ResponseEntity<HashMap<String,String>>(response,HttpStatus.OK);
					
	}
	
	@PostMapping("/accept/{id}")
	public ResponseEntity<HashMap<String, String>> acceptBid(@PathVariable("id") long id){
		HashMap<String, String> response=new HashMap<>();
		String str=bid_statusService.acceptBid(id);
		response.put("Status", str);
		return new ResponseEntity<HashMap<String,String>>(response,HttpStatus.OK);
	}
	
	@PostMapping("/confirm")
	public ResponseEntity<HashMap<String, String>> confirmBid(@RequestBody HashMap<String, Object> body){
		HashMap<String, String> response=new HashMap<>();
		long id = Long.parseLong(body.get("id").toString());
		boolean status = Boolean.parseBoolean(body.get("status").toString());
		String str=bid_statusService.confirmBid(id,status); 
		response.put("Status", str);
		return new ResponseEntity<HashMap<String,String>>(response,HttpStatus.OK);
	}
	
	@GetMapping("/by_srn/{srn}")
	public ResponseEntity<List<HashMap<String, Object>>>  searchBySrn(@PathVariable("srn") long srn)
	{
		List<HashMap<String, Object>> res=bid_statusService.searchBySRN(srn);
		return new ResponseEntity<List<HashMap<String,Object>>>(res,HttpStatus.OK);
	}
	
	@GetMapping("/accepted/{id}")
	public ResponseEntity<List<HashMap<String, Object>>> getAcceptedSRN(@PathVariable("id") long id)
	{
		List<HashMap<String, Object>> res=bid_statusService.allAcceptedSrn(id);
		return new ResponseEntity<List<HashMap<String,Object>>>(res,HttpStatus.OK);
	}
	
	@PostMapping("/find-by-freight")
	public ResponseEntity<List<HashMap<Object, Object>>> viewBidByFreight(@RequestBody HashMap<String, Object> body)
	{
		long cid=Long.parseLong(body.get("cid").toString());
		System.out.println(cid);
		
		List<HashMap<Object, Object>> bdlist = new ArrayList<>();
		bdlist = bid_statusService.viewByClientId(cid);
		
		return new ResponseEntity<List<HashMap<Object, Object>>>(bdlist,HttpStatus.OK);
				
	}
	
	@GetMapping("/by_freight/{c_id}")
	public ResponseEntity<List<HashMap<String, Object>>>  searchByFreight(@PathVariable("c_id") long c_id)
	{
		List<HashMap<String, Object>> res=bid_statusService.allBids(c_id);
		return new ResponseEntity<List<HashMap<String,Object>>>(res,HttpStatus.OK);
	}
	
//	@PostMapping("/notify_bidaccept")
//	public ResponseEntity<HashMap<String, String>>  notifybidaccept(@RequestBody HashMap<String, Object> body)
//	{
//		Notifications_service noti = new Notifications_service();
//		noti.setType("transporter");
//		noti.setSubtype("bid status");
//		noti.setDescription("bid is accepted");
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
	
//	@PostMapping("/notify_bidamt")
//	public ResponseEntity<HashMap<String, String>>  notifybidamt(@RequestBody HashMap<String, Object> body)
//	{
//		Notifications_service noti = new Notifications_service();
//		noti.setType("freight");
//		noti.setSubtype("bid amount");
//		noti.setDescription("new bidding recieved");
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


}
