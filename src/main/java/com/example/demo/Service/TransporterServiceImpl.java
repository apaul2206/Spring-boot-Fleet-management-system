package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Transporter;
import com.example.demo.Repository.LoginRepository;
import com.example.demo.Repository.TransporterRepository;

@Service
public class TransporterServiceImpl implements TransporterService{
	@Autowired
	private TransporterRepository transporterRepository;
	
	@Autowired
	LoginRepository	loginRepository;
	
	@Override
	public Transporter addTransporter(Transporter t) {
		// TODO Auto-generated method stub
		return transporterRepository.save(t);
	}

	@Override
	public List<Long> getIdlist() {
		// TODO Auto-generated method stub
		List<Long> list=new ArrayList<>();
		list = transporterRepository.getAllIds();
		return list;
	}

	@Override
	public List<Transporter> getAll() {
		// TODO Auto-generated method stub
		List<Transporter> list = transporterRepository.findByIsactive(true);
		return list;
	}

	@Override
	public String deletetrans(long id) {
		// TODO Auto-generated method stub
		Transporter tr = transporterRepository.findById(id);
		tr.setIsactive(false);
		transporterRepository.save(tr);
		
//		loginRepository.deleteByTransporter_id(tr);
		return "Success";
	}

	@Override
	public Transporter updateTransporter(Transporter tr) {
		// TODO Auto-generated method stub
		System.out.println("555"+tr);
		Transporter existing = transporterRepository.findById(tr.getId());
	
		if(existing==null)
			return null;
		
		existing.setAddr(tr.getAddr());
		existing.setEmail(tr.getEmail());
		existing.setLevel(tr.getLevel());
		existing.setPerson_name(tr.getPerson_name());
		existing.setT_name(tr.getT_name());
		existing.setTelephone(tr.getTelephone());
		
		return transporterRepository.save(existing);
	}
}
