package ecommerce_backend;

import java.util.*;

public class SellerFuncImplementation {
	private static Scanner in = new Scanner(System.in) ;
	ValidationMethods validationMethods = null ;
	DBConnector db = new DBConnector() ;
	List<SellerRegDetails> sellerList = db.getAllSeller() ;
	SellerRegList sellerRegList = new SellerRegList() ;
	ProductList productList = new ProductList() ;
	List<ProductDetails> productDetailsList = new ArrayList<>() ;
	
	public SellerFuncImplementation() {
		validationMethods = new ValidationMethods() ;
	}
	
	public void showSellerDetails(SellerRegDetails seller) {
		System.out.println("Seller Name: " + seller.getName()) ;
		System.out.println("Seller Email: " + seller.getEmail()) ;
		System.out.println("Seller Password: " + seller.getPassword()) ;
		System.out.println("Seller Address: " + seller.getAddress());
		System.out.println("Seller Money: " + seller.getMoney());
		System.out.println("-----------------------");
	}
	
	public SellerRegDetails signInMethodSeller() {
		String email, password ;
		while(true) {
			System.out.println("Sign in as a seller...!") ;
			System.out.println("Enter you email: ") ;
			email = in.nextLine() ;
			email = validationMethods.validEmail(email) ;
			
			System.out.println("Enter your password: ") ;
			password = in.nextLine() ;
			
			SellerRegDetails sellerProfile = sellerRegList.isRegesterSellerUser(email, password);
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
	
	public void addProduct(int sellerId) {
		int id = 0 ;
		System.out.println("1. Update product quantity which is already exits...!") ;
		System.out.println("2. Add a new Product...!") ;
		int choiceProductOption = in.nextInt();
		in.nextLine();
		
		if(choiceProductOption == 1) {
			productList.showAllProducts();
			
			System.out.println("Enter product Id: ") ;
			int p_id = in.nextInt();
			
			System.out.println("Enter product quantity to add: ") ;
			int quantity = in.nextInt();
			in.nextLine();
			
			productDetailsList = db.getAllProduct() ;
			boolean productExist = false ;
			for(ProductDetails product: productDetailsList) {
				if(product.getProductId() == p_id) {
					quantity = product.getProductQuantity() + quantity ;
					productExist = true ;
					break ;
				}
			}
			
			
			if(productExist) {
				db.updateProductDetails(p_id, quantity) ;
			}else {
				System.out.println("Doesn't find the product. please try again...!") ;
			}
			
		}
		else {
			System.out.println("Enter product name: ") ;
			String name = in.nextLine();
			
			String tag = getProductTagName() ;
			
			System.out.println("Enter product price: ") ;
			int price = in.nextInt();
			in.nextLine();
			
			System.out.println("Enter product Quantity: ") ;
			int quantity = in.nextInt();
			
			ProductDetails productDetails = new ProductDetails(id, sellerId, name, tag, price, quantity, 0);
			db.saveProduct(productDetails) ;	
		}
	}
	
	public void removeProduct(int sellerId) {
		System.out.println("Enter a product id: ") ;
		int id = in.nextInt();
		in.nextLine();
		
		if(db.removeProduct(id, sellerId)) {
			System.out.println("Your product has been removed successfully...!") ;
		}else{
			System.out.println("Product is not available in the store...!") ;
		}
	}
	
	private String getProductTagName() {
		System.out.println("Select a Tag name...!") ;
		System.out.println("1. GROCERY") ; System.out.println("2. EDUCATIONAL EQUIPMENT") ;
		System.out.println("3. ELECTRONIC DEVICE") ; System.out.println("4. HOUSEHOLD EQUIPMENT") ;
		int tagChoice = in.nextInt();
		in.nextLine();
		String tagName = "" ;
		if(tagChoice == 1) {
			tagName = "grocery" ;
		}else if(tagChoice == 2) {
			tagName = "educational" ;
		}else if(tagChoice == 3) {
			tagName = "electronic" ;
		}else if(tagChoice == 4) {
			tagName = "household" ;
		}
		return tagName ;
	}
}
