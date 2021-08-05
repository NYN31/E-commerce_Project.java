package ecommerce_backend;

public class BuyerRegDetails {
	private int Id;
	private String Name ;
	private String Email;
	private String Password;
	private String Address ;
	private String ProfilePicPath = "";
	private double Money ;
	private String BankName ;
	private String AccountNum ;

	
	public BuyerRegDetails(int id, String name, String email, String password,
			String address, double money, String b_name, String a_num) {
		Id = id;
		Name = name ;
		Email = email;
		Password = password;
		Address = address ;
		Money = money ;
		BankName = b_name ;
		AccountNum = a_num ;
	}
	
	public int getId() { return Id; }
	public String getName() { return Name; }
	public String getEmail() { return Email; }
	public String getPassword() { return Password; }
	public String getAddress() { return Address; }
	public String getProfilePicPath() { return ProfilePicPath; }
	public double getMoney() { return Money ; }
	public String getBankName() { return BankName; }
	public String getAccountNumber() { return AccountNum; }
}
