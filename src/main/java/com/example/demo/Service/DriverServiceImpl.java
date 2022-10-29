package com.example.demo.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Driver;
import com.example.demo.Model.Transporter;
import com.example.demo.Repository.DriverRepository;
import com.example.demo.Repository.TransporterRepository;

@Service
public class DriverServiceImpl implements DriverService {

	@Autowired
	DriverRepository driverRepository; 
	
	@Autowired
	private TransporterRepository transporterRepository;
	
	@Override
	public Driver addDriver(Driver dr) {
		// TODO Auto-generated method stub
		return driverRepository.save(dr);
	}
	
	@Override
	public String toggleStatus(long id) {
		// TODO Auto-generated method stub
		Driver dr=driverRepository.findById(id);
		if(dr==null)
			return "No driver";
		
		dr.setStatus(!dr.getStatus());
		driverRepository.save(dr);
		
		return "Success";
		
	}
	
	@Override
	public List<HashMap<Object, Object>> viewByTransId(long tid) {
		// TODO Auto-generated method stub
		Transporter dr = transporterRepository.findById(tid);
		
		List<HashMap<Object, Object>> res=new ArrayList<>();
		List<Driver> obj = driverRepository.findByT_id(dr);
		 System.out.println(obj);
		 for(int i=0;i<obj.size();i++)
		 {
			 HashMap<Object, Object> var=new HashMap<>();
			 var.put("id",obj.get(i).getId());
			 var.put("driver_name", obj.get(i).getD_name());
			 var.put("phone", obj.get(i).getPhone());
			 var.put("nationality", obj.get(i).getNationality());
			 var.put("id_proof", obj.get(i).getID_proof());
			 var.put("dl_no", obj.get(i).getDL_no());
			 var.put("dl_expirydate", obj.get(i).getDL_expydate());
			 var.put("status", obj.get(i).getStatus());
			 
			 res.add(var);
		 }
		 return res;

	}
	@Override
	public List<HashMap<Object, Object>> viewByDriverId(long tid) {
		// TODO Auto-generated method stub
		System.out.println("Getting data for ID: " + tid);
		Driver dr = driverRepository.findById(tid);
		System.out.println("Found Driver is : " + dr);
		
		
		HashMap<Object, Object> var=new HashMap<>();
//		List<Driver> obj = driverRepository.(dr);
		 var.put("id",dr.getId());
		 var.put("driver_name", dr.getD_name());
		 var.put("phone", dr.getPhone());
//		 var.put("nationality", obj.get(i).getNationality());
//		 var.put("id_proof", obj.get(i).getID_proof());
		 var.put("dl_no", dr.getDL_no());
//		 var.put("dl_expirydate", obj.get(i).getDL_expydate());
//		 var.put("status", obj.get(i).getStatus());
		List<HashMap<Object, Object>> res=new ArrayList<>();
		res.add(var);
		
		System.out.println("@@@@@@@@@@@@@@@ "+ res);

//		 }
		 return res;

	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	@Override
	public List<Long> listIDByTransId(long tid) {
		// TODO Auto-generated method stub
		Transporter tr = transporterRepository.findById(tid);
		
		List<Long> obj = driverRepository.findInactiveIDByTid(tr);
		return obj;
	}

	@Override
	public List<Driver> getAll() {
		// TODO Auto-generated method stub
		List<Driver> list = driverRepository.findByIsactive(true);
		return list;
	}

	@Override
	public String deletedriver(long id) {
		// TODO Auto-generated method stub
		Driver dr = driverRepository.findById(id);
		dr.setIsactive(false);
		driverRepository.save(dr);
		return "Success";
	}

	

}
