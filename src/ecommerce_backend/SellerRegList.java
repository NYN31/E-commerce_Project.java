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
	
	public SellerRegDetails isRegesterSellerUser(String email, String pass) {
		for(SellerRegDetails seller: SellerList) {
			if(email.equals(seller.getEmail()) && pass.equals(seller.getPassword())) {
				return seller ;
			}
		}
		return null;
	}
	
	public void showAllSeller() {
		int businessCount = 0 ;
		for(SellerRegDetails seller: SellerList) {
			System.out.println("Seller number: " + ++businessCount) ;
			System.out.println("Seller Name: " + seller.getName()) ;
			System.out.println("Seller Email: " + seller.getEmail()) ;
			System.out.println("Seller CompanyName: " + seller.getCompanyName()) ;
			System.out.println("Seller Address: " + seller.getAddress()) ;
		}
	}
}
