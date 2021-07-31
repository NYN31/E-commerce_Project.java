package ecommerce_backend;

public class ProductDetails {
	private int ProductId; 
	private String ProductName ;
	private int Price ;
	private String ProductImagePath = "";
	private int Quantity ;
	private double Rating ;
	
	public ProductDetails(int productId, String name, int price, int quantity) {
		ProductId = productId ;
		ProductName = name ;
		Price = price ;
		Quantity = quantity ;
		Rating = 5.0 ;
	}
	
	public int getProductId() { return ProductId; }
	public String getProductName() { return ProductName; }
	public int getProductPrice() { return Price; }
	public int getProductQuantity() { return Quantity; }
	public String getProductImagePath() { return ProductImagePath; }
	public double getRating() { return Rating; }
	
	public void setProductRating(double rating) { Rating = rating ; }
	public void setProductQuantity(int quantity) { Quantity = quantity; }
	
}
