package ecommerce_backend;

import java.util.*;

public class BusinessRegList {
	List<BusinessRegDetails> BusinessList = null;
	
	public BusinessRegList() {
		BusinessList = new ArrayList<>() ;
	}
	
	public void addBusiness(BusinessRegDetails businessRegDetails) {
		BusinessList.add(businessRegDetails) ;
	}
	
	public boolean isRegesterBusinessUser(String email, String pass) {
		for(BusinessRegDetails business: BusinessList) {
			if(email.equals(business.getEmail()) && pass.equals(business.getPassword())) {
				return true ;
			}
		}
		return false;
	}
	
	public void showAllBusiness() {
		int businessCount = 0 ;
		for(BusinessRegDetails business: BusinessList) {
			System.out.println("Business number: " + ++businessCount) ;
			System.out.println("Business Name: " + business.getName()) ;
			System.out.println("Business Email: " + business.getEmail()) ;
			System.out.println("Customer CompanyName: " + business.getCompanyName()) ;
			System.out.println("Customer Address: " + business.getAddress()) ;
		}
	}
}
