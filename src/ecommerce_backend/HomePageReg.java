package ecommerce_backend;

import java.util.*;

public class HomePageReg {
	private static Scanner in = new Scanner(System.in) ;
	ValidationMethods validationMethods = null ;
	DBConnector db = new DBConnector() ;
	
	public HomePageReg() {
		validationMethods = new ValidationMethods() ;
	}
	
	public void CreateBuyerAccount() {
		System.out.println("Please create an account as buyer...!") ;
		int id = 0 ;
		System.out.println("Enter you name: ") ;
		String name = in.nextLine();
		name = validationMethods.validName(name) ;
		
		System.out.println("Enter you email: ") ;
		String email = in.nextLine();
		email = validationMethods.validEmail(email) ;

		System.out.println("Enter your password: ") ;
		String password = in.next();
		while(!validationMethods.isValidPassword(password)) {
			System.out.println("Enter a valid password please: ") ;
			password = in.next();
		}
		in.nextLine();
		
		System.out.println("Enter you address: ") ;
		String address = in.nextLine();
		address = validationMethods.validAddress(address) ;
		//System.out.println("Enter you profilePicture: ") ;
		
		BuyerRegDetails buyerRegDetails =
				new BuyerRegDetails(id, name, email, password, address);
		db.saveBuyerAccount(buyerRegDetails);
	}
	
	public void CreateSellerAccount() {
		System.out.println("Fill the form as a seller...!") ;
		int id = 0 ;
		System.out.println("Enter your name: ") ;
		String name = in.nextLine() ;
		name = validationMethods.validName(name) ;
		
		System.out.println("Enter your email: ") ;
		String email = in.nextLine();
		email = validationMethods.validEmail(email) ;
		
		System.out.println("Enter your password: ") ;
		String password = in.next();
		while(!validationMethods.isValidPassword(password)) {
			System.out.println("Enter a valid password please: ") ;
			password = in.next();
		}
		in.nextLine();
		
		System.out.println("Enter your companyName: ") ;
		String companyName = in.nextLine();
		
		System.out.println("Enter your address: ") ;
		String address = in.nextLine();
		address = validationMethods.validAddress(address) ;
	
		SellerRegDetails sellerRegDetails = 
				new SellerRegDetails(id, name, email, password, companyName, address);
		db.saveSellerAccount(sellerRegDetails);
	}
}
