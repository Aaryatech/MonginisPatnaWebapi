package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ats.webapi.model.ConfigureFranchisee;

@Repository
public interface ConfigureFrRepository extends JpaRepository<ConfigureFranchisee, Integer> {
	public ConfigureFranchisee save(ConfigureFranchisee configureFranchisee);

	public ConfigureFranchisee findBySettingId(int setting_id);

	@Query(value = "select menu_id from m_fr_configure WHERE fr_id=:frId ", nativeQuery = true)
	public List<Integer> findConfiguredMenuId(@Param("frId") int frId);

	// 23 march
	@Transactional
	@Modifying
	@Query(" UPDATE ConfigureFranchisee  SET fromTime=:fromTime, toTime=:toTime,date=:date,day=:day,settingType=:settingType "
			+ " WHERE frId IN(:frIdList) AND menuId =:menuId")
	int updateFrConfSelFr(@Param("frIdList") List<Integer> frIdList, @Param("menuId") int menuId,
			@Param("fromTime") String fromTime, @Param("toTime") String toTime,@Param("date") String date,@Param("day") String day,@Param("settingType") int settingType);

	@Transactional
	@Modifying
	@Query(" UPDATE ConfigureFranchisee  SET fromTime=:fromTime, toTime=:toTime,date=:date,day=:day,settingType=:settingType WHERE  menuId =:menuId")
	int updateFrConfAllFr(@Param("menuId") int menuId, @Param("fromTime") String fromTime,
			@Param("toTime") String toTime,@Param("date") String date,@Param("day") String day,@Param("settingType") int settingType);

	
	 ConfigureFranchisee findByFrIdAndMenuIdAndDelStatus(int frId, int menuId, int i);

}
