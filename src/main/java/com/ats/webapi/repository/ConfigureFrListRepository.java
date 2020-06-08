package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ats.webapi.model.ConfigureFrBean;

public interface ConfigureFrListRepository extends JpaRepository<ConfigureFrBean, Integer> {

	@Query(value = " select m_fr_configure.*,m_franchisee.fr_name,m_fr_menu_show.menu_title,m_category.cat_name "
			+ " from m_fr_configure,m_franchisee,m_fr_menu_show,m_category "
			+ " where m_franchisee.fr_id=m_fr_configure.fr_id and m_fr_menu_show.menu_id=m_fr_configure.menu_id "
			+ " AND m_category.cat_id=m_fr_configure.cat_id  order by fr_name ASC,setting_id ASC", nativeQuery = true)

	List<ConfigureFrBean> findConfiFrList();

	@Modifying
	@Transactional
	@Query(value = " DELETE FROM  ConfigureFrBean WHERE settingId IN (:settingIdList)")

	void deleteFrConfBySettingIdList(@Param("settingIdList") List<Integer> settingIdList);

	@Query(value = " SELECT\n" + 
			"    m_fr_configure.*,\n" + 
			"    m_franchisee.fr_name,\n" + 
			"    m_fr_menu_show.menu_title,\n" + 
			"    m_category.cat_name\n" + 
			"FROM\n" + 
			"    m_fr_configure,\n" + 
			"    m_franchisee,\n" + 
			"    m_fr_menu_show,\n" + 
			"    m_category\n" + 
			"WHERE\n" + 
			"    m_franchisee.fr_id = m_fr_configure.fr_id AND m_fr_menu_show.menu_id = m_fr_configure.menu_id AND m_category.cat_id = m_fr_configure.cat_id AND m_fr_configure.fr_id =:frId AND m_fr_configure.cat_id =:catId AND m_fr_configure.is_del = 0", nativeQuery = true)
	List<ConfigureFrBean> findByFrIdAndCatId(@Param("frId") int frId, @Param("catId") int catId);

}
