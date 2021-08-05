package ecommerce_backend;

import java.util.*;

public class BuyerFuncImplementation {
	private static Scanner in = new Scanner(System.in) ;
	ValidationMethods validationMethods = null ;
	DBConnector db = new DBConnector() ;
	List<BuyerRegDetails> buyerList = db.getAllBuyer() ;
	BuyerRegList buyerRegList = new BuyerRegList() ;
	
	public BuyerFuncImplementation() {
		validationMethods = new ValidationMethods()  ;
	}
	
	public void showBuyerDetails(BuyerRegDetails buyer) {
		System.out.println("Buyer Name: " + buyer.getName()) ;
		System.out.println("Buyer Email: " + buyer.getEmail()) ;
		System.out.println("Buyer Password: " + buyer.getPassword()) ;
		System.out.println("Buyer Address: " + buyer.getAddress());
		System.out.println("Buyer Money: " + buyer.getMoney());
		System.out.println("-----------------------");
	}
	
	public BuyerRegDetails signInMethodBuyer() {
		String email, password ;
		while(true) {
			System.out.println("Sign in as a buyer...!") ;
			System.out.println("Enter you email: ") ;
			email = in.nextLine() ;
			email = validationMethods.validEmail(email) ;
			
			System.out.println("Enter your password: ") ;
			password = in.nextLine() ;
			
			BuyerRegDetails buyerProfile = buyerRegList.isRegesterBuyerUser(email, password) ;
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
	
	// 1. Edit profile section feature
	public void EditProfile() {
		while(true) {
			System.out.println("0. Back") ;
			System.out.println("1. Change email");
			System.out.println("2. Change password");
			System.out.println("3. Change name");
			System.out.println("4. Change profile picture");
			System.out.println("Please Enter a Edit section choice: ") ;
			int editProfilechoice = in.nextInt();
			in.nextLine() ;
			
			BuyerRegDetails buyerProfile = null ;
			if(editProfilechoice == 1) {
				System.out.println("Please Enter current email: ") ;
				String currentEmail = in.nextLine() ;
				System.out.println("Please Enter new email: ") ;
				while(true) {
					String newEmail = in.nextLine();
					newEmail = validationMethods.validEmail(newEmail) ;
					
					BuyerRegDetails buyerDetailsForEmailChange = buyerRegList.findEmail(newEmail) ;
					
					if(buyerDetailsForEmailChange != null) {
						System.out.println("The email is already exist...! please try again...!") ;
						continue ;
					} else {
						buyerProfile = buyerRegList.changeEmail(currentEmail, newEmail);
						if(buyerProfile != null) {
							System.out.println("Successfully edited your profile...!") ;
							showBuyerDetails(buyerProfile) ;
						}else {
							System.out.println("Your given info are wrong...!");
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
				while(!validationMethods.isValidPassword(newPassword)) {
					System.out.println("Enter a valid password please: ") ;
					newPassword = in.nextLine();
				}
				
				buyerProfile = buyerRegList.changePassword(currentEmail, currentPassword, newPassword);
				if(buyerProfile != null) {
					System.out.println("Successfully edited your profile...!") ;
					showBuyerDetails(buyerProfile) ;
				}else {
					System.out.println("Your given info are wrong...!");
				}
			}
			else if(editProfilechoice == 3) {
				System.out.println("Please enter your current email: ");
				String currentEmail = in.nextLine();
				System.out.println("Please Enter your current name: ") ;
				String currentName = in.nextLine();
				System.out.println("Please Enter your new name: ") ;
				String newName = in.nextLine();
				newName = validationMethods.validName(newName) ;
				buyerProfile = buyerRegList.changeName(currentEmail, currentName, newName) ;
				if(buyerProfile != null) {
					System.out.println("Successfully edited your profile...!") ;
					showBuyerDetails(buyerProfile) ;
				}else {
					System.out.println("Your given info are wrong...!");
				}
			}
			else {
				System.out.println("You are successfully exited from edit section...!") ;
				break ;
			}
		}
	}
	
}
