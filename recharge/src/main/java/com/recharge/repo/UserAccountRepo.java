package com.recharge.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recharge.model.UserRechargeAccount;

public interface UserAccountRepo extends JpaRepository<UserRechargeAccount, Long>{

	UserRechargeAccount findByDefaultNumberAndRechargeUserUserId(boolean defaultNumber, long userId);

	UserRechargeAccount findById(long id);

	List<UserRechargeAccount> findByRechargeUserUserId(long id);

	List<UserRechargeAccount> findByFavNumberAndRechargeUserUserId(boolean b, long id);
	
}
