package com.recharge.serviceimpl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recharge.dto.OrderDto;
import com.recharge.dto.RechargePlanUserDto;
import com.recharge.dto.UserAccountDto;
import com.recharge.dto.UserDto;
import com.recharge.model.ConfigRechargeCategory;
import com.recharge.model.RechargeOrder;
import com.recharge.model.RechargePlan;
import com.recharge.model.RechargeUser;
import com.recharge.model.RechargeVendor;
import com.recharge.model.UserRechargeAccount;
import com.recharge.repo.ConfigRechargeCategoryRepo;
import com.recharge.repo.OrderRepo;
import com.recharge.repo.RechargePlanRepo;
import com.recharge.repo.UserAccountRepo;
import com.recharge.repo.UserRepo;
import com.recharge.repo.VendorRepo;
import com.recharge.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserServiceImpl userServiceImpl;
	
	Map<Object, Object> map = new HashMap<>();
	
//	RechargeUser
	
	@Autowired
	UserRepo userRepo;
	
	public UserDto addUser(UserDto userDto) {
		RechargeUser rechargeUser = userRepo.findByEmailId(userDto.getEmailId());
		if(rechargeUser==null) {
			String aadhaarNumber = userDto.getAdhaarNumber().replaceAll(" ", "");
			if (aadhaarNumber.matches("\\d+")) {
				if(aadhaarNumber.length() == 12) {
					RechargeUser rechargeUser2 = userRepo.findByAdhaarNumber(userDto.getAdhaarNumber());
					if (rechargeUser2 == null) {
						if(userDto.getMobileNumber().matches("\\d+")) {

							if (userDto.getMobileNumber().length() == 10) {
								RechargeUser rechargeUser3 = new RechargeUser();
								rechargeUser3.setFirstName(userDto.getFirstName());
								rechargeUser3.setLastName(userDto.getLastName());
								rechargeUser3.setEmailId(userDto.getEmailId());
								rechargeUser3.setAdhaarNumber(userDto.getAdhaarNumber());
								rechargeUser3.setMobileNumber(userDto.getMobileNumber());
								rechargeUser3.setDob(userDto.getDob());
								rechargeUser3.setGender(userDto.getGender());
								rechargeUser3.setBalance(new BigDecimal(100));
								RechargeUser rechargeUser4 = userRepo.save(rechargeUser3);
								return userDto(rechargeUser4);
							}else {
//								System.out.println("mobile number should be in 10 digits");
								throw new RuntimeException("mobile number should be 10 digits");
							}
						}else {
							throw new RuntimeException("mobile number should be in digits");
						}
					}else {
						throw new RuntimeException("AdhaarNumber already exists");
//						System.out.println("AdhaarNumber already exists");
					}
				}else {
					throw new RuntimeException("aadhaar number should be 12 digits");
//					System.out.println("aadhaar number should be in 12 digits");
				}
			}else {
				throw new RuntimeException("aadhaar number should be in digits ");
//				System.out.println("aadhaar number should be only in digits ");
			}
			
		}else {
			throw new RuntimeException("emailId already exists");
//			System.out.println("emailId already exists");
		}
	}

	public UserDto getUser(long id) {
		RechargeUser rechargeUser = userRepo.findByUserId(id);
		if (rechargeUser != null) {
			return userDto(rechargeUser);
		}else {
			throw new RuntimeException("userId doesn't exists");
		}
	}
	
	public UserDto updateUser(UserDto userDto) {
		RechargeUser rechargeUser = userRepo.findByUserId(userDto.getUserId());
		if (rechargeUser != null) {
			String updatingAadhaarNumber = userDto.getAdhaarNumber().replaceAll(" ", "");
			String existingAadhaarNumber = rechargeUser.getAdhaarNumber().replaceAll(" ", "");
			if(userDto.getEmailId().equals(rechargeUser.getEmailId())) {
				if (updatingAadhaarNumber.equals(existingAadhaarNumber)) {
					if (userDto.getMobileNumber().matches("\\d+")) {
						if (userDto.getMobileNumber().length() == 10) {
							rechargeUser.setMobileNumber(userDto.getMobileNumber());
							rechargeUser.setFirstName(userDto.getFirstName());
							rechargeUser.setLastName(userDto.getLastName());
							rechargeUser.setGender(userDto.getGender());
							rechargeUser.setDob(userDto.getDob());
							RechargeUser rechargeUser2 = userRepo.save(rechargeUser);
							return userDto(rechargeUser2);
						}else {
							throw new RuntimeException("mobile number should be 10 digits");
						}
					}else {
						throw new RuntimeException("mobile number should be in digits");
					}
				}
				//emailIds are equal and aadhaar numbers are not equal
				else {
					RechargeUser rechargeUser2 = userRepo.findByAdhaarNumber(userDto.getAdhaarNumber());
					if (rechargeUser2 == null) {
						if (updatingAadhaarNumber.matches("\\d+")) {
							if (updatingAadhaarNumber.length() == 12) {
								if (userDto.getMobileNumber().matches("\\d+")) {
									if (userDto.getMobileNumber().length() == 10) {
										rechargeUser.setAdhaarNumber(userDto.getAdhaarNumber());
										rechargeUser.setMobileNumber(userDto.getMobileNumber());
										rechargeUser.setFirstName(userDto.getFirstName());
										rechargeUser.setLastName(userDto.getLastName());
										rechargeUser.setGender(userDto.getGender());
										rechargeUser.setDob(userDto.getDob());
										RechargeUser rechargeUser3 = userRepo.save(rechargeUser);
										return userDto(rechargeUser3);
									}else {
										throw new RuntimeException("mobile number should be 10 digits");
									}
								}else {
									throw new RuntimeException("mobile number should be in digits");
								}
							}else {
								throw new RuntimeException("aadhaar number should be 12 digits");
							}
						}else {
							throw new RuntimeException("aadhaar number should be in digits");
						}
					}else {
						throw new RuntimeException("Aadhaar number already exists");
					}
				}
			}
			//emailIds are not equal
			else {
				RechargeUser rechargeUser2 = userRepo.findByEmailId(userDto.getEmailId());
				if (rechargeUser2 == null) {
					if (updatingAadhaarNumber.equals(existingAadhaarNumber)) {
						if (userDto.getMobileNumber().matches("\\d+")) {
							if (userDto.getMobileNumber().length() == 10) {
								rechargeUser.setEmailId(userDto.getEmailId());
								rechargeUser.setMobileNumber(userDto.getMobileNumber());
								rechargeUser.setFirstName(userDto.getFirstName());
								rechargeUser.setLastName(userDto.getLastName());
								rechargeUser.setGender(userDto.getGender());
								rechargeUser.setDob(userDto.getDob());
								RechargeUser rechargeUser3 = userRepo.save(rechargeUser);
								return userDto(rechargeUser3);
							}else {
								throw new RuntimeException("mobile number should be 10 digits");
							}
						}else {
							throw new RuntimeException("mobile number should be in digits");
							}
					}else {
						RechargeUser rechargeUser3 = userRepo.findByAdhaarNumber(userDto.getAdhaarNumber());
						if (rechargeUser3 == null) {
							if (updatingAadhaarNumber.matches("\\d+")) {
								if (updatingAadhaarNumber.length() == 12) {
									if (userDto.getMobileNumber().matches("\\d+")) {
										if (userDto.getMobileNumber().length() == 10) {
											rechargeUser.setEmailId(userDto.getEmailId());
											rechargeUser.setAdhaarNumber(userDto.getAdhaarNumber());
											rechargeUser.setMobileNumber(userDto.getMobileNumber());
											rechargeUser.setDob(userDto.getDob());
											rechargeUser.setFirstName(userDto.getFirstName());
											rechargeUser.setLastName(userDto.getLastName());
											rechargeUser.setGender(userDto.getGender());
											RechargeUser rechargeUser4 = userRepo.save(rechargeUser);
											return userDto(rechargeUser4);
										}else {
											throw new RuntimeException("mobile numer should be 10 digits");
										}
									}else {
										throw new RuntimeException("mobile number should be in digits");
									}
								}else {
									throw new RuntimeException("aadhaar number should be 12 digits");
								}
							}else {
								throw new RuntimeException("aadhar number should be in digits");
							}
						}else {
							throw new RuntimeException("aadhaar number already exists");
						}
					}
				}else {
					throw new RuntimeException("emailId already exists");
				}
			}
		}else {
			throw new RuntimeException("UserId doesn't exists");
		}
	}
	
	private UserDto userDto(RechargeUser rechargeUser4) {
		UserDto userDto = new UserDto();
		userDto.setUserId(rechargeUser4.getUserId());
		userDto.setFirstName(rechargeUser4.getFirstName());
		userDto.setLastName(rechargeUser4.getLastName());
		userDto.setEmailId(rechargeUser4.getEmailId());
		userDto.setMobileNumber(rechargeUser4.getMobileNumber());
		userDto.setAdhaarNumber(rechargeUser4.getAdhaarNumber());
		userDto.setBalance(rechargeUser4.getBalance());
		userDto.setDob(rechargeUser4.getDob());
		userDto.setGender(rechargeUser4.getGender());
		userDto.setAdhaarNumber(rechargeUser4.getAdhaarNumber());
		userDto.setCreatedOn(rechargeUser4.getCreatedOn());
		userDto.setLastUpdatedOn(rechargeUser4.getLastUpdatedOn());
		return userDto;
	}
	
	
