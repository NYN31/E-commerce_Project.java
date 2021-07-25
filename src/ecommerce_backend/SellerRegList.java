package ecommerce_backend;

import java.util.*;

public class SellerRegList {
	List<SellerRegDetails> SellerList = null;
	
	public SellerRegList() {
		SellerList = new ArrayList<>() ;
	}
	
	public void addSeller(SellerRegDetails sellerRegDetails) {
		SellerList.add(sellerRegDetails) ;
	}
	
	public boolean isRegesterSellerUser(String email, String pass) {
		for(SellerRegDetails business: SellerList) {
			if(email.equals(business.getEmail()) && pass.equals(business.getPassword())) {
				return true ;
			}
		}
		return false;
	}
	
	public void showAllBusiness() {
		int businessCount = 0 ;
		for(SellerRegDetails business: SellerList) {
			System.out.println("Business number: " + ++businessCount) ;
			System.out.println("Business Name: " + business.getName()) ;
			System.out.println("Business Email: " + business.getEmail()) ;
			System.out.println("Customer CompanyName: " + business.getCompanyName()) ;
			System.out.println("Customer Address: " + business.getAddress()) ;
		}
	}
}
