package com.phizercost.babylsms.ui.factory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.phizercost.babylsms.model.customer.Customer;

public class BabylSMSFileReader {

	public static ArrayList<Customer> read(String filePath, String fileName) {

		ArrayList<Customer> customers = new ArrayList<Customer>();
		BufferedReader br = null;
		String line = "";
		try {

			br = new BufferedReader(new FileReader(filePath));
			Customer customer;
			while ((line = br.readLine()) != null) {

				List<String> alist = new ArrayList<String>();
				StringTokenizer st = new StringTokenizer(line, "|");
				while (st.hasMoreTokens()) {
					alist.add(st.nextToken());
				}
				customer = new Customer(alist.get(0), alist.get(1), alist.get(2), alist.get(3), fileName);
				customers.add(customer);

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return customers;

	}

}
