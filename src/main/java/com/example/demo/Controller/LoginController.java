package com.example.demo.Controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Model.Freight;
import com.example.demo.Model.Login;
import com.example.demo.Model.Transporter;
import com.example.demo.Repository.FreightRepository;
import com.example.demo.Repository.TransporterRepository;
import com.example.demo.Service.LoginService;

@CrossOrigin
@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	LoginService loginService;
	
	@Autowired
	TransporterRepository transporterRepository;
	
	@Autowired
	FreightRepository freightRepository; 
	
	@PostMapping("/register")
	public ResponseEntity<String> addUser(@RequestBody HashMap<String, Object> body )
	{
		Login lo = new Login();
		
		lo.setUsername(body.get("username").toString());
		lo.setPassword(body.get("password").toString());
		lo.setType(body.get("type").toString());
		if (body.get("type").toString()=="Transporter")
		{
			Transporter tran= transporterRepository.findById(Long.parseLong(body.get("id").toString()));
			lo.setTransporter_id(tran);
		}
		else if(body.get("type").equals("Freight"))
		{
			Freight fr = freightRepository.findById(Long.parseLong(body.get("id").toString()));
			lo.setFreight_id(fr);
		}
		
		Login l2 = loginService.addUser(lo);
		 if(l2==null)
		    	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{ \"status\" : \"Error\"}");
		    else
		    	return ResponseEntity.status(HttpStatus.OK).body("{ \"status\" : \"Success\"}"); 
	}
	
	@PostMapping("/login")
	public ResponseEntity<HashMap<Object, Object>> loginuser(@RequestBody HashMap<String, String> body)
	{
		String username=body.get("username");
    	String password=body.get("password");
    	HashMap<Object, Object> var = new HashMap<>();
    	var=loginService.login(username,password);
    	System.out.println(var);
    	if(var==null)
    	{	var=new HashMap<>();
    		var.put("status", "Error");
    		System.out.println(var);
    		return new ResponseEntity<HashMap<Object, Object>>(var,HttpStatus.OK);
    		
    	}
    	else
    		return new ResponseEntity<HashMap<Object, Object>>(var,HttpStatus.OK);	
	
	}
	
	
}
