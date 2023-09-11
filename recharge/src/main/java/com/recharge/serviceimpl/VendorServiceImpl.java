package com.recharge.serviceimpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recharge.dto.ConfigRechargeCategoryDto;
import com.recharge.dto.OrderDto;
import com.recharge.dto.RechargePlanDto;
import com.recharge.dto.VendorBalanceDto;
import com.recharge.dto.VendorDto;
import com.recharge.model.ConfigRechargeCategory;
import com.recharge.model.RechargeOrder;
import com.recharge.model.RechargePlan;
import com.recharge.model.RechargeVendor;
import com.recharge.repo.ConfigRechargeCategoryRepo;
import com.recharge.repo.OrderRepo;
import com.recharge.repo.RechargePlanRepo;
import com.recharge.repo.VendorRepo;
import com.recharge.service.VendorService;

@Service
public class VendorServiceImpl  implements VendorService{

	
	@Autowired
	VendorServiceImpl vendorServiceImpl;
	
//	RechargeVendor
	
	@Autowired
	VendorRepo vendorRepo;
	
	@Override
	public VendorDto addVendor(VendorDto vendorDto) {
		RechargeVendor rechargeVendor = vendorRepo.findByEmailId(vendorDto.getEmailId());
		if (rechargeVendor==null) {
			RechargeVendor rechargeVendor2 = vendorRepo.findByTelecomOperatorName(vendorDto.getTelecomOperatorName());
			if (rechargeVendor2 == null){
				RechargeVendor rechargeVendor3 = new RechargeVendor();
				rechargeVendor3.setFirstName(vendorDto.getFirstName());
				rechargeVendor3.setLastName(vendorDto.getLastName());
				rechargeVendor3.setGender(vendorDto.getGender());
				rechargeVendor3.setAddressLine1(vendorDto.getAddressLine1());
				rechargeVendor3.setAddressLine2(vendorDto.getAddressLine2());
				rechargeVendor3.setDob(vendorDto.getDob());
				rechargeVendor3.setTelecomOperatorName(vendorDto.getTelecomOperatorName());
				rechargeVendor3.setEmailId(vendorDto.getEmailId());
				rechargeVendor3.setBalance(new BigDecimal(0));
				RechargeVendor rechargeVendor4 = vendorRepo.save(rechargeVendor3);
				VendorDto vendorDto2 = vendorDto(rechargeVendor4);
				return vendorDto2;
			}else {
				throw new RuntimeException("telecom operator name already exists");
			}
		}else {
			throw new RuntimeException("EmailId already exists");
		}
	}
	
	public VendorDto getVendor(long id) {
		RechargeVendor rechargeVendor = vendorRepo.findByVendorId(id);
		if(rechargeVendor!=null) {
			return vendorDto(rechargeVendor);
		}else {
			throw new RuntimeException("VendorId doesn't exists");
		}
	}
	
	public VendorDto updateVendor(VendorDto vendorDto) {
		RechargeVendor rechargeVendor = vendorRepo.findByVendorId(vendorDto.getVendorId());
		if(rechargeVendor!=null) {
			if(rechargeVendor.getEmailId().equals(vendorDto.getEmailId())) {
				if (vendorDto.getTelecomOperatorName().equalsIgnoreCase(rechargeVendor.getTelecomOperatorName())) {
					return updateVendorDao(vendorDto, rechargeVendor);
				}else {
					RechargeVendor rechargeVendor2 = vendorRepo.findByTelecomOperatorName(vendorDto.getTelecomOperatorName());
					if (rechargeVendor2 == null ) {
						return updateVendorDao(vendorDto, rechargeVendor);
					}else {
						throw new RuntimeException("telecom operator name alreay exists");
					}
				}
			}
			//emailId is not equal
			else {
				RechargeVendor rechargeVendor2 = vendorRepo.findByEmailId(vendorDto.getEmailId());
				if(rechargeVendor2==null) {
					if (vendorDto.getTelecomOperatorName().equals(rechargeVendor.getTelecomOperatorName())) {
						return updateVendorDao(vendorDto, rechargeVendor);
					}else {
						RechargeVendor rechargeVendor3 = vendorRepo.findByTelecomOperatorName(vendorDto.getTelecomOperatorName());
						if (rechargeVendor3 == null) {
							return updateVendorDao(vendorDto, rechargeVendor);
						}else {
							throw new RuntimeException("telecom operator name alreay exists");
						}
					}
				}else {
					throw new RuntimeException("emailId already exists");
				}
			}
		}else {
			throw new RuntimeException("vendorId doesn't exists");
		}
	}
	
