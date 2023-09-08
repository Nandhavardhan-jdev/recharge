package com.recharge.service;

import java.util.List;

import com.recharge.dto.ConfigRechargeCategoryDto;
import com.recharge.dto.OrderDto;
import com.recharge.dto.RechargePlanDto;
import com.recharge.dto.VendorBalanceDto;
import com.recharge.dto.VendorDto;

public interface VendorService {

//	RechargeVendor
	
	VendorDto addVendor(VendorDto vendorDto);

	VendorDto getVendor(long id);

	VendorDto updateVendor(VendorDto vendorDto);

	VendorBalanceDto getVendorBalance(long id);
	
	
//	ConfigRechargeCategor

	ConfigRechargeCategoryDto addCat(ConfigRechargeCategoryDto configRechargeCategoryDto);

	ConfigRechargeCategoryDto get(long id);

	List<ConfigRechargeCategoryDto> getAll();
	
	
//	RechargePlan

	RechargePlanDto add(RechargePlanDto rechargePlanDto);

	RechargePlanDto getPlanId(long id);

	List<RechargePlanDto> getVendorPlan(long id);

	RechargePlanDto updatePlan(RechargePlanDto rechargePlanDto);

	List<OrderDto> getVendorOrderHistory(long id);

	List<OrderDto> getCatOrderHistory(long id);

	List<OrderDto> getPlanOrderHistory(long id);

	OrderDto getOrderHistory(long id);
	
	

}
