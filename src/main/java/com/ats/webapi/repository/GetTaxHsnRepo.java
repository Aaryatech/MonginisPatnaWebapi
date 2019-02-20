package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.GetTaxHsn;

public interface GetTaxHsnRepo extends JpaRepository<GetTaxHsn, Integer>{
	
	
	@Query(value="Select m_tax_hsn.*, m_cat_sub.sub_cat_name,m_category.cat_name from m_cat_sub,m_category,m_tax_hsn "
			+ " where m_cat_sub.del_status=0 AND m_cat_sub.sub_cat_id=m_tax_hsn.sub_cat_id and m_cat_sub.cat_id=m_category.cat_id and m_tax_hsn.del_status=0 ORDER BY m_tax_hsn.tax_hsn_id  DESC ",nativeQuery=true)
	List<GetTaxHsn> getAllGetTaxHsn();
	
	
	@Query(value="Select m_tax_hsn.*, m_cat_sub.sub_cat_name,m_category.cat_name from m_cat_sub,m_category,m_tax_hsn "
			+ " where m_cat_sub.del_status=0 AND m_cat_sub.sub_cat_id=m_tax_hsn.sub_cat_id and m_cat_sub.cat_id=m_category.cat_id and m_tax_hsn.del_status=0 AND  m_tax_hsn.tax_hsn_id=:taxHsnId",nativeQuery=true)
	GetTaxHsn getGetTaxHsnByTxHsnId(@Param("taxHsnId") int taxHsnId);
	
	
	
	@Query(value="Select m_tax_hsn.*, m_cat_sub.sub_cat_name,m_category.cat_name from m_cat_sub,m_category,m_tax_hsn "
			+ " where m_cat_sub.del_status=0 AND m_cat_sub.sub_cat_id=m_tax_hsn.sub_cat_id and m_cat_sub.cat_id=m_category.cat_id and m_tax_hsn.del_status=0 AND  m_tax_hsn.sub_cat_id=:subCatId",nativeQuery=true)
	GetTaxHsn getGetTaxHsnBySubCatd(@Param("subCatId") int subCatId);
	

}
