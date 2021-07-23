package ecommerce_backend;

import java.util.*;

public class CustomerRegList {
	List<CustomerRegDetails> CustomerList = null;
	
	public CustomerRegList() {
		CustomerList = new ArrayList<>() ;
	}
	
	public void addCustomer(CustomerRegDetails customerRegDetails) {
		CustomerList.add(customerRegDetails) ;
	}
	
	public boolean isRegesterCustomerUser(String email, String pass) {
		for(CustomerRegDetails customer: CustomerList) {
			if(email.equals(customer.getEmail()) && pass.equals(customer.getPassword())) {
				return true ;
			}
		}
		return false;
	}
	
	public void showAllCustomer() {
		int customerCount = 0 ;
		for(CustomerRegDetails customer: CustomerList) {
			System.out.println("Customer number: " + ++customerCount) ;
			System.out.println("Customer Name: " + customer.getName()) ;
			System.out.println("Customer Email: " + customer.getEmail()) ;
			System.out.println("Customer Address: " + customer.getAddress());
		}
	}
}
