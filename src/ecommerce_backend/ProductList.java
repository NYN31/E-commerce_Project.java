package ecommerce_backend;

import java.util.*;

public class ProductList {
	private static Scanner in = new Scanner(System.in) ;
	List<ProductDetails> productList = null ;
	
	public ProductList() {
		productList = new ArrayList<>();
	}
	
	public void showAllProducts() {
		if(productList.isEmpty()) {
			System.out.println("Product list is Empty...!") ;
			return ;
		}
		
		if(productList.size() > 0) {
			System.out.println("Watch all prodcuts with details: ") ;
		}
		
		for(ProductDetails product: productList) {
			System.out.println("Product id: " + product.getProductId()) ;
			System.out.println("Product name: " + product.getProductName()) ;
			System.out.println("Product price: " + product.getProductPrice()) ;
			System.out.println("Product Quantity: " + product.getProductQuantity()) ;
			System.out.println("Product Rating: " + product.getRating()) ;
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
				isExistProduct = true ;
			}
		}
		return isExistProduct ;
	}
	
	public boolean showProductById(int id) {
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
				return true;
			}
		}
		return false ;
	}
	
	
	public void giveRatingToProduct() {
		for(ProductDetails product: productList) {
			System.out.println("Product ID: " + product.getProductId()) ;
			System.out.println("Product Name: " + product.getProductName()) ;
			System.out.println("Product Price: " + product.getProductPrice()) ;
			System.out.println("Product Quantity: " + product.getProductQuantity()) ;
			System.out.println("Product Rating: " + product.getRating()) ;
			System.out.println("------------") ;
		}
		System.out.println("Enter product Id: ") ;
		int id = in.nextInt();
		double rating = 10.0 ;
		while(rating > 5.0) {
			System.out.println("Give ratting out of 5: ") ;
			rating = in.nextInt();			
		}
		
		for(ProductDetails product: productList) {
			if(product.getProductId() == id) {
				double rat = (product.getRating() + rating) / 2.0;
				rat = Math.round(rat * 100.0) / 100.0;
				product.setProductRating(rat);
				System.out.println("You rate the product successfully...!") ;
				System.out.println("Product ID: " + product.getProductId()) ;
				System.out.println("Product Name: " + product.getProductName()) ;
				System.out.println("Product Price: " + product.getProductPrice()) ;
				System.out.println("Product Quantity: " + product.getProductQuantity()) ;
				System.out.println("Product Rating: " + product.getRating()) ;
				return ;
			}
		}
		return ;
	}
	
	public ProductDetails findProductById(int id) {
		for(ProductDetails product: productList) {
			if(product.getProductId() == id) {
				System.out.println("You product is found...!") ;
				return product ;
			}
		}
		return null ;
	}
	
	public void changeProductDetailsAfterPurchase(ProductDetails productDetails, int quantity) {
		for(ProductDetails product: productList) {
			if(product.getProductId() == productDetails.getProductId()) {
				quantity = product.getProductQuantity() - quantity ;
				product.setProductQuantity(quantity);
			}
		}
	}
}
