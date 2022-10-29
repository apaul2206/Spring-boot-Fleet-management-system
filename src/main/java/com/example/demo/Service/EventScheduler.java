package com.example.demo.Service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.DriverRepository;
import com.example.demo.Repository.TrailerRepository;
import com.example.demo.Repository.TruckRepository;

@Service
@EnableScheduling
public class EventScheduler {
	@Autowired
	DriverRepository driverRepository; 
	
	@Autowired
	TruckRepository truckRepository;
	
	@Autowired
	TrailerRepository trailerRepository;
	
	@Scheduled(fixedRate  = 10000000)
    public void updateDB()
    {
    	
    	System.out.println("Hello");
    	LocalDate upperLimit=LocalDate.now();
    //	LocalDateTime lowerLimit=upperLimit.minusSeconds(10);
    	
    	driverRepository.updateDLstatus(upperLimit);
    	trailerRepository.updateVLstatus(upperLimit);
    	trailerRepository.updateRWstatus(upperLimit);
    	truckRepository.updateVLstatus(upperLimit);
    	truckRepository.updateRWstatus(upperLimit);
//    	for (Driver driver : ua) {
//    		useractivity.setTimeStamp(upperLimit);
//    		userActivityRepository.save(useractivity);
//       }    	
    	
    }
}