	public VendorDto updateVendorDao(VendorDto vendorDto, RechargeVendor rechargeVendor) {
		rechargeVendor.setFirstName(vendorDto.getFirstName());
		rechargeVendor.setLastName(vendorDto.getLastName());
		rechargeVendor.setGender(vendorDto.getGender());
		rechargeVendor.setAddressLine1(vendorDto.getAddressLine1());
		rechargeVendor.setAddressLine2(vendorDto.getAddressLine2());
		rechargeVendor.setDob(vendorDto.getDob());
		rechargeVendor.setTelecomOperatorName(vendorDto.getTelecomOperatorName());
		rechargeVendor.setEmailId(vendorDto.getEmailId());
		RechargeVendor rechargeVendor2 = vendorRepo.save(rechargeVendor);
		return vendorDto(rechargeVendor2);
	}

	public VendorBalanceDto getVendorBalance(long id) {
		RechargeVendor rechargeVendor = vendorRepo.findByVendorId(id);
		if(rechargeVendor!=null) {
			return vendorBalanceDto(rechargeVendor);
		}else {
			throw new RuntimeException("vendor Id doesn't exists");
		}
	}
	
	private VendorBalanceDto vendorBalanceDto(RechargeVendor rechargeVendor) {
		VendorBalanceDto vendorBalanceDto = new VendorBalanceDto();
		vendorBalanceDto.setVendorId(rechargeVendor.getVendorId());
		vendorBalanceDto.setTelecomOperatorName(rechargeVendor.getTelecomOperatorName());
		vendorBalanceDto.setBalance(rechargeVendor.getBalance());
		vendorBalanceDto.setFirstName(rechargeVendor.getFirstName());
		vendorBalanceDto.setEmailId(rechargeVendor.getEmailId());
		return vendorBalanceDto;
	}

	private VendorDto vendorDto(RechargeVendor rechargeVendor3) {
		VendorDto vendorDto=new VendorDto();
		vendorDto.setVendorId(rechargeVendor3.getVendorId());
		vendorDto.setFirstName(rechargeVendor3.getFirstName());
		vendorDto.setLastName(rechargeVendor3.getLastName());
		vendorDto.setGender(rechargeVendor3.getGender());
		vendorDto.setAddressLine1(rechargeVendor3.getAddressLine1());
		vendorDto.setAddressLine2(rechargeVendor3.getAddressLine2());
		vendorDto.setBalance(rechargeVendor3.getBalance());
		vendorDto.setDob(rechargeVendor3.getDob());
		vendorDto.setTelecomOperatorName(rechargeVendor3.getTelecomOperatorName());
		vendorDto.setEmailId(rechargeVendor3.getEmailId());
		vendorDto.setCreatedOn(rechargeVendor3.getCreatedOn());
		vendorDto.setLastUpdatedOn(rechargeVendor3.getLastUpdatedOn());
		return vendorDto;
	}
	
	
//	ConfigRechargeCategory
	
	@Autowired
	ConfigRechargeCategoryRepo configRechargeCategoryRepo;
	
	public ConfigRechargeCategoryDto addCat(ConfigRechargeCategoryDto configRechargeCategoryDto) {
		ConfigRechargeCategory configRechargeCategory = configRechargeCategoryRepo.findByCategoryName(configRechargeCategoryDto.getCategoryName());
		if(configRechargeCategory==null) {
			ConfigRechargeCategory configRechargeCategory2 = new ConfigRechargeCategory();
			configRechargeCategory2.setCategoryName(configRechargeCategoryDto.getCategoryName());
			ConfigRechargeCategory configRechargeCategory3 = configRechargeCategoryRepo.save(configRechargeCategory2);
			return categoryDto(configRechargeCategory3);
		}else {
			throw new RuntimeException("category name already exists");
		}
	}
	
	public ConfigRechargeCategoryDto get(long id) {
		ConfigRechargeCategory configRechargeCategory = configRechargeCategoryRepo.findByCatId(id);
		if(configRechargeCategory!=null) {
			return categoryDto(configRechargeCategory);
		}else {
			throw new RuntimeException("CatId doesn't exists");
		}
	}
	
	public List<ConfigRechargeCategoryDto> getAll() {
		List<ConfigRechargeCategory> configRechargeCategories = configRechargeCategoryRepo.findAll();
		if(configRechargeCategories!=null) {
			List<ConfigRechargeCategoryDto> configRechargeCategoryDtos = new ArrayList<>();
			configRechargeCategories.forEach((eachCategory)->{
				ConfigRechargeCategoryDto configRechargeCategoryDto = categoryDto(eachCategory);
				configRechargeCategoryDtos.add(configRechargeCategoryDto);
				});
			return configRechargeCategoryDtos;
		}else {
			throw new RuntimeException("No data");
		}
	}
	
