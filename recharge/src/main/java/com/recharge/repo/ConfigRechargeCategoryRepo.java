package com.recharge.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.recharge.model.ConfigRechargeCategory;

@Repository
public interface ConfigRechargeCategoryRepo extends JpaRepository<ConfigRechargeCategory, Long>{

	ConfigRechargeCategory findByCatId(long id);

	ConfigRechargeCategory findByCategoryName(String categoryName);

	List<ConfigRechargeCategory> findAll();
}
