package ecommerce_backend;

import java.util.*;

public class ProductList {
	List<ProductDetails> productList = null ;
	
	public ProductList() {
		productList = new ArrayList<>();
	}
	
	public void addProduct(ProductDetails productDetails) {
		productList.add(productDetails);
	}
	
	public void showAllProducts() {
		int productCount = 0 ;
		if(productList.isEmpty()) {
			System.out.println("Product list is Empty...!") ;
			return ;
		}
		for(ProductDetails product: productList) {
			System.out.println("Product number: " + ++productCount) ;
			System.out.println("Product name: " + product.getProductName()) ;
			System.out.println("Product price: " + product.getProductPrice()) ;
			System.out.println("Product Quantity: " + product.getProductQuantity()) ;
		}
	}
	
	public boolean showProductByName(String name) {
		if(productList.isEmpty()) {
			System.out.println("Product list is Empty...!") ;
			return false;
		}
		for(ProductDetails product: productList) {
			if(product.getProductName().equals(name)) {
				System.out.println("Product name: " + product.getProductName()) ;
				System.out.println("Product price: " + product.getProductPrice()) ;
				System.out.println("Product Quantity: " + product.getProductQuantity()) ;
				return true;
			}
		}
		return false ;
	}
	
//	public void showProductByTag(String tag) {
//		if(productList.isEmpty()) {
//			System.out.println("Product list is Empty...!") ;
//			return ;
//		}
//		for(ProductDetails product: productList) {
//			if(product.getProductTag().equals(tag)) {
//				System.out.println("Product name: " + product.getProductName()) ;
//				System.out.println("Product price: " + product.getProductPrice()) ;
//				System.out.println("Product tag: " + product.getProductTag()) ;
//			}
//		}
//	}
}
