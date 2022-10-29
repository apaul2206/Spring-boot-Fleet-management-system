package com.example.demo.Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
import com.example.demo.Model.Transporter;
import com.example.demo.Repository.TransporterRepository;
import com.example.demo.Service.DriverService;

@CrossOrigin
@Controller
@RequestMapping("/driver")
public class DriverController {
	
	@Autowired
	DriverService driverService;
	
	@Autowired
	TransporterRepository transporterRepository;
	
	@PostMapping("/create")
	public ResponseEntity<String> addDriver(@RequestBody HashMap<String, Object> body )
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Driver dr = new Driver();
		
		dr.setD_name(body.get("d_name").toString());
		dr.setPhone(body.get("phone").toString());		
		dr.setNationality(body.get("nationality").toString());
		dr.setID_proof(body.get("id_proof").toString());
		dr.setStatus(true);
		dr.setDL_no(body.get("dl_no").toString());
		LocalDate dl=LocalDate.parse(body.get("dl_expydate").toString());
		dr.setDL_expydate(dl);
		dr.setDl_status(true);
		long tid = Long.parseLong(body.get("t_id").toString());
		Transporter tr= transporterRepository.findById(tid);
		dr.setT_id(tr);
		
		Driver d2 = driverService.addDriver(dr);
		 if(d2==null)
		    	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{ \"status\" : \"Error\"}");
		    else
		    	return ResponseEntity.status(HttpStatus.OK).body("{ \"status\" : \"Success\"}"); 
	}
	
	@PostMapping("/toggle_status")
	public ResponseEntity<HashMap<String, String>> toggleStatus(@RequestBody HashMap<String, Object> body){
		HashMap<String, String> res=new HashMap<>();
		long id=Long.parseLong(body.get("id").toString());
		String str=driverService.toggleStatus(id);
		
		res.put("status", str);
		
		return new ResponseEntity<HashMap<String,String>>(res,HttpStatus.OK);		
		
	}
	
	@PostMapping("/listdriver-by-transport")
	public ResponseEntity<List<HashMap<Object, Object>>> viewDriverByTrans(@RequestBody HashMap<String, Object> body)
	{
		long tid=Long.parseLong(body.get("tid").toString());
		System.out.println(tid);
		
		List<HashMap<Object, Object>> driverlist = new ArrayList<>();
		driverlist = driverService.viewByTransId(tid);
		
		return new ResponseEntity<List<HashMap<Object, Object>>>(driverlist,HttpStatus.OK);
				
	}
///////////////////////////////////////////////////////////////////////////////////////////////////
@PostMapping("/listdriver-by-id")
public ResponseEntity<List<HashMap<Object, Object>>> viewDriverById(@RequestBody HashMap<String, Object> body)
{
long tid=Long.parseLong(body.get("tid").toString());
System.out.println("####################################" +  tid);

List<HashMap<Object, Object>> driverlist = new ArrayList<>();
driverlist = driverService.viewByDriverId(tid);
System.out.println("^^^^^^^^^^^^^^^^^^^^^^ " + driverlist);

return new ResponseEntity<List<HashMap<Object, Object>>>(driverlist,HttpStatus.OK);

}


///////////////////////////////////////////////////////////////////////////////////////////////////////
	@PostMapping("/listAvailableDriverID-by-transport")
	public ResponseEntity<List<Long>> viewAvailableDriverByTrans(@RequestBody HashMap<String, Object> body)
	{
		long tid=Long.parseLong(body.get("tid").toString());
		System.out.println(tid);
		
		List<Long> driverIDlist = new ArrayList<>();
		driverIDlist = driverService.listIDByTransId(tid);
		
		return new ResponseEntity<List<Long>>(driverIDlist,HttpStatus.OK);
				
	}
	
	@GetMapping("getAllDriver")
	public ResponseEntity<List<Driver>> getAllDriver(HttpServletRequest request)
	{
		List<Driver> list = driverService.getAll();
		return new ResponseEntity<List<Driver>>(list,HttpStatus.OK);
	}
	
	@PostMapping("delete-by-id")
	public ResponseEntity<HashMap<String, String>> deletedriver(@RequestBody HashMap<String, Object> body)
	{
		long id = Long.parseLong(body.get("id").toString());
		
		String str = driverService.deletedriver(id);
		HashMap<String, String> res=new HashMap<>();
		res.put("status",str);
		return new ResponseEntity<HashMap<String,String>>(res,HttpStatus.OK);
	}

}
