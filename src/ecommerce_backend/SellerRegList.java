package ecommerce_backend;

import java.util.*;

public class SellerRegList {
	DBConnector db = new DBConnector() ;
	List<SellerRegDetails> sellerList = null ;
	
	public SellerRegList() {
		sellerList = new ArrayList<>() ;
	}
	
	public SellerRegDetails isRegesterSellerUser(String email, String pass) {
		sellerList = db.getAllSeller() ;
		for(SellerRegDetails seller: sellerList) {
			if(email.equals(seller.getEmail()) && pass.equals(seller.getPassword())) {
				return seller ;
			}
		}
		return null;
	}
	
	public void showAllSeller() {
		for(SellerRegDetails seller: sellerList) {
			System.out.println("Seller Id: " +  seller.getId()) ;
			System.out.println("Seller Name: " + seller.getName()) ;
			System.out.println("Seller Email: " + seller.getEmail()) ;
			System.out.println("Seller CompanyName: " + seller.getCompanyName()) ;
			System.out.println("Seller Address: " + seller.getAddress()) ;
		}
	}
}
