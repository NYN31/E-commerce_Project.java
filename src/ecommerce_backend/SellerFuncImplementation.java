package ecommerce_backend;

import java.util.*;

public class SellerFuncImplementation {
	private static Scanner in = new Scanner(System.in) ;
	ValidationMethods validationMethods = null ;
	
	public SellerFuncImplementation() {
		validationMethods = new ValidationMethods() ;
	}
	
	public void showSellerDetails(SellerRegDetails seller) {
		System.out.println("Seller Name: " + seller.getName()) ;
		System.out.println("Seller Email: " + seller.getEmail()) ;
		System.out.println("Seller Password: " + seller.getPassword()) ;
		System.out.println("Seller Address: " + seller.getAddress());
		System.out.println("-----------------------");
	}
	
	public SellerRegDetails signInMethodSeller(SellerRegList sellerList) {
		String email, password ;
		while(true) {
			System.out.println("Sign in as a seller...!") ;
			System.out.println("Enter you email: ") ;
			email = in.nextLine() ;
			email = validationMethods.validEmail(email) ;
			
			System.out.println("Enter your password: ") ;
			password = in.nextLine() ;
			
			SellerRegDetails sellerProfile = sellerList.isRegesterSellerUser(email, password);
			if(sellerProfile != null) {
				System.out.println("You are sign in successfully...!") ;
				return sellerProfile;
			}
			else {
				System.out.println("Email or Password is incorrect...!") ;
				System.out.println("If you don't want to sign in, then press q or Q: ") ;
				String q = in.nextLine() ;
				if(q.equals("q") || q.equals("Q")) {
					break ;
				}
			}
		}
		return null ;
	}
	
	public ProductDetails addProduct() {
		System.out.println("Enter a product id: ") ;
		int id = in.nextInt();
		in.nextLine();
		
		System.out.println("Enter product name: ") ;
		String name = in.nextLine();
		
		System.out.println("Enter product price: ") ;
		int price = in.nextInt();
		in.nextLine();
		
		System.out.println("Enter product Quantity: ") ;
		int quantity = in.nextInt();
		
		ProductDetails productDetails = new ProductDetails(id, name, price, quantity);
		return productDetails ;
	}
	
	public SellerExtendsProductList removeProduct(SellerExtendsProductList sellerExtendsProductList) {
		System.out.println("Enter a product id: ") ;
		int id = in.nextInt();
		in.nextLine();
		
		System.out.println("Enter a product name: ") ;
		String name = in.nextLine();
		sellerExtendsProductList.removeProduct(id, name) ;
		return sellerExtendsProductList ;
	}
}
