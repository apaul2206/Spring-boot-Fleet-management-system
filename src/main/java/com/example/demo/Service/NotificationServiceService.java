package com.example.demo.Service;

import java.util.HashMap;
import java.util.List;

import com.example.demo.Model.Notifications_service;

public interface NotificationServiceService {
	
	List<HashMap<String,Object>> getSrnStatusFreight(long c_id , String dt);
	List<HashMap<String,Object>> getSrnStatustransporter(long t_id,String dt);
	String addNotification(Notifications_service noti);
	List<HashMap<String, Object>> getFreightNoti(long c_id);
	List<HashMap<String, Object>> getTransporterNoti(long t_id);
	

}
