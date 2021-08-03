package ecommerce_backend;

public class SellerRegDetails {
	private int Id;
	private String Name ;
	private String Email;
	private String Password;
	private String CompanyName;
	private String Address;
	private String LogoImagePath = "" ;
	
	public SellerRegDetails(int id, String name, String email, String password,
			String companyName, String address) {
		Id = id ;
		Name = name ;
		Email = email;
		Password = password;
		CompanyName = companyName ;
		Address = address ;
		LogoImagePath = "" ;
	}
	
	public int getId() { return Id; }
	public String getName() { return Name; }
	public String getEmail() { return Email; }
	public String getPassword() { return Password; }
	public String getCompanyName() { return CompanyName; }
	public String getAddress() { return Address; }
	public String getLogoImagePath() { return LogoImagePath; }
}
