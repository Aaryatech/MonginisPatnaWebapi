package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.MCategory;
import com.ats.webapi.model.SubCategory;


public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer> {
	public SubCategory save(SubCategory subCategory);

	public List<SubCategory> findByDelStatus(int i);

	@Query(value="Select * from m_cat_sub where cat_id in(:catId) and del_status=0",nativeQuery=true)
	public List<SubCategory> findByCatIdInAndDelStatus(@Param("catId")List<String> catId);

	@Query(value="Select * from m_cat_sub where del_status=0",nativeQuery=true)
	public List<SubCategory> findAllSubCategories();

	//public List<SubCategory> findSubCategoryByCatId(int catId);
}
