package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Driver;
import com.example.demo.Model.Freight;
import com.example.demo.Model.ServiceRequest;
import com.example.demo.Model.Transporter;
import com.example.demo.Repository.FreightRepository;
import com.example.demo.Repository.ServiceRequestRepository;

@Service
public class FreightServiceImpl implements FreightService{

	@Autowired
	private FreightRepository freightRepository; 
	
	
	@Autowired
	private ServiceRequestRepository serviceRequestRepository;
	
	
	@Override
	
	public Freight addFreight(Freight f) {
		// TODO Auto-generated method stub
		return freightRepository.save(f);
	}
	@Override
	public List<Freight> viewAll() {
		// TODO Auto-generated method stub
		List<Freight> list = freightRepository.findByIsactive(true);
		return list;
	}
	@Override
	public String deletefreight(long id) {
		// TODO Auto-generated method stub
		Freight fr = freightRepository.findById(id);
		fr.setIsactive(false);
		freightRepository.save(fr);
		return "Success";
	}
//
//	@Override
//	public Long totalactivetrip(long tid) {
//		// TODO Auto-generated method stub
//		Transporter tr = transporterRepository.findById(tid);
//		System.out.println(tr.getId());
//		List<Driver> dr = driverRepository.findByActiveT_id(tr);
//		System.out.println(dr);
//		Long obj = tripRepository.getCount1(dr);
//		return obj;
//	}
//
	@Override
	public Long totalfreightactivetrip(long tid) {
		// TODO Auto-generated method stub
		return null;
	}

}
