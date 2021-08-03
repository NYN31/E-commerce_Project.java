package ecommerce_backend;

import java.util.*;

public class BuyerRegList {
	DBConnector db = new DBConnector() ;
	List<BuyerRegDetails> buyerList = null ;
	
	public BuyerRegList() {
		buyerList = new ArrayList<>() ;
	}
	
	
	public BuyerRegDetails isRegesterBuyerUser(String email, String pass) {
		buyerList = db.getAllBuyer();
		for(BuyerRegDetails buyer: buyerList) {
			if(email.equals(buyer.getEmail()) && pass.equals(buyer.getPassword())) {
				return buyer ;
			}
		}
		return null;
	}
	
	public void showAllBuyer() {;
		for(BuyerRegDetails buyer: buyerList) {
			System.out.println("Customer Id: " + buyer.getId()) ;
			System.out.println("Buyer Name: " + buyer.getName()) ;
			System.out.println("Buyer Email: " + buyer.getEmail()) ;
			System.out.println("Buyer Password: " + buyer.getPassword()) ;
			System.out.println("Buyer Address: " + buyer.getAddress());
			System.out.println("-----------------------");
		}
	}
	
	public BuyerRegDetails findEmail(String email) {
		buyerList = db.getAllBuyer();
		for(BuyerRegDetails buyer: buyerList) {
			if(buyer.getEmail().equals(email)) {
				return buyer ;
			}
		}
		return null ;
	}
	
	public BuyerRegDetails changeEmail(String currentEmail, String newEmail) {
		buyerList = db.getAllBuyer();
		for(BuyerRegDetails buyer: buyerList) {
			if(buyer.getEmail().equals(currentEmail)) {
				return (BuyerRegDetails)db.setEmail(currentEmail, newEmail);
			}
		}
		return null;
	}
	
	public BuyerRegDetails changePassword(String currentEmail, String currentPassword, String newPassword) {
		buyerList = db.getAllBuyer();
		for(BuyerRegDetails buyer: buyerList) {
			if(buyer.getEmail().equals(currentEmail) && buyer.getPassword().equals(currentPassword)) {
				return db.setPassword(currentEmail, currentPassword, newPassword);
			}
		}
		return null;
	}
	
	public BuyerRegDetails changeName(String currentEmail, String currentName, String newName) {
		buyerList = db.getAllBuyer();
		for(BuyerRegDetails buyer: buyerList) {
			if(buyer.getEmail().equals(currentEmail) && buyer.getName().equals(currentName)) {
				return (BuyerRegDetails)db.setName(currentEmail, currentName, newName) ;
			}
		}
		return null ;
	}
}
