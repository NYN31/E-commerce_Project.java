package ecommerce_backend;

public class BuyerRegDetails {
	private String Name ;
	private String Email;
	private String Password;
	private String Address ;
	private String ProfilePicPath = "";

	
	public BuyerRegDetails(String name, String email, String password,
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
	
	public void setName(String name) { Name = name ; }
	public void setEmail(String email) { Email = email ; }
	public void setPassword(String password) { Password = password ; }
	public void setAddress(String address) { Address = address; }
	public void setProfilePicPath(String profilePicPath) { ProfilePicPath = profilePicPath ; }
}
