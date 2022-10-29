package com.example.demo.Service;

import java.util.HashMap;
import java.util.List;

import com.example.demo.Model.Trailer;

public interface TrailerService {
	List<HashMap<Object, Object>> viewByTransId(long tid);

	List<Long> listIDByTransId(long tid);

	Trailer addTrailer(Trailer tr);

	List<Trailer> getAll();

	String deletetrailer(long id);

	List<Long> trailercount();

	List<Long> trailerIDlist(long tid);

	List<Long> totaltrailer(long tid);
}
