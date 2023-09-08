package com.recharge.model;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;



@Entity
@Table(name = "RECHARGE_PLAN")
public class RechargePlan {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "RECHARGE_AMOUNT")
	private BigDecimal rechargeAmount;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "VALIDITY")
	private String validity;
	
	@ManyToOne
	@JoinColumn(name="CAT_ID")
	private ConfigRechargeCategory configRechargeCategory ;
	
	@ManyToOne
	@JoinColumn(name="VENDOR_ID")
	private RechargeVendor rechargeVendor;
	
	@Column(name = "CREATED_ON", updatable=false)
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;
	
	@Column(name="LAST_MODIFIED_ON")
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdatedOn;

	public RechargePlan() {
		super();
		
	}

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

	public ConfigRechargeCategory getConfigRechargeCategory() {
		return configRechargeCategory;
	}

	public void setConfigRechargeCategory(ConfigRechargeCategory configRechargeCategory) {
		this.configRechargeCategory = configRechargeCategory;
	}

	public RechargeVendor getRechargeVendor() {
		return rechargeVendor;
	}

	public void setRechargeVendor(RechargeVendor rechargeVendor) {
		this.rechargeVendor = rechargeVendor;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getLastUpdatedOn() {
		return lastUpdatedOn;
	}

	public void setLastUpdatedOn(Date lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}

	

}

