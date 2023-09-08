package com.recharge.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.recharge.model.RechargeUser;

@Repository
public interface UserRepo extends JpaRepository<RechargeUser, Long>{

	RechargeUser findByEmailId(String emailId);

	RechargeUser findByAdhaarNumber(String adhaarNumber);

	RechargeUser findByUserId(long id);

}
