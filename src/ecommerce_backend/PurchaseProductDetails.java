package ecommerce_backend;

public class PurchaseProductDetails {
	private int Id; 
	private int ProductId ;
	private String ProductName ;
	private int BuyerId;
	private int productQuantity ;
	private int productPrice;
	private int SellerId ;
	
	public PurchaseProductDetails(int id, String p_name, int p_id, int b_id,
			int quantity, int price, int s_id) {
		Id = id;
		ProductId = p_id;
		ProductName = p_name;
		BuyerId = b_id;
		productQuantity = quantity ;
		productPrice = price ;
		SellerId = s_id ;
	}
	
	public int getId() { return Id; }
	public int getProductId() { return ProductId ; }
	public String getProductName() { return ProductName; }
	public int getBuyerId() { return BuyerId ; }
	public int getProductQuantity() { return productQuantity ; }
	public int getProductPrice() { return productPrice; }
	public int getSellerId() { return SellerId; }
}
