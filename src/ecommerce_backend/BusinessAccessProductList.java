package ecommerce_backend;

public class BusinessAccessProductList extends ProductList {
	
	public void addProduct(ProductDetails productDetails) {
		productList.add(productDetails);
	}
	
	public void removeProduct(int id, String name) {
		if(productList.isEmpty()) {
			System.out.println("Product list is Empty...!") ;
			return ;
		}
		ProductDetails deleteProduct = null ;
		for(ProductDetails product: productList) {
			if(product.getProductName().equals(name) && product.getProductId() == id) {
				deleteProduct = product;
				break ;
			}
		}
		if(deleteProduct == null) System.out.println("Product is not found in the list...!");
		else {
			productList.remove(deleteProduct) ;
		}
	}
	

}
