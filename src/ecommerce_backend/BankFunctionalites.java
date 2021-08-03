package ecommerce_backend;

import java.util.*;

public class BankFunctionalites {
	private static Scanner in = new Scanner(System.in) ;
	DBConnector db = new DBConnector() ;
	List<BankAccountDetails> bankAccountList = null ;
	
	
	public BankAccountDetails isBuyerConnectWithBank(ExistingBankDetails existingBankDetails) {
		BankAccountDetails buyerBankAccountDetails = null ;
		System.out.println("Enter Account Name: ");
		String accountName = in.nextLine();
		System.out.println("Enter Account Number: ");
		String accountNumber = in.nextLine();
		System.out.println("Enter Bank Name: ");
		String bankName = in.nextLine();
		System.out.println("Enter Bank Branch: ");
		String bankBranch = in.nextLine();
		
		
		buyerBankAccountDetails = existingBankDetails.findAccountDetails(accountName,
				accountNumber, bankName, bankBranch);
		return buyerBankAccountDetails ;
	}
	
	public BankAccountDetails isSellerConnectWithBank(ExistingBankDetails existingBankDetails) {
		BankAccountDetails sellerBankAccountDetails = null ;
		
		System.out.println("Enter Account Name: ");
		String accountName = in.nextLine();
		System.out.println("Enter Account Number: ");
		String accountNumber = in.nextLine();
		System.out.println("Enter Bank Name: ");
		String bankName = in.nextLine();
		System.out.println("Enter Bank Branch: ");
		String bankBranch = in.nextLine();
		
		
		sellerBankAccountDetails = existingBankDetails.findAccountDetails(accountName,
				accountNumber, bankName, bankBranch);
		return sellerBankAccountDetails ;
	}
	
	public BankAccountDetails createBankAccount(ExistingBankDetails existingBankDetails) {
		BankAccountDetails accountDetails = null ;
		System.out.println("Please chose which bank you want to prefer: ") ;
		System.out.println("1. BRACK BANK") ;
		System.out.println("2. DUTCH-BANGLA BANK") ;
		System.out.println("3. ISLAMI BANK") ;
		System.out.println("4. JANATA BANK") ;
		System.out.println("5. PRIME BANK") ;
		int bankChoice = in.nextInt();
		in.nextLine();
		
		String bankName = "" ;
		if(bankChoice == 1) {
			bankName = "brack" ;
		}else if(bankChoice == 2) {
			bankName = "dutch-bangla" ;
		}else if(bankChoice == 3) {
			bankName = "islami" ;
		}else if(bankChoice == 4) {
			bankName = "janata" ;
		}else if(bankChoice == 5) {
			bankName = "prime" ;
		}
		
		System.out.println("Enter Account Name: ");
		String accountName = in.nextLine();
		System.out.println("Enter Account Number: ");
		String accountNumber = in.nextLine();
		System.out.println("Enter Bank Branch: ");
		String bankBranch = in.nextLine();
		
		accountDetails = new BankAccountDetails(accountName,
				accountNumber, bankName, bankBranch);
		return accountDetails ;
	}
}
