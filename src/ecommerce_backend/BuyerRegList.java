package ecommerce_backend;

import java.util.*;

public class BuyerRegList {
	List<BuyerRegDetails> BuyerList = null;
	
	public BuyerRegList() {
		BuyerList = new ArrayList<>() ;
	}
	
	public List<BuyerRegDetails> getBuyerList() {
		return BuyerList ;
	}
	
	public void setBuyerList(List<BuyerRegDetails> list) {
		BuyerList = list ;
	}
	
	public void addBuyer(BuyerRegDetails buyerRegDetails) {
		BuyerList.add(buyerRegDetails) ;
	}
	
	public BuyerRegDetails isRegesterBuyerUser(String email, String pass) {
		for(BuyerRegDetails buyer: BuyerList) {
			if(email.equals(buyer.getEmail()) && pass.equals(buyer.getPassword())) {
				return buyer ;
			}
		}
		return null;
	}
	
	public void showAllBuyer() {
		int buyerCount = 0 ;
		for(BuyerRegDetails buyer: BuyerList) {
			System.out.println("Customer number: " + ++buyerCount) ;
			System.out.println("Buyer Name: " + buyer.getName()) ;
			System.out.println("Buyer Email: " + buyer.getEmail()) ;
			System.out.println("Buyer Password: " + buyer.getPassword()) ;
			System.out.println("Buyer Address: " + buyer.getAddress());
			System.out.println("-----------------------");
		}
	}
	
	public BuyerRegDetails findEmail(String email) {
		for(BuyerRegDetails buyer: BuyerList) {
			if(buyer.getEmail().equals(email)) {
				return buyer ;
			}
		}
		return null ;
	}
	
	public BuyerRegDetails changeEmail(String currentEmail, String newEmail) {
		for(BuyerRegDetails buyer: BuyerList) {
			if(buyer.getEmail().equals(currentEmail)) {
				buyer.setEmail(newEmail);
				return buyer;
			}
		}
		return null;
	}
	
	public BuyerRegDetails changePassword(String currentEmail, String currentPassword, String newPassword) {
		for(BuyerRegDetails buyer: BuyerList) {
			if(buyer.getEmail().equals(currentEmail) && buyer.getPassword().equals(currentPassword)) {
				buyer.setPassword(newPassword);
				return buyer;
			}
		}
		return null;
	}
	
	public BuyerRegDetails changeName(String currentEmail, String currentName, String newName) {
		for(BuyerRegDetails buyer: BuyerList) {
			if(buyer.getEmail().equals(currentEmail) && buyer.getName().equals(currentName)) {
				buyer.setName(newName) ;
				return buyer;
			}
		}
		return null ;
	}
}
