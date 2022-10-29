package com.example.demo.Service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Login;
import com.example.demo.Repository.LoginRepository;

@Service
public class LoginServiceImpl implements LoginService{
	@Autowired
	private LoginRepository loginRepository;

	@Override
	public Login addUser(Login lo) {
		// TODO Auto-generated method stub
		return loginRepository.save(lo);
	}

	@Override
	public HashMap<Object, Object> login(String username, String password) {
		// TODO Auto-generated method stub
		
		HashMap<Object, Object> map=new HashMap<>();
		Login user = loginRepository.findByUsernameAndPassword(username,password);
		
		if(user==null)
		{
			System.out.println("111111111111111111111111111111");
			return null;
		}
		
		else if(user.getType().equals("Admin"))
		{
			map.put("type", user.getType());
			map.put("status", "Success");
		}
		
		else if(user.getType().equals("Freight"))
		{
			map.put("type", user.getType());
			map.put("id", user.getFreight_id().getId());
			map.put("cname", user.getFreight_id().getC_name());
			map.put("status", "Success");
		}

		else 
		{
			map.put("type", user.getType());
			map.put("id", user.getTransporter_id().getId());
			map.put("tname", user.getTransporter_id().getT_name());
			
			map.put("status", "Success");
		}
		
		return map;
	}
	
	
}
