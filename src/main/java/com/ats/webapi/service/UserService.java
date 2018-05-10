package com.ats.webapi.service;

import java.util.List;

import com.ats.webapi.model.Department;
import com.ats.webapi.model.GetUserType;
import com.ats.webapi.model.Info;
import com.ats.webapi.model.LoginResponse;
import com.ats.webapi.model.User;

public interface UserService {

	public LoginResponse findUserByUsername(String username,String password);

	public String save(User user);

	public Info insertUser(User user);

	public List<Department> getAllDept();

	public List<GetUserType> getAllUserType();
}
