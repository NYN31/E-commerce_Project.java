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
				System.out.println("Please Sign-in here...!") ;
				System.out.println("1. Buyer sign in") ;
				System.out.println("2. Seller sign in");
				System.out.println("3. Exited from sign-in section") ;
				
				while(true) {
					System.out.println("Enter you sign in choice: ") ;
					int singInChoice = in.nextInt();
					in.nextLine();
					
					if(singInChoice == 1) {
						System.out.println("Sign in as a buyer...!") ;
						signIn = signInMethodBuyer(buyerList);
						
						if(signIn) {
							System.out.println("All Buyer feature...!") ;
						}
					}
					else if(singInChoice == 2) {
						System.out.println("Sign in as a seller...!") ;
						signIn = signInMethodSeller(sellerList) ;
						
						if(signIn) {
							System.out.println("All Seller feature...!") ;
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
	
	public static boolean signInMethodSeller(SellerRegList sellerList) {
		String email, password ;
		while(true) {
			System.out.println("Enter you email: ") ;
			email = in.nextLine() ;
			email = validEmail(email) ;
			
			System.out.println("Enter your password: ") ;
			password = in.nextLine() ;
			
			if(sellerList.isRegesterSellerUser(email, password)) {
				System.out.println("You are sign in successfully...!") ;
				return true ;
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
		return false ;
	}
	
	public static boolean signInMethodBuyer(BuyerRegList buyerList) {
		String email, password ;
		while(true) {
			System.out.println("Enter you email: ") ;
			email = in.nextLine() ;
			email = validEmail(email) ;
			
			System.out.println("Enter your password: ") ;
			password = in.nextLine() ;
			
			if(buyerList.isRegesterBuyerUser(email, password)) {
				System.out.println("You are sign in successfully...!") ;
				return true ;
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
		return false ;
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
