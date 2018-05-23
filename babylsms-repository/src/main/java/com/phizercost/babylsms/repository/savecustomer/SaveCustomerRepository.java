package com.phizercost.babylsms.repository.savecustomer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phizercost.babylsms.model.customer.Customer;

@Repository
public interface SaveCustomerRepository extends JpaRepository<Customer, Integer>{
	
}
