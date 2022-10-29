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
import com.example.demo.Model.Truck;
import com.example.demo.Repository.TransporterRepository;
import com.example.demo.Repository.TruckRepository;
import com.example.demo.Service.TruckService;

@CrossOrigin
@Controller
@RequestMapping("/truck")
public class TruckController {
	@Autowired
	private TruckService truckService;
	List<Long> truckIDlist = new ArrayList<>();
	int count;
	
	@Autowired
	TransporterRepository transporterRepository;
	
	@Autowired
	TruckRepository truckRepository;
	
	@PostMapping("/create")
	public ResponseEntity<String> addTruck(@RequestBody HashMap<String, Object> body )
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Truck tr = new Truck();
		
		tr.setType(body.get("type").toString());
		tr.setRegistration(body.get("registration").toString());
		float ton = Float.parseFloat(body.get("tonnage").toString());
		tr.setTonnage(ton);
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
		
		Truck t2 = truckService.addTruck(tr);
		 if(t2==null)
		    	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{ \"status\" : \"Error\"}");
		    else
		    	return ResponseEntity.status(HttpStatus.OK).body("{ \"status\" : \"Success\"}"); 
	}
	
	@PostMapping("/listtruck-by-transport")
	public ResponseEntity<List<HashMap<Object, Object>>> viewDriverByTrans(@RequestBody HashMap<String, Object> body)
	{
		long tid=Long.parseLong(body.get("tid").toString());
		System.out.println(tid);
		
		List<HashMap<Object, Object>> trucklist = new ArrayList<>();
		trucklist = truckService.viewByTransId(tid);
		
		return new ResponseEntity<List<HashMap<Object, Object>>>(trucklist,HttpStatus.OK);
				
	}
	
	@PostMapping("/listAvailabletruckID-by-transport")
	public ResponseEntity<List<Long>> viewAvailableTruckByTrans(@RequestBody HashMap<String, Object> body)
	{
		long tid=Long.parseLong(body.get("tid").toString());
		System.out.println(tid);
		
		List<Long> truckIDlist = new ArrayList<>();
		truckIDlist = truckService.listIDByTransId(tid);
		
		return new ResponseEntity<List<Long>>(truckIDlist,HttpStatus.OK);
				
	}
	
	@PostMapping("/trucks-by-transport")
	public ResponseEntity<List<Long>> transpoter_truck(@RequestBody HashMap<String, Object> body)
	{
		long tid=Long.parseLong(body.get("tid").toString());
		
		
//		List<Long> truckIDlist = new ArrayList<>();
		truckIDlist = truckService.totaltrucks(tid);
		
		int count = truckIDlist.size();
		
//		return new ResponseEntity<List<Long>>(truckIDlist,HttpStatus.OK);
		return new ResponseEntity(count,HttpStatus.OK);
	
        
//		return new ResponseEntity<Long>(truckIDlist,HttpStatus.OK);
		
	}
//	@GetMapping("/totaltruckcount")
//	public ResponseEntity<List<Long>> getAllTruckCount(HttpServletRequest req)
//	{
//		List<Long> list = new ArrayList();
//		list = truckRepository.getCount();
//		
//		return new ResponseEntity<List<Long>>(list,HttpStatus.OK);
//	}
	@GetMapping("truck-by-transport")
	
	public ResponseEntity<List<Long>> gettruck(HttpServletRequest req)
	{
		List<Long> list = new ArrayList<>();

		return new ResponseEntity<List<Long>>(list,HttpStatus.OK);
		
	}
	
	@PostMapping("/truckdetails")
	public ResponseEntity<List<HashMap<Object, Object>>> viewtruc(@RequestBody HashMap<String, Object> body)
	{
	long tid=Long.parseLong(body.get("tid").toString());
	System.out.println("####################################" +  tid);

	List<HashMap<Object, Object>> trucklist = new ArrayList<>();
	trucklist = truckService.viewtruckdetails(tid);
	System.out.println("^^^^^^^^^^^^^^^^^^^^^^ " + trucklist);

	return new ResponseEntity<List<HashMap<Object, Object>>>(trucklist,HttpStatus.OK);

	}
	
	
	@GetMapping("getAllTruck")
	public ResponseEntity<List<Truck>> getAllTruck(HttpServletRequest req)
	{
		List<Truck> list = truckService.getAll();
		
		return new ResponseEntity<List<Truck>>(list,HttpStatus.OK);
	}
	
	@PostMapping("delete-by-id")
	public ResponseEntity<HashMap<String, String>> deletetruck(@RequestBody HashMap<String, Object> body)
	{
		long id = Long.parseLong(body.get("id").toString());
		
		String str = truckService.deletetruck(id);
		HashMap<String, String> res=new HashMap<>();
		res.put("status",str);
		return new ResponseEntity<HashMap<String,String>>(res,HttpStatus.OK);
	}
}