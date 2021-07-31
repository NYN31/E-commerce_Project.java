package ecommerce_backend;

public class BankAccountDetails {
	private String AccountName ;
	private String AccountNumber ;
	private String BankName ;
	private String BankBranch;
	private double Money ;
	
	
	public BankAccountDetails(String name, String number, String bankName,
				String branch) {
		AccountName = name ;
		AccountNumber = number ;
		BankName = bankName ;
		BankBranch = branch ;
		Money = 0 ;
	}
	
	public String getAccountName() { return AccountName; }
	public String getAccountNumber() { return AccountNumber; }
	public String getBankName() { return BankName; }
	public String getBankBranch() { return BankBranch; }
	public double getMoney() { return Money; }
	
	public void setMoney(double money) { Money = money ; }
}
