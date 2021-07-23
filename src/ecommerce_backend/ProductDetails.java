package ecommerce_backend;

public class ProductDetails {
	private String ProductName ;
	private int Price ;
	private String ProductImagePath = "";
	private int Quantity ;
	
	public ProductDetails(String name, int price, int quantity) {
		ProductName = name ;
		Price = price ;
		Quantity = quantity ;
	}
	
	public String getProductName() { return ProductName; }
	public int getProductPrice() { return Price; }
	public int getProductQuantity() { return Quantity; }
	public String getProductImagePath() { return ProductImagePath; }
	
}
