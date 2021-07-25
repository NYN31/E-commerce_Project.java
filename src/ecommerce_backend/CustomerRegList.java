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
			System.out.println("Customer Password: " + customer.getPassword()) ;
			System.out.println("Customer Address: " + customer.getAddress());
			System.out.println("-----------------------");
		}
	}
	
	public CustomerRegDetails findEmail(String email) {
		for(CustomerRegDetails customer: CustomerList) {
			if(customer.getEmail().equals(email)) {
				return customer ;
			}
		}
		return null ;
	}
	
	public String changeEmail(String currentEmail, String newEmail) {
		for(CustomerRegDetails customer: CustomerList) {
			if(customer.getEmail().equals(currentEmail)) {
				customer.setEmail(newEmail);
				return "Your email has been changed successfully...!";
			}
		}
		return "Customer not found...!";
	}
	public String changePassword(String currentEmail, String currentPassword, String newPassword) {
		for(CustomerRegDetails customer: CustomerList) {
			if(customer.getEmail().equals(currentEmail) && customer.getPassword().equals(currentPassword)) {
				customer.setPassword(newPassword);
				return "Your password has been changed successfully...!";
			}
		}
		return "Customer not found...!";
	}
}
