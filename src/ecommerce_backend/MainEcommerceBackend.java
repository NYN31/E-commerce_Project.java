package ecommerce_backend;

import java.io.*;
import java.util.*;
import java.util.regex.*;



public class MainEcommerceBackend {
	private static final Scanner in = new Scanner(System.in) ;
	public static void main(String[] args) throws IOException {
		
		// Object creation
		BuyerRegList buyerList = new BuyerRegList();
		SellerRegList sellerList = new SellerRegList();
		ProductList productList = new ProductList();
		BuyerExtendsProductList buyerExtendsProductList = new BuyerExtendsProductList();
		SellerExtendsProductList sellerExtendsProductList = new SellerExtendsProductList();
		HomePageReg homePageReg = new HomePageReg() ;
		BuyerFuncImplementation buyerFuncImp = new BuyerFuncImplementation();
		SellerFuncImplementation sellerFuncImp = new SellerFuncImplementation() ;
		BankAccountDetails sellerBankAccountDetails = null ;
		BankAccountDetails buyerBankAccountDetails = null ;
		ExistingBankDetails existingBankDetails= new ExistingBankDetails() ;
		BankFunctionalites bankFunctionalites = new BankFunctionalites() ;
		ValidationMethods validationMethods = new ValidationMethods() ;
		List<PurchaseProductDetails> purchaseList = new ArrayList<>() ;
		
		
		
		boolean signIn, sellerSignIn, buyerSignIn, isSellerConnectBank, isBuyerConnectBank ;
		signIn = sellerSignIn = buyerSignIn = isBuyerConnectBank = isSellerConnectBank = false ;
		
		while(true) {
			System.out.println("Welcome to the online shop...!") ;
			System.out.println("Please choose your option...!") ;
			System.out.println("0. Exit from the application");
			System.out.println("1. Create account") ;
			System.out.println("2. Sign in") ;
			System.out.println("3. Create bank Account") ;
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
					BuyerRegDetails buyerRegDetails = homePageReg.CreateBuyerAccount();
					buyerList.addBuyer(buyerRegDetails) ;
					System.out.println("You are registered successfully as a buyer...!");
				}
				else if(regPageChoice == 2) {
					SellerRegDetails sellerRegDetails = homePageReg.CreateSellerAccount();
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
					System.out.println("3. Exited from sign-in section. You are in home page...!") ;
					System.out.println("Enter you sign in choice: ") ;
					int singInChoice = in.nextInt();
					in.nextLine();
					
					if(singInChoice == 1) {
						BuyerRegDetails buyerProfile = buyerFuncImp.signInMethodBuyer(buyerList);
						
						if(buyerProfile != null) {
							System.out.println("Buyer Details: ") ;
							buyerFuncImp.showBuyerDetails(buyerProfile) ;
							signIn = buyerSignIn = false ;
							while(true) {
								System.out.println("All Buyer feature...!") ;
								System.out.println("1. Edit profile"); // done, without picture
								System.out.println("2. View products"); // done
								System.out.println("3. Buy a product"); // done
								System.out.println("4. Rate a product"); // done
								System.out.println("5. Check purchase history"); // done
								System.out.println("6. Connect bank"); // done
								System.out.println("7. Add money"); // done
								System.out.println("8. Sign out"); // done
								System.out.println("9. Back"); // done
								System.out.println("Enter you Buyer feature option: ") ;
								int buyerChoice = in.nextInt();
								in.nextLine();
								
								if(buyerChoice == 1) {
									System.out.println("Buyer profile edit sectoin...!") ;
									buyerList = buyerFuncImp.EditProfile(buyerList);
								}
								else if(buyerChoice == 2) {
									System.out.println("View all products...!");
									productList.showAllProducts();
								}
								else if(buyerChoice == 3) {
									System.out.println("Buy Product: ") ;
									System.out.println("Enter Product ID: ") ;
									int id = in.nextInt();
									System.out.println("Enter Quantity: ");
									int quantity = in.nextInt();
						
									ProductDetails productDetails = productList.findProductById(id) ;
									double money = quantity * productDetails.getProductPrice() ;
									boolean isEnoughQuantity = quantity <= productDetails.getProductQuantity();
									boolean isEnoughMoney = money <= buyerBankAccountDetails.getMoney() ;
									boolean isParchased = validationMethods.isParchaseable(isEnoughMoney, isEnoughQuantity) ;
									if(isParchased) {
										productList.changeProductDetailsAfterPurchase(productDetails, quantity) ;
										existingBankDetails.changeBankMoneyAfterPurchase(buyerBankAccountDetails, money) ;
										existingBankDetails.addBankMoneyAfterPurchase(sellerBankAccountDetails, money);
										purchaseList.add(new PurchaseProductDetails(buyerProfile.getEmail(),
												productDetails.getProductName(), quantity, (int)money)) ; 
									}
								}
								else if(buyerChoice == 4) {
									System.out.println("Rate a product...!") ;
									productList.giveRatingToProduct() ;
								}
								else if(buyerChoice == 5) {
									System.out.println("Show purchase list: ") ;
									for(PurchaseProductDetails list: purchaseList) {
										if(list.getProductEmail().equals(buyerProfile.getEmail())) {
											System.out.println("Product name: " + list.getProductName()) ;
											System.out.println("Product quantity: " + list.getProductQuantity()) ;
											System.out.println("Product price: " + list.getProductPrice()) ;
										}
									}
								}
								else if(buyerChoice == 6) {
									System.out.println("Connect with bank (buyer)...!") ;
									buyerBankAccountDetails = 
											bankFunctionalites.isBuyerConnectWithBank(existingBankDetails);
									if(buyerBankAccountDetails != null) {
										isBuyerConnectBank = true ;
										System.out.println("You are connected with bank...!") ;
									}
									else {
										System.out.println("Please create account in a bank...!") ;
										buyerBankAccountDetails = 
												bankFunctionalites.createBankAccount(existingBankDetails);
										if(buyerBankAccountDetails != null) {
											isBuyerConnectBank = true ;
											System.out.println("You are connected with bank...!") ;
										}
									}
									existingBankDetails.addAccount(buyerBankAccountDetails) ;
									existingBankDetails.AccountDetailsHere(buyerBankAccountDetails);
								}
								else if(buyerChoice == 7) {
									System.out.println("Add money in bank account: ") ;
									if(!isBuyerConnectBank) {
										System.out.println("Please connect with a bank first...!") ;
										continue ;
									}
									System.out.println("Enter Money to add in bank...!") ;
									double money = in.nextDouble() ;
									buyerBankAccountDetails = 
											existingBankDetails.addMoney(buyerBankAccountDetails, money);
									existingBankDetails.AccountDetailsHere(buyerBankAccountDetails);

								}
								else if(buyerChoice == 8) {
									System.out.println("You are successfully sign out...!") ;
									signIn = buyerSignIn = false ;
									break ;
								}
								else {
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
						SellerRegDetails sellerProfile = sellerFuncImp.signInMethodSeller(sellerList);
						
						
						if(sellerProfile != null) {
							System.out.println("Seller Details:") ;
							sellerFuncImp.showSellerDetails(sellerProfile) ;
							signIn = sellerSignIn = true ;
							while(true) {
								System.out.println("All Buyer feature...!") ;
								System.out.println("1. Add a product"); // done
								System.out.println("2. Remove a product"); // done
								System.out.println("3. Product details"); // done
								System.out.println("4. Connect Bank"); // done
								System.out.println("5. Withdraw money");
								System.out.println("6. Check sell histroy");
								System.out.println("7. Sign-out"); // done
								System.out.println("8. Back"); // done
								
								System.out.println("Enter you Seller feature option: ") ;
								int sellerChoice = in.nextInt();
								in.nextLine();
								
								if(sellerChoice == 1) {
									System.out.println("Add a product...!") ;
									ProductDetails productDetails = sellerFuncImp.addProduct() ;
									sellerExtendsProductList.addProduct(productDetails) ;
									productList = sellerExtendsProductList ;
									System.out.println("Product added successfully...!") ;
								}
								else if(sellerChoice == 2) {
									System.out.println("Remove a product...!");
									sellerExtendsProductList = sellerFuncImp.removeProduct(sellerExtendsProductList) ;
									productList = sellerExtendsProductList ;
								}
								else if(sellerChoice == 3) {
									System.out.println("Show all products section...!");
									sellerExtendsProductList.showAllProducts(); 
								}
								else if(sellerChoice == 4) {
									System.out.println("Connect with bank...!") ;
									sellerBankAccountDetails = 
											bankFunctionalites.isSellerConnectWithBank(existingBankDetails);
									if(sellerBankAccountDetails != null) {
										isSellerConnectBank = true ;
										System.out.println("You are connected with bank...!") ;
									}
									else {
										System.out.println("Please create account in a bank...!") ;
										sellerBankAccountDetails = 
												bankFunctionalites.createBankAccount(existingBankDetails);
										if(sellerBankAccountDetails != null) {
											isSellerConnectBank = true ;
											System.out.println("You are connected with bank...!") ;
										}
									}
									existingBankDetails.addAccount(sellerBankAccountDetails) ;
									existingBankDetails.AccountDetailsHere(sellerBankAccountDetails);
								}
								else if(sellerChoice == 5) {
									System.out.println("Withdraw money here...!") ;
									if(!isSellerConnectBank) {
										System.out.println("Please connect with a bank first...!") ;
										continue ;
									}
									System.out.println("Enter Money to withdraw from bank...!") ;
									
									double money = in.nextDouble() ;
									if(money > sellerBankAccountDetails.getMoney()) {
										System.out.println("You don't have sufficient balance, please make less you amount...!") ;
										money = in.nextDouble();
									}
									sellerBankAccountDetails = 
											existingBankDetails.withdrawMoney(sellerBankAccountDetails, money);
									existingBankDetails.AccountDetailsHere(sellerBankAccountDetails);
								}
								else if(sellerChoice == 6) {
									System.out.println("Show sell history list: ") ;
									for(PurchaseProductDetails list: purchaseList) {
										System.out.println("Product name: " + list.getProductName()) ;
										System.out.println("Product quantity: " + list.getProductQuantity()) ;
										System.out.println("Product price: " + list.getProductPrice()) ;
									}
								}
								else if(sellerChoice == 7) {
									System.out.println("You are successfully sign out...!") ;
									signIn = sellerSignIn = false ;
									break ;
								}
								else {
									System.out.println("You are successfully exited from seller section...!") ;
									signIn = sellerSignIn = false ;
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
	

}


