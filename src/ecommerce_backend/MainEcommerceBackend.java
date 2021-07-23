package ecommerce_backend;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class MainEcommerceBackend {
	private static final String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
	
	private static final Scanner in = new Scanner(System.in) ;
	public static void main(String[] args) throws IOException {
		
		
		/* Creating object of CustomerList, BusinessList, ProductList */
		CustomerRegList customerList = new CustomerRegList();
		BusinessRegList businessList = new BusinessRegList();
		//ProductList productList = new ProductList() ;
		
		System.out.println("0. If you want ot exits") ;
		System.out.println("1. Business Registration") ;
		System.out.println("2. Customer Registration") ;
		System.out.println("3. Customer Signin") ;
		System.out.println("4. Business Signin") ;
		System.out.println("5. Signout") ;
		System.out.println("6. All registered Customer");
		System.out.println("7. All registered Business");
		System.out.println("8. Add product(added by business user)");
		System.out.println("9. View product(viewed by customer & business user)");
		System.out.println("10. Show all products details") ;
		
		boolean customerSignin = false ;
		boolean businessSignin = false ;
		boolean isSignin = false ;
		while(true) {
			System.out.println("Chose an operation: ") ;
			int choise = in.nextInt();
			in.nextLine();
	
			if(choise == 1) {
				System.out.println("Fill the form as a business...!") ;
				System.out.println("Enter your name: ") ;
				String name = in.nextLine() ;
				name = validName(name) ;
				
				
				System.out.println("Enter your email: ") ;
				String email = in.nextLine();
				email = validEmail(email) ;
				
				System.out.println("Enter your password: ") ;
				String password = in.next();
				while(!isValidPassword(password)) {
					System.out.println("Enter a valid password please: ") ;
					password = in.next();
				}
				in.nextLine();
				
				System.out.println("Enter your companyName: ") ;
				String companyName = in.nextLine();
				
				System.out.println("Enter your address: ") ;
				String address = in.nextLine();
				address = validAddress(address) ;
			
				BusinessRegDetails businessRegDetails = 
						new BusinessRegDetails(name, email, password, companyName, address);
				businessList.addBusiness(businessRegDetails) ;
				System.out.println("You are registered successfully as a Business...!"); 
			}
			
			else if(choise == 2) {
				System.out.println("Fill the form as a Customer...!") ;
				System.out.println("Enter your name: ") ;
				String name = in.nextLine() ;
				name = validName(name) ;
				
				
				System.out.println("Enter your email: ") ;
				String email = in.nextLine();
				email = validEmail(email) ;
				
				System.out.println("Enter your password: ") ;
				String password = in.next();
				while(!isValidPassword(password)) {
					System.out.println("Enter a valid password please: ") ;
					password = in.next();
				}
				in.nextLine();
				
				System.out.println("Enter your address: ") ;
				String address = in.nextLine();
				address = validAddress(address) ;
				
				
				CustomerRegDetails customerRegDetails =
						new CustomerRegDetails(name, email, password, address);
				customerList.addCustomer(customerRegDetails) ;
				System.out.println("You are registered successfully as a customer...!"); 
			}
			else if(choise == 3) {
				if(isSignin) {
					System.out.print("You are already Signed in ") ;
					if(customerSignin) { System.out.println("as a customer") ; }
					else if(businessSignin) { System.out.println("as a business") ; }
					continue ;
				}
				System.out.println("Customer sign in here: ") ;
				System.out.println("Enter your email: ") ;
				String email = in.next();
				System.out.println("Enter your password: ") ;
				String password = in.next();
				
				if(customerList.isRegesterCustomerUser(email, password)) {
					isSignin = true ;
					customerSignin = true ;
					System.out.println("You are successfully sign in...!") ;
				} else {
					System.out.println("Please Regester yourself or your password/email is wrong") ;
				}
			}
			else if(choise == 4) {
				if(isSignin) {
					System.out.print("You are already Signed in ") ;
					if(customerSignin) { System.out.println("as a customer") ; }
					else if(businessSignin) { System.out.println("as a business") ; }
					continue ;
				}
				System.out.println("Business sign in here: ") ;
				System.out.println("Enter your email: ") ;
				String email = in.next();
				System.out.println("Enter your password: ") ;
				String password = in.next();
				
				if(businessList.isRegesterBusinessUser(email, password)) {
					isSignin = true ;
					businessSignin = true ;
					System.out.println("You are successfully sign in...!") ;
				} else {
					System.out.println("Please Regester yourself or your password/email is wrong...!") ;
				}
			}
			else if(choise == 5) {
				if(!isSignin) {
					System.out.println("Please, sign in first...!") ;
					continue ;
				} else {
					isSignin = false ;
					customerSignin = false ;
					businessSignin = false ;
					System.out.println("You are successfully sign out...!") ;
				}
			}
			
			else if(choise == 6) {
				if(!isSignin) {
					System.out.println("Please sign in first...!") ;
					continue ;
				}
				System.out.println("Here are all regestered customer: ") ;
				customerList.showAllCustomer() ;
			}
			else if(choise == 7) {
				if(!isSignin) {
					System.out.println("Please sign in first...!") ;
					continue ;
				}
				System.out.println("Here are all regestered business: ") ;
				businessList.showAllBusiness() ;
			}
//			else if(choise == 8) {
//				if(!isSignin) {
//					System.out.println("Please sign in first...!");
//				}
//				else if(customerSignin) {
//					System.out.println("You can't be add any product") ;
//				}
//				else if(businessSignin) {
//					System.out.println("Add a business product for purchase...!") ;
//					System.out.println("Enter product name: ") ;
//					String name = in.nextLine();
//					
//					System.out.println("Enter product price: ") ;
//					int price = in.nextInt();
//					in.nextLine();
//					
//					System.out.println("Enter product Quantity: ") ;
//					int quantity = in.nextInt();
//					
//					ProductDetails productDetails = new ProductDetails(name, price, quantity);
//					productList.addProduct(productDetails) ;
//					System.out.println("Product added successfully...!") ;
//				}
//			}
//			else if(choise == 9) {
//				if(!isSignin) {
//					System.out.println("Please sign in first...!") ;
//					continue ;
//				}
//				
//				System.out.println("Enter product name for search: ") ;
//				String productName = in.nextLine();
//				if(productList.showProductByName(productName)) {
//					continue ;
//				} else {
//					System.out.println("The product is stock out...!");
//				}
//			}
//			else if(choise == 10) {
//				productList.showAllProducts();
//			}
			else if(choise == 0) {
				break ;
			}
		}
		in.close();
	}
	
	public static String validName(String name) {
		while(!(name.length() >= 3 && name.length() < 65)) {					
			System.out.println("Enter a valid name please: ") ;
			name = in.nextLine();
		}
		return name ;
	}
	
	public static String validEmail(String Email) {
		Pattern pattern = Pattern.compile(regex);
		String email = Email ;
		while(true) {
			Matcher matcher = pattern.matcher(email);
			if(matcher.matches()) {
				break ;
			}
			System.out.println("Enter a valid email please: ") ;
			email = in.nextLine();
		}
		return email ;
	}

	
	public static boolean isValidPassword(String password)
    {
            boolean isValid = true;
            if (password.length() > 15 || password.length() < 6)
            {
                    System.out.println("Password must be less than 15 and more than 5 characters in length.");
                    isValid = false;
            }
            String upperCaseChars = "(.*[A-Z].*)";
            if (!password.matches(upperCaseChars ))
            {
                    System.out.println("Password must have atleast one uppercase character");
                    isValid = false;
            }
            String lowerCaseChars = "(.*[a-z].*)";
            if (!password.matches(lowerCaseChars ))
            {
                    System.out.println("Password must have atleast one lowercase character");
                    isValid = false;
            }
            String specialChars = "(.*[@,#,$,%].*$)";
            if (!password.matches(specialChars ))
            {
                    System.out.println("Password must have atleast one special character among @#$%");
                    isValid = false;
            }
            return isValid; 
    }
	
	public static String validAddress(String parameterAddress) {
		String address = parameterAddress ;
		while(address.length() == 0) {					
			System.out.println("Enter a valid address please: ") ;
			address = in.nextLine();
			if(address.length() > 0) {
				return address ;
			}
		}
		return address ;
	}
}