//	RechargeVendor
	
	@Autowired
	VendorRepo vendorRepo;
	
	public Object getTeleNames() {
		List<RechargeVendor> rechargeVendors = vendorRepo.findAll();
		if (rechargeVendors != null) {
			List<String> rechargeVendors2 = rechargeVendors.stream()
					.map(RechargeVendor::getTelecomOperatorName)
					.toList();
			return rechargeVendors2;
		}else {
			throw new RuntimeException("no data");
		}
	}
	
	
//	ConfigRechargeCategory
	
	@Autowired
	ConfigRechargeCategoryRepo configRechargeCategoryRepo;
	
	public List<String> getCategories() {
		List<ConfigRechargeCategory> configRechargeCategories = configRechargeCategoryRepo.findAll();
		if (configRechargeCategories != null) {
			List<String> configRechargeCategories2 = configRechargeCategories.stream()
					.map(ConfigRechargeCategory::getCategoryName)
					.toList();
			return configRechargeCategories2;
		}else {
			throw new RuntimeException("no data");
		}
	}
	
	
 //	RechargePlan
	
	@Autowired
	RechargePlanRepo rechargePlanRepo;
	
	public List<RechargePlanUserDto> getAllPlansByVendorAndCategory(long vId, long cId){
		RechargeVendor rechargeVendor = vendorRepo.findByVendorId(vId);
		if (rechargeVendor != null) {
			ConfigRechargeCategory configRechargeCategory = configRechargeCategoryRepo.findByCatId(cId);
			if (configRechargeCategory != null) {
				List<RechargePlan> rechargePlans = rechargePlanRepo.findByRechargeVendorVendorIdAndConfigRechargeCategoryCatId(vId,cId);
				if (!rechargePlans.isEmpty()) {
					List<RechargePlanUserDto> rechargePlanUserDtos = rechargePlans.stream()
							.map(userServiceImpl::userPlanDto).toList();
					return rechargePlanUserDtos;
				}else {
					throw new RuntimeException("no data exists");
				}
			}else {
				throw new RuntimeException("category id "+cId+" doesn't exists");
			}
		}else {
			throw new RuntimeException("vendor id "+vId+" doesn't exists");
		}
	}
	
	public List<RechargePlanUserDto> getAllPlansByVendor(long vId){
		RechargeVendor rechargeVendor = vendorRepo.findByVendorId(vId);
		if (rechargeVendor != null) {
			List<RechargePlan> rechargePlans = rechargePlanRepo.findByRechargeVendorVendorId(vId);
			if (!rechargePlans.isEmpty()) {
				List<RechargePlanUserDto> rechargePlanUserDtos = rechargePlans.stream()
						.map(userServiceImpl::userPlanDto)
						.toList();
				return rechargePlanUserDtos;
			}else {
				throw new RuntimeException("no data exists");
			}
		}else {
			throw new RuntimeException("vendorId "+vId+" doesn't exists");
		}
	}
	
	public RechargePlanUserDto getPlan(long id) {
		RechargePlan rechargePlan = rechargePlanRepo.findById(id);
		if (rechargePlan != null) {
			return userPlanDto(rechargePlan);
		}else {
			throw new RuntimeException("plan Id "+id+" doesn't exists");
		}
	}
	
	public RechargePlanUserDto userPlanDto(RechargePlan rechargePlan) {
		RechargePlanUserDto rechargePlanUserDto = new RechargePlanUserDto();
		rechargePlanUserDto.setId(rechargePlan.getId());
		rechargePlanUserDto.setRechargeAmount(rechargePlan.getRechargeAmount());
		rechargePlanUserDto.setValidity(rechargePlan.getValidity());
		rechargePlanUserDto.setDescription(rechargePlan.getDescription());
		rechargePlanUserDto.setRechargeVendor(rechargePlan.getRechargeVendor().getVendorId());
		rechargePlanUserDto.setConfigRechargeCategory(rechargePlan.getConfigRechargeCategory().getCatId());
		return rechargePlanUserDto;
	}

	
