package ecommerce_backend;

import java.util.*;

public class BankFunctionalites {
	private static Scanner in = new Scanner(System.in) ;
	DBConnector db = new DBConnector() ;
	List<BankAccountDetails> bankAccountList = null ;
	ExistingBankDetails existingBankDetails = new ExistingBankDetails() ;
	
	
	public BankAccountDetails verifyBuyerBankAccount() {
		BankAccountDetails buyerBankAccountDetails = null ;
		System.out.println("Enter Account Number: ");
		String accountNumber = in.nextLine();

		String bankName = getBankName() ;
		
		
		buyerBankAccountDetails = existingBankDetails.findAccountDetails(accountNumber, bankName);
		return buyerBankAccountDetails ;
	}
	
	public BankAccountDetails verifySellerBankAccount() {
		BankAccountDetails sellerBankAccountDetails = null ;
		
		System.out.println("Enter Account Number: ");
		String accountNumber = in.nextLine();
		String bankName = getBankName() ;
		
		
		sellerBankAccountDetails = existingBankDetails.findAccountDetails(accountNumber, bankName);
		return sellerBankAccountDetails ;
	}
	
	public void createBankAccount() {
		BankAccountDetails accountDetails = null ;
		String bankName = getBankName() ;
		int id = 0 ;
		System.out.println("Enter Account Name: ");
		String accountName = in.nextLine();
		System.out.println("Enter Account Number: ");
		String accountNumber = in.nextLine();
		System.out.println("Enter Account Email: ");
		String email = in.nextLine();
		System.out.println("Enter Bank Branch: ");
		String bankBranch = in.nextLine();
		System.out.println("Deposite some money: ");
		double money = in.nextInt();
		in.nextLine();
		
		accountDetails = new BankAccountDetails(id, accountName,
				accountNumber, email, bankName, bankBranch, money);
		db.saveBankAccount(accountDetails);
		System.out.println("You have created a bank account successfully...!") ;
	}
	
	private static String getBankName() {
		System.out.println("Select a bank account...!") ;
		System.out.println("Please chose a bank: ") ;
		System.out.println("1. BRACK BANK") ; System.out.println("2. DUTCH-BANGLA BANK") ;
		System.out.println("3. ISLAMI BANK") ; System.out.println("4. JANATA BANK") ;
		System.out.println("5. PRIME BANK") ;
		int bankChoice = in.nextInt();
		in.nextLine();
		String bankName = "" ;
		if(bankChoice == 1) {
			bankName = "brack" ;
		}else if(bankChoice == 2) {
			bankName = "dutch bangla" ;
		}else if(bankChoice == 3) {
			bankName = "islami" ;
		}else if(bankChoice == 4) {
			bankName = "janata" ;
		}else if(bankChoice == 5) {
			bankName = "prime" ;
		}
		return bankName ;
	}
}
