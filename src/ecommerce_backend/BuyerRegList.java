package ecommerce_backend;

import java.util.*;

public class BuyerRegList {
	DBConnector db = new DBConnector() ;
	List<BuyerRegDetails> buyerList = null ;
	List<BankAccountDetails> bankAccounts = null ;
	List<SellerRegDetails> sellerRegList = new ArrayList<>() ;
	
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
			System.out.println("Buyer Money: " + buyer.getMoney());
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
	
	public BuyerRegDetails addMoney(BuyerRegDetails buyer, String accNum, String bankName, double money) {
		BuyerRegDetails buyerRegDetails = null ;
		bankAccounts = db.getAllBankAccounts();
		if(buyer.getAccountNumber().equals(accNum) && buyer.getBankName().equals(bankName)) {
			buyerRegDetails = db.saveMoneyToAccount(buyer.getId(), buyer.getMoney() + money) ;
		} else {
			System.out.println("Don't match with given info") ;
			return buyer ;
		}
		
		for(BankAccountDetails account: bankAccounts) {
			if(account.getAccountNumber().equals(accNum) &&
					account.getBankName().equals(bankName)) {				
				db.takeMoney(accNum, account.getMoney() - money) ;
			}
		}
		return buyerRegDetails ;
	}
	
	public BuyerRegDetails changeMoneyAfterPurchase(BuyerRegDetails buyer, int slr_id, double money) {
		BuyerRegDetails buyerRegDetails = db.saveMoneyToAccount(buyer.getId(), money) ;
		sellerRegList = db.getAllSeller() ;
		for(SellerRegDetails seller: sellerRegList) {
			if(seller.getId() == slr_id) {
				db.saveMoneyToSellerAccount(slr_id, seller.getMoney() + money);
				break ;
			}
		}
		return buyerRegDetails ;
	}
	
	public double getMoneyFromBank(BuyerRegDetails buyer) {
		bankAccounts = db.getAllBankAccounts() ;
		double money = 0.0 ;
		for(BankAccountDetails account: bankAccounts)  {
			if(account.getAccountNumber().equals(buyer.getAccountNumber()) && 
					account.getBankName().equals(buyer.getBankName())) {
				money = account.getMoney();
			}
		}
		return money ;
	}
}