	private ConfigRechargeCategoryDto categoryDto(ConfigRechargeCategory configRechargeCategory) {
		ConfigRechargeCategoryDto configRechargeCategoryDto = new ConfigRechargeCategoryDto();
		configRechargeCategoryDto.setCatId(configRechargeCategory.getCatId());
		configRechargeCategoryDto.setCategoryName(configRechargeCategory.getCategoryName());
		configRechargeCategoryDto.setCreatedOn(configRechargeCategory.getCreatedOn());
		configRechargeCategoryDto.setLastUpdatedOn(configRechargeCategory.getLastUpdatedOn());
		return configRechargeCategoryDto;
	}
	
	
//	RechargePlan
	
	@Autowired
	RechargePlanRepo rechargePlanRepo;
	
	public RechargePlanDto add(RechargePlanDto rechargePlanDto) {
		RechargeVendor rechargeVendor = vendorRepo.findByVendorId(rechargePlanDto.getVendorId());
		if(rechargeVendor!=null) {
			ConfigRechargeCategory configRechargeCategory = configRechargeCategoryRepo.findByCatId(rechargePlanDto.getCatId());
			if(configRechargeCategory!=null) {
				RechargePlan rechargePlan = rechargePlanRepo.findByRechargeAmountAndRechargeVendorVendorId(rechargePlanDto.getRechargeAmount(),rechargePlanDto.getVendorId());
				if(rechargePlan==null) {
					RechargePlan rechargePlan2 = new RechargePlan();
					rechargePlan2.setRechargeAmount(rechargePlanDto.getRechargeAmount());
					rechargePlan2.setDescription(rechargePlanDto.getDescription());
					rechargePlan2.setValidity(rechargePlanDto.getValidity());
					rechargePlan2.setRechargeVendor(rechargeVendor);
					rechargePlan2.setConfigRechargeCategory(configRechargeCategory);
					RechargePlan rechargePlan3 = rechargePlanRepo.save(rechargePlan2);
					return planDto(rechargePlan3);
				}else {
					throw new RuntimeException("recharge amount already exists");
				}
			}else {
				throw new RuntimeException("category Id doesn't exists");
			}
		}else {
			throw new RuntimeException("vendorId doesn't exists");
		}
	}

	public RechargePlanDto getPlanId(long id) {
		RechargePlan rechargePlan = rechargePlanRepo.findById(id);
		if (rechargePlan!=null) {
			return planDto(rechargePlan);
		}else {
			throw new RuntimeException("plan Id doesn't exists");
		}
	}
	
	public List<RechargePlanDto> getVendorPlan(long id){
		RechargeVendor rechargeVendor = vendorRepo.findByVendorId(id);
		if (rechargeVendor!=null) {
			List<RechargePlan> rechargePlan = rechargePlanRepo.findByRechargeVendorVendorId(id);
			if (!rechargePlan.isEmpty()) {
//				List<RechargePlanDto> rechargePlanDtos = new ArrayList<>(); 
//				rechargePlan.forEach((eachRechargePlan)->{
//					RechargePlanDto rechargePlanDto = planDto(eachRechargePlan);
//					rechargePlanDtos.add(rechargePlanDto);
//				});
//				return rechargePlanDtos;
				List<RechargePlanDto> rechargePlanDtos = rechargePlan.stream()
						.map(new VendorServiceImpl()::planDto)
						.toList();
 				return rechargePlanDtos;
				
			}else {
				throw new RuntimeException("no data");
			}
		}else {
			throw new RuntimeException("vendor Id doesn't exists");
		}
	}
	
	public RechargePlanDto updatePlan(RechargePlanDto rechargePlanDto) {
		RechargePlan rechargePlan = rechargePlanRepo.findById(rechargePlanDto.getId());
		if(rechargePlan!=null) {
			int comparasion = rechargePlan.getRechargeAmount().compareTo(rechargePlanDto.getRechargeAmount());
			if(comparasion==0) {
				return updatePlanDao(rechargePlanDto, rechargePlan);
			}
//			rechargeamount are not equal
			else {
				RechargePlan rechargePlan2 = rechargePlanRepo.findByRechargeAmountAndRechargeVendorVendorId(rechargePlanDto.getRechargeAmount(), rechargePlanDto.getVendorId());
				if(rechargePlan2==null) {
					return updatePlanDao(rechargePlanDto, rechargePlan);
				}else {
					throw new RuntimeException("recharge amount already exists");
				}
			}
		}else {
			throw new RuntimeException("plan id doesn't exists");
		}
	}
	
