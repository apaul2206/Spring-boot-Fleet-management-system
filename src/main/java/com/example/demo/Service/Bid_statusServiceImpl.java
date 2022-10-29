package com.example.demo.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Bid_status;
import com.example.demo.Model.Freight;
import com.example.demo.Model.Notifications_service;
import com.example.demo.Model.ServiceRequest;
import com.example.demo.Model.Transporter;
import com.example.demo.Model.Trip;
import com.example.demo.Repository.Bid_statusRepository;
import com.example.demo.Repository.FreightRepository;
import com.example.demo.Repository.ServiceRequestRepository;
import com.example.demo.Repository.TransporterRepository;
import com.example.demo.Repository.TripRepository;

@Service
public class Bid_statusServiceImpl implements Bid_statusService {
	
	@Autowired
	ServiceRequestRepository serviceRequestRepository;
	
	@Autowired
	Bid_statusRepository bid_statusRepository;
	
	@Autowired
	TransporterRepository transporterRepository;
	
	@Autowired
	FreightRepository 	freightRepository;
	
	@Autowired
	TripRepository tripRepository;

	@Autowired
	NotificationServiceService notificationServiceService;
	
	@Override
	public String addBid(long srn,int amt,long t_id) {
		// TODO Auto-generated method stub
		Bid_status bs=new Bid_status();
		
		ServiceRequest sr=serviceRequestRepository.findBySrn(srn);
		Transporter transporter=transporterRepository.findById(t_id);
		LocalDateTime response_dt=LocalDateTime.now();
		
		
		bs.setDOR(response_dt);
		bs.setBid_amt(amt);
		bs.setC_id(sr.getC_id());
		bs.setSrn(sr);
		bs.setT_id(transporter);
		
		bid_statusRepository.save(bs);
		
		
		notifyrequest(bs.getSrn().getSrn(),bs.getId());
		
		return "success";
		
		
	}
	
	public boolean notifyrequest(long srn,long bid)
	{
		Notifications_service noti = new Notifications_service();
		noti.setType("freight");
		noti.setSubtype("new bid recieved");
		
		//long srn = Long.parseLong(body.get("srn").toString());	
		ServiceRequest sr = serviceRequestRepository.findBySrn(srn);
		if(sr!=null)
		{
			noti.setSrn(sr);
			noti.setDescription(sr.getDescription());
		}
		System.out.println("bidid :    "+bid);
		Bid_status bidid=bid_statusRepository.findById(bid);
		if(bidid!=null)
		{
			noti.setBid_id(bidid);
		}
		LocalDateTime dt=LocalDateTime.now();
		noti.setInserttime(dt);
		
		String res= notificationServiceService.addNotification(noti);
		return true;
	}
	

	@Override
	public String acceptBid(long bid_id) {
		// TODO Auto-generated method stub
		System.out.println("ertyuio:"+bid_id);
		Bid_status bs=bid_statusRepository.findById(bid_id);
		if(bs==null)
			return "Error";
		LocalDateTime acc_dt=LocalDateTime.now();
		bs.setAcc_dt(acc_dt);
		bs.setStatus(true);
		
		bid_statusRepository.save(bs);
		
		notifyacceptbid(bs.getSrn().getSrn(), bs.getId());
		return "Accepted";
	}
	
	public boolean notifyacceptbid(long srn,long bid)
	{
		Notifications_service noti = new Notifications_service();
		noti.setType("transporter");
		noti.setSubtype("bid accepted");
		
		//long srn = Long.parseLong(body.get("srn").toString());	
		ServiceRequest sr = serviceRequestRepository.findBySrn(srn);
		if(sr!=null)
		{
			noti.setSrn(sr);
			noti.setDescription(sr.getDescription());
		}
		System.out.println("bidid :    "+bid);
		Bid_status bidid=bid_statusRepository.findById(bid);
		if(bidid!=null)
		{
			noti.setBid_id(bidid);
		}
		LocalDateTime dt=LocalDateTime.now();
		noti.setInserttime(dt);
		
		String res= notificationServiceService.addNotification(noti);
		return true;
	}

	@Override
	public List<HashMap<String, Object>> searchBySRN(long SRN) {
		// TODO Auto-generated method stub
		
		List<HashMap<String, Object>> res=new ArrayList<>();
		ServiceRequest sr=serviceRequestRepository.findBySrn(SRN);
		
		if(sr==null)
			return null;
		
		List<Bid_status> bids=bid_statusRepository.findBySrn(sr);
		if(bids==null)
		{
			HashMap<String, Object> hashmap=new HashMap<>();
			hashmap.put("status", "No_bids" );
			res.add(hashmap);
			return res;
		}
		else {
			HashMap<String, Object> hashmap=new HashMap<>();
			hashmap.put("status", "Success" );
			res.add(hashmap);
		}
		
		for(Bid_status bid : bids)
		{
			HashMap<String, Object> hashmap=new HashMap<>();
			hashmap.put("t_name",bid.getT_id().getT_name());
			hashmap.put("t_id",bid.getT_id().getId());
			hashmap.put("bid_no", bid.getId());
			hashmap.put("acc_bid_date", bid.getAcc_dt());
			hashmap.put("res_bid_date", bid.getDOR());
			hashmap.put("SRN", bid.getSrn().getSrn());
			hashmap.put("description", bid.getSrn().getDescription());
			hashmap.put("amt", bid.getBid_amt());
			
			res.add(hashmap);
		}
		
		return res;
		
	}

