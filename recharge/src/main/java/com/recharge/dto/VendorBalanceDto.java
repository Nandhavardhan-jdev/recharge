package com.recharge.dto;

import java.math.BigDecimal;

public class VendorBalanceDto {

	private long vendorId;
	private String firstName;
	private String telecomOperatorName;
	private String emailId;
	private BigDecimal balance;
	
	public long getVendorId() {
		return vendorId;
	}
	public void setVendorId(long vendorId) {
		this.vendorId = vendorId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getTelecomOperatorName() {
		return telecomOperatorName;
	}
	public void setTelecomOperatorName(String telecomOperatorName) {
		this.telecomOperatorName = telecomOperatorName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	
}