//	RechargeOrder
	
	@Autowired
	OrderRepo orderRepo;
	
	public OrderDto order(OrderDto orderDto) {
		RechargeUser rechargeUser = userRepo.findByUserId(orderDto.getUserId());
		if (rechargeUser != null) {
			RechargePlan rechargePlan = rechargePlanRepo.findById(orderDto.getPlanId());
			if (rechargePlan != null) {
				BigDecimal rechargeAmount = rechargePlan.getRechargeAmount();
				BigDecimal userBalance = rechargeUser.getBalance();
				int comparsion = rechargeAmount.compareTo(userBalance);
				if (comparsion <= 0) {
					rechargeUser.setBalance(userBalance.subtract(rechargeAmount));
					rechargePlan.getRechargeVendor().setBalance(rechargePlan.getRechargeVendor().getBalance().add(rechargeAmount));
					RechargeOrder rechargeOrder = new RechargeOrder();
					rechargeOrder.setAmount(rechargeAmount);
					rechargeOrder.setContactNo(orderDto.getContactNo());
					rechargeOrder.setDescription("Recharged Successfully for "+orderDto.getContactNo());
					rechargeOrder.setTransactionId(UUID.randomUUID().toString().replaceAll("-", ""));
					rechargeOrder.setRechargeUser(rechargeUser);
					rechargeOrder.setRechargePlan(rechargePlan);
					RechargeOrder rechargeOrder2 = orderRepo.save(rechargeOrder);
					return orderDto(rechargeOrder2);
				}else {
					throw new RuntimeException("Insufficient Funds\nuser balance is : "+userBalance+"rs");
				}
			}else {
				throw new RuntimeException("planId "+orderDto.getPlanId()+" doesn't exists");
			}
		}else {
			throw new RuntimeException("userID "+orderDto.getUserId()+" doesn't exists");
		}
	}
	
	public List<OrderDto> getUserOrderHistory(long id){
		RechargeUser rechargeUser = userRepo.findByUserId(id);
		if (rechargeUser != null) {
			List<RechargeOrder> rechargeOrders = orderRepo.findByRechargeUserUserId(id);
			if (!rechargeOrders.isEmpty()) {
				List<OrderDto> orderDtos = rechargeOrders.stream().map(userServiceImpl::orderDto).toList();
				return orderDtos;
			}else {
				throw new RuntimeException("no data exists");
			}
		}else {
			throw new RuntimeException("user Id "+id+" doesn't exists");
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
	
	public OrderDto orderDto(RechargeOrder rechargeOrder2) {
		OrderDto orderDto = new OrderDto();
		orderDto.setId(rechargeOrder2.getId());
		orderDto.setAmount(rechargeOrder2.getAmount());
		orderDto.setContactNo(rechargeOrder2.getContactNo());
		orderDto.setDescription(rechargeOrder2.getDescription());
		orderDto.setTransactionId(rechargeOrder2.getTransactionId());
		orderDto.setUserId(rechargeOrder2.getRechargeUser().getUserId());
		orderDto.setPlanId(rechargeOrder2.getRechargePlan().getId());
		return orderDto;
	}

	
	
//	UserRechargeAccount
	
	@Autowired
	UserAccountRepo userAccountRepo;
	
	@Override
	public UserAccountDto addAccount(UserAccountDto userAccountDto) {
		RechargeUser rechargeUser = userRepo.findByUserId(userAccountDto.getUserId());
		if (rechargeUser != null) {
			if (userAccountDto.isDefaultNumber() == false) {
				UserRechargeAccount userRechargeAccount = new UserRechargeAccount();
				userRechargeAccount.setDefaultNumber(userAccountDto.isDefaultNumber());
				userRechargeAccount.setFavNumber(userAccountDto.isFavNumber());
				userRechargeAccount.setMobileNo(userAccountDto.getMobileNo());
				userRechargeAccount.setNickName(userAccountDto.getNickName());
				userRechargeAccount.setTelecomOperatorName(userAccountDto.getTelecomOperatorName());
				userRechargeAccount.setRechargeUser(rechargeUser);
				userRechargeAccount.setStatus(1);
				UserRechargeAccount userRechargeAccount2 = userAccountRepo.save(userRechargeAccount);
				return accountDto(userRechargeAccount2);
			}else {
				UserRechargeAccount userRechargeAccount = userAccountRepo.findByDefaultNumberAndRechargeUserUserId(userAccountDto.isDefaultNumber(), userAccountDto.getUserId());
				if (userRechargeAccount == null) {
					UserRechargeAccount userRechargeAccount2 = new UserRechargeAccount();
					userRechargeAccount2.setDefaultNumber(userAccountDto.isDefaultNumber());
					userRechargeAccount2.setFavNumber(userAccountDto.isFavNumber());
					userRechargeAccount2.setMobileNo(userAccountDto.getMobileNo());
					userRechargeAccount2.setNickName(userAccountDto.getNickName());
					userRechargeAccount2.setTelecomOperatorName(userAccountDto.getTelecomOperatorName());
					userRechargeAccount2.setRechargeUser(rechargeUser);
					userRechargeAccount2.setStatus(1);
					UserRechargeAccount userRechargeAccount3 = userAccountRepo.save(userRechargeAccount2);
					return accountDto(userRechargeAccount3);
				}else {
					userRechargeAccount.setDefaultNumber(false);
					UserRechargeAccount userRechargeAccount2 = new UserRechargeAccount();
					userRechargeAccount2.setDefaultNumber(userAccountDto.isDefaultNumber());
					userRechargeAccount2.setFavNumber(userAccountDto.isFavNumber());
					userRechargeAccount2.setMobileNo(userAccountDto.getMobileNo());
					userRechargeAccount2.setNickName(userAccountDto.getNickName());
					userRechargeAccount2.setTelecomOperatorName(userAccountDto.getTelecomOperatorName());
					userRechargeAccount2.setRechargeUser(rechargeUser);
					userRechargeAccount2.setStatus(1);
					UserRechargeAccount userRechargeAccount3 = userAccountRepo.save(userRechargeAccount2);
					return accountDto(userRechargeAccount3);
				}
			}
		}else {
			throw new RuntimeException("user ID "+userAccountDto.getUserId()+" doesn't exists");
		}
	}

	@Override
	public UserAccountDto updateAccount(UserAccountDto userAccountDto) {
		UserRechargeAccount userRechargeAccount = userAccountRepo.findById(userAccountDto.getId());
		if (userRechargeAccount != null) {
			if (userAccountDto.isDefaultNumber() == false) {
				userRechargeAccount.setDefaultNumber(userAccountDto.isDefaultNumber());
				userRechargeAccount.setFavNumber(userAccountDto.isFavNumber());
				userRechargeAccount.setMobileNo(userAccountDto.getMobileNo());
				userRechargeAccount.setNickName(userAccountDto.getNickName());
				userRechargeAccount.setTelecomOperatorName(userAccountDto.getTelecomOperatorName());
//				UserRechargeAccount userRechargeAccount2 = userAccountRepo.save(userRechargeAccount);
				return accountDto(userRechargeAccount);
			}else {
				UserRechargeAccount userRechargeAccount2 = userAccountRepo.findByDefaultNumberAndRechargeUserUserId(userAccountDto.isDefaultNumber(),userAccountDto.getUserId());
				if (userRechargeAccount2 == null) {
					userRechargeAccount.setDefaultNumber(userAccountDto.isDefaultNumber());
					userRechargeAccount.setFavNumber(userAccountDto.isFavNumber());
					userRechargeAccount.setMobileNo(userAccountDto.getMobileNo());
					userRechargeAccount.setNickName(userAccountDto.getNickName());
					userRechargeAccount.setTelecomOperatorName(userAccountDto.getTelecomOperatorName());
					UserRechargeAccount userRechargeAccount3 = userAccountRepo.save(userRechargeAccount);
					return accountDto(userRechargeAccount3);
				}else {
					userRechargeAccount2.setDefaultNumber(false);
					userRechargeAccount.setDefaultNumber(userAccountDto.isDefaultNumber());
					userRechargeAccount.setFavNumber(userAccountDto.isFavNumber());
					userRechargeAccount.setMobileNo(userAccountDto.getMobileNo());
					userRechargeAccount.setNickName(userAccountDto.getNickName());
					userRechargeAccount.setTelecomOperatorName(userAccountDto.getTelecomOperatorName());
					UserRechargeAccount userRechargeAccount3 = userAccountRepo.save(userRechargeAccount);
					return accountDto(userRechargeAccount3);
				}
			}
		}else {
			throw new RuntimeException("account Id "+userAccountDto.getId()+" doesn't exists");
		}
	}

	@Override
	public Object deleteAccount(long id) {
		UserRechargeAccount userRechargeAccount = userAccountRepo.findById(id);
		if (userRechargeAccount != null) {
			if (userRechargeAccount.getStatus() != 0) {
				userRechargeAccount.setStatus(0);
				userAccountRepo.save(userRechargeAccount);
				map.clear();
				map.put("status", "account deleted");
				return map;
			}else {
				map.clear();
				map.put("status", "account already deleted");
				return map;
			}
		}else {
			throw new RuntimeException("account Id "+id+" doesn't exists");
		}
	}
	
	@Override
	public List<UserAccountDto> savedAccounts(long id) {
		RechargeUser rechargeUser = userRepo.findByUserId(id);
		if (rechargeUser != null) {
			List<UserRechargeAccount> userRechargeAccounts = userAccountRepo.findByRechargeUserUserId(id);
			if (!userRechargeAccounts.isEmpty()) {
				List<UserAccountDto> userAccountDtos = userRechargeAccounts.stream()
						.filter(status-> status.getStatus() == 1)
						.map(userServiceImpl::accountDto)
						.toList();
				if (!userAccountDtos.isEmpty()) {
					return userAccountDtos;
				}else {
					throw new RuntimeException("no data exists");
				}
			}else {
				throw new RuntimeException("no data exists");
			}
		}else {
			throw new RuntimeException("user Id "+id+" doesn't exists");
		}
	}
	
	@Override
	public List<UserAccountDto> favAccounts(long id) {
		RechargeUser rechargeUser = userRepo.findByUserId(id);
		if (rechargeUser != null) {
			List<UserRechargeAccount> userRechargeAccounts = userAccountRepo.findByFavNumberAndRechargeUserUserId(true, id);
			if (!userRechargeAccounts.isEmpty()) {
				List<UserAccountDto> userAccountDtos = userRechargeAccounts.stream()
						.filter(each -> each.getStatus() == 1)
						.map(userServiceImpl::accountDto)
						.toList();
				if (!userAccountDtos.isEmpty()) {
					return userAccountDtos;
				}else {
					throw new RuntimeException("no data exists");
				}
			}else {
				throw new RuntimeException("no data exists");
			}
		}else{
			throw new RuntimeException("user Id "+id+" doesn't exists");
		}
	}
	
	@Override
	public UserAccountDto defaultAccount(long id) {
		RechargeUser rechargeUser = userRepo.findByUserId(id);
		if (rechargeUser != null) {
			UserRechargeAccount userRechargeAccount = userAccountRepo.findByDefaultNumberAndRechargeUserUserId(true, id);
			if (userRechargeAccount != null) {
				if (userRechargeAccount.getStatus() == 1) {
					return accountDto(userRechargeAccount);
				}else {
					throw new RuntimeException("no data exists");
				}
			}else {
				throw new RuntimeException("no data exists");
			}
		}else {
			throw new RuntimeException("user Id "+id+" doesn't exists");
		}
	}

	
	public UserAccountDto accountDto(UserRechargeAccount userRechargeAccount) {
		UserAccountDto userAccountDto = new UserAccountDto();
		userAccountDto.setId(userRechargeAccount.getId());
		userAccountDto.setDefaultNumber(userRechargeAccount.isDefaultNumber());
		userAccountDto.setFavNumber(userRechargeAccount.isFavNumber());
		userAccountDto.setMobileNo(userRechargeAccount.getMobileNo());
		userAccountDto.setNickName(userRechargeAccount.getNickName());
		userAccountDto.setTelecomOperatorName(userRechargeAccount.getTelecomOperatorName());
		userAccountDto.setUserId(userRechargeAccount.getRechargeUser().getUserId());
		userAccountDto.setStatus(userRechargeAccount.getStatus());
		return userAccountDto;
	}

	


	
	
}
