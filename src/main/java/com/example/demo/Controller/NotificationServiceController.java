package com.example.demo.Controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Service.NotificationServiceService;



@Controller
@RequestMapping("/service_noti")
public class NotificationServiceController {
	
	@Autowired
	NotificationServiceService notificationServiceService;
	
	@PostMapping("/to_freight")
	public ResponseEntity<List<HashMap<String, Object>>> notificationToFreigth(@RequestBody HashMap<String, Object> body){
	
		long c_id=Long.parseLong(body.get("cid").toString());
		String dt=body.get("datetime").toString();
		List<HashMap<String, Object>> response=notificationServiceService.getSrnStatusFreight(c_id, dt);
		return new ResponseEntity<List<HashMap<String,Object>>>(response,HttpStatus.OK);
		
	} 
	
	@PostMapping("/to_freight_viewall")
	public ResponseEntity<List<HashMap<String, Object>>> notificationToFreightViewAll(@RequestBody HashMap<String, Object> body){
	
		long c_id=Long.parseLong(body.get("cid").toString());
		List<HashMap<String, Object>> response=notificationServiceService.getFreightNoti(c_id);
		return new ResponseEntity<List<HashMap<String,Object>>>(response,HttpStatus.OK);
		
	}
	
	@PostMapping("/to_transporter")
	public ResponseEntity<List<HashMap<String, Object>>> notificationToTransporter(@RequestBody HashMap<String, Object> body){
	
		long t_id=Long.parseLong(body.get("tid").toString());
		String dt=body.get("datetime").toString();
		List<HashMap<String, Object>> response=notificationServiceService.getSrnStatustransporter(t_id, dt);
		return new ResponseEntity<List<HashMap<String,Object>>>(response,HttpStatus.OK);
		
	}
	
	@PostMapping("/to_transporter_viewall")
	public ResponseEntity<List<HashMap<String, Object>>> notificationToTransporterViewAll(@RequestBody HashMap<String, Object> body){
	
		long t_id=Long.parseLong(body.get("tid").toString());
		List<HashMap<String, Object>> response=notificationServiceService.getTransporterNoti(t_id);
		return new ResponseEntity<List<HashMap<String,Object>>>(response,HttpStatus.OK);
		
	} 

}
