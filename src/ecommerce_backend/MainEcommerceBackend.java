package ecommerce_backend;

import java.io.*;
import java.util.*;



public class MainEcommerceBackend {
	private static final Scanner in = new Scanner(System.in) ;
	public static void main(String[] args) throws IOException {
		
		
		DBConnector db = new DBConnector();
		List<SellerRegDetails> sellerList = db.getAllSeller();
		
		// Object creation
		ProductList productList = new ProductList();
		BuyerRegList buyerRegList = new BuyerRegList() ;
		SellerRegList sellerRegList = new SellerRegList() ;
		
		HomePageReg homePageReg = new HomePageReg() ;
		BuyerFuncImplementation buyerFuncImp = new BuyerFuncImplementation();
		SellerFuncImplementation sellerFuncImp = new SellerFuncImplementation() ;
		
		BankAccountDetails sellerBankAccountDetails = null ;
		BankAccountDetails buyerBankAccountDetails = null ;
		
		ExistingBankDetails existingBankDetails= new ExistingBankDetails() ;
		BankFunctionalites bankFunctionalites = new BankFunctionalites() ;
		
		ValidationMethods validationMethods = new ValidationMethods() ;
		List<PurchaseProductDetails> purchaseList = new ArrayList<>() ;
		
		
		
		boolean isSellerConnectBank, isBuyerConnectBank ;
		isBuyerConnectBank = isSellerConnectBank = false ;
		
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
				System.out.println("3. Redirect to homepage");
				System.out.println("Please enter you options for create account: ");
				int regPageChoice = in.nextInt();
				in.nextLine();
				
				if(regPageChoice == 1) {
					homePageReg.CreateBuyerAccount();
					System.out.println("You are registered successfully as a buyer...!");
				}
				else if(regPageChoice == 2) {
					homePageReg.CreateSellerAccount();
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
					System.out.println("3. Redirect to homepage") ;
					System.out.println("Enter you sign in choice: ") ;
					int singInChoice = in.nextInt();
					in.nextLine();
					
					if(singInChoice == 1) {
						BuyerRegDetails buyerProfile = buyerFuncImp.signInMethodBuyer();
						
						if(buyerProfile != null) {
							System.out.println("Buyer Details: ") ;
							buyerFuncImp.showBuyerDetails(buyerProfile);
							while(true) {
								System.out.println("All Buyer feature...!") ;
								System.out.println("1. Edit profile"); // done, without picture db
								System.out.println("2. View products"); // done
								System.out.println("3. Buy a product"); // done
								System.out.println("4. Rate a product"); // done
								System.out.println("5. Check purchase history"); // done
								System.out.println("6. Verify or Connect bank account"); // done
								System.out.println("7. Add money"); // done
								System.out.println("8. Sign out"); // done
								System.out.println("Enter you Buyer feature option: ") ;
								int buyerChoice = in.nextInt();
								in.nextLine();
								
								if(buyerChoice == 1) {
									System.out.println("Buyer profile edit sectoin...!") ;
									buyerFuncImp.EditProfile();
								}
								else if(buyerChoice == 2) {
									System.out.println("View all products...!");
									productList.showAllProducts();
								}
								else if(buyerChoice == 3) {
									productList.showAllProducts();
									System.out.println("Buy Product: ") ;
									System.out.println("Enter Product ID: ") ;
									int id = in.nextInt();
									System.out.println("Enter Quantity: ");
									int quantity = in.nextInt();
						
									ProductDetails productDetails = productList.findProductById(id) ;
								
									if(productDetails == null) {
										System.out.println("Wrong Info") ;
										continue ;
									}
									int sellerId = productDetails.getSellerId() ;

									
									int money = quantity * productDetails.getProductPrice() ;
									boolean isEnoughQuantity = quantity <= productDetails.getProductQuantity();
									boolean isEnoughMoney = money <= buyerProfile.getMoney() ;
									boolean isParchased = validationMethods.isParchaseable(isEnoughMoney, isEnoughQuantity) ;
									
									if(isParchased) {
										productList.changeProductDetailsAfterPurchase(productDetails, quantity) ;
										
										buyerProfile = buyerRegList.changeMoneyAfterPurchase(buyerProfile,
												productDetails.getSellerId(), money) ;
										
										productList.addPurchase(id, sellerId, productDetails.getProductName(),
												buyerProfile.getId(), quantity, money) ;
										System.out.println("You are successfully purchased your product...!") ;
									}
								}
								else if(buyerChoice == 4) {
									System.out.println("Rate a product...!") ;
									productList.giveRatingToProduct(buyerProfile.getId()) ;
								}
								else if(buyerChoice == 5) {
									System.out.println("Show purchase list: ") ;
									purchaseList = db.getAllPurchases();
									for(PurchaseProductDetails list: purchaseList) {
										if(list.getBuyerId() == buyerProfile.getId()) {
											System.out.println("Product Id: " + list.getProductId()) ;
											System.out.println("Product name: " + list.getProductName()) ;
											System.out.println("Product quantity: " + list.getProductQuantity()) ;
											System.out.println("Product price: " + list.getProductPrice()) ;
											System.out.println("--------------") ;
										}
									}
								}
								else if(buyerChoice == 6) {
									System.out.println("Please create account in a bank...!") ;
									bankFunctionalites.createBankAccount();
								}
								else if(buyerChoice == 7) {
									System.out.println("Add money in bank account...!") ;
//									if(!isBuyerConnectBank) {
//										System.out.println("Please verify bank account first...!") ;
//										continue ;
//									}
									String bankName = getBankName() ;
									
									System.out.println("Enter bank account number: ") ;
									String accNum = in.nextLine(); 
									
									System.out.println("Enter Money to add in bank...!") ;
									double money = in.nextDouble() ;
									if(money > buyerRegList.getMoneyFromBank(buyerProfile)) {
										System.out.println("You don't have sufficient balance in the bank, please make less ") ;
										money = in.nextDouble();
									}
									
									double prevMoney = buyerProfile.getMoney() ;
									buyerProfile = 
											buyerRegList.addMoney(buyerProfile, accNum, bankName, money);
									double currentMoney = buyerProfile.getMoney();
									if(prevMoney == currentMoney) {
										System.out.println("Your given info is wrong. Money doesn't added successfully...!") ;
									}else {
										System.out.println("Successfully added money...!") ;
									}
									buyerFuncImp.showBuyerDetails(buyerProfile);
								}
								else {
									System.out.println("You are successfully sign out...!") ;
									break ;
								}
							}
						}
						else {
							System.out.println("You are not successfully sign in...!") ;
						}
					}
					else if(singInChoice == 2) {
						SellerRegDetails sellerProfile = sellerFuncImp.signInMethodSeller();
						
						
						if(sellerProfile != null) {
							System.out.println("Seller Details:") ;
							sellerFuncImp.showSellerDetails(sellerProfile) ;
							while(true) {
								System.out.println("All seller feature...!") ;
								System.out.println("1. Add a product"); // done
								System.out.println("2. Remove a product"); // done
								System.out.println("3. Product details"); // done
								System.out.println("4. Verify or Connect Bank account"); // done
								System.out.println("5. Withdraw money"); // done
								System.out.println("6. Check sell histroy");
								System.out.println("7. Sign-out"); // done
								
								System.out.println("Enter you Seller feature option: ") ;
								int sellerChoice = in.nextInt();
								in.nextLine();
								
								if(sellerChoice == 1) {
									System.out.println("Add a product...!") ;
									sellerFuncImp.addProduct(sellerProfile.getId()) ;
									System.out.println("Product added successfully...!") ;
								}
								else if(sellerChoice == 2) {
									System.out.println("Show all products section...!");
									productList.showAllProducts(); 
									System.out.println("Remove a product...!");
									sellerFuncImp.removeProduct(sellerProfile.getId()) ;
								}
								else if(sellerChoice == 3) {
									System.out.println("Show all products section...!");
									productList.showAllProducts(); 
								}
								else if(sellerChoice == 4) {
									System.out.println("Please create account in a bank...!") ;
									bankFunctionalites.createBankAccount();
								}
								else if(sellerChoice == 5) {
									System.out.println("Withdraw money here...!") ;
//									if(!isSellerConnectBank) {
//										System.out.println("Please connect with a bank first...!") ;
//										continue ;
//									}
									
									String bankName = getBankName() ;
									System.out.println("Enter bank account number: ") ;
									String acc_num = in.nextLine();
									
									System.out.println("Enter Money to withdraw from Accounts...!") ;
									double money = in.nextDouble() ;
									if(money > sellerProfile.getMoney()) {
										System.out.println("You don't have sufficient balance for withdrawing, please make less ") ;
										money = in.nextDouble();
									}
									
									if(money < 0) money = 0 ;
									double prevMoney = sellerProfile.getMoney() ;
									sellerProfile = 
											sellerRegList.withdrawMoneyFromAccounts(sellerProfile, bankName, acc_num, money);
									double currentMoney = sellerProfile.getMoney() ;
									if(prevMoney == currentMoney) {
										System.out.println("Your given info is wrong. Money doesn't withdraw successfully...!") ;
									}
									System.out.println("Hello") ;
									sellerFuncImp.showSellerDetails(sellerProfile);
								}
								else if(sellerChoice == 6) {
									System.out.println("Show sell history list: ") ;
									purchaseList = db.getAllPurchases();
									boolean flag = false ;
									for(PurchaseProductDetails list: purchaseList) {
										if(list.getSellerId() == sellerProfile.getId()) {
											flag = true ;
											System.out.println("Product Id: " + list.getProductId()) ;
											System.out.println("Product name: " + list.getProductName()) ;
											System.out.println("Product quantity: " + list.getProductQuantity()) ;
											System.out.println("Product price: " + list.getProductPrice()) ;
											System.out.println("----------------") ;
										}
									}
									if(!flag) { System.out.println("Empty sell history list...!"); }
								}
								else if(sellerChoice == 7) {
									System.out.println("You are successfully sign out...!") ;
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
	
	private static String getBankName() {
		System.out.println("Select a bank account...!") ;
		System.out.println("Please chose which bank you want to prefer: ") ;
		System.out.println("1. BRACK BANK") ; System.out.println("2. DUTCH-BANGLA BANK") ;
		System.out.println("3. ISLAMI BANK") ; System.out.println("4. JANATA BANK") ;
		System.out.println("5. PRIME BANK") ;
		int bankChoice = in.nextInt();
		in.nextLine();
		String bankName = "" ;
		if(bankChoice == 1) {
			bankName = "brack" ;
		}else if(bankChoice == 2) {
			bankName = "dutch bangla" ;
		}else if(bankChoice == 3) {
			bankName = "islami" ;
		}else if(bankChoice == 4) {
			bankName = "janata" ;
		}else if(bankChoice == 5) {
			bankName = "prime" ;
		}
		return bankName ;
	}
}


