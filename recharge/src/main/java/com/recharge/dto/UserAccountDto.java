package com.recharge.dto;

public class UserAccountDto {

	private long id;
	private String telecomOperatorName;
	private String mobileNo;
	private String nickName;
	private boolean favNumber;
	private boolean defaultNumber;
	private long userId;
	private int status;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTelecomOperatorName() {
		return telecomOperatorName;
	}
	public void setTelecomOperatorName(String telecomOperatorName) {
		this.telecomOperatorName = telecomOperatorName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public boolean isFavNumber() {
		return favNumber;
	}
	public void setFavNumber(boolean favNumber) {
		this.favNumber = favNumber;
	}
	public boolean isDefaultNumber() {
		return defaultNumber;
	}
	public void setDefaultNumber(boolean defaultNumber) {
		this.defaultNumber = defaultNumber;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
