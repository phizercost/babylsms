package com.phizercost.babylsms.service.savecustomer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phizercost.babylsms.model.customer.Customer;
import com.phizercost.babylsms.repository.savecustomer.SaveCustomerRepository;

 
@Service
public class SaveCustomerServiceImpl implements SaveCustomerService{
	
	@Autowired
	private SaveCustomerRepository saveCustomerRepository;
	
	public void saveCustomer(Customer customerDAO){
		Customer customer = new Customer();
		customer.setDob(customerDAO.getDob());
		customer.setFilename(customerDAO.getFilename());
		customer.setGender(customerDAO.getGender());
		customer.setPhoneNumber(customerDAO.getPhoneNumber());
		customer.setRegistrationDate(customerDAO.getRegistrationDate());
		
		saveCustomerRepository.save(customer);
	}

}
