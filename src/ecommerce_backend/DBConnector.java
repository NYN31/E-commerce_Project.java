package ecommerce_backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBConnector {
	public DBConnector() {
		
	}
	
	private Connection getConnection() {
		Connection con = null ;
		try {
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ecommerce","root","") ;
		}catch(Exception e) {
			System.out.println(e) ;
		}
		return con ;
	}
	
	/*** BUYER & SELLER SECTION ***/
	public void saveBuyerAccount(BuyerRegDetails buyer) {
		try{ 
			Connection con = getConnection() ;
			String query = " insert into buyers (id, name, email, password, address, pic_path, "
					+ "money, bank_name, acc_num) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
		    // create the mysql insert preparedstatement
		    PreparedStatement preparedStmt = con.prepareStatement(query);
		    preparedStmt.setInt(1, buyer.getId());
		    preparedStmt.setString (2, buyer.getName());
		    preparedStmt.setString (3, buyer.getEmail());
		    preparedStmt.setString (4, buyer.getPassword());
		    preparedStmt.setString (5, buyer.getAddress());
		    preparedStmt.setString (6, "");
		    preparedStmt.setDouble (7, buyer.getMoney()) ;
		    preparedStmt.setString (8, buyer.getBankName()) ;
		    preparedStmt.setString (9, buyer.getAccountNumber()) ;

		    // execute the preparedstatement
		    preparedStmt.execute();
			con.close(); 
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	public void saveSellerAccount(SellerRegDetails seller) {
		try{ 
			Connection con = getConnection() ;
			String query = " insert into sellers (id, name, email, password, company, address, pic_path, money"
					+ ", bank_name, acc_num) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
		    PreparedStatement preparedStmt = con.prepareStatement(query);
		    preparedStmt.setInt(1, seller.getId());
		    preparedStmt.setString (2, seller.getName());
		    preparedStmt.setString (3, seller.getEmail());
		    preparedStmt.setString (4, seller.getPassword());
		    preparedStmt.setString (5, seller.getCompanyName());
		    preparedStmt.setString (6, seller.getAddress());
		    preparedStmt.setString (7, "");
		    preparedStmt.setDouble (8, seller.getMoney());
		    preparedStmt.setString (9, seller.getBankName());
		    preparedStmt.setString (10, seller.getAccountNumber());

		    preparedStmt.execute();
			con.close(); 
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	public List<BuyerRegDetails> getAllBuyer() {
		List<BuyerRegDetails> list = new ArrayList<>() ;
		try{ 
			Connection con = getConnection() ;
			Statement stmt = con.createStatement();  
			ResultSet rs = stmt.executeQuery("select * from buyers");
			while(rs.next()) {
				BuyerRegDetails buyerRegDetails = new BuyerRegDetails(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(7),
						rs.getString(8), rs.getString(9)) ;
				list.add(buyerRegDetails) ;
			}
			con.close(); 
		}catch(Exception e){
			System.out.println(e);
		}
		return list ;
	}
	
	public List<SellerRegDetails> getAllSeller() {
		List<SellerRegDetails> list = new ArrayList<>() ;
		try{ 
			Connection con = getConnection() ;
			Statement stmt = con.createStatement();  
			ResultSet rs = stmt.executeQuery("select * from sellers");
			while(rs.next()) {
				SellerRegDetails sellerRegDetails = new SellerRegDetails(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),
						rs.getDouble(8), rs.getString(9), rs.getString(10)) ;
				list.add(sellerRegDetails) ;
			}
			con.close(); 
		}catch(Exception e){
			System.out.println(e);
		}
		return list ;
	}
	
	public BuyerRegDetails setEmail(String currentEmail, String newEmail) { // hasProblem
		BuyerRegDetails buyer = null ;
		try {
			Connection con = getConnection() ;
			Statement stmt = con.createStatement();  
			
			String query = "update buyers set email='"+newEmail+"' where email='"+currentEmail+ "';";
		    PreparedStatement preparedStmt = con.prepareStatement(query);
		    preparedStmt.executeQuery();
		    
		    ResultSet rs = stmt.executeQuery("select * from buyers");
		    while(rs.next()) {
		    	if(rs.getString(3).equals(newEmail)) {
		    	    buyer = new BuyerRegDetails(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getDouble(6), rs.getString(7), rs.getString(8)) ;
		    	    break ;
		    	}
		    }
		    
			con.close(); 
		} catch(Exception e) {
			System.out.println(e) ;
		}
		return buyer ;
	}
	
	public BuyerRegDetails saveMoneyToAccount(int byr_id, double money) {
		BuyerRegDetails buyerRegDetails = null ;
		try {
			Connection con = getConnection() ;
			Statement stmt = con.createStatement();  
			
			String query = "update buyers set money='"+money+"' where id='"+byr_id+"';";
		    PreparedStatement preparedStmt = con.prepareStatement(query);
		    preparedStmt.executeQuery();
		    
		    ResultSet rs = stmt.executeQuery("select * from buyers");
		    while(rs.next()) {
		    	if(rs.getInt(1) == byr_id) {
		    	    buyerRegDetails = new BuyerRegDetails(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(7),
						rs.getString(8), rs.getString(9)) ;
		    	    break ;
		    	}
		    }
		    
			con.close(); 
		} catch(Exception e) {
			System.out.println(e) ;
		}
		return buyerRegDetails ;
	}
	public void saveMoneyToSellerAccount(int slr_id, double money) {
		try {
			Connection con = getConnection() ;
			
			String query = "update sellers set money='"+money+"' where id='"+slr_id+"';";
		    PreparedStatement preparedStmt = con.prepareStatement(query);
		    preparedStmt.executeQuery();
		    
			con.close(); 
		} catch(Exception e) {
			System.out.println(e) ;
		}
	}
	
	public SellerRegDetails takeMoneyFromAccounts(int slr_id, double money) {
		SellerRegDetails sellerRegDetails = null ;
		try {
			Connection con = getConnection() ;
			Statement stmt = con.createStatement();  
			
			String query = "update sellers set money='"+money+"' where id='"+slr_id+"';";
		    PreparedStatement preparedStmt = con.prepareStatement(query);
		    preparedStmt.executeQuery();
		    
		    ResultSet rs = stmt.executeQuery("select * from sellers");
		    while(rs.next()) {
		    	if(rs.getInt(1) == slr_id) {
		    		sellerRegDetails = new SellerRegDetails(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),
						rs.getDouble(8), rs.getString(9), rs.getString(10)) ;
		    	    break ;
		    	}
		    }
		    
			con.close(); 
		} catch(Exception e) {
			System.out.println(e) ;
		}
		return sellerRegDetails ;
	}
	
	public BuyerRegDetails setPassword(String email, String currentPass, String newPass) {
		BuyerRegDetails buyer = null ;
		try {
			Connection con = getConnection() ;
			Statement stmt = con.createStatement();  
			
			String query = "update buyers set password='"+newPass+"' where password='"+currentPass+ "' and email='"+email+"';";
		    PreparedStatement preparedStmt = con.prepareStatement(query);
		    preparedStmt.executeQuery();
		    
		    ResultSet rs = stmt.executeQuery("select * from buyers");
		    while(rs.next()) {
		    	if(rs.getString(3).equals(email) && rs.getString(4).equals(newPass)) {
		    	    buyer = new BuyerRegDetails(rs.getInt(1), rs.getString(2),
							rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(7),
							rs.getString(8), rs.getString(9)) ;
		    	    break ;
		    	}
		    }
		    
			con.close(); 
		} catch(Exception e) {
			System.out.println(e) ;
		}
		return buyer ;
	}
	
	public BuyerRegDetails setName(String email, String currentName, String newName) {
		BuyerRegDetails buyer = null ;
		try {
			Connection con = getConnection() ;
			Statement stmt = con.createStatement();  
			
			String query = "update buyers set name='"+newName+"' where name='"+currentName+ "' and email='"+email+"';";
		    PreparedStatement preparedStmt = con.prepareStatement(query);
		    preparedStmt.executeQuery();
		    
		    ResultSet rs = stmt.executeQuery("select * from buyers");
		    while(rs.next()) {
		    	if(rs.getString(3).equals(email) && rs.getString(2).equals(newName)) {
		    	    buyer = new BuyerRegDetails(rs.getInt(1), rs.getString(2),
							rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(7),
							rs.getString(8), rs.getString(9)) ;
		    	    break ;
		    	}
		    }
		    
			con.close(); 
		} catch(Exception e) {
			System.out.println(e) ;
		}
		return buyer ;
	}
	
	
	/*** PRODUCT SECTION ***/
	
	public void saveProduct(ProductDetails product) {
		try{ 
			Connection con = getConnection() ;
			String query = " insert into products (id, seller_id, name, tag, price, quantity, rating)"
			        + " values (?, ?, ?, ?, ?, ?, ?)";
			
		    PreparedStatement preparedStmt = con.prepareStatement(query);
		    preparedStmt.setInt(1, product.getProductId());
		    preparedStmt.setInt (2, product.getSellerId());
		    preparedStmt.setString (3, product.getProductName());
		    preparedStmt.setString (4, product.getProductTag());
		    preparedStmt.setInt (5, product.getProductPrice());
		    preparedStmt.setInt (6, product.getProductQuantity());
		    preparedStmt.setDouble (7, product.getRating());

		    preparedStmt.execute();
			con.close(); 
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	public void updateProductDetails(int id, int quantity) {
		try {
			Connection con = getConnection() ; 	
			String query = "update products set quantity='"+quantity+"' where id='"+id+ "';";
		    PreparedStatement preparedStmt = con.prepareStatement(query);
		    preparedStmt.executeQuery();
		} catch(Exception e) {
			System.out.println(e) ;
		}
	}
	
	public List<ProductDetails> getAllProduct()  {
		List<ProductDetails> list = new ArrayList<>() ;
		try{ 
			Connection con = getConnection() ;
			Statement stmt = con.createStatement();  
			ResultSet rs = stmt.executeQuery("select * from products");;
			while(rs.next()) {
				ProductDetails productDetails = new ProductDetails(rs.getInt(1), rs.getInt(2),
						rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getInt(7)) ;
				list.add(productDetails) ;
			}
			con.close(); 
		}catch(Exception e){
			System.out.println(e);
		}
		return list ;
	}
	
	public boolean removeProduct(int id, int sellerId) {
		boolean flag = false ;
		try {
			Connection con = getConnection() ;
			Statement stmt = con.createStatement(); 
			ResultSet rs = stmt.executeQuery("select * from products");
			while(rs.next()) {
				if(rs.getInt(1) == id && rs.getInt(2) == sellerId) {
					flag = true ;
					break ;
				}
			}
			
			String query = "delete from products where id='"+id+ "' and seller_id='"+sellerId+"';";
		    PreparedStatement preparedStmt = con.prepareStatement(query);
		    preparedStmt.executeQuery();
		    
			con.close(); 
		} catch(Exception e) {
			System.out.println(e) ;
		}
		return flag ;
	}
	
	/*** PRODUCT RATING SECTION***/
	public void rateProduct(ProductRating productRatings) {
		try{ 
			Connection con = getConnection() ;
			String query = " insert into product_ratings (id, product_id, buyer_id, ratings)"
			        + " values (?, ?, ?, ?)";
			
		    PreparedStatement preparedStmt = con.prepareStatement(query);
		    preparedStmt.setInt(1, productRatings.getId());
		    preparedStmt.setInt (2, productRatings.getProductId());
		    preparedStmt.setInt (3, productRatings.getBuyerId());
		    preparedStmt.setDouble (4, productRatings.getRatings());
		    preparedStmt.execute();
		    
			con.close(); 
		}catch(Exception e){
			System.out.println(e);
		}
	}
	public ProductDetails saveRatingAtProductTable(int productId, double newRatings) {
		ProductDetails productDetails = null ;
		try {
			Connection con = getConnection() ;
			Statement stmt = con.createStatement();  
			
			String query = "update products set rating='"+newRatings+"' where id='"+productId+ "';";
		    PreparedStatement preparedStmt = con.prepareStatement(query);
		    preparedStmt.executeQuery();
		    
		    ResultSet rs = stmt.executeQuery("select * from products");
		    while(rs.next()) {
		    	if(rs.getInt(1) == productId) {
		    		productDetails = new ProductDetails(rs.getInt(1), rs.getInt(2),
							rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getDouble(7)) ;
		    		break ;
		    	}
		    }
		    
			con.close(); 
		} catch(Exception e) {
			System.out.println(e) ;
		}
		return productDetails ;
	}
	
	public void saveProductQuantity(int productId, int newQuantity)  {
		try {
			Connection con = getConnection() ; 
			String query = "update products set quantity='"+ newQuantity +"' where id='"+ productId + "';";
		    PreparedStatement preparedStmt = con.prepareStatement(query);
		    preparedStmt.executeQuery();
		    
			con.close(); 
		} catch(Exception e) {
			System.out.println(e) ;
		}
	}
	
	public List<ProductRating> getAllRating() {
		List<ProductRating> list = new ArrayList<>() ;
		try{ 
			Connection con = getConnection() ;
			Statement stmt = con.createStatement();  
			ResultSet rs = stmt.executeQuery("select * from product_ratings");
			while(rs.next()) {
				ProductRating productRating = new ProductRating(rs.getInt(1), rs.getInt(2),
						rs.getInt(3), (double)rs.getDouble(4)) ;
				list.add(productRating) ;
			}
			con.close(); 
		}catch(Exception e){
			System.out.println(e);
		}
		return list ;
	}
	
	
	/*** PRODUCT PURCHASE SECTION***/
	public void savePurchase(PurchaseProductDetails purchase) {
		try{ 
			Connection con = getConnection() ;
			String query = " insert into product_purchase (id, product_name, product_id, buyer_id, price, quantity, seller_id)"
			        + " values (?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
		    preparedStmt.setInt(1, purchase.getId());
		    preparedStmt.setString (2, purchase.getProductName());
		    preparedStmt.setInt (3, purchase.getProductId());
		    preparedStmt.setInt (4, purchase.getBuyerId());
		    preparedStmt.setInt (5, purchase.getProductPrice());
		    preparedStmt.setInt (6, purchase.getProductQuantity());
		    preparedStmt.setInt (7, purchase.getSellerId());

		    preparedStmt.execute();
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	public List<PurchaseProductDetails> getAllPurchases() {
		List<PurchaseProductDetails> list = new ArrayList<>();
		try {
			Connection con = getConnection() ;
			Statement stmt = con.createStatement();  
			ResultSet rs = stmt.executeQuery("select * from product_purchase");;
			while(rs.next()) {
				PurchaseProductDetails purchase = new PurchaseProductDetails(rs.getInt(1), rs.getString(2),
						rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7)) ;
				list.add(purchase) ;
			}
			con.close(); 
		} catch(Exception e) {
			System.out.println(e) ;
		}
		return list ;
	}

	/*** BANK ACCOUNT SECTION ***/
	public List<BankAccountDetails> getAllBankAccounts() {
		List<BankAccountDetails> list = new ArrayList<>() ;
		try{ 
			Connection con = getConnection() ;
			Statement stmt = con.createStatement();  
			ResultSet rs = stmt.executeQuery("select * from bank_accounts");;
			while(rs.next()) {
				BankAccountDetails bankAccountDetails = new BankAccountDetails(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7)) ;
				list.add(bankAccountDetails) ;
			}
			con.close(); 
		}catch(Exception e){
			System.out.println(e);
		}
		return list ;
	}
	
	public void saveBankAccount(BankAccountDetails account) {
		try{ 
			Connection con = getConnection() ;
			String query = " insert into bank_accounts (id, acc_name, acc_number, email, bank_name, bank_branch, money)"
			        + " values (?, ?, ?, ?, ?, ?, ?)";
			
		    PreparedStatement preparedStmt = con.prepareStatement(query);
		    preparedStmt.setInt(1, account.getId());
		    preparedStmt.setString (2, account.getAccountName());
		    preparedStmt.setString (3, account.getAccountNumber());
		    preparedStmt.setString (4, account.getEmail());
		    preparedStmt.setString (5, account.getBankName());
		    preparedStmt.setString (6, account.getBankBranch());
		    preparedStmt.setDouble (7, account.getMoney());

		    preparedStmt.execute();
			con.close(); 
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	public BankAccountDetails getBankAccount(String accNo, String bankName) {
		BankAccountDetails bankAccountDetails = null ;
		try {
			Connection con = getConnection() ;
			Statement stmt = con.createStatement();  
		    ResultSet rs = stmt.executeQuery("select * from bank_accounts") ;
		    
		    while(rs.next()) {
		    	if(rs.getString(3).equals(accNo) && rs.getString(5).equals(bankName)) {
		    		bankAccountDetails = new BankAccountDetails(rs.getInt(1), rs.getString(2),rs.getString(3),
		    				rs.getString(4),rs.getString(5),rs.getString(6),rs.getDouble(7)) ;
		    		break ;
		    	}
		    }
		    
			con.close(); 
		} catch(Exception e) {
			System.out.println(e) ;
		}
		
		return bankAccountDetails ;
	}
	
	public BankAccountDetails saveMoney(String acc_num, double newMoney) {
		BankAccountDetails bankAccountDetails = null ;
		try {
			Connection con = getConnection() ;
			Statement stmt = con.createStatement();  
			
			String query = "update bank_accounts set money='"+newMoney+"' where acc_number='"+acc_num+ "';";
		    PreparedStatement preparedStmt = con.prepareStatement(query);
		    preparedStmt.executeQuery();
		    
		    ResultSet rs = stmt.executeQuery("select * from bank_accounts");
		    while(rs.next()) {
		    	if(rs.getString(3).equals(acc_num)) {
		    		bankAccountDetails = new BankAccountDetails(rs.getInt(1), rs.getString(2),rs.getString(3),
		    				rs.getString(4),rs.getString(5),rs.getString(6),rs.getDouble(7)) ;
		    		break ;
		    	}
		    }
		    
			con.close(); 
		} catch(Exception e) {
			System.out.println(e) ;
		}
		return bankAccountDetails ;
	}
	public BankAccountDetails takeMoney(String acc_number, double newMoney) {
		BankAccountDetails bankAccountDetails = null ;
		try {
			Connection con = getConnection() ;
			Statement stmt = con.createStatement();  
			
			String query = "update bank_accounts set money='"+newMoney+"' where acc_number='"+acc_number+ "';";
		    PreparedStatement preparedStmt = con.prepareStatement(query);
		    preparedStmt.executeQuery();
		    
		    ResultSet rs = stmt.executeQuery("select * from bank_accounts");
		    while(rs.next()) {
		    	if(rs.getString(3).equals(acc_number)) {
		    		bankAccountDetails = new BankAccountDetails(rs.getInt(1), rs.getString(2),rs.getString(3),
		    				rs.getString(4),rs.getString(5),rs.getString(6),rs.getDouble(7)) ;
		    		break ;
		    	}
		    }
		    
			con.close(); 
		} catch(Exception e) {
			System.out.println(e) ;
		}
		return bankAccountDetails ;
	}
}
