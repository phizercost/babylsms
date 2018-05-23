package com.phizercost.babylsms.service.showcustomersbyfile;

import java.util.List;

import com.phizercost.babylsms.model.customer.Customer;


public interface ShowCustomersByFileService {
	public List<Customer> getCustomersByFile(int id);
}
