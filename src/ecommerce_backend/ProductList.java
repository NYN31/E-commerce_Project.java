package ecommerce_backend;

import java.util.*;

public class ProductList {
	private static Scanner in = new Scanner(System.in) ;
	DBConnector db = new DBConnector() ;
	List<ProductDetails> productList = null ;
	List<ProductRating> productRatingList = null ;
	
	public ProductList() {
		productList = new ArrayList<>();
	}
	
	public void showAllProducts() {
		productList = db.getAllProduct();
		if(productList.isEmpty()) {
			System.out.println("Product list is Empty...!") ;
			return ;
		}
		
		if(productList.size() > 0) {
			System.out.println("Watch all prodcuts with details: ") ;
		}
		
		for(ProductDetails product: productList) {
			System.out.println("Product id: " + product.getProductId()) ;
			System.out.println("Seller id: " + product.getSellerId()) ;
			System.out.println("Product name: " + product.getProductName()) ;
			System.out.println("Product tag: " + product.getProductTag()) ;
			System.out.println("Product price: " + product.getProductPrice()) ;
			System.out.println("Product Quantity: " + product.getProductQuantity()) ;
			if(product.getRating() == 0) { System.out.println("Doesn't given any rating...!"); }
			else {
				String rat = String.format("%.2f", product.getRating()) ;
				System.out.println("Product Rating: " + rat) ; 
			}
			System.out.println("------------------------") ;
		}
	}
	
	public boolean showProductByName(String name) {
		boolean isExistProduct = false;
		if(productList.isEmpty()) {
			System.out.println("Product list is Empty...!") ;
			return isExistProduct;
		}
		
		for(ProductDetails product: productList) {
			if(product.getProductName().equals(name)) {
				System.out.println("Product id: " + product.getProductId()) ;
				System.out.println("Product name: " + product.getProductName()) ;
				System.out.println("Product price: " + product.getProductPrice()) ;
				System.out.println("Product Quantity: " + product.getProductQuantity()) ;
				if(product.getRating() == 0) { System.out.println("Doesn't given any rating...!"); }
				else {
					String rat = String.format("%.2f", product.getRating()) ;
					System.out.println("Product Rating: " + rat) ; 
				}
				isExistProduct = true ;
			}
		}
		return isExistProduct ;
	}
	
	public boolean showProductById(int id) {
		productList = db.getAllProduct();
		if(productList.isEmpty()) {
			System.out.println("Product list is Empty...!") ;
			return false;
		}
		for(ProductDetails product: productList) {
			if(product.getProductId() == id) {
				System.out.println("Product id: " + product.getProductId()) ;
				System.out.println("Product name: " + product.getProductName()) ;
				System.out.println("Product price: " + product.getProductPrice()) ;
				System.out.println("Product Quantity: " + product.getProductQuantity()) ;
				if(product.getRating() == 0) { System.out.println("Doesn't given any rating...!"); }
				else {
					String rat = String.format("%.2f", product.getRating()) ;
					System.out.println("Product Rating: " + rat) ; 
				}
				return true;
			}
		}
		return false ;
	}
	
	
	public void giveRatingToProduct(int buyerId) {
		productList = db.getAllProduct();
		for(ProductDetails product: productList) {
			System.out.println("Product ID: " + product.getProductId()) ;
			System.out.println("Product Name: " + product.getProductName()) ;
			System.out.println("Product Price: " + product.getProductPrice()) ;
			System.out.println("Product Quantity: " + product.getProductQuantity()) ;
			if(product.getRating() == 0) { System.out.println("Doesn't given any rating...!"); }
			else {
				String rat = String.format("%.2f", product.getRating()) ;
				System.out.println("Product Rating: " + rat) ; 
			}
			System.out.println("----------------") ;
		}
		int id = 0 ;
		System.out.println("Enter product Id for ratting: ") ;
		int p_id = in.nextInt();
		double rating = 10.0 ;
		while(rating > 5.0) {
			System.out.println("Give ratting out of 5: ") ;
			rating = in.nextDouble();	
		}
		
		ProductRating productRating = new ProductRating(id, p_id, buyerId, rating) ;
		db.rateProduct(productRating) ;
		productRatingList = db.getAllRating() ;
		double totalRating = 0.0;
		int totalRatingCount = 0;
		
		for(ProductRating ratingDetails: productRatingList) {
			if(ratingDetails.getProductId() == p_id) {
				totalRatingCount += 1 ;
				totalRating += ratingDetails.getRatings();
			}
		}
		double rat = totalRating / totalRatingCount ;
		rat = Math.round(rat * 100.0) / 100.0 ;
		ProductDetails productDetails = db.saveRatingAtProductTable(p_id, rat);
		if(productDetails != null) {
			System.out.println("Rating updated successfully...!") ;
			showProductById(productDetails.getProductId()) ;
		}else {
			System.out.println("Your rating doesn't updated successfully...!") ;
		}
	}
	
	public ProductDetails findProductById(int id) {
		productList = db.getAllProduct();
		for(ProductDetails product: productList) {
			if(product.getProductId() == id) {
				System.out.println("You product is found...!") ;
				return product ;
			}
		}
		return null ;
	}
	
	public void changeProductDetailsAfterPurchase(ProductDetails product, int quantity) {
		productList = db.getAllProduct() ;
		for(ProductDetails prod: productList) {
			if(product.getProductId() == prod.getProductId()) {
				quantity = prod.getProductQuantity() - quantity ;
				db.saveProductQuantity(prod.getProductId(), quantity);
				break ;
			}
		}
	}
	
	public void addPurchase(int p_id, int s_id, String p_name, 
			int b_id, int quantity, int money) {
		int id = 0 ;
		PurchaseProductDetails purchase = 
				new PurchaseProductDetails(id, p_name, p_id, b_id, quantity, money, s_id) ;
		db.savePurchase(purchase) ;
	}
}
