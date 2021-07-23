package ecommerce_backend;

public class CustomerRegDetails {
	private String Name ;
	private String Email;
	private String Password;
	private String Address ;
	private String ProfilePicPath = "";

	
	public CustomerRegDetails(String name, String email, String password,
			String address) {
		Name = name ;
		Email = email;
		Password = password;
		Address = address ;

	}
	public String getName() { return Name; }
	public String getEmail() { return Email; }
	public String getPassword() { return Password; }
	public String getAddress() { return Address; }
	public String getProfilePicPath() { return ProfilePicPath; }
	
}
