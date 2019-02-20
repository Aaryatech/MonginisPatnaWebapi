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

	@Query(value="Select * from m_cat_sub where del_status=0 order by cat_id,sub_cat_id",nativeQuery=true)
	public List<SubCategory> findAllSubCategories();
/*
	@Query(value="Select * from m_cat_sub where del_status=:delStatus and cat_id=:catId order by cat_id,sub_cat_id",nativeQuery=true)
	public List<SubCategory> findByCatIdAndDelStatusOrderByCatIdAndSubCatId(@Param("catId")int catId,@Param("delStatus") int delStatus);
*/
	public List<SubCategory> findByCatIdAndDelStatus(int catId, int i);
	
	//sachin FEB 18-2019
	@Query(value=" Select " + 
			"        m_cat_sub.* " + 
			"    from " + 
			"        m_cat_sub " + 
			"    where " + 
			"         m_cat_sub.sub_cat_id NOT IN(SELECT m_tax_hsn.sub_cat_id from m_tax_hsn WHERE m_tax_hsn.del_status=0) " + 
			"        AND m_cat_sub.cat_id=:catId " + 
			"        AND m_cat_sub.del_status=0 ",nativeQuery=true)
	public List<SubCategory> getAllSubCategoriesForTaxHsn(@Param("catId") int catId);

}
