package ecommerce_backend;

public class PurchaseProductDetails {
	private String userEmail;
	private String productName ;
	private int productQuantity ;
	private int productPrice;
	
	public PurchaseProductDetails(String email, String name, int quantity, int price) {
		userEmail = email ;
		productName = name ;
		productQuantity = quantity ;
		productPrice = price ;
	}
	
	public String getProductEmail() { return userEmail ; }
	public String getProductName() { return productName ; }
	public int getProductQuantity() { return productQuantity ; }
	public int getProductPrice() { return productPrice; }
}
