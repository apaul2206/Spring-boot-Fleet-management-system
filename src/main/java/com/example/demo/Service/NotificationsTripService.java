package com.example.demo.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import com.example.demo.Model.Notifications_trip;

public interface NotificationsTripService {

	List<HashMap<String,Object>> sendTripStatus(long c_id,String dt);

	String addNotification(Notifications_trip noti);

	List<HashMap<String, Object>> sendTripNoti(long c_id);
	
}
