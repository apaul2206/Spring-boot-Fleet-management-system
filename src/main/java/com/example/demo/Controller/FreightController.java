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

import com.example.demo.Model.Freight;
import com.example.demo.Service.FreightService;

@CrossOrigin
@Controller
@RequestMapping("freight")
public class FreightController {
	@Autowired
	private FreightService freightService; 

	@PostMapping("/create")
	public ResponseEntity<String> addfreight(@RequestBody HashMap<String, Object> body )
	{
		Freight fr = new Freight();
		//cam.setId(body.get("id"));
		fr.setC_name(body.get("c_name").toString());
		fr.setPerson_name(body.get("p_name").toString());
		fr.setTelephone(body.get("telephone").toString());
		fr.setEmail(body.get("email").toString());
		fr.setAddr(body.get("addr").toString());
		
		Freight f2 = freightService.addFreight(fr);
		 if(f2==null)
		    	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{ \"status\" : \"Error\"}");
		    else
		    	return ResponseEntity.status(HttpStatus.OK).body("{ \"status\" : \"Success\"}"); 
	}
	
	@GetMapping("getAllFreight")
	public ResponseEntity<List<Freight>> viewAllFreight(HttpServletRequest request)
	{
		List<Freight> list= new ArrayList<>();
		list = freightService.viewAll();
		return new ResponseEntity<List<Freight>>(list,HttpStatus.OK);
	}
	
	@PostMapping("delete-by-id")
	public ResponseEntity<HashMap<String, String>> deletefreight(@RequestBody HashMap<String, Object> body)
	{
		long id = Long.parseLong(body.get("id").toString());
		
		String str = freightService.deletefreight(id);
		HashMap<String, String> res=new HashMap<>();
		res.put("status",str);
		return new ResponseEntity<HashMap<String,String>>(res,HttpStatus.OK);
	}

	
	
}
