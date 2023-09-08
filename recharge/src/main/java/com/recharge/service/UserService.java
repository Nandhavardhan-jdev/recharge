package com.recharge.service;

import java.util.List;

import com.recharge.dto.OrderDto;
import com.recharge.dto.RechargePlanUserDto;
import com.recharge.dto.UserAccountDto;
import com.recharge.dto.UserDto;
import com.recharge.model.RechargePlan;
import com.recharge.model.UserRechargeAccount;

public interface UserService {

	
//	Rechargeuser
	
	UserDto addUser(UserDto userDto);

	UserDto getUser(long id);

	UserDto updateUser(UserDto userDto);

	
//	RechargeVendor
	
	Object getTeleNames();

	List<String> getCategories();

	
//	RechargePlan
	
	List<RechargePlanUserDto> getAllPlansByVendorAndCategory(long vId, long cId);

	List<RechargePlanUserDto> getAllPlansByVendor(long vId);

	RechargePlanUserDto getPlan(long id);

	OrderDto order(OrderDto orderDto);

	List<OrderDto> getUserOrderHistory(long id);

	OrderDto getOrderHistory(long id);

	UserAccountDto addAccount(UserAccountDto userAccountDto);

	UserAccountDto updateAccount(UserAccountDto userAccountDto);

	Object deleteAccount(long id);

	List<UserAccountDto> savedAccounts(long id);

	List<UserAccountDto> favAccounts(long id);

	UserAccountDto defaultAccount(long id); 

}
