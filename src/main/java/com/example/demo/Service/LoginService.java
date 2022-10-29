package com.example.demo.Service;

import java.util.HashMap;

import com.example.demo.Model.Login;

public interface LoginService {

	Login addUser(Login lo);

	HashMap<Object, Object> login(String username, String password);

}
