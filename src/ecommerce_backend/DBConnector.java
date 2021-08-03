package ecommerce_backend;

import java.sql.*;
import java.util.*;

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
	
	public void saveBuyerAccount(BuyerRegDetails buyer) {
		try{ 
			Connection con = getConnection() ;
			String query = " insert into buyers (name, email, password, address, pic_path)"
			        + " values (?, ?, ?, ?, ?)";
			
		    // create the mysql insert preparedstatement
		    PreparedStatement preparedStmt = con.prepareStatement(query);
		    preparedStmt.setString (1, buyer.getName());
		    preparedStmt.setString (2, buyer.getEmail());
		    preparedStmt.setString (3, buyer.getPassword());
		    preparedStmt.setString (4, buyer.getAddress());
		    preparedStmt.setString (5, "");

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
			String query = " insert into sellers (name, email, password, company, address, pic_path)"
			        + " values (?, ?, ?, ?, ?, ?)";
			
		    PreparedStatement preparedStmt = con.prepareStatement(query);
		    preparedStmt.setString (1, seller.getName());
		    preparedStmt.setString (2, seller.getEmail());
		    preparedStmt.setString (3, seller.getPassword());
		    preparedStmt.setString (4, seller.getCompanyName());
		    preparedStmt.setString (5, seller.getAddress());
		    preparedStmt.setString (6, "");

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
						rs.getString(3), rs.getString(4), rs.getString(5)) ;
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
						rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)) ;
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
						rs.getString(3), rs.getString(4), rs.getString(5)) ;
		    	    break ;
		    	}
		    }
		    
			con.close(); 
		} catch(Exception e) {
			System.out.println(e) ;
		}
		return buyer ;
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
						rs.getString(3), rs.getString(4), rs.getString(5)) ;
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
						rs.getString(3), rs.getString(4), rs.getString(5)) ;
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
}
