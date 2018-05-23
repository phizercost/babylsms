package com.phizercost.babylsms.service.showcustomersbyfile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.phizercost.babylsms.model.customer.Customer;

import com.phizercost.babylsms.repository.customer.CustomerRepository;


public class ShowCustomersByFileServiceImpl implements ShowCustomersByFileService{
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public List<Customer> getCustomersByFile(int id) {
		return customerRepository.findByCustomerInFile(id);
	}
	


}
