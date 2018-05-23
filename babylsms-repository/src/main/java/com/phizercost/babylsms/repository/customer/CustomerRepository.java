package com.phizercost.babylsms.repository.customer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phizercost.babylsms.model.customer.Customer;


@Repository
public interface CustomerRepository  extends JpaRepository<Customer, Integer>{
	
	@Query("select c from Customer c, FileObject f where c.fileName = f.fileName and f.status =1 order by c.fileName") //Name of table and attributes should be the same as the class and case sensitive
	List<Customer> getAllCustomers();
	
	@Query("SELECT c FROM Customer c where c.id = key")
    List<Customer> findByCustomerInFile(@Param("id") int key);
}
