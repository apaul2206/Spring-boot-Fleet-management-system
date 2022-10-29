package com.example.demo.Service;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Driver;
import com.example.demo.Model.Transporter;
import com.example.demo.Model.Truck;
import com.example.demo.Repository.TransporterRepository;
import com.example.demo.Repository.TruckRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;

@Service
public class TruckServiceImpl implements TruckService{

	@Autowired
	private TruckRepository truckRepository;
	
	@Autowired
	private TransporterRepository transporterRepository;

	@Override
	public Truck addTruck(Truck tr) {
		// TODO Auto-generated method stub
		return truckRepository.save(tr);
	}

	@Override
	public List<HashMap<Object, Object>> viewByTransId(long tid) {
		// TODO Auto-generated method stub
		Transporter tr = transporterRepository.findById(tid);
		
		List<HashMap<Object, Object>> res=new ArrayList<>();
		List<Truck> obj = truckRepository.findByT_id(tr);
		 System.out.println(obj);
		 for(int i=0;i<obj.size();i++)
		 {
			 HashMap<Object, Object> var=new HashMap<>();
			 var.put("id",obj.get(i).getId());
			 var.put("type", obj.get(i).getType());
			 var.put("tonnage", obj.get(i).getTonnage());
			 var.put("registration", obj.get(i).getRegistration());
			 var.put("vl", obj.get(i).getVL_expydate());
			 var.put("rw_expydate", obj.get(i).getRW_expydate());
			 var.put("status", obj.get(i).getStatus());
			 
			 res.add(var);
		 }
		 return res;

	}

	@Override
	public List<Long> listIDByTransId(long tid) {
		// TODO Auto-generated method stub
		Transporter tr = transporterRepository.findById(tid);
		
		List<Long> obj = truckRepository.findInactiveIDByTid(tr);
		return obj;
	}

	
	@Override
	public List<Long> totaltrucks(long tid) {
		// TODO Auto-generated method stub
		Transporter tr = transporterRepository.findById(tid);
		
		List<Long> obj = truckRepository.findInactiveIDByTid(tr);
		return obj;
	}
	
//	@Override
//	public List<Long> truckcount() {
//		// TODO Auto-generated method stub
//	
//		
//		List<Long> list = new ArrayList<>();
//		list = truckRepository.getCount();
//		return list;
//	}
//	
	public static Connection getConnection() throws Exception {
		try {
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/fleetfinal";
			String username = "root";
			String password = "Deevia@123";
			
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, username, password);
//			System.out.println("Connected");
			
			return conn;
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		return null;
	}
	@Override
	public List<HashMap<Object, Object>> viewtruckdetails(long transporter_id) {
		// TODO Auto-generated method stub
		try {
		System.out.println("Getting data for ID: " + transporter_id);
		Truck dr = truckRepository.findById(transporter_id);
		System.out.println("Found Driver is : " + dr);
		
		
		HashMap<Object, Object> var=new HashMap<>();
//		List<Driver> obj = driverRepository.(dr);
		Connection con = getConnection();
		
		PreparedStatement statement = con.prepareStatement("Select count(*) from truck where transporter_id = 101");
		
		ResultSet result = statement.executeQuery();
		System.out.println("@@@@@@@@@@@@@@@ "+ result);

//		 }
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return null;
		}
		return null;
	

	}
	
	
	@Override
	public List<Truck> getAll() {
		// TODO Auto-generated method stub
		List<Truck> list = truckRepository.findByIsactive(true);
		return list;
	}

	@Override
	public String deletetruck(long id) {
		// TODO Auto-generated method stub
		Truck tr = truckRepository.findById(id);
		tr.setIsactive(false);
		truckRepository.save(tr);
		return "Success";
	}

	@Override
	public List<Long> trucklist(long tid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Long> truckcount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Long> getCount() {
		// TODO Auto-generated method stub
		return null;
	}

	

	}
