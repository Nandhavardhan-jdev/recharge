package com.recharge.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recharge.model.ConfigRechargeCategory;
import com.recharge.model.RechargeOrder;

public interface OrderRepo extends JpaRepository<RechargeOrder, Long>{

	List<RechargeOrder> findByRechargeUserUserId(long id);

	List<RechargeOrder> findByRechargePlanRechargeVendorVendorId(long id);

	List<RechargeOrder> findByRechargePlanConfigRechargeCategoryCatId(long id);

	List<RechargeOrder> findByRechargePlanId(long id);
	
	RechargeOrder findById(long id);

}
