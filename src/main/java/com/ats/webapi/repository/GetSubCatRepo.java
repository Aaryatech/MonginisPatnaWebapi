package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.GetSubCat;

public interface GetSubCatRepo extends JpaRepository<GetSubCat, Integer> {

	@Query(value="SELECT\n" + 
			"        subcat.sub_cat_id,\n" + 
			"        subcat.sub_cat_name,\n" + 
			"        subcat.cat_id \n" + 
			"    FROM\n" + 
			"        m_fr_menu_show fr,\n" + 
			"        m_cat_sub subcat,\n" + 
			"        m_category cat \n" + 
			"    WHERE\n" + 
			"        subcat.cat_id=cat.cat_id \n" + 
			"        AND   fr.cat_id=cat.cat_id \n" + 
			"        AND  subcat.del_status=0\n" + 
			"        AND   fr.menu_id=:menuId",nativeQuery=true)
		List<GetSubCat> getSubCatByMenuId(@Param("menuId") int menuId);
	
	@Query(value="SELECT sub_cat_id, sub_cat_name, cat_id FROM `m_cat_sub` WHERE sub_cat_id=:subCatId",nativeQuery=true)
	public GetSubCat findBySubCatId(@Param("subCatId") int subCatId);
	
}
