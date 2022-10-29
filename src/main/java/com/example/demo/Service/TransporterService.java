package com.example.demo.Service;

import java.util.List;

import com.example.demo.Model.Transporter;

public interface TransporterService {

	Transporter addTransporter(Transporter tr);

	List<Long> getIdlist();

	List<Transporter> getAll();

	String deletetrans(long id);

	Transporter updateTransporter(Transporter tr);

}
