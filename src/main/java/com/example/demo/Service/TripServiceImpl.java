package com.example.demo.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Driver;
import com.example.demo.Model.Freight;
import com.example.demo.Model.Notifications_trip;
import com.example.demo.Model.ServiceRequest;
import com.example.demo.Model.Trailer;
import com.example.demo.Model.Transporter;
import com.example.demo.Model.Trip;
import com.example.demo.Model.Truck;
import com.example.demo.Repository.Bid_statusRepository;
import com.example.demo.Repository.DriverRepository;
import com.example.demo.Repository.FreightRepository;
import com.example.demo.Repository.ServiceRequestRepository;
import com.example.demo.Repository.TrailerRepository;
import com.example.demo.Repository.TransporterRepository;
import com.example.demo.Repository.TripRepository;
import com.example.demo.Repository.TruckRepository;

@Service
public class TripServiceImpl implements TripService {
	
	@Autowired
	TruckRepository truckRepository;
	
	@Autowired
	TrailerRepository trailerRepository;
	
	@Autowired
	DriverRepository driverRepository;
	
	@Autowired
     FreightRepository freightRepository; 
	
	@Autowired
	TripRepository tripRepository;
	
	@Autowired
	ServiceRequestRepository serviceRequestRepository;
	
	@Autowired
	private TransporterRepository transporterRepository;
	
	@Autowired
	Bid_statusRepository bid_statusRepository;
	
	@Autowired
	NotificationsTripService notificationtripService;

	@Override
	public String assignTrip(long driver_id, long truck_id, long trailer_id, long srn) {
		// TODO Auto-generated method stub
		ServiceRequest sr=serviceRequestRepository.findBySrn(srn);
		if(sr==null)
			return "srn doesnot exists";
		
		Driver dr=driverRepository.findById(driver_id);
		if(dr==null || !dr.getStatus())
			return "Driver not available";
		
		Truck trck=truckRepository.findById(truck_id);
		if(trck==null || !trck.getStatus())
			return "No truck";
		
		Trailer trlr=trailerRepository.findById(trailer_id);
		if(trlr==null || ! trlr.getStatus())
			return "No trailer";
		
		Trip trip=new Trip();
		
		trip.setDriver_id(dr);
		trip.setSrn(sr);
		trip.setTruck_id(trck);
		trip.setTrailer_id(trlr);
		trip.setStatus("active");
		
		tripRepository.save(trip);
		
		dr.setStatus(false);
		driverRepository.save(dr);
		
		trck.setStatus(false);
		truckRepository.save(trck);
		
		trlr.setStatus(false);
		trailerRepository.save(trlr);
		
		return ""+trip.getId(); 
		
	}
	
	@Override
	public List<HashMap<Object, Object>> viewActiveTripByTransId(long tid) {
		// TODO Auto-generated method stub
		Transporter tr = transporterRepository.findById(tid);
		List<Driver> dr = driverRepository.findByActiveT_id(tr);
		
		List<HashMap<Object, Object>> res=new ArrayList<>();
		List<Trip> obj = tripRepository.findByDriver_idIn(dr , "delivered");
		 System.out.println(obj);
		 for(int i=0;i<obj.size();i++)
		 {
			 HashMap<Object, Object> var=new HashMap<>();
			 var.put("id",obj.get(i).getId());
			 var.put("driver_id", obj.get(i).getDriver_id().getId());
			 var.put("truck_id", obj.get(i).getTruck_id().getId());
			 var.put("trailer_id", obj.get(i).getTrailer_id().getId());
			 var.put("srn", obj.get(i).getSrn().getSrn());
			 var.put("pickup_date", obj.get(i).getSrn().getPickup_date());
			 var.put("dropoff_date", obj.get(i).getSrn().getDropoff_date());
			 var.put("pickup_loc", obj.get(i).getSrn().getPickup_loc());
			 var.put("dropoff_loc", obj.get(i).getSrn().getDropoff_loc());
			 var.put("desc", obj.get(i).getSrn().getDescription());
			 var.put("status", obj.get(i).getStatus());
			 
			 
			 res.add(var);
		 }
		 return res;
//		return null;

	}

	@Override
	public List<Long> listSRNByTransId(long tid) {
		// TODO Auto-generated method stub
		Transporter tr = transporterRepository.findById(tid);
		
		List<ServiceRequest> obj = bid_statusRepository.findAcceptedSRNByTid(tr);
		List<Long> srns=new ArrayList<>();
		for(ServiceRequest s:obj) {
			srns.add(s.getSrn());
		}
		return srns;
		
	}

	@Override
	public String updatetripstatus(long tripid, String status) {
		// TODO Auto-generated method stub
		Trip tid = tripRepository.findById(tripid);
		if(tid==null)
				return "No Trip";
		if(tid.getStatus().equals("Delivered"))
				return "Cannot change status";
		
		tid.setStatus(status);
		if(status.equals("Delivered"))
		{
			tid.getDriver_id().setStatus(true);
			tid.getTrailer_id().setStatus(true);
			tid.getTruck_id().setStatus(true);
		}
		tripRepository.save(tid);
		
		
		Notifications_trip noti = new Notifications_trip();
		noti.setType("freight");
		noti.setSubtype("trip status");
		noti.setDescription(status);
		System.out.println("00000000000000000000000000000");
		noti.setSrn(tid.getSrn());
		System.out.println("111111111111111111111111111111111");
//		long tid = Long.parseLong(body.get("trip_id").toString());
		System.out.println("td id : "+tid);
		noti.setTrip_id(tid);
		
		LocalDateTime dt=LocalDateTime.now();
		noti.setInserttime(dt);
		
		String result= notificationtripService.addNotification(noti);
		
		return "Success";
	}

	@Override
	public List<Long> trailercount1() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Long> trailercount() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Long totaltrip(long tid) {
		// TODO Auto-generated method stub
		Transporter tr = transporterRepository.findById(tid);
		System.out.println(tr.getId());
		List<Driver> dr = driverRepository.findByT_id(tr);
		System.out.println(dr);
		Long obj = tripRepository.getCount(dr);
		return obj;
	}


	@Override
	public Long totalactivetrip(long tid) {
		// TODO Auto-generated method stub
		Transporter tr = transporterRepository.findById(tid);
		System.out.println(tr.getId());
		List<Driver> dr = driverRepository.findByActiveT_id(tr);
		System.out.println(dr);
		Long obj = tripRepository.getCount1(dr);
		return obj;
	}


	
	@Override
	public Long totalactivefreighttrip(long tid) {
		// TODO Auto-generated method stub
		Freight tr = freightRepository.findById(tid);
		System.out.println(tr.getId());
		List<ServiceRequest> dr = serviceRequestRepository.findSrnByCidId(tr);
		System.out.println(dr);
		Long obj = tripRepository.getfreightcount(dr);
		System.out.println(obj);
		return obj;
	}
	
}
