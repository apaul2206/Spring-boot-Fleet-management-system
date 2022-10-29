package com.example.demo.Controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Service.NotificationsTripService;

@Controller
@RequestMapping("/trip_noti")
public class NotificationsTripController {
	
	@Autowired
	NotificationsTripService notificationsTripService;
	
	@PostMapping("/to_freight")
	public ResponseEntity<List<HashMap<String, Object>>> notificationToFreight(@RequestBody HashMap<String, Object> body){
		long c_id=Long.parseLong(body.get("cid").toString());
		String date_time=body.get("datetime").toString();
		List<HashMap<String, Object>> response = notificationsTripService.sendTripStatus(c_id, date_time);
		
		return new ResponseEntity<List<HashMap<String,Object>>>(response,HttpStatus.OK);
	}
	
	@PostMapping("/to_tripnoti")
	public ResponseEntity<List<HashMap<String, Object>>> notificationTrip(@RequestBody HashMap<String, Object> body){
		long c_id=Long.parseLong(body.get("cid").toString());
		List<HashMap<String, Object>> response = notificationsTripService.sendTripNoti(c_id);
		
		return new ResponseEntity<List<HashMap<String,Object>>>(response,HttpStatus.OK);
	}
	

}
