package com.example.demo.Controller;

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

import com.example.demo.Model.Transporter;
import com.example.demo.Service.TransporterService;

@CrossOrigin
@Controller
@RequestMapping("transporter")
public class TransporterController {
	@Autowired
	private TransporterService transporterService;
	
	@PostMapping("/create")
	public ResponseEntity<String> addtransporter(@RequestBody HashMap<String, Object> body )
	{
		Transporter tr = new Transporter();
		
		tr.setT_name(body.get("t_name").toString());
		tr.setPerson_name(body.get("p_name").toString());		
		tr.setTelephone(body.get("telephone").toString());
		tr.setEmail(body.get("email").toString());
		tr.setAddr(body.get("addr").toString());
		tr.setLevel(body.get("level").toString());
		
		Transporter t2 = transporterService.addTransporter(tr);
		 if(t2==null)
		    	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{ \"status\" : \"Error\"}");
		    else
		    	return ResponseEntity.status(HttpStatus.OK).body("{ \"status\" : \"Success\"}"); 
	}
	
	@PostMapping("/update")
	public ResponseEntity<String> updatetransporter(@RequestBody HashMap<String, Object> body )
	{
		System.out.println(body);
		Transporter tr = new Transporter();
		System.out.println("1");
		tr.setId(Long.parseLong(body.get("id").toString()));
		System.out.println("2");
		tr.setT_name(body.get("t_name").toString());
		System.out.println("3");
		tr.setPerson_name(body.get("person_name").toString());
		System.out.println("4");
		tr.setTelephone(body.get("telephone").toString());
		System.out.println("5");
		tr.setEmail(body.get("email").toString());
		System.out.println("6");
		tr.setAddr(body.get("addr").toString());
		System.out.println("7");
		tr.setLevel(body.get("level").toString());
		System.out.println("8");
		
		Transporter t2 = transporterService.updateTransporter(tr);
		System.out.println("4");
		 if(t2==null)
		    	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{ \"status\" : \"Error\"}");
		    else
		    	return ResponseEntity.status(HttpStatus.OK).body("{ \"status\" : \"Success\"}"); 
	}
	
	@PostMapping("/transporterlist")
	public ResponseEntity<List<Long>> transporterlist(HttpServletRequest request)
	{
		
		List<Long> tids=new ArrayList<>();
		tids=transporterService.getIdlist();		
		return new ResponseEntity<List<Long>>(tids,HttpStatus.OK);
	}
	
	@GetMapping("/getAllTransporter")
	public ResponseEntity<List<Transporter>> viewAllTransporter(HttpServletRequest request)
	{
		List<Transporter> list= new ArrayList<>();
		list = transporterService.getAll();
		return new ResponseEntity<List<Transporter>>(list,HttpStatus.OK);
	}
	
	
	@PostMapping("delete-by-id")
	public ResponseEntity<HashMap<String, String>> deletetrans(@RequestBody HashMap<String, Object> body)
	{
		long id = Long.parseLong(body.get("id").toString());
		
		String str = transporterService.deletetrans(id);
		HashMap<String, String> res=new HashMap<>();
		res.put("status",str);
		return new ResponseEntity<HashMap<String,String>>(res,HttpStatus.OK);
	}
}
