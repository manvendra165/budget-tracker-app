package in.bta.statement.models;

public class AccountHolder {

	
	private long ahId;
	private String fullName;
	private String mobile;
	

	private String mailId;
	private double currentBalance;
	

	public AccountHolder() {
		super();
	}
	
	public AccountHolder(long ahId, String fullName, String mobile, String mailId, double currentBalance) {
		super();
		this.ahId = ahId;
		this.fullName = fullName;
		this.mobile = mobile;
		this.mailId = mailId;
		this.currentBalance = currentBalance;
	}



	public long getAhId() {
		return ahId;
	}


	public String getFullName() {
		return fullName;
	}


	public String getMobile() {
		return mobile;
	}


	public String getMailId() {
		return mailId;
	}


	public double getCurrentBalance() {
		return currentBalance;
	}


	public void setAhId(long ahId) {
		this.ahId = ahId;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public void setMailId(String mailId) {
		this.mailId = mailId;
	}


	public void setCurrentBalance(double currentBalance) {
		this.currentBalance = currentBalance;
	}
	
	
	
	
}
