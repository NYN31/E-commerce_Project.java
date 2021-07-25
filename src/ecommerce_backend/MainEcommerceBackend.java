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
		ProductList productList = new ProductList() ;
		CustomerAccessProductList customerAccessProdcutList = new CustomerAccessProductList();
		BusinessAccessProductList businessAccessProductList = new BusinessAccessProductList();
		
		
		System.out.println("0. If you want ot exits") ;
		System.out.println("1. Customer Registration") ;
		System.out.println("2. Business Registration") ;
		System.out.println("3. Customer Signin") ;
		System.out.println("4. Business Signin") ;
		System.out.println("5. Signout(Customer & Business both)") ;
		System.out.println("6. All registered Customer");
		System.out.println("7. All registered Business");
		System.out.println("8. Some Seller feature");
		System.out.println("9. Some Buyer feature");
		
		boolean customerSignin = false ;
		boolean businessSignin = false ;
		boolean isSignin = false ;
		while(true) {
			System.out.println("Chose an operation: ") ;
			int choise = in.nextInt();
			in.nextLine();
			
			if(choise == 1) {
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
			
			else if(choise == 2) {
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
			else if(choise == 8) {	
				if(!isSignin) {
					System.out.println("Please sign in first...!");
					continue ;
				}
				else if(customerSignin) {
					System.out.println("You have no access any here...!") ;
				}
				else if(businessSignin) {
					System.out.println("0. Exit point") ;
					System.out.println("1. Add a product") ;
					System.out.println("2. Remove a product") ;
					System.out.println("3. Product list with details") ;
					System.out.println("4. Connect bank") ;
					System.out.println("5. Withdraw money") ;
					System.out.println("6. Check sell history") ;
					
					while(true) {
						System.out.println("Please Enter a Business section choise: ") ;
						int businesschoice = in.nextInt();
						
						if(businesschoice == 0) {
							System.out.println("You are successfully exited from business section...!") ;
							break ;
						}
						else if(businesschoice == 1) {
							System.out.println("Add a product...!") ;
							System.out.println("Enter a product id: ") ;
							int id = in.nextInt();
							in.nextLine();
							
							System.out.println("Enter product name: ") ;
							String name = in.nextLine();
							
							System.out.println("Enter product price: ") ;
							int price = in.nextInt();
							in.nextLine();
							
							System.out.println("Enter product Quantity: ") ;
							int quantity = in.nextInt();
							
							ProductDetails productDetails = new ProductDetails(id, name, price, quantity);
							businessAccessProductList.addProduct(productDetails) ;
							System.out.println("Product added successfully...!") ;
						}
						else if(businesschoice == 2) {
							System.out.println("Remove a product...!");
							System.out.println("Enter a product id: ") ;
							int id = in.nextInt();
							in.nextLine();
							
							System.out.println("Enter a product name: ") ;
							String name = in.nextLine();
							businessAccessProductList.removeProduct(id, name) ;
						}
						else if(businesschoice == 3) {
							businessAccessProductList.showAllProducts(); 
						}
						
					}

				}
			}
			else if(choise == 9) {
				
				if(!isSignin) {
					System.out.println("Please sign in first...!");
					continue ;
				}
				else if(businessSignin) {
					System.out.println("You have no access any here...!") ;
				}
				else {
					System.out.println("0. Exit point");
					System.out.println("1. Edit profile");
					System.out.println("2. View prodcut");
					System.out.println("3. Buy a product");
					System.out.println("4. Rate a product");
					System.out.println("5. Check purchase history");
					System.out.println("6. Connect bank");
					System.out.println("7. Add money");
					
					while(true) {						
						System.out.println("Please Enter a Customer section choice: ");
						int customerchoise = in.nextInt();
						
						if(customerchoise == 0) {
							System.out.println("You are successfully exited from buyer section...!") ;
							break ;
						}
						else if(customerchoise == 1) {
							System.out.println("1. Change email");
							System.out.println("2. Change password");
							System.out.println("3. Change name");
							System.out.println("4. Change profile picture");
							
							while(true) {
								System.out.println("Please Enter a Edit section choice: ") ;
								int editProfilechoice = in.nextInt();
								in.nextLine() ;
								if(editProfilechoice == 0) {
									System.out.println("You are successfully exited from edit section...!") ;
									break ;
								}
								if(editProfilechoice == 1) {
									System.out.println("Please Enter current email: ") ;
									String currentEmail = in.nextLine() ;
									System.out.println("Please Enter new email: ") ;
									while(true) {
										String newEmail = in.nextLine();
										newEmail = validEmail(newEmail) ;
										CustomerRegDetails customerDetailsForEmailChange = customerList.findEmail(newEmail) ;
										if(customerDetailsForEmailChange != null) {
											System.out.println("The email is already exist...! please try again...!") ;
											continue ;
										} else {
											System.out.println(customerList.changeEmail(currentEmail, newEmail));
											break ;
										}
									}
								}
								else if(editProfilechoice == 2) {
									System.out.println("Please Enter you current email: ") ;
									String currentEmail = in.nextLine();
									System.out.println("Please Enter you current password: ") ;
									String currentPassword = in.nextLine();
									
									System.out.println("Please Enter your new password: ") ;
									String newPassword = in.nextLine();
									while(!isValidPassword(newPassword)) {
										System.out.println("Enter a valid password please: ") ;
										newPassword = in.nextLine();
									}
									
									System.out.println(customerList.changePassword(currentEmail, currentPassword, newPassword));
								}
							}
						}
					}
				}
			}
			else if(choise == 10) {
				if(!isSignin) {
					System.out.println("Please sign in first...!");
					continue ;
				}
				System.out.println("Enter product name for details view: ") ;
				String productName = in.nextLine();
				if(productList.showProductByName(productName)) {
					continue ;
				} else {
					System.out.println("The product is stock out...!");
				}
			}
			else if(choise == 10) {
				
			}
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
