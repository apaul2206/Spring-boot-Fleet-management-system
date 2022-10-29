package com.example.demo.Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

import com.example.demo.Model.Driver;
import com.example.demo.Model.Trailer;
import com.example.demo.Model.Transporter;
import com.example.demo.Repository.TransporterRepository;
import com.example.demo.Service.TrailerService;

@CrossOrigin
@Controller
@RequestMapping("/trailer")
public class TrailerController {

	@Autowired
	private TrailerService trailerService;
	
	List<Long> trailerIDlist = new ArrayList<>();
	@Autowired
	TransporterRepository transporterRepository;
	
	@PostMapping("/create")
	public ResponseEntity<String> addTailer(@RequestBody HashMap<String, Object> body )
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Trailer tr = new Trailer();
		
		tr.setType(body.get("type").toString());
		tr.setRegistration(body.get("registration").toString());		
		tr.setStatus(true);
		LocalDate vl=LocalDate.parse(body.get("vl_expydate").toString());
		tr.setVL_expydate(vl);
		LocalDate rw=LocalDate.parse(body.get("rw_expydate").toString());
		tr.setRW_expydate(rw);
		tr.setVl_status(true);
		tr.setRw_status(true);
		long tid = Long.parseLong(body.get("t_id").toString());
		Transporter tran= transporterRepository.findById(tid);
		tr.setT_id(tran);
		
		Trailer t2 = trailerService.addTrailer(tr);
		 if(t2==null)
		    	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{ \"status\" : \"Error\"}");
		    else
		    	return ResponseEntity.status(HttpStatus.OK).body("{ \"status\" : \"Success\"}"); 
	}
	
	@PostMapping("/listtrailer-by-transport")
	public ResponseEntity<List<HashMap<Object, Object>>> viewTrailerByTrans(@RequestBody HashMap<String, Object> body)
	{
		long tid=Long.parseLong(body.get("tid").toString());
		System.out.println(tid);
		
		List<HashMap<Object, Object>> driverlist = new ArrayList<>();
		driverlist = trailerService.viewByTransId(tid);
		
		return new ResponseEntity<List<HashMap<Object, Object>>>(driverlist,HttpStatus.OK);
				
	}
	

	@PostMapping("/trailers-by-transport")
	public ResponseEntity<List<Long>> transpoter_trailer(@RequestBody HashMap<String, Object> body)
	{
		long tid=Long.parseLong(body.get("tid").toString());
		
		
//		List<Long> truckIDlist = new ArrayList<>();
		trailerIDlist = trailerService.totaltrailer(tid);
		
		int count = trailerIDlist.size();
		
//		return new ResponseEntity<List<Long>>(truckIDlist,HttpStatus.OK);
		return new ResponseEntity(count,HttpStatus.OK);
	
        
//		return new ResponseEntity<Long>(truckIDlist,HttpStatus.OK);
		
	}
	
	
	@PostMapping("/listAvailabletrailerID-by-transport")
	public ResponseEntity<List<Long>> viewAvailableTrailerByTrans(@RequestBody HashMap<String, Object> body)
	{
		long tid=Long.parseLong(body.get("tid").toString());
		System.out.println(tid);
		
		List<Long> trailerIDlist = new ArrayList<>();
		trailerIDlist = trailerService.listIDByTransId(tid);
		
		return new ResponseEntity<List<Long>>(trailerIDlist,HttpStatus.OK);
				
	}
	
	@GetMapping("getAllTrailer")
	public ResponseEntity<List<Trailer>> getAllTrailer(HttpServletRequest req)
	{
		List<Trailer> list = trailerService.getAll();
		return new ResponseEntity<List<Trailer>>(list,HttpStatus.OK);
	}
	
	@PostMapping("delete-by-id")
	public ResponseEntity<HashMap<String, String>> deletetrans(@RequestBody HashMap<String, Object> body)
	{
		long id = Long.parseLong(body.get("id").toString());
		
		String str = trailerService.deletetrailer(id);
		HashMap<String, String> res=new HashMap<>();
		res.put("status",str);
		return new ResponseEntity<HashMap<String,String>>(res,HttpStatus.OK);
	}
	
	
	@GetMapping("trailer-by-transport")
	public ResponseEntity<List<Long>> gettruck(HttpServletRequest req)
	{
		List<Long> list = new ArrayList<>();
		list = trailerService.trailercount();
		
		
		return new ResponseEntity<List<Long>>(list,HttpStatus.OK);
	}
}

