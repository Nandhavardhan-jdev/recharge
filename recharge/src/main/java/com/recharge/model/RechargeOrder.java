package com.recharge.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "RECHARGE_ORDER")
public class RechargeOrder {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="AMOUNT")
	private BigDecimal amount;
	
	@Column(name="CONTACT_NO")
	private String contactNo;

	
	@Column(name="TRANSACTION_ID")
	private String transactionId;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	@JsonManagedReference
	private RechargeUser rechargeUser;

	@ManyToOne
	@JoinColumn(name = "RECHARGE_PLAN_ID")
	@JsonManagedReference
	private RechargePlan rechargePlan;
	

	public RechargeOrder() {
		super();
	
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public RechargeUser getRechargeUser() {
		return rechargeUser;
	}

	public void setRechargeUser(RechargeUser rechargeUser) {
		this.rechargeUser = rechargeUser;
	}

	public RechargePlan getRechargePlan() {
		return rechargePlan;
	}

	public void setRechargePlan(RechargePlan rechargePlan) {
		this.rechargePlan = rechargePlan;
	}
	
	
}

