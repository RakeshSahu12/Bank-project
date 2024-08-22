package BankTransaction;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BankBean implements Serializable {
	private String AccNo;
	private String AccName;
	private Float AccBalance;

	public BankBean() {

	}

	public String getAccNo() {
		return AccNo;
	}

	public void setAccNo(String accNo) {
		AccNo = accNo;
	}

	public String getAccName() {
		return AccName;
	}

	public void setAccName(String accName) {
		AccName = accName;
	}

	public Float getAccBalance() {
		return AccBalance;
	}

	public void setAccBalance(Float accBalance) {
		AccBalance = accBalance;
	}

}
