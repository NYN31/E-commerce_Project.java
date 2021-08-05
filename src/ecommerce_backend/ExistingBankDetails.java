package ecommerce_backend;

import java.util.*;

public class ExistingBankDetails {
	DBConnector db = new DBConnector() ;
	List<BankAccountDetails> bankAccountList = null ;
	
	public ExistingBankDetails() {
		bankAccountList = new ArrayList<>() ;
	}
	
	public void addAccount(BankAccountDetails bankAccountDetails) {
		bankAccountList.add(bankAccountDetails) ;
	}
	
	public void AccountDetailsHere(BankAccountDetails account) {
		System.out.println("ID: " + account.getId());
		System.out.println("Account name: " + account.getAccountName()) ;
		System.out.println("Account number: " + account.getAccountNumber()) ;
		System.out.println("Account email: " + account.getEmail()) ;
		System.out.println("Bank name: " + account.getBankName()) ;
		System.out.println("Bank branch: " + account.getBankBranch()) ;
		System.out.println("Money: " + account.getMoney()) ;
	}
	
	public BankAccountDetails findAccountDetails(String accNo, String bankName) {
		bankAccountList = db.getAllBankAccounts() ;
		
		if(bankAccountList.size() == 0) {
			System.out.println("Empty account...!") ;
			return null ;
		}
		for(BankAccountDetails account: bankAccountList) {
			if(account == null) { break ; }
			if(accNo.equals(account.getAccountNumber()) && bankName.equals(account.getBankName())) {
				return (BankAccountDetails)db.getBankAccount(accNo, bankName) ;
			}
		}
		return null ;
	}
	
	public void showAll() {
		if(bankAccountList != null) {
			System.out.println("---->") ;
			for(BankAccountDetails account: bankAccountList) {
				System.out.println(account.getAccountName()) ;
				System.out.println(account.getAccountNumber()) ;
				System.out.println(account.getBankName()) ;
				System.out.println(account.getBankBranch()) ;
				System.out.println("--------------") ;
			}
		}
	}
	
	public BankAccountDetails addMoney(BankAccountDetails account,String bankName, double money) {
		bankAccountList = db.getAllBankAccounts();
		BankAccountDetails bankAccount = account ;
		for(BankAccountDetails acc: bankAccountList) {
			if(acc.getAccountNumber().equals(account.getAccountNumber()) && 
					acc.getBankName().equals(bankName)) {
				bankAccount = db.saveMoney(acc.getAccountNumber(), acc.getMoney()+money);
				System.out.println(money + " taka added successfully and you current balance is " + bankAccount.getMoney() + "...!") ;
				return bankAccount ;
			}
		}
		return bankAccount ;
	}
	
	public BankAccountDetails withdrawMoney(BankAccountDetails account, double money, String bankName) {
		bankAccountList = db.getAllBankAccounts();
		BankAccountDetails bankAccount = account ;
		for(BankAccountDetails acc: bankAccountList) {
			if(acc.getAccountNumber().equals(account.getAccountNumber()) && 
					acc.getBankName().equals(bankName)) {
				bankAccount = db.takeMoney(acc.getAccountNumber(), account.getMoney() - money);
				System.out.println(money + " taka withdraw successfully and you current balance is " + bankAccount.getMoney() + "...!") ;
				return bankAccount ;
			}
		}
		return bankAccount ;
	}
	
	public void withdrawBankMoneyAfterPurchase(String acc_number, double money) {
		bankAccountList  = db.getAllBankAccounts();
		for(BankAccountDetails acc: bankAccountList) {
			if(acc == null) break ;
			if(acc.getAccountNumber().equals(acc_number)) {
				money = acc.getMoney() - money ;
				db.takeMoney(acc.getAccountNumber(), money);
				return ;
			}
		}
	}
	public void addBankMoneyAfterPurchase(String email, double money) {
		bankAccountList = db.getAllBankAccounts();
		for(BankAccountDetails acc: bankAccountList) {
			if(acc == null) break ;
			if(acc.getEmail().equals(email)) {
				money = acc.getMoney() + money ;
				db.saveMoney(acc.getAccountNumber(), money);
			}
		}
	}
}