	@Override
	public List<HashMap<String, Object>> allAcceptedSrn(long c_id) {
		// TODO Auto-generated method stub
		List<HashMap<String, Object>> res=new ArrayList<>();
		
		Freight f=freightRepository.findById(c_id);
		
		if(f==null)
			return null;
		List<Bid_status> bids=bid_statusRepository.findByStatusAndC_id(true, f);
		if(bids==null) {
			HashMap<String, Object> hashmap=new HashMap<>();
			hashmap.put("status", "No accepted SRN");
			res.add(hashmap);
			return res;
		}
		else
		{
			HashMap<String, Object> hashmap=new HashMap<>();
			hashmap.put("status", "Success");
			res.add(hashmap);
		}
		
		for(Bid_status bid:bids) {
			HashMap<String, Object> hashmap=new HashMap<>();
			hashmap.put("company", bid.getC_id().getC_name());
			hashmap.put("transported", bid.getT_id().getT_name());
			hashmap.put("srn", bid.getSrn().getSrn());
			hashmap.put("commodity", bid.getSrn().getCommodity());
			hashmap.put("from", bid.getSrn().getPickup_loc());
			hashmap.put("to",bid.getSrn().getDropoff_loc());
			hashmap.put("c_date", bid.getSrn().getPickup_date());
			hashmap.put("d_date", bid.getSrn().getDropoff_date());
			hashmap.put("tonnage", bid.getSrn().getTonnage());
			hashmap.put("distance", bid.getSrn().getDist());
			
			Trip t=tripRepository.findBySrn(bid.getSrn());
			
			if(t==null)
				hashmap.put("status", "trip not assigned");
			else
				hashmap.put("status", t.getStatus());
			
			res.add(hashmap);
			
		}
		
		return res;
	}
	
	@Override
	public List<HashMap<Object, Object>> viewByClientId(long c_id) {
		// TODO Auto-generated method stub
		Freight fr = freightRepository.findById(c_id);
		
		 List<HashMap<Object, Object>> res=new ArrayList<>();
		 List<Bid_status> obj = bid_statusRepository.findByC_id(fr);

		 System.out.println(obj);
		 for(int i=0;i<obj.size();i++)
		 {
			 HashMap<Object, Object> var=new HashMap<>();
			 var.put("id",obj.get(i).getId());
			 var.put("status", obj.get(i).isStatus());
			 var.put("rd", obj.get(i).getDOR());
			 var.put("bid_amt", obj.get(i).getBid_amt());
			 var.put("srn", obj.get(i).getSrn().getSrn());
			 var.put("t_name", obj.get(i).getT_id().getT_name());
			 var.put("description", obj.get(i).getSrn().getDescription());
			 var.put("acc_date", obj.get(i).getAcc_dt());
			 
			 res.add(var);
		 }
		 return res;
	
	}
	
	@Override
	public List<HashMap<String, Object>> allBids(long c_id) {
		// TODO Auto-generated method stub
		List<HashMap<String, Object>> res=new ArrayList<>();
		Freight fr=freightRepository.findById(c_id);
		List<Bid_status> bids;
		if(fr==null)
			return null;
		System.out.println("wertyuiop[dfghjkl;xcvbnm,etrytyhujxgcfvgbhnytyui");
		List<ServiceRequest> srns=bid_statusRepository.getNotAcceptedSR(true, fr);
		if(srns==null)
			return null;
		
		if(srns.size()!=0)
			bids=bid_statusRepository.findBySrnNotInC_id(srns, fr);
		else
			bids=bid_statusRepository.getFreightBids(fr);
		
		if(bids==null || bids.size()==0)
		{
			HashMap<String, Object> hashmap=new HashMap<>();
			hashmap.put("status", "No_bids" );
			res.add(hashmap);
			return res;
		}
		else {
			HashMap<String, Object> hashmap=new HashMap<>();
			hashmap.put("status", "Success" );
			res.add(hashmap);
		}
		
		System.out.println("000000000000000000 SIZE : "+bids.size());
		for(Bid_status bid : bids)
		{
			System.out.println("1111111111111111111");
			HashMap<String, Object> hashmap=new HashMap<>();
			hashmap.put("t_name",bid.getT_id().getT_name());
			hashmap.put("t_id",bid.getT_id().getId());
			hashmap.put("bid_no", bid.getId());
			hashmap.put("acc_bid_date", bid.getAcc_dt());
			hashmap.put("res_bid_date", bid.getDOR());
			hashmap.put("SRN", bid.getSrn().getSrn());
			hashmap.put("description", bid.getSrn().getDescription());
			hashmap.put("amt", bid.getBid_amt());
			
			
			res.add(hashmap);
		}
		
		return res;
		
		
		
	}

	@Override
	public String confirmBid(long id,boolean status) {
		// TODO Auto-generated method stub
		System.out.println("ertyuio:"+id);
		Bid_status bs=bid_statusRepository.findById(id);
		if(bs==null)
			return "Error";
		LocalDateTime acc_dt=LocalDateTime.now();
		bs.setComfirmed_dt(acc_dt);
		bs.setStatus(status);
		
		bid_statusRepository.save(bs);
		
		notifyconfirmbid(bs.getSrn().getSrn(), bs.getId());
		return "Confirmed";
		
	}

	public boolean notifyconfirmbid(long srn,long bid)
	{
		Notifications_service noti = new Notifications_service();
		noti.setType("freight");
		noti.setSubtype("bid confirmed");
		
		//long srn = Long.parseLong(body.get("srn").toString());	
		ServiceRequest sr = serviceRequestRepository.findBySrn(srn);
		if(sr!=null)
		{
			noti.setSrn(sr);
			noti.setDescription(sr.getDescription());
		}
		System.out.println("bidid :    "+bid);
		Bid_status bidid=bid_statusRepository.findById(bid);
		if(bidid!=null)
		{
			noti.setBid_id(bidid);
		}
		LocalDateTime dt=LocalDateTime.now();
		noti.setInserttime(dt);
		
		String res= notificationServiceService.addNotification(noti);
		return true;
	}
	
}
