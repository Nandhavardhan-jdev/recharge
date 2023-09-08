package com.recharge.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.recharge.model.RechargeVendor;

@Repository
public interface VendorRepo extends JpaRepository<RechargeVendor, Long> {

	RechargeVendor findByEmailId(String emailId);
	RechargeVendor findByVendorId(long vendorId);
	RechargeVendor findByTelecomOperatorName(String telecomOperatorName);
	List<RechargeVendor> findAll();
}
