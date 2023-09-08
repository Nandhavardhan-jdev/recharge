package com.recharge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;

import com.recharge.dto.OrderDto;
import com.recharge.dto.RechargePlanDto;
import com.recharge.dto.RechargePlanUserDto;
import com.recharge.dto.UserAccountDto;
import com.recharge.dto.UserDto;
import com.recharge.model.ConfigRechargeCategory;
import com.recharge.model.RechargeOrder;
import com.recharge.model.UserRechargeAccount;
import com.recharge.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
//	RechargeUser

	@PostMapping("/add/user")
	public UserDto addUser(@RequestBody UserDto userDto) {
		return userService.addUser(userDto);
	}
	
	@GetMapping("/get/user/{id}")
	public UserDto getUser(@PathVariable long id) {
		return userService.getUser(id);
	}
	
	@PutMapping("/update/user")
	public UserDto updateUser(@RequestBody UserDto userDto) {
		return userService.updateUser(userDto);
	}
	
	
//	RechargeVendor
	
	@GetMapping("/getall/telenames")
	public Object getTeleNames() {
		return userService.getTeleNames();
	}
	
	
//	ConfigRechargeCategory
	
	@GetMapping("/getall/categories")
	public List<String> getCategories() {
		return userService.getCategories();
	}
	
	
//	RechargePlan
	
	@GetMapping("/getall/plans/{vId}/{cId}")
	public List<RechargePlanUserDto> getAllPlansByVendorAndCategory(@PathVariable long vId,@PathVariable long cId){
		return userService.getAllPlansByVendorAndCategory(vId,cId);
	}
	
	@GetMapping("/getall/plans/{vId}")
	public List<RechargePlanUserDto> getAllPlansByVendor(@PathVariable long vId){
		return userService.getAllPlansByVendor(vId);
	}

	@GetMapping("/get/plans/{id}")
	public RechargePlanUserDto getPlan(@PathVariable long id) {
		return userService.getPlan(id);
	}
	
	
//	RechargeOrder
	
	@PostMapping("/order")
	public OrderDto order(@RequestBody OrderDto orderDto) {
		return userService.order(orderDto);
	}
	
	@GetMapping("/user/order/history/{id}")
	public List<OrderDto> getUserOrderHistory(@PathVariable long id) {
		return userService.getUserOrderHistory(id);
	}
	
	@GetMapping("/order/history/{id}")
	public OrderDto getOrderHistory(@PathVariable long id) {
		return userService.getOrderHistory(id);
	}

	
//	UserRechargeAccount
	
	@PostMapping("/account")
	public UserAccountDto addAccount(@RequestBody UserAccountDto userAccountDto) {
		return userService.addAccount(userAccountDto);
	}
	
	@PutMapping("/update/account")
	public UserAccountDto updateAccount(@RequestBody UserAccountDto userAccountDto) {
		return userService.updateAccount(userAccountDto);
	}

	@DeleteMapping("/delete/account/{id}")
	public Object deleteAccount(@PathVariable long id) {
		return userService.deleteAccount(id);
	}
	
	@GetMapping("/saved/accounts/{id}")
	public List<UserAccountDto> savedAccounts(@PathVariable long id){
		return userService.savedAccounts(id);
	}

	@GetMapping("/fav/accounts/{id}")
	public List<UserAccountDto> favAccounts(@PathVariable long id){
		return userService.favAccounts(id);
	}
	
	@GetMapping("/default/account/{id}")
	public UserAccountDto defaultAccount(@PathVariable long id) {
		return userService.defaultAccount(id);
	}
	
}
