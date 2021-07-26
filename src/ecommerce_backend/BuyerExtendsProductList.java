package ecommerce_backend;

public class BuyerExtendsProductList extends ProductList {
	
	
	public ProductDetails giveRatingToProduct(int productId, float rating) {
		for(ProductDetails product: productList) {
			if(product.getProductId() == productId) {
				product.setProductRating(rating) ;
				return product ;
			}
		}
		return null ;
	}

}
