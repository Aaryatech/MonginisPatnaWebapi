package com.ats.webapi.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.SettingNew;

public interface SettingNewRepository extends JpaRepository<SettingNew, Integer>{
	SettingNew findBySettingId(int id);

	SettingNew findBySettingKey(String key);

	@Transactional
	@Modifying
	@Query(" UPDATE SettingNew SET setting_value1=:value WHERE setting_id=:settingId")
	int udateValueById(@Param("settingId")int settingId,@Param("value") String value);
	
	@Transactional
	@Modifying
	@Query(" UPDATE SettingNew SET setting_value1=:value WHERE setting_key=:settingKey")
	int udateValueByKey(@Param("settingKey") String settingKey,@Param("value") String value);

}