	public RechargePlanDto updatePlanDao(RechargePlanDto rechargePlanDto, RechargePlan rechargePlan) {
		rechargePlan.setRechargeAmount(rechargePlanDto.getRechargeAmount());
		rechargePlan.setValidity(rechargePlanDto.getValidity());
		rechargePlan.setDescription(rechargePlanDto.getDescription());
		RechargePlan rechargePlan2 = rechargePlanRepo.save(rechargePlan);
		return planDto(rechargePlan2);
	}
	
	private RechargePlanDto planDto(RechargePlan rechargePlan3) {
		RechargePlanDto rechargePlanDto = new RechargePlanDto();
		rechargePlanDto.setId(rechargePlan3.getId());
		rechargePlanDto.setRechargeAmount(rechargePlan3.getRechargeAmount());
		rechargePlanDto.setValidity(rechargePlan3.getValidity());
		rechargePlanDto.setDescription(rechargePlan3.getDescription());
		rechargePlanDto.setVendorId(rechargePlan3.getRechargeVendor().getVendorId());
		rechargePlanDto.setCatId(rechargePlan3.getConfigRechargeCategory().getCatId());
		rechargePlanDto.setCreatedOn(rechargePlan3.getCreatedOn());
		rechargePlanDto.setLastUpdatedOn(rechargePlan3.getLastUpdatedOn());
		return rechargePlanDto;
	}
	
	
//	RechargeOrder
	
	@Autowired
	OrderRepo orderRepo;
	
	public List<OrderDto> getVendorOrderHistory(long id){
		RechargeVendor rechargeVendor = vendorRepo.findByVendorId(id);
		if (rechargeVendor != null) {
			List<RechargeOrder> rechargeOrders = orderRepo.findByRechargePlanRechargeVendorVendorId(id);
			if (!rechargeOrders.isEmpty()) {
				List<OrderDto> orderDtos = rechargeOrders.stream().map(vendorServiceImpl::orderDto).toList();
				return orderDtos;
			}else {
				throw new RuntimeException("no data exists");
			}
		}else {
			throw new RuntimeException("vendor Id "+id+" doesn't exists");
		}
	}

	public List<OrderDto> getCatOrderHistory(long id){
		ConfigRechargeCategory configRechargeCategory = configRechargeCategoryRepo.findByCatId(id);
		if (configRechargeCategory != null) {
			List<RechargeOrder> rechargeOrders = orderRepo.findByRechargePlanConfigRechargeCategoryCatId(id);
			if (!rechargeOrders.isEmpty()) {
				List<OrderDto> orderDtos = rechargeOrders.stream().map(vendorServiceImpl::orderDto).toList();
				return orderDtos;
			}else {
				throw new RuntimeException("no data exists");
			}
		}else {
			throw new RuntimeException("cat Id "+id+" doesn't exists");
		}
	}

	public List<OrderDto> getPlanOrderHistory(long id){
		RechargePlan rechargePlan = rechargePlanRepo.findById(id);
		if (rechargePlan != null) {
			List<RechargeOrder> rechargeOrders = orderRepo.findByRechargePlanId(id);
			if (!rechargeOrders.isEmpty()) {
				List<OrderDto> orderDtos = rechargeOrders.stream().map(vendorServiceImpl::orderDto).toList();
				return orderDtos;
			}else {
				throw new RuntimeException("no data exists");
			}
		}else {
			throw new RuntimeException("plan Id "+id+" doesn't exists");
		}
	}
	
	public OrderDto getOrderHistory(long id) {
		RechargeOrder rechargeOrder = orderRepo.findById(id);
		if (rechargeOrder != null) {
			return orderDto(rechargeOrder);
		}else {
			throw new RuntimeException("order Id "+id+" doesn't exists");
		}
	}
	
	public OrderDto orderDto(RechargeOrder rechargeOrder) {
		OrderDto orderDto = new OrderDto();
		orderDto.setId(rechargeOrder.getId());
		orderDto.setAmount(rechargeOrder.getAmount());
		orderDto.setContactNo(rechargeOrder.getContactNo());
		orderDto.setDescription(rechargeOrder.getDescription());
		orderDto.setTransactionId(rechargeOrder.getTransactionId());
		orderDto.setUserId(rechargeOrder.getRechargeUser().getUserId());
		orderDto.setPlanId(rechargeOrder.getRechargePlan().getId());
		return orderDto;
	}
	
}
