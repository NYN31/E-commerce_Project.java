package ecommerce_backend;

public class ProductDetails {
	private int ProductId; 
	private String ProductName ;
	private int Price ;
	private String ProductImagePath = "";
	private int Quantity ;
	
	public ProductDetails(int productId, String name, int price, int quantity) {
		ProductId = productId ;
		ProductName = name ;
		Price = price ;
		Quantity = quantity ;
	}
	
	public int getProductId() { return ProductId; }
	public String getProductName() { return ProductName; }
	public int getProductPrice() { return Price; }
	public int getProductQuantity() { return Quantity; }
	public String getProductImagePath() { return ProductImagePath; }
	
}
