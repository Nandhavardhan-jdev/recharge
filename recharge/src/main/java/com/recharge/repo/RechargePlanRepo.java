package com.recharge.repo;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.recharge.model.RechargePlan;

@Repository
public interface RechargePlanRepo extends JpaRepository<RechargePlan, Long>{

	RechargePlan findByRechargeAmountAndRechargeVendorVendorId(BigDecimal rechargeAmount, long vendorId);

	RechargePlan findById(long id);

	List<RechargePlan> findByRechargeVendorVendorId(long id);

	List<RechargePlan> findByRechargeVendorVendorIdAndConfigRechargeCategoryCatId(long vId, long cId);





}
