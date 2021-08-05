package ecommerce_backend;

import java.util.*;

public class SellerRegList {
	DBConnector db = new DBConnector() ;
	List<SellerRegDetails> sellerList = null ;
	List<BankAccountDetails> bankAccounts = new ArrayList<>() ;
	
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
	
	public SellerRegDetails withdrawMoneyFromAccounts(SellerRegDetails seller, String bankName, String accNum, double money) {
		SellerRegDetails sellerRegDetails = seller ;
		bankAccounts = db.getAllBankAccounts();
		if(seller.getAccountNumber().equals(accNum) && seller.getBankName().equals(bankName)) {
			sellerRegDetails = db.takeMoneyFromAccounts(seller.getId(), seller.getMoney() - money) ;
		} else {
			System.out.println("Don't match with given info") ;
			System.out.println(accNum + " " + bankName) ;
			System.out.println(seller.getAccountNumber() + seller.getBankName()) ;
			return  sellerRegDetails ;
		}
		
		for(BankAccountDetails account: bankAccounts) {
			if(account.getAccountNumber().equals(accNum) &&
					account.getBankName().equals(bankName)) {				
				db.saveMoney(accNum, account.getMoney() + money) ;
			}
		}
		return sellerRegDetails ;
	}
}
