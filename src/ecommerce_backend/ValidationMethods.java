package ecommerce_backend;

import java.util.*;
import java.util.regex.*;

public class ValidationMethods {
	private static final String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
	private static Scanner in = new Scanner(System.in);
	
	public String validName(String name) {
		while(!(name.length() >= 3 && name.length() < 65)) {					
			System.out.println("Enter a valid name please: ") ;
			name = in.nextLine();
		}
		return name ;
	}
	
	public String validEmail(String Email) {
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

	
	public boolean isValidPassword(String password)
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
	
	public String validAddress(String parameterAddress) {
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
	
	public boolean isParchaseable(boolean money, boolean quantity) {
		boolean flag = true ;
		if(!money) {
			System.out.println("You haven't enough money") ;
			flag = false ;
		}
		if(!quantity) {
			System.out.println("The product has such quantity.") ;
			flag = false ;
		}
		return flag ;
	}
}
