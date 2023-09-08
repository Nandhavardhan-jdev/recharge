package com.recharge.dto;

import java.math.BigDecimal;


public class RechargePlanUserDto {

	private long id;
	private BigDecimal rechargeAmount;
	private String description;
	private String validity;
	private long configRechargeCategory ;
	private long rechargeVendor;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public BigDecimal getRechargeAmount() {
		return rechargeAmount;
	}
	public void setRechargeAmount(BigDecimal rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getValidity() {
		return validity;
	}
	public void setValidity(String validity) {
		this.validity = validity;
	}
	public long getConfigRechargeCategory() {
		return configRechargeCategory;
	}
	public void setConfigRechargeCategory(long configRechargeCategory) {
		this.configRechargeCategory = configRechargeCategory;
	}
	public long getRechargeVendor() {
		return rechargeVendor;
	}
	public void setRechargeVendor(long rechargeVendor) {
		this.rechargeVendor = rechargeVendor;
	}
	
}
