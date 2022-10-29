package com.example.demo.Service;

import java.util.List;

import com.example.demo.Model.Freight;

public interface FreightService {
	Freight addFreight(Freight f);

	List<Freight> viewAll();

	String deletefreight(long id);

	Long totalfreightactivetrip(long tid);	
}
