package ecommerce_backend;

import java.util.*;

public class BuyerRegList {
	List<BuyerRegDetails> BuyerList = null;
	
	public BuyerRegList() {
		BuyerList = new ArrayList<>() ;
	}
	
	public void addBuyer(BuyerRegDetails buyerRegDetails) {
		BuyerList.add(buyerRegDetails) ;
	}
	
	public boolean isRegesterBuyerUser(String email, String pass) {
		for(BuyerRegDetails buyer: BuyerList) {
			if(email.equals(buyer.getEmail()) && pass.equals(buyer.getPassword())) {
				return true ;
			}
		}
		return false;
	}
	
	public void showAllCustomer() {
		int customerCount = 0 ;
		for(BuyerRegDetails customer: BuyerList) {
			System.out.println("Customer number: " + ++customerCount) ;
			System.out.println("Customer Name: " + customer.getName()) ;
			System.out.println("Customer Email: " + customer.getEmail()) ;
			System.out.println("Customer Password: " + customer.getPassword()) ;
			System.out.println("Customer Address: " + customer.getAddress());
			System.out.println("-----------------------");
		}
	}
	
	public BuyerRegDetails findEmail(String email) {
		for(BuyerRegDetails customer: BuyerList) {
			if(customer.getEmail().equals(email)) {
				return customer ;
			}
		}
		return null ;
	}
	
	public String changeEmail(String currentEmail, String newEmail) {
		for(BuyerRegDetails customer: BuyerList) {
			if(customer.getEmail().equals(currentEmail)) {
				customer.setEmail(newEmail);
				return "Your email has been changed successfully...!";
			}
		}
		return "Customer not found...!";
	}
	
	public String changePassword(String currentEmail, String currentPassword, String newPassword) {
		for(BuyerRegDetails customer: BuyerList) {
			if(customer.getEmail().equals(currentEmail) && customer.getPassword().equals(currentPassword)) {
				customer.setPassword(newPassword);
				return "Your password has been changed successfully...!";
			}
		}
		return "Customer not found...!";
	}
	
	public String changeName(String currentEmail, String currentName, String newName) {
		for(BuyerRegDetails customer: BuyerList) {
			if(customer.getEmail().equals(currentEmail) && customer.getName().equals(currentName)) {
				customer.setName(newName) ;
				return "Your name has been updated successfully...!";
			}
		}
		return "Customer not found...!";
	}
}
