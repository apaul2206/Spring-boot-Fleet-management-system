package com.example.demo.Service;

import java.util.HashMap;
import java.util.List;

public interface Bid_statusService {
	
	String addBid(long srn,int amt,long t_id);
	String acceptBid(long bid_id);
	
	List<HashMap<String, Object>> searchBySRN(long SRN);
	
	List<HashMap<String, Object>> allAcceptedSrn(long c_id);

	List<HashMap<Object, Object>> viewByClientId(long c_id);
	
	List<HashMap<String, Object>> allBids(long c_id);
	String confirmBid(long id, boolean status);
}
