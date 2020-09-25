package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Customer;

public class CustomerService {
	
	private Map<String, Customer> customerMap;
	
	private static final CustomerService instance = new CustomerService();  // 싱글턴 기법
		
	
	private CustomerService() {
		customerMap = new HashMap<String, Customer>();
		System.out.println("In CustomerService Constructor");		
	}
	
	public static CustomerService getInstance() {  // 싱글턴 기법
		return instance;
	}
	
	public void addCustomer(Customer customer) {
		customerMap.put(customer.getId(),customer);
		
	}
	public Customer findCustomer(String id) {
		if(id!=null) 
			return customerMap.get(id.toLowerCase());
		else
			return null;
	}
	
	public List<Customer> getAllCustomers() {
		List<Customer> customerList = new ArrayList<Customer>(customerMap.values());
		
		return customerList;
	}
	
	public Customer login(String id, String password) {
		Customer customer = findCustomer(id);
	
		if(customer != null) {
			if( password.equals(customer.getPassword())  ) {
				System.out.println("login success");
				return customer;
			}
					
			else {
				System.out.println("login failed");
				return null;
			}
		}	
		return null;
	}
	
	
	
}
