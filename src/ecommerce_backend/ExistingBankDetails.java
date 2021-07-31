package ecommerce_backend;

import java.util.*;

public class ExistingBankDetails {
	List<BankAccountDetails> bankAccountList ;
	
	public ExistingBankDetails() {
		bankAccountList = new ArrayList<>() ;
	}
	
	public void addAccount(BankAccountDetails bankAccountDetails) {
		bankAccountList.add(bankAccountDetails) ;
	}
	
	public void AccountDetailsHere(BankAccountDetails account) {
		System.out.println("Account name: " + account.getAccountName()) ;
		System.out.println("Account number: " + account.getAccountNumber()) ;
		System.out.println("Bank name: " + account.getBankName()) ;
		System.out.println("Bank branch: " + account.getBankBranch()) ;
		System.out.println("Money: " + account.getMoney()) ;
	}
	
	public BankAccountDetails findAccountDetails(String name, String accNo,
			String bankName, String branch) {
		if(bankAccountList.size() == 0) {
			System.out.println("Empty account...!") ;
			return null ;
		}
		for(BankAccountDetails account: bankAccountList) {
			if(account == null) { break ; }
			if(accNo.equals(account.getAccountNumber()) && bankName.equals(account.getBankName())
					&& branch.equals(account.getBankBranch())) {
				return account ;
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
	
	public BankAccountDetails addMoney(BankAccountDetails account, double money) {
		for(BankAccountDetails acc: bankAccountList) {
			if(acc == null) break ;
			if(acc.getAccountNumber().equals(account.getAccountNumber()) && 
					acc.getBankName().equals(account.getBankName())) {
				acc.setMoney(acc.getMoney() + money);
				System.out.println(money + " taka added successfully and you current balance is " + acc.getMoney() + "...!") ;
				return acc ;
			}
		}
		return account ;
	}
	
	public BankAccountDetails withdrawMoney(BankAccountDetails account, double money) {
		for(BankAccountDetails acc: bankAccountList) {
			if(acc == null) break ;
			if(acc.getAccountNumber().equals(account.getAccountNumber()) && 
					acc.getBankName().equals(account.getBankName())) {
				acc.setMoney(acc.getMoney() - money);
				System.out.println(money + " taka withdraw successfully and you current balance is " + acc.getMoney() + "...!") ;
				return acc ;
			}
		}
		return account ;
	}
	
	public void changeBankMoneyAfterPurchase(BankAccountDetails account, double money) {
		for(BankAccountDetails acc: bankAccountList) {
			if(acc == null) break ;
			if(acc.getAccountNumber().equals(account.getAccountNumber()) && 
					acc.getBankName().equals(account.getBankName())) {
				money = acc.getMoney() - money ;
				acc.setMoney(money);
			}
		}
	}
	public void addBankMoneyAfterPurchase(BankAccountDetails account, double money) {
		for(BankAccountDetails acc: bankAccountList) {
			if(acc == null) break ;
			if(acc.getAccountNumber().equals(account.getAccountNumber()) && 
					acc.getBankName().equals(account.getBankName())) {
				money = acc.getMoney() + money ;
				acc.setMoney(money);
			}
		}
	}
}
