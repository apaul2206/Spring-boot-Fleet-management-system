package com.example.demo.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Trailer;
import com.example.demo.Model.Transporter;
import com.example.demo.Repository.TrailerRepository;
import com.example.demo.Repository.TransporterRepository;

@Service
public class TrailerServiceImpl implements TrailerService{

	@Autowired
	private TrailerRepository trailerRepository;
	
	@Autowired
	private TransporterRepository transporterRepository;
	
	@Override
	public Trailer addTrailer(Trailer tr) {
		// TODO Auto-generated method stub
		return trailerRepository.save(tr);
	}
	
	@Override
	public List<HashMap<Object, Object>> viewByTransId(long tid) {
		// TODO Auto-generated method stub
		Transporter tr = transporterRepository.findById(tid);
		
		List<HashMap<Object, Object>> res=new ArrayList<>();
		List<Trailer> obj = trailerRepository.findByT_id(tr);
		 System.out.println(obj);
		 for(int i=0;i<obj.size();i++)
		 {
			 HashMap<Object, Object> var=new HashMap<>();
			 var.put("id",obj.get(i).getId());
			 var.put("type", obj.get(i).getType());
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
		
		List<Long> obj = trailerRepository.findInactiveIDByTid(tr);
		return obj;
	}
	
	
	@Override
	public List<Trailer> getAll() {
		// TODO Auto-generated method stub
		List<Trailer> list = trailerRepository.findByIsactive(true);
		return list;
	}

	@Override
	public String deletetrailer(long id) {
		// TODO Auto-generated method stub
		Trailer tr = trailerRepository.findById(id);
		tr.setIsactive(false);
		trailerRepository.save(tr);
		return "Success";
	}

	@Override
	public List<Long> trailercount() {
		// TODO Auto-generated method stub
	
		
		List<Long> list = new ArrayList<>();
		list = trailerRepository.getCount();
		return list;
	}

	@Override
	public List<Long> totaltrailer(long tid) {
		// TODO Auto-generated method stub
		Transporter tr = transporterRepository.findById(tid);
		
		List<Long> obj = trailerRepository.findInactiveIDByTid(tr);
		return obj;
	}

	@Override
	public List<Long> trailerIDlist(long tid) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
