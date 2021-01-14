package com.ats.webapi.controller.newpos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.webapi.model.newpos.Customer;
import com.ats.webapi.repository.newposrepo.CustomerRepository;

@RestController
public class CustomerApiController {

	@Autowired
	CustomerRepository custRepo;
	
	
	@RequestMapping(value="/getAllCustomer",method=RequestMethod.GET)
	public @ResponseBody List<Customer> getAllCustomer(){
		List<Customer> customerList=new ArrayList<Customer>();
		try {
			customerList=custRepo.getAllCustomer();
		} catch (Exception e) {
			customerList=new ArrayList<Customer>();
			// TODO: handle exception
			e.printStackTrace();
		}
		return customerList;
	}
	
	
}
