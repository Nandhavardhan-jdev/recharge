package com.recharge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recharge.dto.ConfigRechargeCategoryDto;
import com.recharge.dto.OrderDto;
import com.recharge.dto.RechargePlanDto;
import com.recharge.dto.VendorBalanceDto;
import com.recharge.dto.VendorDto;
import com.recharge.model.RechargeOrder;
import com.recharge.repo.RechargePlanRepo;
import com.recharge.service.VendorService;

@RestController
@RequestMapping("/vendor")
public class VendorController {

	@Autowired
	VendorService vendorService;
		
	
//	RechargeVendor
	
	@PostMapping("/add/vendor")
	public VendorDto addVendor(@RequestBody VendorDto vendorDto) {
		return vendorService.addVendor(vendorDto);
	}
	
	@GetMapping("/get/vendor/{id}")
	public VendorDto getVendor(@PathVariable long id) {
		return vendorService.getVendor(id);
	}
	
	@PutMapping("/update/vendor")
	public VendorDto updateVendor(@RequestBody VendorDto vendorDto) {
		return vendorService.updateVendor(vendorDto);
	}
	
	@GetMapping("/get/vendor/balance/{id}")
	public VendorBalanceDto getVendorBalance(@PathVariable long id) {
		return vendorService.getVendorBalance(id);
	}
	
		
//	ConfigRechargeCategory
	
	@PostMapping("/add/cat")
	public ConfigRechargeCategoryDto addCat(@RequestBody ConfigRechargeCategoryDto configRechargeCategoryDto) {
		return vendorService.addCat(configRechargeCategoryDto);
	}
	
	@GetMapping("/get/cat/{id}")
	public ConfigRechargeCategoryDto get(@PathVariable long id) {
		return vendorService.get(id);
	}
	
	@GetMapping("/getall/cat")
	public List<ConfigRechargeCategoryDto> getAll() {
		return vendorService.getAll();
	}
	
//	RechargePlan
	
	@PostMapping("/add/plan")
	public RechargePlanDto add(@RequestBody RechargePlanDto rechargePlanDto) {
		return vendorService.add(rechargePlanDto);
	}
	
	@GetMapping("/get/plan/{id}")
	public RechargePlanDto getPlanId(@PathVariable long id) {
		return vendorService.getPlanId(id);
	}
	
	@GetMapping("/get/vendorplan/{id}")
	public List<RechargePlanDto> getVendorPlan(@PathVariable long id){
		return vendorService.getVendorPlan(id);
	}
	
	@PutMapping("/update/plan")
	public RechargePlanDto updatePlan(@RequestBody RechargePlanDto rechargePlanDto) {
		return vendorService.updatePlan(rechargePlanDto);
	}
	
	
//	RechargeOrder
	
	@GetMapping("/vendor/order/history/{id}")
	public List<OrderDto> getVendorOrderHistory(@PathVariable long id){
		return vendorService.getVendorOrderHistory(id);
	}

	@GetMapping("/cat/order/history/{id}")
	public List<OrderDto> getCatOrderHistory(@PathVariable long id){
		return vendorService.getCatOrderHistory(id);
	}
	
	@GetMapping("/plan/order/history/{id}")
	public List<OrderDto> getPlanOrderHistory(@PathVariable long id){
		return vendorService.getPlanOrderHistory(id);
	}

	@GetMapping("/order/history/{id}")
	public OrderDto getOrderHistory(@PathVariable long id) {
		return vendorService.getOrderHistory(id);
	}
	
}
