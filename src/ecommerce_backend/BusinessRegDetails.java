package ecommerce_backend;

public class BusinessRegDetails {
	private String Name ;
	private String Email;
	private String Password;
	private String CompanyName;
	private String Address;
	private String LogoImagePath = "" ;
	
	public BusinessRegDetails(String name, String email, String password,
			String companyName, String address) {
		Name = name ;
		Email = email;
		Password = password;
		CompanyName = companyName ;
		Address = address ;
		LogoImagePath = "" ;
	}
	public String getName() { return Name; }
	public String getEmail() { return Email; }
	public String getPassword() { return Password; }
	public String getCompanyName() { return CompanyName; }
	public String getAddress() { return Address; }
	public String getLogoImagePath() { return LogoImagePath; }
}
