package ecommerce_backend;

import java.util.*;

public class ProductList {
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
}
