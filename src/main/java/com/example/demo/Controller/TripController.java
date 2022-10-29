package com.example.demo.Controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Model.Notifications_trip;
import com.example.demo.Model.ServiceRequest;
import com.example.demo.Model.Trip;
import com.example.demo.Repository.FreightRepository;
import com.example.demo.Repository.ServiceRequestRepository;
import com.example.demo.Repository.TripRepository;
import com.example.demo.Service.NotificationsTripService;
import com.example.demo.Service.TripService;

@CrossOrigin
@Controller
@RequestMapping("/trip")
public class TripController {
	
	@Autowired
	TripService tripService; 
	
	@Autowired
	private NotificationsTripService notificationtripService;
	
	@Autowired
	private TripRepository tripRepository;
	
	@Autowired
	private FreightRepository freightRepository; 
	
	@Autowired
	ServiceRequestRepository serviceRequestRepository;
	
	@PostMapping("/assign")
	public ResponseEntity<HashMap<String, String>> assignTrip(@RequestBody HashMap<String, Object> body)
	{
		long driver_id=Long.parseLong(body.get("driver").toString());
		System.out.println(driver_id);
		long truck_id=Long.parseLong(body.get("truck").toString());
		System.out.println(truck_id);
		long trailer_id=Long.parseLong(body.get("trailer").toString());
		System.out.println(trailer_id);
		long srn=Long.parseLong(body.get("srn").toString());
		System.out.println(srn);
		HashMap<String, String> res=new HashMap<>();
		String str= tripService.assignTrip(driver_id, truck_id, trailer_id, srn);
		System.out.println(str);
		long tid;
		
		try {
			tid=Long.parseLong(str);
			
		}catch (Exception e) {
			// TODO: handle exception
			res.put("status", str);
			return new ResponseEntity<HashMap<String,String>>(res,HttpStatus.OK);
		}
		
		
		Notifications_trip noti = new Notifications_trip();
		noti.setType("freight");
		noti.setSubtype("trip status");
		noti.setDescription("package has been shipped");
		System.out.println("00000000000000000000000000000");
		ServiceRequest sr = serviceRequestRepository.findBySrn(srn);
		if(sr!=null)
			noti.setSrn(sr);
		System.out.println("111111111111111111111111111111111");
//		long tid = Long.parseLong(body.get("trip_id").toString());
		System.out.println("td id : "+tid);
		Trip tr = tripRepository.findById(tid);
		if(tr!=null)
			noti.setTrip_id(tr);
		
		LocalDateTime dt=LocalDateTime.now();
		noti.setInserttime(dt);
		
		String result= notificationtripService.addNotification(noti);
		HashMap<String, String>  response  =new HashMap<>();
		response.put("status", result);
		
		
		return new ResponseEntity<HashMap<String,String>>(response,HttpStatus.OK);
		
	}
	
	@PostMapping("/update-trip-status")
	public ResponseEntity<String> updateTripstatus(@RequestBody HashMap<String, Object> body)
	{
		long tripid = Long.parseLong(body.get("tripid").toString());
		
		String res=tripService.updatetripstatus(tripid,body.get("status").toString()); 
		return new ResponseEntity<String>(res,HttpStatus.OK);
	}

	@PostMapping("/listtrip-by-transport")
	public ResponseEntity<List<HashMap<Object, Object>>> viewDriverByTrans(@RequestBody HashMap<String, Object> body)
	{
		long tid=Long.parseLong(body.get("tid").toString());
		System.out.println(tid);
		
		List<HashMap<Object, Object>> triplist = new ArrayList<>();
		triplist = tripService.viewActiveTripByTransId(tid);
		
		return new ResponseEntity<List<HashMap<Object, Object>>>(triplist,HttpStatus.OK);
				
	}
	
	@PostMapping("/listAcceptedSRN-by-transport")
	public ResponseEntity<List<Long>> viewAcceptedSRNByTrans(@RequestBody HashMap<String, Object> body)
	{
		long tid=Long.parseLong(body.get("tid").toString());
		System.out.println(tid);
		
		List<Long> SRNlist = new ArrayList<>();
		SRNlist = tripService.listSRNByTransId(tid);
		
		return new ResponseEntity<List<Long>>(SRNlist,HttpStatus.OK);
				
	}
	
	
	@PostMapping("/trip-by-transport")
	public ResponseEntity <Long> transpoter_trailer(@RequestBody HashMap<String, Object> body)
	{
		long tid=Long.parseLong(body.get("tid").toString());
		
//		Long tripIDlist = new ArrayList();
		Long tripIDlist = tripService.totaltrip(tid);
		
//		Long count = tripIDlist.size();
		
//		return new ResponseEntity<List<Long>>(truckIDlist,HttpStatus.OK);
		return new ResponseEntity(tripIDlist,HttpStatus.OK);
 
//		return new ResponseEntity<Long>(truckIDlist,HttpStatus.OK);
		
	}
	
	@PostMapping("/activetrip-by-transport")
	public ResponseEntity <Long> transpoter_active(@RequestBody HashMap<String, Object> body)
	{
		long tid=Long.parseLong(body.get("tid").toString());
		
//		Long tripIDlist = new ArrayList();
		Long tripIDlist = tripService.totalactivetrip(tid);
		
//		Long count = tripIDlist.size();
		
//		return new ResponseEntity<List<Long>>(truckIDlist,HttpStatus.OK);
		return new ResponseEntity(tripIDlist,HttpStatus.OK);
 
//		return new ResponseEntity<Long>(truckIDlist,HttpStatus.OK);
		
	}
	
	@PostMapping("/activefreighttrip-by-transport")
	public ResponseEntity <Long> freight_active(@RequestBody HashMap<String, Object> body)
	{
		long tid=Long.parseLong(body.get("tid").toString());
		
//		Long tripIDlist = new ArrayList();
		Long tripIDlist = tripService.totalactivefreighttrip(tid);
		
//		Long count = tripIDlist.size();
		
//		return new ResponseEntity<List<Long>>(truckIDlist,HttpStatus.OK);
		return new ResponseEntity<Long>(tripIDlist,HttpStatus.OK);
 
//		return new ResponseEntity<Long>(truckIDlist,HttpStatus.OK);
		
	}
	
	

	@GetMapping("tripactiveve-by-transport")
	public ResponseEntity<List<Long>> gettruck1(HttpServletRequest req)
	{
		List<Long> list = new ArrayList<>();
		list = tripService.trailercount1();
		
		
		return new ResponseEntity<List<Long>>(list,HttpStatus.OK);
	}
	
	@GetMapping("trip-by-transport")
	public ResponseEntity<List<Long>> gettruck(HttpServletRequest req)
	{
		List<Long> list = new ArrayList<>();
		list = tripService.trailercount();
		
		
		return new ResponseEntity<List<Long>>(list,HttpStatus.OK);
	}
	
}
