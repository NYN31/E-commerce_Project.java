package ecommerce_backend;

public class BankAccountDetails {
	private int Id;
	private String AccountName ;
	private String AccountNumber ;
	private String Email ;
	private String BankName ;
	private String BankBranch;
	private double Money ;
	
	
	public BankAccountDetails(int id, String name, String number, String email,
			String bankName, String branch, double money) {
		Id = id ;
		AccountName = name ;
		AccountNumber = number ;
		Email = email ;
		BankName = bankName ;
		BankBranch = branch ;
		Money = money ;
	}
	
	public int getId() { return Id; }
	public String getAccountName() { return AccountName; }
	public String getAccountNumber() { return AccountNumber; }
	public String getEmail() { return Email; }
	public String getBankName() { return BankName; }
	public String getBankBranch() { return BankBranch; }
	public double getMoney() { return Money; }
	
	public void setMoney(double money) { Money = money ; }
}
