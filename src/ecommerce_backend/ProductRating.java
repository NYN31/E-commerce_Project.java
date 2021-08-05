package ecommerce_backend;

public class ProductRating {
	private int Id ;
	private int ProductId ;
	private int BuyerId ;
	private double Ratings ;
	
	public ProductRating(int id, int p_id, int b_id, double ratings) {
		Id = id;
		ProductId = p_id;
		BuyerId = b_id ;
		Ratings = ratings ;
	}
	
	public int getId() { return Id; }
	public int getProductId() { return ProductId; }
	public int getBuyerId() { return BuyerId; }
	public double getRatings() { return Ratings; }
}
