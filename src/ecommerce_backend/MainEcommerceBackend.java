package ecommerce_backend;

import java.io.*;
import java.util.*;
import java.util.regex.*;



public class MainEcommerceBackend {
	private static final String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
	

	
	private static final Scanner in = new Scanner(System.in) ;
	public static void main(String[] args) throws IOException {
		BuyerRegList buyerList = new BuyerRegList();
		SellerRegList sellerList = new SellerRegList();
		ProductList productList = new ProductList();
		BuyerExtendsProductList buyerExtendsProductList = new BuyerExtendsProductList();
		SellerExtendsProductList sellerExtendsProductList = new SellerExtendsProductList();
		
		
		boolean signIn, sellerSignIn, buyerSignIn ;
		signIn = sellerSignIn = buyerSignIn = false ;
		
		while(true) {
			System.out.println("Welcome to the online shop...!") ;
			System.out.println("Please choose your option...!") ;
			System.out.println("0. Exit from the application");
			System.out.println("1. Create account") ;
			System.out.println("2. Sign in") ;
			System.out.println("Please enter you options: ") ;
			int homePageChoice = in.nextInt() ;
			if(homePageChoice == 1) {
				System.out.println("Create account from here...!") ;
				System.out.println("1. Create buyer account");
				System.out.println("2. Create seller account");
				System.out.println("Please enter you options for create account: ");
				int regPageChoice = in.nextInt();
				in.nextLine();
				
				if(regPageChoice == 1) {
					System.out.println("Please create an account as buyer...!") ;
					
					System.out.println("Enter you name: ") ;
					String name = in.nextLine();
					name = validName(name) ;
					
					System.out.println("Enter you email: ") ;
					String email = in.nextLine();
					email = validEmail(email) ;

					System.out.println("Enter your password: ") ;
					String password = in.next();
					while(!isValidPassword(password)) {
						System.out.println("Enter a valid password please: ") ;
						password = in.next();
					}
					in.nextLine();
					
					System.out.println("Enter you address: ") ;
					String address = in.nextLine();
					address = validAddress(address) ;
					//System.out.println("Enter you profilePicture: ") ;
					
					BuyerRegDetails buyerRegDetails =
							new BuyerRegDetails(name, email, password, address);
					buyerList.addBuyer(buyerRegDetails) ;
					System.out.println("You are registered successfully as a buyer...!");			
				}
				else if(regPageChoice == 2) {
					System.out.println("Fill the form as a seller...!") ;
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
				
					SellerRegDetails sellerRegDetails = 
							new SellerRegDetails(name, email, password, companyName, address);
					sellerList.addSeller(sellerRegDetails) ;
					System.out.println("You are registered successfully as a seller...!");
				}
				else {
					System.out.println("You are redirect to Home page...!") ;
					break ;
				}
			}
			else if(homePageChoice == 2) {
				
				while(true) {
					System.out.println("Please Sign-in here...!") ;
					System.out.println("1. Buyer sign in") ;
					System.out.println("2. Seller sign in");
					System.out.println("3. Exited from sign-in section") ;
					System.out.println("Enter you sign in choice: ") ;
					int singInChoice = in.nextInt();
					in.nextLine();
					
					if(singInChoice == 1) {
						BuyerRegDetails buyerProfile = signInMethodBuyer(buyerList);
						

						
						if(buyerProfile != null) {
							System.out.println("Buyer Details:") ;
							showBuyerDetailsHere(buyerProfile) ;
							signIn = buyerSignIn = true ;
							while(true) {
								System.out.println("All Buyer feature...!") ;
								System.out.println("1. Edit profile"); // done, without picture
								System.out.println("2. View products"); // done
								System.out.println("3. Buy a product");
								System.out.println("4. Rate a product");
								System.out.println("5. Check purchase history");
								System.out.println("6. Connect bank");
								System.out.println("7. Add money");
								System.out.println("8. Sign out");
								System.out.println("9. Back");
								System.out.println("Enter you Buyer feature option: ") ;
								int buyerChoice = in.nextInt();
								in.nextLine();
								
								if(buyerChoice == 1) {
									System.out.println("Buyer profile edit sectoin...!") ;
									
									while(true) {
										System.out.println("0. Back") ;
										System.out.println("1. Change email");
										System.out.println("2. Change password");
										System.out.println("3. Change name");
										System.out.println("4. Change profile picture");
										System.out.println("Please Enter a Edit section choice: ") ;
										int editProfilechoice = in.nextInt();
										in.nextLine() ;
										if(editProfilechoice == 1) {
											System.out.println("Please Enter current email: ") ;
											String currentEmail = in.nextLine() ;
											System.out.println("Please Enter new email: ") ;
											while(true) {
												String newEmail = in.nextLine();
												newEmail = validEmail(newEmail) ;
												BuyerRegDetails buyerDetailsForEmailChange = buyerList.findEmail(newEmail) ;
												if(buyerDetailsForEmailChange != null) {
													System.out.println("The email is already exist...! please try again...!") ;
													continue ;
												} else {
													buyerProfile = buyerList.changeEmail(currentEmail, newEmail);
													if(buyerProfile != null) {
														System.out.println("Successfully edited your profile...!") ;
														showBuyerDetailsHere(buyerProfile) ;
													}else {
														System.out.println("You given info are wrong...!");
													}
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
											
											buyerProfile = buyerList.changePassword(currentEmail, currentPassword, newPassword);
											if(buyerProfile != null) {
												System.out.println("Successfully edited your profile...!") ;
												showBuyerDetailsHere(buyerProfile) ;
											}else {
												System.out.println("You given info are wrong...!");
											}
										}
										else if(editProfilechoice == 3) {
											System.out.println("Please enter your current email: ");
											String currentEmail = in.nextLine();
											System.out.println("Please Enter your current name: ") ;
											String currentName = in.nextLine();
											System.out.println("Please Enter your new name: ") ;
											String newName = in.nextLine();
											newName = validName(newName) ;
											buyerProfile = buyerList.changeName(currentEmail, currentName, newName) ;
											if(buyerProfile != null) {
												System.out.println("Successfully edited your profile...!") ;
												showBuyerDetailsHere(buyerProfile) ;
											}else {
												System.out.println("You given info are wrong...!");
											}
										}
										else {
											System.out.println("You are successfully exited from edit section...!") ;
											break ;
										}
									}
								}
								else if(buyerChoice == 2) {
									System.out.println("View all products...!");
									productList.showAllProducts();
									
								}else if(buyerChoice == 3) {
									
								}else if(buyerChoice == 4) {
									System.out.println("Rate a product...!") ;
									System.out.println("Enter product Id: ") ;
									int productId = in.nextInt();
									System.out.println("Enter product raging: ") ;
									
								}else if(buyerChoice == 5) {
									
								}else if(buyerChoice == 6) {
									
								}else if(buyerChoice == 7) {
									
								}else if(buyerChoice == 8) {
									
								}else {
									System.out.println("You are successfully exited from buyer section...!") ;
									break ;
								}
							}
						}
						else {
							System.out.println("You are not successfully sign in...!") ;
						}
					}
					else if(singInChoice == 2) {
						SellerRegDetails sellerProfile = signInMethodSeller(sellerList);
						

						
						if(sellerProfile != null) {
							System.out.println("Seller Details:") ;
							showSellerDetailsHere(sellerProfile) ;
							signIn = sellerSignIn = true ;
							while(true) {
								System.out.println("All Buyer feature...!") ;
								System.out.println("1. Add a product"); // done
								System.out.println("2. Remove a product"); // done
								System.out.println("3. Product details"); // done
								System.out.println("4. Connect Bank");
								System.out.println("5. Withdraw money");
								System.out.println("6. Check sell histroy");
								System.out.println("7. Sign-out");
								System.out.println("8. Back");
								
								System.out.println("Enter you Seller feature option: ") ;
								int sellerChoice = in.nextInt();
								in.nextLine();
								
								if(sellerChoice == 1) {
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
									sellerExtendsProductList.addProduct(productDetails) ;
									System.out.println("Product added successfully...!") ;
								}
								else if(sellerChoice == 2) {
									System.out.println("Remove a product...!");
									System.out.println("Enter a product id: ") ;
									int id = in.nextInt();
									in.nextLine();
									
									System.out.println("Enter a product name: ") ;
									String name = in.nextLine();
									sellerExtendsProductList.removeProduct(id, name) ;
								}
								else if(sellerChoice == 3) {
									sellerExtendsProductList.showAllProducts(); 
								}else if(sellerChoice == 4) {
									
								}else if(sellerChoice == 5) {
									
								}else if(sellerChoice == 6) {
									
								}else if(sellerChoice == 7) {
									
								}else {
									System.out.println("You are successfully exited from seller section...!") ;
									break ;
								}
							}
						}
					}
					else {
						System.out.println("You are exited from sign in section...!") ;
						break ;
					}
				}		
			}
			else {
				System.out.println("You're exited from application...!") ;
				break ;
			}
		}
	}
	
	public static void showBuyerDetailsHere(BuyerRegDetails buyer) {
		System.out.println("Buyer Name: " + buyer.getName()) ;
		System.out.println("Buyer Email: " + buyer.getEmail()) ;
		System.out.println("Buyer Password: " + buyer.getPassword()) ;
		System.out.println("Buyer Address: " + buyer.getAddress());
		System.out.println("-----------------------");
	}
	public static void showSellerDetailsHere(SellerRegDetails seller) {
		System.out.println("Seller Name: " + seller.getName()) ;
		System.out.println("Seller Email: " + seller.getEmail()) ;
		System.out.println("Seller Password: " + seller.getPassword()) ;
		System.out.println("Seller Address: " + seller.getAddress());
		System.out.println("-----------------------");
	}
	
	public static BuyerRegDetails signInMethodBuyer(BuyerRegList buyerList) {
		String email, password ;
		while(true) {
			System.out.println("Sign in as a buyer...!") ;
			System.out.println("Enter you email: ") ;
			email = in.nextLine() ;
			email = validEmail(email) ;
			
			System.out.println("Enter your password: ") ;
			password = in.nextLine() ;
			
			BuyerRegDetails buyerProfile = buyerList.isRegesterBuyerUser(email, password) ;
			if(buyerProfile != null) {
				System.out.println("You are sign in successfully...!") ;
				return buyerProfile ;
			}
			else {
				System.out.println("Email or Password is incorrect...!") ;
				System.out.println("If you don't want to sign in, then press q or Q: ") ;
				String q = in.nextLine() ;
				if(q.equals("q") || q.equals("Q")) {
					break ;
				}
			}
		}
		return null ;
	}
	
	public static SellerRegDetails signInMethodSeller(SellerRegList sellerList) {
		String email, password ;
		while(true) {
			System.out.println("Sign in as a seller...!") ;
			System.out.println("Enter you email: ") ;
			email = in.nextLine() ;
			email = validEmail(email) ;
			
			System.out.println("Enter your password: ") ;
			password = in.nextLine() ;
			
			SellerRegDetails sellerProfile = sellerList.isRegesterSellerUser(email, password);
			if(sellerProfile != null) {
				System.out.println("You are sign in successfully...!") ;
				return sellerProfile;
			}
			else {
				System.out.println("Email or Password is incorrect...!") ;
				System.out.println("If you don't want to sign in, then press q or Q: ") ;
				String q = in.nextLine() ;
				if(q.equals("q") || q.equals("Q")) {
					break ;
				}
			}
		}
		return null ;
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
