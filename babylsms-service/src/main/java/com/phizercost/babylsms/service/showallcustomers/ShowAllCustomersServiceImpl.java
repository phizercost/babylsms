package com.phizercost.babylsms.service.showallcustomers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phizercost.babylsms.model.customer.Customer;
import com.phizercost.babylsms.repository.customer.CustomerRepository;

 

@Service
public class ShowAllCustomersServiceImpl implements ShowAllCustomersService{

	@Autowired
	private CustomerRepository customerRepository;
	public List<Customer> getAllCustomers() {
		return customerRepository.getAllCustomers();
	}

}
